package com.aconite.apm.gui.automation.outputfilesplitters;

import com.aconite.apm.gui.automation.databasegathers.ExtractLogDataGatherer;
import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.TokenDataRecord;
import com.aconite.apm.gui.automation.utilities.ConfigUtils;
import com.aconite.apm.gui.automation.utilities.SFTPFileRetriever;
import com.aconite.apm.gui.automation.utilities.XmlUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class TokenDataFileSplitter
{
    public List<TokenDataRecord> records;
    String outputFilePath;
    int rowCount = 0;
    private int recordCount = 0;
    private String parsedFileName = "";

    private void setParsedFileName(String fileName){
        this.parsedFileName = fileName;
    }

    public String getParsedFileName(){
        return (this.parsedFileName);
    }

    private void setRecordCount(int count){
        this.recordCount = count;
    }

    public int getRecordCount(){
        return (this.recordCount);
    }

    public void splitFileInRecords() throws Exception
    {
        records = new ArrayList<>();

        ExtractLogDataExtractRecord extractLogDataExtractRecord = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENDATAEXTRACT);

        for(String outputFileName : extractLogDataExtractRecord.getFileNames()) {

            if (outputFileName == null) {
                throw new IllegalStateException("No filename retrieved from database. Did the task complete successfully?");
            }
            else {

                retrieveFileFromSftp(outputFileName);

                String localFilePath = outputFilePath + "\\" + outputFileName;

                NodeList nodeList = XmlUtils.splitFileIntoRecords(localFilePath);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    records.add(getRecord(nodeList.item(i)));
                    rowCount++;
                }
                this.setRecordCount(rowCount);
                if(parsedFileName.equals("")) {
                    this.setParsedFileName(outputFileName);
                }
                else{
                    this.setParsedFileName(this.getParsedFileName() + ";" + outputFileName);
                }
            }

        }
    }

    private TokenDataRecord getRecord(Node node)
    {
        TokenDataRecord record = new TokenDataRecord();
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element element = (Element) node;
            record.setId(XmlUtils.getTagValue("ID", element));
            record.setStatus(XmlUtils.getTagValue("STATUS", element));
            record.setTokenProductId(XmlUtils.getTagValue("TOKEN_PRODUCT_ID", element));
            record.setDefaultTokenAppId(XmlUtils.getTagValue("DEFAULT_TOKEN_APP_ID", element));
            record.setCustomerCode(XmlUtils.getTagValue("CUSTOMER_CODE", element));
        }

        return record;
    }

    public void printRecords()
    {
        System.out.println("Print Records: TokenDataFileSplitter");
        for (TokenDataRecord tokenDataRecord : records)
        {
            System.out.println(tokenDataRecord.toString());
        }
    }

    private void retrieveFileFromSftp(String outputFileName) throws SftpException, JSchException
    {
        outputFilePath = ConfigUtils.cfgProperty("local_folder");
        String srcFolder = ConfigUtils.cfgProperty("extractlog_filepath");

        SFTPFileRetriever.downloadFile(srcFolder, outputFileName, outputFilePath);
    }

}