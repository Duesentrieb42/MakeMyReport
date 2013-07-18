package DAL;

import Entities.Customer;
import Entities.Report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;
import java.util.ArrayList;


/**
 * Created by Vitali on 14.07.13.
 */
public class DL extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DataReport";

    // Customer
    private static final String TABLE_CUSTOMERS = "customers";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LOGO = "logo";

    // Report
    private static final String TABLE_REPORT = "reports";

    private static final String KEY_REPORT_ID = "id";
    private static final String KEY_REPORT_CUSTOMER_ID = "customerid";
    private static final String KEY_REPORT_NAME = "name";

    // Report Entry
    private static final String TABLE_REPORT_ENTRY = "report_entries";

    private static final String KEY_REPORT_ENTTRY_ID = "id";
    private static final String KEY_REPORT_ENTRY_IMAGE = "image";

    static private DL dl;

    public DL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DL GetDL(Context context) {
        if (dl == null) {
            dl = new DL(context);


        }
        return dl;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_LOGO + " BLOB"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_REPORT_TABLE = "CREATE TABLE " + TABLE_REPORT + "("
                + KEY_REPORT_ID + " INTEGER PRIMARY KEY,"
                + KEY_REPORT_CUSTOMER_ID + " INTEGER,"
                + KEY_REPORT_NAME + " TEXT"+ ")";
        db.execSQL(CREATE_REPORT_TABLE);

        String CREATE_ENTRIES = "CREATE TABLE " + TABLE_REPORT_ENTRY + "("
                + KEY_REPORT_ENTTRY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REPORT_ENTRY_IMAGE + " BLOB)";
        db.execSQL(CREATE_ENTRIES);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT_ENTRY);

        // Create tables again
        onCreate(db);
    }


    // Save --------------------------------------------------------------------------------Save

    public SaveResult SaveCustomer(Customer customer) {

        try{

            SQLiteDatabase db = this.getWritableDatabase();

            // Image nach Byte-Array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            customer.Logo().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Values für Insert
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, customer.Name());
            values.put(KEY_DESCRIPTION, customer.Description());
            values.put(KEY_LOGO, byteArray);

            // Inserting Row
            db.insert(TABLE_CUSTOMERS, null, values);
            db.close(); // Closing database connection

            return SaveResult.Success;

        }catch(Exception ex){
            return SaveResult.Error;
        }
    }

    public SaveResult SaveReport(Report report) {

        try{

            SQLiteDatabase db = this.getWritableDatabase();

            // Values für Insert
            ContentValues values = new ContentValues();
            values.put(KEY_REPORT_ID, report.ReportID());
            values.put(KEY_REPORT_CUSTOMER_ID, report.CustomerID());
            values.put(KEY_REPORT_NAME, report.Name());

            // Inserting Row
            db.insert(TABLE_REPORT, null, values);
            db.close(); // Closing database connection

            return SaveResult.Success;

        }catch(Exception ex){
            return SaveResult.Error;
        }
    }

    public enum SaveResult {
        Error,
        Success
    }

    // Read -------------------------------------------------------------------------------Read

    public ArrayList<Customer> GetAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<Customer>();

        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                byte[] byteArray = cursor.getBlob(3);

                InputStream is = new ByteArrayInputStream(byteArray);
                Bitmap logo = BitmapFactory.decodeStream(is);

                customers.add(new Customer(id,name,description,logo));

            } while (cursor.moveToNext());
        }

        return customers;
    }

    public Customer getCustomer(int Customerid) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] { KEY_ID,
                KEY_NAME, KEY_DESCRIPTION,KEY_LOGO }, KEY_ID + "=?",
                new String[] { String.valueOf(Customerid) }, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();

            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            byte[] byteArray = cursor.getBlob(3);

            InputStream is = new ByteArrayInputStream(byteArray);
            Bitmap logo = BitmapFactory.decodeStream(is);

            Customer customer = new Customer(id,name,description,logo);

            return customer;
        }

        return null;
    }

    public ArrayList<Report> GetReports(int CustomerID) {

        ArrayList<Report> reports = new ArrayList<Report>();

        String selectQuery = "SELECT  * FROM " + TABLE_REPORT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(0));
                int customerid = Integer.parseInt(cursor.getString(1));
                String name = cursor.getString(2);

                reports.add(new Report(id,customerid, name));

            } while (cursor.moveToNext());
        }

        return reports;

    }

    public Report getReport(int ReportID) {

        SQLiteDatabase db =
                this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] { KEY_REPORT_ID,
                KEY_REPORT_CUSTOMER_ID, KEY_REPORT_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(ReportID) }, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();

            int id = Integer.parseInt(cursor.getString(0));
            int customerid = Integer.parseInt(cursor.getString(1));
            String name = cursor.getString(2);

            return new Report(id,customerid, name);

        }

        return null;
    }

}
