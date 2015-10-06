package com.emythmakers.ashik.icare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DataSource {

    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private ContactModel contactModel;

    public DataSource(Context context) {
        helper=new DatabaseHelper(context);

    }

    public  void open(){
      database=helper.getWritableDatabase();
    }

    public  void close(){
        helper.close();
    }

    public  long insertData(ContactModel contactModel){

      this.open();
        ContentValues cv=new ContentValues();
        cv.put(DatabaseHelper.COL_NAME,contactModel.getName());
        cv.put(DatabaseHelper.COL_AGE,contactModel.getAge());
        cv.put(DatabaseHelper.COL_HEIGHT,contactModel.getHeight());
        cv.put(DatabaseHelper.COL_WEIGHT,contactModel.getWeight());

        long inserted=database.insert(DatabaseHelper.TABLE_CONTACT,null,cv);
        database.close();
        this.close();

        return inserted;
    }

    public boolean updateData(String id, ContactModel contactModel){

        this.open();

        ContentValues cv=new ContentValues();
        cv.put(DatabaseHelper.COL_NAME,contactModel.getName());
        cv.put(DatabaseHelper.COL_AGE,contactModel.getAge());
        cv.put(DatabaseHelper.COL_HEIGHT,contactModel.getHeight());
        cv.put(DatabaseHelper.COL_WEIGHT,contactModel.getWeight());

        long check=database.update(DatabaseHelper.TABLE_CONTACT, cv,
                DatabaseHelper.COL_ID + " = " + id, null);

        if (check>0){
           return  true;

        }else{
            return  false;
        }

    }

    public ArrayList<String> GetAllEmployeeName(){
        ArrayList<String> arrayList=new ArrayList<>();

        Cursor cursor=database.query(DatabaseHelper.TABLE_CONTACT, null, null, null, null, null, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++){

                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                arrayList.add(name);
                cursor.moveToNext();
                this.close();

            }

        }
        return  arrayList;
    }

    public ArrayList<ContactModel> getAllContact(){
        ArrayList<ContactModel> contactList=new ArrayList<ContactModel>();
        this.open();

        Cursor cursor=database.query(DatabaseHelper.TABLE_CONTACT,null,null,null,null,null,null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++){

                String id=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String age=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AGE));
                String height=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HEIGHT));
                String weight=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_WEIGHT));

                contactModel=new ContactModel(id,name,age,height,weight);

                contactList.add(contactModel);
                cursor.moveToNext();
                this.close();

            }

        }
       return  contactList;
    }


    public Boolean CheckRow(){
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_CONTACT,null,null,null,null,null,null);

        if (cursor.getCount()==0){
            return false;
        }else {
            return true;
        }
    }

    public  boolean deleteData(String id){

        this.open();
        database.delete(DatabaseHelper.TABLE_CONTACT,DatabaseHelper.COL_ID + " = "+id, null);
        this.close();
        return  true;
    }

    public  ContactModel singleContact(String id){
        this.open();

        Cursor cursor=database.query(DatabaseHelper.TABLE_CONTACT,
                new String[]{DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME, DatabaseHelper.COL_AGE, DatabaseHelper.COL_HEIGHT, DatabaseHelper.COL_WEIGHT},
                DatabaseHelper.COL_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();
        String mId=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String age=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AGE));
        String height=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HEIGHT));
        String weight=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_WEIGHT));

        cursor.close();
        contactModel=new ContactModel(mId,name,age,height,weight);
        this.close();
        return contactModel;
    }
}
