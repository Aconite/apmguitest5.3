package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadVppDataExtract
 * This will call all the functions to put enough data in for a vppdataextract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinImport;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadVPP;


import java.util.HashMap;

public class DataLoadVppDataExtract
{


    public DataLoadVppDataExtract(){

    }

    public static String runDataLoadVppDataExtract() {

        HashMap<Integer, HashMap<String, String>> responseData;
        HashMap<Integer, HashMap<String, String>> responseData2;
        StringBuilder out = new StringBuilder();
        String pinEncrypted;
        String pinVerificationValue;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;
        String pinId;
        int iterations;

        /*--------------------------------------------------------------
         * Pin Import
         *--------------------------------------------------------------*/
        responseData = DataLoadPinImport.runPinImportSingle();

        for (int i = 0; i < responseData.size(); i++ ) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pinId = responseData.get(i).get("PINID");
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE: PINID=");
                out.append(pinId);
            }
            else {
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail
         * Added to ensure there is a token application in the database
         * when starting from an empty database
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadTOWPD.runTOWPD_OT1_Mail(5);

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    panId = responseData.get(i).get("PANID");
                    panClear = responseData.get(i).get("PAN");
                    panSeqNum = responseData.get(i).get("PANSequenceNumber");
                    panExpDate = responseData.get(i).get("PANExpiryDate");
                    issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                    pinEncrypted = responseData.get(i).get("PIN");
                    pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                    pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                    out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
                    out.append(panId);
                    out.append(", PAN=");
                    out.append(panClear);
                    out.append(", PANSEQUENCENUMBER=");
                    out.append(panSeqNum);
                    out.append(", PANEXPIRYDATE=");
                    out.append(panExpDate);
                    out.append(", ISSUERPANALIAS=");
                    out.append(issuerPanAlias);
                    out.append(", PIN=");
                    out.append(pinEncrypted);
                    out.append(", PINVERIFICATIONVALUE=");
                    out.append(pinVerificationValue);
                    out.append(", PVVKEYINDEX=");
                    out.append(pvvKeyIndex);
                    out.append("\n");
                }
                else {
                    out.append("TOWPD_OT1_Mail FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }

            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PAN
         * VPP SET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPAN();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runSetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }

                        out.append("\n");

                    }

                }
                else {

                    out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));

                }
            }

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PANID
         * VPP SET PIN
         *--------------------------------------------------------------*/

        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPANID();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")){

                    out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PANID: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runSetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }

                        out.append("\n");

                    }

                }
                else {
                    out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PANID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }

            }

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY ISSUER PIN ID
         * VPP SET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByIssuerPinId();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runSetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }

                        out.append("\n");

                    }

                }
                else {
                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }
            }

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PAN ALIAS
         * VPP SET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPanAlias();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runSetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 11 - VPP SET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }

                        out.append("\n");
                    }

                }
                else {
                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }
            }

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PAN
         * VPP GET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPAN();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runGetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }
                        out.append("\n");
                    }

                }
                else {

                    out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));

                }
            }

        }


        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PAN ID
         * VPP GET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPANID();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")){

                    out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PANID: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runGetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }
                        out.append("\n");
                    }

                }
                else {
                    out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PANID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }

            }

        }



        /*--------------------------------------------------------------
         * SET VPP PIN ID BY ISSUER PIN ID
         * VPP GET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByIssuerPinId();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runGetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }
                        out.append("\n");
                    }

                }
                else {
                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }
            }

        }

        /*--------------------------------------------------------------
         * SET VPP PIN ID BY PAN ALIAS
         * VPP GET PIN
         *--------------------------------------------------------------*/
        iterations = 5;
        for (int it = 0; it < iterations; it++) {

            responseData = DataLoadVPP.runSetVPPPINIDByPanAlias();

            for (int i = 0; i < responseData.size(); i++) {

                if (responseData.get(i).get("ResponseCode").equals("1")) {

                    out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS: PASS");
                    out.append("\n");

                    responseData2 = DataLoadVPP.runGetVPPPIN();

                    for (int j = 0; j < responseData.size(); j++) {

                        if (responseData2.get(j).get("ResponseCode").equals("1")) {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS");
                        } else {
                            out.append("TRX_TYPE_ID 12 - VPP GET PIN: FAIL: ");
                            out.append(responseData2.get(i).get("ErrorDescription"));
                        }
                        out.append("\n");
                    }

                }
                else {
                    out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: FAIL: ");
                    out.append(responseData.get(i).get("ErrorDescription"));
                }
            }

        }

        return (out.toString());
    }
}