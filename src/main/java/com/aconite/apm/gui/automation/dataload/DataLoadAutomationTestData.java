package com.aconite.apm.gui.automation.dataload;

import com.aconite.apm.gui.automation.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataLoadAutomationTestData {

    private Connection connection;

    public String institutionOne;
    public String issuerOne;



    private static final String SQL =   "SELECT INST.NAME, ISS.NAME " +
                                        "FROM C_D_INSTITUTION INST " +
                                        "INNER JOIN C_D_ISSUER ISS ON INST.ID = ISS.INSTITUTION_ID " +
                                        "WHERE ISS.ID = ?";

    private static final String SQL2 =  "SELECT ISSUER_TOKEN_PRODUCT_CODE " +
                                        "FROM C_D_TOKEN_PRODUCT " +
                                        "WHERE ISSUER_ID = ? " +
                                        "AND TOKEN_PRODUCT_GROUP_ID = ? " +
                                        "ORDER BY ID ASC";

//    private static final String SQL2 =  "SELECT CDTP.ISSUER_TOKEN_PRODUCT_CODE " +
//                                        "FROM C_D_TOKEN_PRODUCT CDTP " +
//                                        "LEFT JOIN C_D_ISSUER ISS ON CDTP.ISSUER_ID = ISS.ID " +
//                                        "LEFT JOIN C_D_TOKEN_PRODUCT_GROUP CDTPG ON CDTP.TOKEN_PRODUCT_GROUP_ID = CDTPG.ID " +
//                                        "WHERE ISS.NAME = 'TestBank' " +
//                                        "AND CDTPG.NAME = 'TestBank HK'";

//    public DataLoadAutomationTestData(){
//
//        getInstitutionOne();
//        tokenProductList = getTokenProductsForIssuerOne();
//
//    }

    public String[] getInstitutionOne()
    {
        String[] output = new String[2];
        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, "1");

            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next())
            {
                output[0] = (rs.getString(1));
                output[1] = (rs.getString(2));

            }

            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return output;
    }

    public List<String> getTokenProductsForIssuerOne(){

        List<String> tokenProductList = new ArrayList<>();

        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(SQL2);
            preparedSelect.setString(1, "1");
            preparedSelect.setString(2, "1");

            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next())
            {
                //System.out.println("RS = " + rs.getString(1));
                try{
                    tokenProductList.add(rs.getString(1));
                }
                catch (NullPointerException npe)
                {
                    npe.printStackTrace();
                }
            }

            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return tokenProductList;

    }

}
