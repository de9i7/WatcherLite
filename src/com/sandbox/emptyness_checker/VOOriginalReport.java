

package com.sandbox.emptyness_checker;

import java.util.Date;

/**
 * Value object for Original Report.
 *
 * @author ILaryukhin
 * @version 1.0
 */
public class VOOriginalReport {

    private Integer originalId;

    private String fileName;
    private String filePath;
    private Long fileSize;
    private Date fileTS;
    private String supplierCode;

    private String checksum;
    private String checksumUnique;
    private String checksumXSD;

    private String companyName;
    private String reportNumber;
    private String reportVersion;
    private String reportType;
    private String header;

    private String errorCode;
    private String params;
    private String detailDescription;

    private Integer uniqueId;

    private Boolean current;

    private String enterpriseNumber;
    private String enterpriseType;
    private String reportTitle;
    private String version;
    private String partType;
    private String partClass;
    private String programCode;
    private String date;
    private String eccn;
    private String state;
    private String lineNumber;
    private String predecessorFLBPart;
    private String predecessorFLBInstanceNum;
    private String crossReference;
    private String redarsReportNumber;
    private String redarsReportVersion;
    private String redarsFileReportNumber;
    private String redarsPartType;
    private String redarsProgramCode;
    private String redarsEccn;
    private String redarsDocumentType;
    private String redarsCategory;
    private String adadsCategory;

    private String tempPath;

    private Integer archiveId;
    private Integer sessionId;
    private String xmlFileContent;

    private String errFilename;
    private Date errPrevSessDate;
    private String errSupCode;
    private String disclosureLevel;
    private String AuthoritySystem;

    /**
     * Returns original report id.
     *
     * @return original report id.
     */
    public Integer getOriginalId() {
        return originalId;
    }

    /**
     * Sets original report id.
     *
     * @param originalId original report id.
     */
    public void setOriginalId(Integer originalId) {
        this.originalId = originalId;
    }

    /**
     * Returns file name.
     *
     * @return file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns file path.
     *
     * @return file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath file path.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns file size.
     *
     * @return file size.
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize file size.
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Returns file timestamp.
     *
     * @return file timestamp.
     */
    public Date getFileTS() {
        return fileTS;
    }

    /**
     * Sets file timestamp.
     *
     * @param fileTS file timestamp.
     */
    public void setFileTS(Date fileTS) {
        this.fileTS = fileTS;
    }

    /**
     * Returns supplier code.
     *
     * @return supplier code.
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Sets supplier code.
     *
     * @param supplierCode supplier code.
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * Returns checksum.
     *
     * @return checksum.
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Sets checksum.
     *
     * @param checksum checksum.
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     * Returns unique checksum.
     *
     * @return unique checksum.
     */
    public String getChecksumUnique() {
        return checksumUnique;
    }

    /**
     * Sets unique checksum.
     *
     * @param checksumUnique unique checksum.
     */
    public void setChecksumUnique(String checksumUnique) {
        this.checksumUnique = checksumUnique;
    }

    /**
     * Returns XSD checksum.
     *
     * @return XSD checksum.
     */
    public String getChecksumXSD() {
        return checksumXSD;
    }

    /**
     * Sets XSD checksum.
     *
     * @param checksumXSD XSD checksum.
     */
    public void setChecksumXSD(String checksumXSD) {
        this.checksumXSD = checksumXSD;
    }

    /**
     * Returns Company Name.
     *
     * @return CompanyName.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets Company Name.
     *
     * @param companyName Company Name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Return report number.
     *
     * @return report number.
     */
    public String getReportNumber() {
        return reportNumber;
    }

    /**
     * Sets report number.
     *
     * @param reportNumber report number.
     */
    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * Return report version.
     *
     * @return report version.
     */
    public String getReportVersion() {
        return reportVersion;
    }

    /**
     * Sets report version.
     *
     * @param reportVersion report version.
     */
    public void setReportVersion(String reportVersion) {
        this.reportVersion = reportVersion;
    }

    /**
     * Returns report type.
     *
     * @return report type.
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * Sets report type.
     *
     * @param reportType report type.
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * Returns xml header.
     *
     * @return xml header.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets xml header.
     *
     * @param header xml header.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Returns error description params.
     *
     * @return error description params.
     */
    public String getParams() {
        return params;
    }

    /**
     * Sets error description params.
     *
     * @param params error description params.
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * Returns error id.
     *
     * @return error id.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error id.
     *
     * @param errorCode error id.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns detail description.
     *
     * @return detail description.
     */
    public String getDetailDescription() {
        return detailDescription;
    }

    /**
     * Sets detail description.
     *
     * @param detailDescription detail description.
     */
    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    /**
     * Returns unique report id.
     *
     * @return unique report id.
     */
    public Integer getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets unique report id.
     *
     * @param uniqueId unique report id.
     */
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Checks whether this unique report is current.
     *
     * @return check result.
     */
    public Boolean getCurrent() {
        return current;
    }

    /**
     * Sets this unique report current.
     *
     * @param current current flag.
     */
    public void setCurrent(Boolean current) {
        this.current = current;
    }

    /**
     * Returns enterprise number.
     *
     * @return enterprise number.
     */
    public String getEnterpriseNumber() {
        return enterpriseNumber;
    }

    /**
     * Sets enterprise number.
     *
     * @param enterpriseNumber enterprise number.
     */
    public void setEnterpriseNumber(String enterpriseNumber) {
        this.enterpriseNumber = enterpriseNumber;
    }

    /**
     * Returns enterprise type.
     *
     * @return enterprise type.
     */
    public String getEnterpriseType() {
        return enterpriseType;
    }

    /**
     * Sets enterprise type.
     *
     * @param enterpriseType enterprise type.
     */
    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    /**
     * Returns report title.
     *
     * @return report title.
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * Sets report title.
     *
     * @param reportTitle report title.
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * Returns verion.
     *
     * @return verion.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets verion.
     *
     * @param version verion.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Returns part type.
     *
     * @return part type.
     */
    public String getPartType() {
        return partType;
    }

    /**
     * Sets part type.
     *
     * @param partType part type.
     */
    public void setPartType(String partType) {
        this.partType = partType;
    }

    /**
     * Returns part class.
     *
     * @return part class.
     */
    public String getPartClass() {
        return partClass;
    }

    /**
     * Sets part class.
     *
     * @param partClass part class.
     */
    public void setPartClass(String partClass) {
        this.partClass = partClass;
    }

    /**
     * Returns program code.
     *
     * @return program code.
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets program code.
     *
     * @param programCode program code.
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Returns date.
     *
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns ECCN.
     *
     * @return ECCN.
     */
    public String getEccn() {
        return eccn;
    }

    /**
     * Sets ECCN.
     *
     * @param eccn ECCN.
     */
    public void setEccn(String eccn) {
        this.eccn = eccn;
    }

    /**
     * Returns state.
     *
     * @return state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns line number.
     *
     * @return line number.
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets line number.
     *
     * @param lineNumber line number.
     */
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Returns predecessor FLB part.
     *
     * @return predecessor FLB part.
     */
    public String getPredecessorFLBPart() {
        return predecessorFLBPart;
    }

    /**
     * Sets predecessor FLB part.
     *
     * @param predecessorFLBPart predecessor FLB part.
     */
    public void setPredecessorFLBPart(String predecessorFLBPart) {
        this.predecessorFLBPart = predecessorFLBPart;
    }

    /**
     * Returns predecessor FLB instance number.
     *
     * @return predecessor FLB instance number.
     */
    public String getPredecessorFLBInstanceNum() {
        return predecessorFLBInstanceNum;
    }

    /**
     * Sets predecessor FLB instance number.
     *
     * @param predecessorFLBInstanceNum predecessor FLB instance number.
     */
    public void setPredecessorFLBInstanceNum(String predecessorFLBInstanceNum) {
        this.predecessorFLBInstanceNum = predecessorFLBInstanceNum;
    }

    /**
     * Returns cross reference.
     *
     * @return cross reference.
     */
    public String getCrossReference() {
        return crossReference;
    }

    /**
     * Sets cross reference.
     *
     * @param crossReference cross reference.
     */
    public void setCrossReference(String crossReference) {
        this.crossReference = crossReference;
    }

    /**
     * Returns REDARS report number.
     *
     * @return REDARS report number.
     */
    public String getRedarsReportNumber() {
        return redarsReportNumber;
    }

    /**
     * Sets REDARS report number.
     *
     * @param redarsReportNumber REDARS report number.
     */
    public void setRedarsReportNumber(String redarsReportNumber) {
        this.redarsReportNumber = redarsReportNumber;
    }

    /**
     * Returns REDARS report version.
     *
     * @return REDARS report version.
     */
    public String getRedarsReportVersion() {
        return redarsReportVersion;
    }

    /**
     * Sets REDARS report version.
     *
     * @param redarsReportVersion REDARS report version.
     */
    public void setRedarsReportVersion(String redarsReportVersion) {
        this.redarsReportVersion = redarsReportVersion;
    }

    /**
     * Returns REDARS file report number.
     *
     * @return REDARS file report number.
     */
    public String getRedarsFileReportNumber() {
        return redarsFileReportNumber;
    }

    /**
     * Sets REDARS file report number.
     *
     * @param redarsFileReportNumber REDARS file report number.
     */
    public void setRedarsFileReportNumber(String redarsFileReportNumber) {
        this.redarsFileReportNumber = redarsFileReportNumber;
    }

    /**
     * Returns REDARS part type.
     *
     * @return REDARS part type.
     */
    public String getRedarsPartType() {
        return redarsPartType;
    }

    /**
     * Sets REDARS part type.
     *
     * @param redarsPartType REDARS part type.
     */
    public void setRedarsPartType(String redarsPartType) {
        this.redarsPartType = redarsPartType;
    }

    /**
     * Returns REDARS program code.
     *
     * @return REDARS program code.
     */
    public String getRedarsProgramCode() {
        return redarsProgramCode;
    }

    /**
     * Sets REDARS program code.
     *
     * @param redarsProgramCode REDARS program code.
     */
    public void setRedarsProgramCode(String redarsProgramCode) {
        this.redarsProgramCode = redarsProgramCode;
    }

    /**
     * Sets REDARS ECCN.
     *
     * @return REDARS ECCN.
     */
    public String getRedarsEccn() {
        return redarsEccn;
    }

    /**
     * Sets REDARS ECCN.
     *
     * @param redarsEccn REDARS ECCN.
     */
    public void setRedarsEccn(String redarsEccn) {
        this.redarsEccn = redarsEccn;
    }

    /**
     * Returns REDARS document type.
     *
     * @return REDARS document type.
     */
    public String getRedarsDocumentType() {
        return redarsDocumentType;
    }

    /**
     * Sets REDARS document type.
     *
     * @param redarsDocumentType REDARS document type.
     */
    public void setRedarsDocumentType(String redarsDocumentType) {
        this.redarsDocumentType = redarsDocumentType;
    }

    /**
     * Returns REDARS category.
     *
     * @return REDARS category.
     */
    public String getRedarsCategory() {
        return redarsCategory;
    }

    /**
     * Sets REDARS category.
     *
     * @param redarsCategory REDARS category.
     */
    public void setRedarsCategory(String redarsCategory) {
        this.redarsCategory = redarsCategory;
    }

    /**
     * Returns ADADS category.
     *
     * @return ADADS category.
     */
    public String getAdadsCategory() {
        return adadsCategory;
    }

    /**
     * Sets ADADS category.
     *
     * @param adadsCategory ADADS category.
     */
    public void setAdadsCategory(String adadsCategory) {
        this.adadsCategory = adadsCategory;
    }

    /**
     * Returns temp path.
     *
     * @return temp path.
     */
    public String getTempPath() {
        return tempPath;
    }

    /**
     * Sets temp path.
     *
     * @param tempPath temp path.
     */
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    /**
     * Returns archive id.
     *
     * @return archive id.
     */
    public Integer getArchiveId() {
        return archiveId;
    }

    /**
     * Sets archive id.
     *
     * @param archiveId archive id.
     */
    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    /**
     * Returns session id.
     *
     * @return session id.
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * Sets session id.
     *
     * @param sessionId session id.
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Returns xml file content in string.
     *
     * @return xml file content in string.
     */
    public String getXmlFileContent() {
        return xmlFileContent;
    }

    /**
     * Sets xml file content by string.
     *
     * @param xmlFileContent xml file content.
     */
    public void setXmlFileContent(String xmlFileContent) {
        this.xmlFileContent = xmlFileContent;
    }

    /**
     * Returns filename for error message.
     *
     * @return filename for error message.
     */
    public String getErrFilename() {
        return errFilename;
    }

    /**
     * Sets filename for error message.
     *
     * @param errFilename filename for error message.
     */
    public void setErrFilename(String errFilename) {
        this.errFilename = errFilename;
    }

    /**
     * Returns previous session date for error message.
     *
     * @return previous session date for error message.
     */
    public Date getErrPrevSessDate() {
        return errPrevSessDate;
    }

    /**
     * Sets previous session date for error message.
     *
     * @param errPrevSessDate previous session date for error message.
     */
    public void setErrPrevSessDate(Date errPrevSessDate) {
        this.errPrevSessDate = errPrevSessDate;
    }

    /**
     * Returns supplier code for error message.
     *
     * @return supplier code for error message.
     */
    public String getErrSupCode() {
        return errSupCode;
    }

    /**
     * Sets supplier code for error message.
     *
     * @param errSupCode supplier code for error message.
     */
    public void setErrSupCode(String errSupCode) {
        this.errSupCode = errSupCode;
    }

    /**
     * Returns Disclosure Level for unique report.
     * @return Disclosure Level for unique report.
     */
    public String getDisclosureLevel() {
        return disclosureLevel;
    }

    /**
     * Sets Disclosure Level for unique report.
     * @param disclosureLevel Disclosure Level for unique report.
     */
    public void setDisclosureLevel(String disclosureLevel) {
        this.disclosureLevel = disclosureLevel;
    }

    /**
     * Returns Authority System for report.
     * @return Authority System for report.
     */
    public String getAuthoritySystem() {
        return AuthoritySystem;
    }

    /**
     * Sets Authority System for report.
     * @param authoritySystem Authority System for report.
     */
    public void setAuthoritySystem(String authoritySystem) {
        AuthoritySystem = authoritySystem;
    }
}
