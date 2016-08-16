package com.example.android.knowledgeloot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by da7th on 8/16/2016.
 */
public class LootDBHelper extends SQLiteOpenHelper {


    //tag used to find errors in this particular file
    private String DB_LOG_TAG = "DATABASE_HELPER";

    public LootDBHelper(Context context) {
        super(context, lootSQLContract.TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase lootDB) {

        lootDB.execSQL("create table " + lootSQLContract.TABLE_NAME + " (_ID INTEGER PRIMARY KEY " +
        "AUTOINCREMENT, LOOT TEXT)");

        Log.v(DB_LOG_TAG, "the onCreate method has been called the table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase lootDB, int i, int i1) {

        //upon the need to upgrade, the entire table is deleted
        deleteAllData(lootDB);
        //upon the need to upgrade, the entire table is dropped if it still exists
        lootDB.execSQL("DROP TABLE IF EXISTS " + lootSQLContract.TABLE_NAME);
        //and here it is recreated by calling the onCreate method again
        onCreate(lootDB);
        Log.v(DB_LOG_TAG, "the onUpgrade method has been called and the table has been dropped " +
                "and recreated");

    }

    public Boolean insertData(String loot){

        //create a writable instance of the database
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(DB_LOG_TAG, "database is set to get Writable");

        //we use a variable of content values to take in the values to be set
        ContentValues contentValues = new ContentValues();
        contentValues.put(lootSQLContract.COL_1, loot);
        Log.v(DB_LOG_TAG, "database contentValues set");

        //the insert function takes the table name and the content values set and sets it to the
        //table, in order to check that the insert funcation was successful it must not return -1
        //by placing the result in a long variable we can check for the -1 using an if statement
        long result = db.insert(lootSQLContract.TABLE_NAME, null, contentValues);
        Log.v(DB_LOG_TAG, "database result is " + result);

        return result != -1;
    }

    public Cursor readAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        //a cursor object allows for random read and write, this code allows us to store all of the
        // table data into the cursor res and return it to where the method was called to be shown
        Cursor res = db.query(lootSQLContract.TABLE_NAME, new String[]{lootSQLContract._ID,
                lootSQLContract.COL_1}, null, null, null, null, null);
        return res;
    }


    public Boolean updateData(String id, String loot){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(lootSQLContract._ID, id);
        contentValues.put(lootSQLContract.COL_1, loot);

        //the following method call will replace the row based on the unique identifier which in
        // this case is the id
        db.update(lootSQLContract.TABLE_NAME, contentValues, "_ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //the following deleted a row of data according to the given id
        return db.delete(lootSQLContract.TABLE_NAME, "_ID = ?", new String[]{id});
    }

    public Integer deleteAllData(SQLiteDatabase db) {
        db = this.getWritableDatabase();

        //the following will delete all the data in the table
        return db.delete(lootSQLContract.TABLE_NAME, "select * from " +
                lootSQLContract.TABLE_NAME, new String[]{"0"});
    }

    public Cursor readData(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //a cursor object allows for random read and write, this code allows us to store all of the
        // table data into the cursor res and return it to where the method was called to be shown
        Cursor res = db.query(lootSQLContract.TABLE_NAME, new String[]{lootSQLContract._ID,
                        lootSQLContract.COL_1,}, lootSQLContract._ID + " like" + "'%" + id + "%'", null,
                null, null, null);

        return res;
    }

    //the contract class by which to setup the table
    //basecolumn will setup two extra columns for _ID and _count
    public static abstract class lootSQLContract implements BaseColumns{

        //table name and column names
        public static final String TABLE_NAME = "lootCollection";
        public static final String COL_1 = "loot";
    }
}
