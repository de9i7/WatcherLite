package com.sandbox.emptyness_checker;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class performs "blank" report processing.
 * @author Kachurovskiy Denis 10/30/2013
 */
public class BlankReportChecker {

    private static final BlankReportChecker INSTANCE = new BlankReportChecker();

    /** N/A value */
    private static final String NA_VALUE = "N/A";

    /** Contains open and close tags that must be ignored
     *  while xml processing
    */
    private Set<String> m_ommitedTags;

    /**
     * Returns instance of this class
     * @return
     */
    public static BlankReportChecker getInstance() {
        return INSTANCE;
    }

    /**
     * Private constructor
     */
    private BlankReportChecker(){
    }

    /**
     * Checks that the original report is "blank" and
     * it corresponds to MAPL/MKPL REDARS category
     * @param xmlContent xml report content
     * @param skippedTags sections that must be omitted
     * @return blank report flag
     */
    public boolean isReportBlank(
        final String xmlContent,
        final String... skippedTags
    ) throws FileNotFoundException, XMLStreamException {

        if (skippedTags.length > 0) {
            m_ommitedTags = prepareOmittedTags(skippedTags);
        }

        boolean isEmpty = true;
        StringReader sr = new StringReader(xmlContent.replaceAll("\\r\\n\\t+", ""));
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inputFactory.createXMLEventReader(sr);

            TagLock lock = new TagLock();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    processBeginningTag(lock, event);

                } else if (event.isEndElement()) {
                    processClosingTag(lock, event);

                } else if (event.isCharacters()) {
                    isEmpty = isElementEmpty(lock, event);
                    if (!isEmpty) {
                        System.out.println("Not empty element: " + event.asCharacters().getData());
                        break;
                    }
                }
            }

        } finally {
            sr.close();
        }

        return isEmpty;
    }

    /**
     * If there is no locked section check element emptiness.
     * @param lock TagLock instance
     * @param event XMLEvent instance
     * @return element emptiness tag
     */
    private boolean isElementEmpty(TagLock lock, XMLEvent event) {
        boolean isEmpty = true;
        if (!lock.isLocked()) {
            String element = event.asCharacters().getData();
            if (!element.isEmpty()
                && !element.equalsIgnoreCase(NA_VALUE)
                && element.trim().length() > 0) {
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    /**
     * Checks if starting related to skipped tags and locks
     * corresponding section by current tag.
     * @param lock TagLock instance
     * @param event XML event to be processed
     * @return
     */
    private void processClosingTag(TagLock lock, XMLEvent event) {
        if (lock.isLocked()
            && event.asEndElement().getName().getLocalPart().equalsIgnoreCase(lock.getLock())
        ) {
            lock.unlock();
        }
    }

    /**
     * Receives tag name from XML event and checks it for locking
     * necessity.
     * @param lock TagLock instance
     * @param event XML event to be processed
     * @return
     */
    private void processBeginningTag(TagLock lock, XMLEvent event) {
        if (!lock.isLocked()
            && m_ommitedTags.contains(event.asStartElement().getName().getLocalPart())
        ) {
            lock.lock(event.asStartElement().getName().getLocalPart());
        }
    }

    /**
     * Iterates throw received tags list, removes <> characters
     * and saves that values into container. Each tag is unique
     * inside the result container.
     * @param tags tags list
     */
    private Set<String> prepareOmittedTags(String[] tags) {
        Set<String> result = new TreeSet<String>();
        for (String tag : tags) {
            result.add(tag.replaceAll("[\\\\<>]", ""));
        }
        return result;
    }

    /**
     * This class holds locking tag name and performs
     * operations on it
     */
    private class TagLock {

        /** Stores tag name that is locked */
        private String lock;

        public TagLock() {
            lock = null;
        }
        /**
         * Returns locking tag.
         * @return tag name
         */
        private String getLock() {
            return lock;
        }

        /**
         * Saves tag name to be locked
         * @param lockingTag
         */
        private void lock(String lockingTag) {
            lock = lockingTag;
        }

        /**
         * Unlocks current tag.
         */
        private void unlock() {
            lock = null;
        }

        /**
         * Checks if lock is occupied by some tag
         * @return lock flag
         */
        private boolean isLocked() {
            return lock != null;
        }
    }
}
