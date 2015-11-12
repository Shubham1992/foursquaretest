package com.trending.foursquare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.trending.foursquare.utils.BeanClass;

import javax.crypto.KeyAgreement;


public class SqlExecutor {

    private static final String DATABASE_NAME = "Query_Manager";
    private static final int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "foursquare";


    // Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_ADDRESS = "ADDRESS";

    private  dbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    public SqlExecutor(Context c){

        ourContext = c;

    }



    private static class dbHelper extends SQLiteOpenHelper{


        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            TABLE_NAME= BeanClass.getInstance().getSearchTerm();

            Log.d("key table name", TABLE_NAME);
            Log.d("key table name", KEY_NAME);
            Log.d("key table name", KEY_ADDRESS);
            Log.d("key table name", KEY_CATEGORY);

            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_ADDRESS+ " TEXT NOT NULL, " +
                    KEY_CATEGORY + " TEXT NOT NULL" +");";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            Log.d("key","success");
            onCreate(db);
        }

    }

    public SqlExecutor open(){
        ourHelper = new dbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }


    public long addRow(String name, String locationname, String restaurant) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_ADDRESS,locationname);
        cv.put(KEY_CATEGORY, restaurant);


        return ourDatabase.insert(TABLE_NAME,null,cv);
    }

    public String[] getData(){

        String [] columns = new String[]{KEY_ID,KEY_NAME,KEY_ADDRESS,KEY_CATEGORY};

        Cursor c = ourDatabase.query(TABLE_NAME,columns,null,null,null,null,null);

        String[] result = new String[20];
        int i= 0;

        int iName = c.getColumnIndex(KEY_NAME);
        int iaddress = c.getColumnIndex(KEY_ADDRESS);
        int icategory = c.getColumnIndex(KEY_CATEGORY);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result [i] = c.getString(iName)+","+c.getString(iaddress)+","+c.getString(icategory);
            i++;
        }

        return result;
    }

}
