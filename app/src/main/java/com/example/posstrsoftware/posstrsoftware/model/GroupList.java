package com.example.posstrsoftware.posstrsoftware.model;

import java.io.Serializable;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class GroupList implements Serializable{
    private  int id;
    private String groupText;

    public GroupList(int id, String groupText) {
        this.id = id;
        this.groupText = groupText;
    }
    public GroupList(){

    }

    public GroupList(String groupText) {
        this.groupText = groupText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
    }
}
