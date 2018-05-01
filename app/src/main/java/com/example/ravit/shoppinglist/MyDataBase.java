package com.example.ravit.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ravit on 4/23/2018.
 */

public class MyDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Shopping.db";
    public static final int DATABASEVERSION = 1;
    public static final String TABLE_NAME = "shopping_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "PRICE";
    public static final String COL4 = "QUANTITY";
    public static final String COL5 = "FAVORITE";
    public int total_cost=0;


    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table IF NOT EXISTS "+ TABLE_NAME + " " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,PRICE TEXT, QUANTITY TEXT, FAVORITE TEXT)");
        /*String query = "CREATE TABLE" + TABLE_NAME + "(" +
                        COL1 + "INTEGER PRIMARY KEY AUTO INCREMENT" +
                        COL2 + "TEXT" +
                        COL3 + "TEXT" +
                        COL4 + "TEXT" +
                        COL5 + "TEXT" +
                        ");";
        db.execSQL(query);*/


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String name, String price, String quantity, String favorite) {
        //db.execSQL("insert into TABLE_NAME values('"+s1+"',"+"'"+s2+"',"+"'"+s3+"',"+"'"+s4+"')");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("555","Opended db");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,price);
        contentValues.put(COL4,quantity);
        contentValues.put(COL5,favorite);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<TaskInfo> retrieveData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TaskInfo> alist=new ArrayList<TaskInfo>();
        TaskInfo ts=new TaskInfo();

        total_cost = 0;
        Cursor c = db.rawQuery("select * from shopping_table", null);
        Log.i("dbstore", "here");

        while (c.moveToNext())
        {

                String name1=c.getString(1);
                Log.i("Retrieve: Item Name",name1);
                String price1=c.getString(2);
                int cost=Integer.parseInt(price1);


                String quantity1=c.getString(3);
                int quantity = Integer.parseInt(quantity1);
            int updated_price1 = cost * quantity;
            total_cost +=updated_price1 ;

                String favorite1=c.getString(4);
                String id1 =c.getString(0);


                Log.i("Retrieve method","Data loaded" );

                ts=new TaskInfo();

                ts.setId(id1);
                ts.setName(name1);
                ts.setPrice(price1);
                ts.setQuantity(quantity1);
                ts.setFavorite(favorite1);

                Log.i("1000",ts.getName());
                alist.add(ts);

            Log.i("Retrieve method","Data added to Array List" );

        }
        return alist;
    }

    public int itemSum(){
        Log.i("fun:itemSum",""+total_cost);

        return total_cost;
    }

    /*public int findStatusFavorite(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("fun:findStatusFavorite",""+total_cost);
        db.execSQL("select FAVORITE from shopping_table" +" where ID='"+row_id+"'");
        return row_id;
    }*/

    public ArrayList<TaskInfo> filterFavorite() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TaskInfo> alist=new ArrayList<TaskInfo>();
        TaskInfo ts=new TaskInfo();
        String status="True";
        Cursor c = db.rawQuery("select * from shopping_table where FAVORITE='"+status+"'", null);
        Log.i("filterFavorite fun", "here");
        total_cost=0;
        while (c.moveToNext())
        {

            String name1=c.getString(1);
            Log.i("Retrieve: Item Name",name1);
            String price1=c.getString(2);
            int cost=Integer.parseInt(price1);


            String quantity1=c.getString(3);
            int quantity = Integer.parseInt(quantity1);
            int updated_price1 = cost * quantity;
            total_cost +=updated_price1 ;
            String favorite1=c.getString(4);
            String id1 =c.getString(0);


            Log.i("Retrieve method","Data loaded" );

            ts=new TaskInfo();

            ts.setId(id1);
            ts.setName(name1);
            ts.setPrice(price1);
            ts.setQuantity(quantity1);
            ts.setFavorite(favorite1);

            Log.i("1000",ts.getName());
            alist.add(ts);

            Log.i("Retrieve method","Data added to Array List" );

        }
        return alist;
    }

    public void delete(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("MyDataBase:Delete",row_id);
        //db.execSQL("delete from myalarmtable" +" where time_date='"+s13+"'");
        db.execSQL("delete from shopping_table" +" where ID='"+row_id+"'");
    }
}




