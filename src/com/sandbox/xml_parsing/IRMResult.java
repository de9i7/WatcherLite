/*
 * @(#)IVTResult.java
 * 
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage.  Release of this
 * file requires prior approval from Luxoft Management.
 *
 * Copyright (c)2007 The Boeing Company All rights reserved.  
 */
package com.sandbox.xml_parsing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * IVTResult.
 *
 * @author ILaryukhin
 */
public class IRMResult {

    private int resultCode;
    private List<File> fileName;
    private String errorMessage;

    public static final int RESULT_IVT_ERROR_ONE = 1;
    public static final int RESULT_IVT_ERROR_TWO = 2;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_NO_MODELS = -1;

    public IRMResult() {
        this.fileName = new ArrayList<File>(1);
    }

    /**
     * Sets result code.
     *
     * @param resultCode result code.
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Returns error message.
     *
     * @return error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns U3D result file full path.
     *
     * @return result file full path
     */
    public List<File> getObjFilesList() {
        return fileName;
    }

    /**
     * Add transformation result file path.
     *
     * @param fileName transformation result file
     */
    public void addObjFile(File fileName) {
        this.fileName.add(fileName);
    }

    public int getResultCode() {
        return resultCode;
    }
}
