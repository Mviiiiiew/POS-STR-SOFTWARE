package com.example.posstrsoftware.posstrsoftware.model;

import java.io.Serializable;

/**
 * Created by Wasabi on 10/27/2016.
 */

public class CompanyList implements Serializable {
    int PassWord;

    public int getPassWord() {
        return PassWord;
    }

    public void setPassWord(int passWord) {
        PassWord = passWord;
    }

    int id;
    String CompanyName;
    String CompanyAddress;
    String Telephone;
    String TAXID;
    String DivisionName;
    String POSMachineID;
    String RegisterID;
    String ENDbillText;
    Double VATRate;

    @Override
    public String toString() {
        return VATRate + "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getTAXID() {
        return TAXID;
    }

    public void setTAXID(String TAXID) {
        this.TAXID = TAXID;
    }

    public String getDivisionName() {
        return DivisionName;
    }

    public void setDivisionName(String divisionName) {
        DivisionName = divisionName;
    }

    public String getPOSMachineID() {
        return POSMachineID;
    }

    public void setPOSMachineID(String POSMachineID) {
        this.POSMachineID = POSMachineID;
    }

    public String getRegisterID() {
        return RegisterID;
    }

    public void setRegisterID(String registerID) {
        RegisterID = registerID;
    }

    public String getENDbillText() {
        return ENDbillText;
    }

    public void setENDbillText(String ENDbillText) {
        this.ENDbillText = ENDbillText;
    }

    public Double getVATRate() {
        return VATRate;
    }

    public void setVATRate(Double VATRate) {
        this.VATRate = VATRate;
    }
}
