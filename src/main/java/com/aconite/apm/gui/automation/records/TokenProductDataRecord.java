package com.aconite.apm.gui.automation.records;

public class TokenProductDataRecord
{
    private String parentInstitution;
    private String parentIssuer;
    private String id;
    private String name;
    private String issuerTokenProductCode;
    private String issuer;
    private String tokenProductGroup;
    private String retentionDays;
    private String country;
    private String language;
    private String persoBureau;
    private String feedbackRequired;
    private String active;
    private String serviceCode;
    private String testOutput;

    public TokenProductDataRecord(){
        testOutput = "";
    }

    public String getParentInstitution()
    {
        return parentInstitution;
    }

    public void setParentInstitution(String parentInstitution)
    {
        this.parentInstitution = parentInstitution;
    }

    public String getParentIssuer()
    {
        return parentIssuer;
    }

    public void setParentIssuer(String parentIssuer)
    {
        this.parentIssuer = parentIssuer;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getIssuerTokenProductCode()
    {
        return issuerTokenProductCode;
    }

    public void setIssuerTokenProductCode(String issuerTokenProductCode) { this.issuerTokenProductCode = issuerTokenProductCode; }

    public String getIssuer()
    {
        return issuer;
    }

    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getTokenProductGroup()
    {
        return tokenProductGroup;
    }

    public void setTokenProductGroup(String tokenProductGroup) { this.tokenProductGroup = tokenProductGroup; }

    public String getRetentionDays()
    {
        return retentionDays;
    }

    public void setRetentionDays(String retentionDays) { this.retentionDays = retentionDays; }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country) { this.country = country; }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language) { this.language = language; }

    public String getPersoBureau()
    {
        return persoBureau;
    }

    public void setPersoBureau(String persoBureau) { this.persoBureau = persoBureau; }

    public String getFeedbackRequired()
    {
        return feedbackRequired;
    }

    public void setFeedbackRequired(String feedbackRequired) { this.feedbackRequired = feedbackRequired; }

    public String getActive()
        {
            return active;
        }

    public void setActive(String active) { this.active = active; }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString()
    {
        return "Record: [ID=" + getId() + ", NAME=" + getName() + ", ISSUERTOKENPRODUCTCODE=" + getIssuerTokenProductCode() +
                ", ISSUER=" + getIssuer() + ", TOKENPRODUCTGROUP=" + getTokenProductGroup() + ", RETENTIONDAYS=" +
                getRetentionDays() + ", COUNTRY=" + getCountry() + ", LANGUAGE=" + getLanguage() + ", PERSOBUREAU=" +
                getPersoBureau() + ", FEEDBACKREQUIRED=" + getFeedbackRequired() + ", ACTIVE=" + getActive() +
                ", SERVICECODE=" + getServiceCode() + "]";
    }

   public String toStringTable()
    {
        return "Record: [ID=" + getId() + ", NAME=" + getName() + ", ISSUERTOKENPRODUCTCODE=" + getIssuerTokenProductCode() +
                ", ISSUER=" + getIssuer() + ", TOKENPRODUCTGROUP=" + getTokenProductGroup() + ", RETENTIONDAYS=" +
                getRetentionDays() + ", COUNTRY=" + getCountry() + ", LANGUAGE=" + getLanguage() + ", PERSOBUREAU=" +
                getPersoBureau() + ", FEEDBACKREQUIRED=" + getFeedbackRequired() + ", ACTIVE=" + getActive() + "]";
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

        final TokenProductDataRecord other = (TokenProductDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getName().equals(other.getName()))
        {
            return false;
        }

        if (!this.getIssuerTokenProductCode().equals(other.getIssuerTokenProductCode()))
        {
            return false;
        }

        if (!this.getIssuer().equals(other.getIssuer()))
        {
            return false;
        }

        if (!this.getTokenProductGroup().equals(other.getTokenProductGroup()))
        {
            return false;
        }

        if (!this.getRetentionDays().equals(other.getRetentionDays()))
        {
            return false;
        }

        if (!this.getCountry().equals(other.getCountry()))
        {
            return false;
        }

        if (!this.getLanguage().equals(other.getLanguage()))
        {
            return false;
        }

        if (!this.getPersoBureau().equals(other.getPersoBureau()))
        {
            return false;
        }

        if (!this.getFeedbackRequired().equals(other.getFeedbackRequired()))
        {
            return false;
        }

        if (!this.getActive().equals(other.getActive()))
        {
            return false;
        }
        if (this.getServiceCode() != null && other.getServiceCode() != null) {
            if (!this.getServiceCode().equals(other.getServiceCode())) {
                return false;
            }
        }


        return true;
    }

    public boolean equalsTable(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        final TokenProductDataRecord other = (TokenProductDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getName().equals(other.getName()))
        {
            return false;
        }

        if (!this.getIssuerTokenProductCode().equals(other.getIssuerTokenProductCode()))
        {
            return false;
        }

        if (!this.getIssuer().equals(other.getIssuer()))
        {
            return false;
        }

        if (!this.getTokenProductGroup().equals(other.getTokenProductGroup()))
        {
            return false;
        }

        if (!this.getRetentionDays().equals(other.getRetentionDays()))
        {
            return false;
        }

        if (!this.getCountry().equals(other.getCountry()))
        {
            return false;
        }

        if (!this.getLanguage().equals(other.getLanguage()))
        {
            return false;
        }

        if (!this.getPersoBureau().equals(other.getPersoBureau()))
        {
            return false;
        }

        if (!this.getFeedbackRequired().equals(other.getFeedbackRequired()))
        {
            return false;
        }

        if (!this.getActive().equals(other.getActive()))
        {
            return false;
        }

        return true;
    }
}