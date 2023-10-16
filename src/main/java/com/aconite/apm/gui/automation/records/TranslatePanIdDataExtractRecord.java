package com.aconite.apm.gui.automation.records;

public class TranslatePanIdDataExtractRecord
{
    private String reqDateTime;
    private String id;
    private String institutionId;
    private String trxTypeId;
    private String trxStatus;
    private String reqPanId;

    private String millisecondsToOneDP(String param){
        if (param.substring(param.length()-3).equals(":00")){
            param = param + ".0";
        }
        return param;
    }


    public String getReqDateTime()
    {
        return reqDateTime;
    }

    public void setReqDateTime(String reqDateTime)
    {
        this.reqDateTime = millisecondsToOneDP(reqDateTime);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getInstitutionId()
    {
        return institutionId;
    }

    public void setInstitutionId(String institutionId)
    {
        this.institutionId = institutionId;
    }

    public String getTrxTypeId()
    {
        return trxTypeId;
    }

    public void setTrxTypeId(String trxTypeId)
    {
        this.trxTypeId = trxTypeId;
    }

    public String getTrxStatus()
    {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus)
    {
        this.trxStatus = trxStatus;
    }

    public String getReqPanId()
    {
        return reqPanId;
    }

    public void setReqPanId(String reqPanId)
    {
        this.reqPanId = reqPanId;
    }

    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder();
        out.append("Record: [REQDATETIME=");
        out.append(getReqDateTime());
        out.append(", ID=");
        out.append(getId());
        out.append(", INSTITUTIONID=");
        out.append(getInstitutionId());
        out.append(", TRXTYPEID=");
        out.append(getTrxTypeId());
        out.append(", TRXSTATUS=");
        out.append(getTrxStatus());
        if(getTrxTypeId().equals("16")){
            out.append(", REQPANID=");
        }
        else{
            out.append(", RSPPANID=");
        }
        out.append(getReqPanId());
        out.append("]");

        return out.toString();

//        return "Record: [REQDATETIME=" + getReqDateTime() + ", ID=" + getId() + ", INSTITUTIONID=" + getInstitutionId() + ", TRXTYPEID=" + getTrxTypeId() + ", TRXSTATUS=" +
//                getTrxStatus() + ", REQPANID=" + getReqPanId () + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        final TranslatePanIdDataExtractRecord other = (TranslatePanIdDataExtractRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getReqDateTime().equals(other.getReqDateTime()))
        {
            return false;
        }

        if (!this.getInstitutionId().equals(other.getInstitutionId()))
        {
            return false;
        }

        if (!this.getTrxTypeId().equals(other.getTrxTypeId()))
        {
            return false;
        }

        if (!this.getTrxStatus().equals(other.getTrxStatus()))
        {
            return false;
        }

        if (!this.getReqPanId().equals(other.getReqPanId()))
        {
            return false;
        }

        return true;
    }
}
