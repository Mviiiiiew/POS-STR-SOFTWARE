package com.example.posstrsoftware.posstrsoftware.model;

import java.io.Serializable;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class UnitList implements Serializable {

    private  int id;
    private String unitText;

    public UnitList(int id, String unitText) {
        this.id = id;
        this.unitText = unitText;
    }

    public UnitList() {

    }

    public UnitList(String unitText) {
        this.unitText = unitText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitText() {
        return unitText;
    }

    public void setUnitText(String unitText) {
        this.unitText = unitText;
    }



}
