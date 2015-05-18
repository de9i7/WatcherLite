/*
 * @(#)IVTResultParser.java
 * 
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage.  Release of this
 * file requires prior approval from Luxoft Management.
 *
 * Copyright (c)2007 The Boeing Company All rights reserved.  
 */
package com.sandbox.xml_parsing;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * IRMResultParser.
 *
 * @author ILaryukhin
 */
public final class IRMResultParser extends DefaultHandler {
    private static final String SCHEMA_FILE =
            "com/sandbox/xml_parsing/ivt-result.xsd";
    private static final String IVT_RESULT_CODE_ELEMENT = "result-code";
    private static final String IVT_PATH_ELEMENT = "path";
    private static final String IVT_MESSAGE_ELEMENT = "message";
    private final IRMResult result;

    private StringBuilder characters = new StringBuilder();

    private IRMResultParser(IRMResult result) {
        super();
        this.result = result;
    }

    /**
     * Parses IVT result xml file.
     *
     * @param inputSource result file input source
     * @return parsed result data
     */
    public static IRMResult parse(InputSource inputSource){
        IRMResult result = new IRMResult();

        IRMResultParser handler = new IRMResultParser(result);

        // parse DeepServer result

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        parserFactory.setXIncludeAware(false);
        InputStream schemaAsStream = null;

        try {
            parserFactory.setFeature(
                    XMLConstants.FEATURE_SECURE_PROCESSING, false);
            SchemaFactory factory = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setErrorHandler(handler);

            schemaAsStream =
                    handler.getClass().getClassLoader().getResourceAsStream(SCHEMA_FILE);

            Schema schema = factory.newSchema(new StreamSource(schemaAsStream));

            parserFactory.setSchema(schema);
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(inputSource, handler);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException");
        } catch (SAXNotSupportedException e) {
            System.out.println("SAXNotSupportedException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (SAXNotRecognizedException e) {
            System.out.println("SAXNotRecognizedException");
        } catch (SAXException e) {
            System.out.println("SAXException");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(schemaAsStream);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        characters.append(ch, start, length).toString().replace("\"", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String uri, String localName, String qName){
        if (IVT_RESULT_CODE_ELEMENT.equals(qName)) {
            result.setResultCode(Integer.parseInt(
                    characters.toString().trim()));
        } else if (IVT_PATH_ELEMENT.equals(qName)) {
            result.addObjFile(new File(characters.toString().trim()));
        } else if (IVT_MESSAGE_ELEMENT.equals(qName)) {
            result.setErrorMessage(characters.toString().trim().replace("\"", ""));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startElement(
        String uri,
        String localName,
        String qName,
        Attributes attributes
    )  {
        characters.setLength(0);
    }

}
