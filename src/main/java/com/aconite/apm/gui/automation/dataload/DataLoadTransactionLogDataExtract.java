package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadTransactionLogDataExtract
 * This will call functions not called elsewhere to put
 * enough data in for a transaction_log_data_extract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTokenDelete;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTranslate;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadVPP;
import static com.aconite.apm.gui.automation.utilities.WebRequests.*;

import java.util.HashMap;

public class DataLoadTransactionLogDataExtract
{
    public static String runDataLoadTransactionLogDataExtract() throws Exception {

        StringBuilder out = new StringBuilder();
        String xmlrsp;
        String panId;
        String pan;
        String panSeqNum;
        String panExpDate;
        String encryptedPinBlock;

        HashMap<Integer, HashMap<String, String>> responseData;

        /*
         * Token Delete
         */

        xmlrsp = dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanId(3));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 10 - TOKEN DELETE BY PAN ID => Success");
            } else {
                out.append("TRX_TYPE_ID 10 - TOKEN DELETE BY PAN ID => FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanSeqNoExpiryDate(3));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 10 - TOKEN DELETE BY PAN - PAN SEQ NUM - PAN EXP DATE => Success");
            } else {
                out.append("TRX_TYPE_ID 10 - TOKEN DELETE BY PAN - PAN SEQ NUM - PAN EXP DATE => FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanAlias(3));
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 10 -  TOKEN DELETE BY PAN ALIAS => Success");
            } else {
                out.append("TRX_TYPE_ID 10 - TOKEN DELETE BY PAN ALIAS => FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        /*
         * VPP
         */
        xmlrsp = dataload(DataLoadVPP.dataloadSetVPPPINIdByPAN());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: PASS");
            } else {
                out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadVPP.dataloadSetVPPPINIdByPANId());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PAN ID: PASS");
            } else {
                out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PAN ID FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadVPP.dataloadSetVPPPINIdByIssuerPinId());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: PASS");
            } else {
                out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadVPP.dataloadSetVPPPINIdByPanAlias());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS: PASS");
            } else {
                out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadVPP.dataloadVPPSetPIN());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
            } else {
                out.append("TRX_TYPE_ID 11 - VPP SET PIN FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadVPP.dataloadVPPGetPIN());
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                encryptedPinBlock = responseData.get(i).get("EncryptedPinBlock");
                out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS - ");
                out.append("ENCRYPTEDPINBLOCK=");
                out.append(encryptedPinBlock);
            }else {
                out.append("TRX_TYPE_ID 12 - VPP GET PIN FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }


        /*
         * Translate
         */

        xmlrsp = dataload(DataLoadTranslate.dataloadTranslatePan(5));
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                out.append("TRX_TYPE_ID 19 - TRANSLATE PAN: PANID=");
                out.append(panId);
            } else {
                out.append("TRX_TYPE_ID 19 - TRANSLATE PAN=> ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }


        xmlrsp = dataload(DataLoadTranslate.dataloadTranslatePanAlias(5));
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pan = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                out.append("TRX_TYPE_ID 23 - TRANSLATE PAN ALIAS: PAN=");
                out.append(pan);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
            } else {
                out.append("TRX_TYPE_ID 23 - TRANSLATE PAN ALIAS=> ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = dataload(DataLoadTranslate.dataloadTranslatePanId(5));
        responseData = getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pan = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                out.append("TRX_TYPE_ID 16 - TRANSLATE PAN ID: PAN=");
                out.append(pan);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
            } else {
                out.append("TRX_TYPE_ID 16 - TRANSLATE PAN ID=> ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        return(out.toString());
    }
}


