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

    // Contacts table name
    private static final String TABLE_CUSTOMERS = "customers";


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LOGO = "logo";

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
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

        // Create tables again
        onCreate(db);
    }


    // Save --------------------------------------------------------------------------------Save

    public CustomerSaveResult SaveCustomer(Customer customer) {

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

            return CustomerSaveResult.Success;

        }catch(Exception ex){
            return CustomerSaveResult.Error;
        }

    }

    public enum CustomerSaveResult {
        Error,
        Success
    }

    // Read --------------------------------------------------------------------------------Read

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

    public ArrayList<Report> GetReports(int CustomerID, Context context) {

        // TODO FAKEDATEN
        ArrayList<Report> Reports = new ArrayList<Report>();

        for (int Index = 0; Index < 50; Index++) {
            Reports.add(Index, new Report(Index, CustomerID, "Report_" + Index));
        }

        return Reports;
    }





}
