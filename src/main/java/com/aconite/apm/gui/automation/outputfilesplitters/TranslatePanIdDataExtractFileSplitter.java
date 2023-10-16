package com.aconite.apm.gui.automation.outputfilesplitters;

import com.aconite.apm.gui.automation.databasegathers.ExtractLogDataGatherer;
import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.TranslatePanIdDataExtractRecord;
import com.aconite.apm.gui.automation.utilities.ConfigUtils;
import com.aconite.apm.gui.automation.utilities.SFTPFileRetriever;
import com.aconite.apm.gui.automation.utilities.XmlUtils;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class TranslatePanIdDataExtractFileSplitter
{
    public List<TranslatePanIdDataExtractRecord> records;
    String outputFileName = null;
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

        ExtractLogDataExtractRecord extractLogDataExtractRecord = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TRANSLATEPANIDDATAEXTRACT);

        for(String outputFileName : extractLogDataExtractRecord.getFileNames()) {

            if (outputFileName == null) {
                throw new IllegalStateException("No filename retrieved from database. Did the task complete successfully?");
            }
            else {

                retrieveFileFromSftp(outputFileName);

                String localFilePath = outputFilePath + "\\" + outputFileName;

                records = new ArrayList<>();

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

    private TranslatePanIdDataExtractRecord getRecord(Node node)
    {

        TranslatePanIdDataExtractRecord record = new TranslatePanIdDataExtractRecord();
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element element = (Element) node;

            record.setReqDateTime(AdminCommon.getStandardMilliseconds(XmlUtils.getTagValue("REQ_DATETIME", element)));
            record.setId(XmlUtils.getTagValue("ID", element));
            record.setInstitutionId(XmlUtils.getTagValue("INSTITUTION_ID", element));
            record.setTrxTypeId(XmlUtils.getTagValue("TRX_TYPE_ID", element));
            record.setTrxStatus(XmlUtils.getTagValue("TRX_STATUS", element));
            if(!XmlUtils.getTagValue("REQ_PAN_ID", element).equals("")) {
                record.setReqPanId(XmlUtils.getTagValue("REQ_PAN_ID", element));
            }
            if(!XmlUtils.getTagValue("RSP_PAN_ID", element).equals("")) {
                record.setReqPanId(XmlUtils.getTagValue("RSP_PAN_ID", element));
            }


        }

        return record;
    }

    public void printRecords()
    {
        System.out.println("Print Records: TranslatePanIdDataExtractFileSplitter");
        for (TranslatePanIdDataExtractRecord translatePanIdDataExtractRecord : records)
        {
            System.out.println(translatePanIdDataExtractRecord.toString());
        }
    }

    private void retrieveFileFromSftp(String outputFileName) throws SftpException, JSchException
    {
        outputFilePath = ConfigUtils.cfgProperty("local_folder");
        String srcFolder = ConfigUtils.cfgProperty("extractlog_filepath");

        SFTPFileRetriever.downloadFile(srcFolder, outputFileName, outputFilePath);
    }

}