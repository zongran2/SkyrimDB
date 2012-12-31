package com.sivanov.skyrimdb.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "SkyrimDB";
    private final File dbPath;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
        dbPath = context.getDatabasePath(DB_NAME);

        createDataBase();
    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     * 
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            checkDB = SQLiteDatabase.openDatabase(dbPath.getPath(), null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase(){
 
        boolean dbExist = checkDataBase();
 
        if(dbExist){
            //do nothing - database already exist
        }else{
 
            //By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
 
            copyDataBase();
        }
 
    }
    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transferring bytestream.
     * */
    private void copyDataBase() {

        // Open your local db as the input stream
        InputStream myInput;
        try {
            myInput = context.getAssets().open(DB_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Can't open DB asset");
        }

        // Open the empty db as the output stream
        OutputStream myOutput;
        try {
            myOutput = new FileOutputStream(dbPath);
        } catch (FileNotFoundException e) {
            throw new Error("Can't open destination DB file");
        }

        // transfer bytes from the input file to the output file
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
        } catch (IOException ignored) {
        } finally {
            try {
                myOutput.close();
            } catch (IOException ignored) {}
            try {
                myInput.close();
            } catch (IOException ignored) {}
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
