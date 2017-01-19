package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.posstrsoftware.posstrsoftware.model.GroupList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class GroupDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperGroup;

    public  GroupDAO(Context context){
        dbHelperGroup = new DbHelper(context);
    }
    public void open(){
        database = dbHelperGroup.getWritableDatabase();
    }
    public void close() {
        dbHelperGroup.close();
    }

    public ArrayList<GroupList> getAllGroupList(){

        ArrayList<GroupList> groupLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM group_list where delete_flag = 'N' order by group_text asc;",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GroupList mGroupList = new GroupList();
            mGroupList.setId(cursor.getInt(0));
            mGroupList.setGroupText(cursor.getString(1));
            groupLists.add(mGroupList);
            cursor.moveToNext();


        }
        cursor.close();
        return groupLists;
    }
    public ArrayList<GroupList> getAllFixGroupList(int idGroup){

        ArrayList<GroupList> groupLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select * from group_list where id_group = '"+idGroup+"' and delete_flag ='N' union all select * from group_list where id_group <> '"+idGroup+"'  and delete_flag='N';",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GroupList mGroupList = new GroupList();
            mGroupList.setId(cursor.getInt(0));
            mGroupList.setGroupText(cursor.getString(1));
            groupLists.add(mGroupList);
            cursor.moveToNext();


        }
        cursor.close();
        return groupLists;
    }




    public int  add(GroupList groupList){
        String query = "Select count(*) from group_list where group_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1,groupList.getGroupText());
        stmt.bindString(2,"N");
        int count_row = (int)stmt.simpleQueryForLong();
        if(stmt !=null) stmt.close();
        if( count_row != 0){
            return 0;
        }else{
            ContentValues values = new ContentValues();
            values.put("group_text",groupList.getGroupText());
            this.database.insert("group_list",null,values);
            return 1;
        }

    }



    public int update(GroupList groupList) {
        String query = "Select count(*) from group_list where group_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, groupList.getGroupText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            GroupList updateGroupList = groupList;
            ContentValues values = new ContentValues();
            values.put("group_text", updateGroupList.getGroupText());
            values.put("id_group", updateGroupList.getId());
            String where = "id_group=" + updateGroupList.getId();
            this.database.update("group_list", values, where, null);
            return 1;
        }
    }
    public void updatenow(GroupList groupList) {

            GroupList updateGroupList = groupList;
            ContentValues values = new ContentValues();
            values.put("group_text", updateGroupList.getGroupText());
            values.put("id_group", updateGroupList.getId());
            String where = "id_group=" + updateGroupList.getId();
            this.database.update("group_list", values, where, null);


    }


    public void  delete(GroupList groupList){

        this.database.execSQL("UPDATE group_list set delete_flag = 'Y', group_text = 'null' where id_group = "+ groupList.getId());

    }


}
