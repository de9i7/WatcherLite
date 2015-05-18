package com.sandbox.common.static_inner;

import java.util.regex.Pattern;

/**
 * This class contains method for tooling data parsing (Tooling number,
 * Tooling Revision, Tooling Sheet Id)
 *
 * @author dkachurovskiy
 */
public class ToolingDataParser {

    public static final String TOOLING_NUMBER = "toolingNumber";
    public static final String TOOLING_REVISION = "toolingRevision";
    public static final String TOOLING_SHEET_ID = "toolingSheetId";

    public static final Pattern TOOLING_PART_NUM_PATTERN =
        Pattern.compile("^(.+)_(\\d+)_([\\w&[-]&[^_]]+)$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    /**
     * Tooling Number, Tooling Revision and Tooling SheetId calculation Rules for Tooling document.
     *
     * @param dataToParse input string
     * @return map
     */
    public static ToolingRecord calculateToolingNumberRevisionSheetId(String dataToParse) {

//        Map<String, String> resultData = new HashMap<String, String>();
        ToolingRecord resultData = new ToolingRecord();

        if (dataToParse != null && !dataToParse.isEmpty()) {
//            Matcher matcher = TOOLING_PART_NUM_PATTERN.matcher(dataToParse);
//            if (matcher.matches()) {
                resultData.setToolingNumber(dataToParse + " A");
                resultData.setToolingRevision(dataToParse + " B");
                resultData.setToolingSheetId(dataToParse + " C");
//            }
        }
        return resultData;
    }

    /**
     * Model object that stores parsed tooling data
     */
    public static class ToolingRecord {
        private String toolingNumber;
        private String toolingRevision;
        private String toolingSheetId;

        public String getToolingNumber() {
            return toolingNumber;
        }

        public void setToolingNumber(String toolingNumber) {
            this.toolingNumber = toolingNumber;
        }

        public String getToolingRevision() {
            return toolingRevision;
        }

        public void setToolingRevision(String toolingRevision) {
            this.toolingRevision = toolingRevision;
        }

        public String getToolingSheetId() {
            return toolingSheetId;
        }

        public void setToolingSheetId(String toolingSheetId) {
            this.toolingSheetId = toolingSheetId;
}
}
}
