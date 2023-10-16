@dataloadtest
Feature: DataLoad

  Background: User wants to load data

  Scenario: Add data
    Given Pin Import Single
#    Given Pin Import Multiple 3 iterations 3 records
#    Given Pin Verify By PAN ID 2 iterations 3 records
#    Given Pin Verify By PanSeqNumExpiryDate 2 iterations 3 records
#    Given Pin Verify By PanAlias 2 iterations 3 records
#    Given PIN Delete by PIN ID 2 iterations 3 records
#    Given PIN Delete by Issuer PIN ID 2 iterations 3 records
#    Given PIN Advice By PAN ID SMS 3 iterations 3 records
#    Given PIN Advice By PAN ID Mail 3 iterations 3 records
#    Given PIN Advice By PAN Alias SMS 3 iterations 3 records
#    Given PIN Advice By PAN Alias Mail 3 iterations 3 records
#    Given PIN Advice By PanSeqNumExpiryDate SMS 3 iterations 3 records
#    Given PIN Advice By PanSeqNumExpiryDate Mail 3 iterations 3 records
#    Given Token Import OrderType 1 SMS
#    Given TOWPD Order_Type_1 SMS 3 iterations 3 records
#    Given TOWPD Order_Type_1 Mail 3 iterations 3 records
#    Given TOWPD Order_Type_1 Web 3 iterations 3 records
#    Given TOWPD Order_Type_1 SMS DM1 3 iterations 3 records
#    Given TOWPD Order_Type_1 Mail DM1 3 iterations 3 records
#    Given TOWPD Order_Type_2 SMS 3 iterations 3 records
    Given TOWPD Order_Type_2 Mail 5 iterations 1 records
#    Given TOWPD Order_Type_2 Web 3 iterations 3 records
#    Given TOWPD Order_Type_2 SMS DM1 3 iterations 3 records
#    Given TOWPD Order_Type_2 Mail DM1 3 iterations 3 records
#    Given TOWPD Order_Type_3 SMS 3 iterations 3 records
#    Given TOWPD Order_Type_3 Mail 3 iterations 3 records
#    Given TOWPD Order_Type_3 Web 3 iterations 3 records
#    Given TOWPD Order_Type_3 SMS DM1 3 iterations 3 records
#    Given TOWPD Order_Type_3 Mail DM1 3 iterations 3 records
#    Given TOWPD Order_Type_4 SMS 3 iterations 3 records
#    Given TOWPD Order_Type_4 Mail 3 iterations 3 records
#    Given TOWPD Order_Type_4 Web 3 iterations 3 records
#    Given TOWPD Order_Type_4 SMS DM1 3 iterations 3 records
#    Given TOWPD Order_Type_4 Mail DM1 3 iterations 3 records
#    Given TOWPD Order_Type_6 SMS 3 iterations 3 records
#    Given TOWPD Order_Type_6 Mail 3 iterations 3 records
#    Given TOWPD Order_Type_6 Web 3 iterations 3 records
#    Given TOWPD Order_Type_6 SMS DM1 3 iterations 3 records
#    Given TOWPD Order_Type_6 Mail DM1 3 iterations 3 records
#    Given Token Delete By PAN ID 3 iterations 3 records
#    Given Token Delete By PanSeqNumExpiryDate 3 iterations 3 records
#    Given Token Delete By PAN Alias 3 iterations 3 records
#    Given Set VPP PIN ID By PAN 1 iterations
#    Given Set VPP PIN ID By PAN 1 iterations
#    Given VPP Set PIN 1 iterations
#    Given Set VPP PIN ID By PAN 1 iterations
#    Given VPP Get PIN 1 iterations
#    Given Set VPP PIN ID By PAN ID 3 iterations
#    Given VPP Set PIN 3 iterations
#    Given Set VPP PIN ID By PAN ID 3 iterations
#    Given VPP Get PIN 3 iterations
#    Given Set VPP PIN ID By Issuer PIN ID 3 iterations
#    Given Set VPP PIN ID By PAN Alias 3 iterations
#    Given VPP Set PIN 3 iterations
#    Given VPP Get PIN 3 iterations
#    Given Translate PAN ID 3 iterations 3 records
#    Given Translate PAN 3 iterations 3 records
#    Given Translate PAN Alias 3 iterations 3 records
#    Given PIN Export by PAN ID 3 iterations 3 records
#    Given PIN Export by PanSeqNumExpiryDate 3 iterations 3 records
#    Given PIN Export by PAN Alias 3 iterations 3 records
#    Given PIN Update by PAN ID 3 iterations 3 records
#    Given PIN Update by PanSeqNumExpiryDate 3 iterations 3 records
#    Given PIN Update by PAN Alias 3 iterations 3 records
#    Given Rollback PIN Change by PAN ID 3 iterations 3 records
#    Given Rollback PIN Change by PanSeqNumExpiryDate 3 iterations 3 records
#    Given Rollback PIN Change by PAN Alias 3 iterations 3 records
