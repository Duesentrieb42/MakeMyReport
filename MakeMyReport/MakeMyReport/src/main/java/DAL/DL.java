package DAL;

import Entities.Customer;
import Entities.Report;
import Entities.Report_Entry;

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
public class DL extends SQLiteOpenHelper implements itf_DL_Customers,itf_DL_Reports,itf_DL_Report_Entries{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DataReport";
    private static DL dl;

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

        db.execSQL(CustomerTable.GetCreateString());
        db.execSQL(ReportTable.GetCreateString());
        db.execSQL(ReportEntryTable.GetCreateString());

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.TableName);

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ReportTable.TableName);

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ReportEntryTable.TableName);

        // Create tables again
        onCreate(db);
    }

    //-------------------------------------------------------------------  Customers

    @Override
    public boolean SaveCustomer(Customer customer) {

        try{

            SQLiteDatabase db = this.getWritableDatabase();

            // Image nach Byte-Array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            customer.Logo().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Values für Insert
            ContentValues values = new ContentValues();
            values.put(CustomerTable.CustomerName.Name(), customer.Name());
            values.put(CustomerTable.CustomerDescription.Name(), customer.Description());
            values.put(CustomerTable.CustomerLogo.Name(), byteArray);

            // Inserting Row
            db.insert(CustomerTable.TableName, null, values);
            db.close(); // Closing database connection

            return true;

        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public ArrayList<Customer> GetCustomers() {

        ArrayList<Customer> customers = new ArrayList<Customer>();

        String selectQuery = "SELECT  * FROM " + CustomerTable.TableName;

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

    @Override
    public Customer GetCustomer(int CustomerID) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(CustomerTable.TableName, new String[] { CustomerTable.CustomerID.Name(),
                CustomerTable.CustomerName.Name(),
                CustomerTable.CustomerDescription.Name(),
                CustomerTable.CustomerLogo.Name() },
                CustomerTable.CustomerID.Name() + "=?",
                new String[] { String.valueOf(CustomerID) }, null, null, null, null);
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

    @Override
    public boolean UpdateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean DeleteCustomer(int CustomerID) {
        return false;
    }

    //-------------------------------------------------------------------  Reports

    @Override
    public ArrayList<Report> GetReports(int CustomerID) {
        return new ArrayList<Report>();
    }

    @Override
    public Customer GetReport(int ReportsID) {
        return null;
    }

    public boolean SaveReport(Report report) {
        return false;
    }

    @Override
    public boolean UpdateReport(Report report) {
        return false;
    }

    @Override
    public boolean DeleteReport(int ReportID) {
        return false;
    }

    //-------------------------------------------------------------------  ReportEntries

    @Override
    public ArrayList<Report_Entry> GetReportEntries(int ReportID) {
        return null;
    }

    @Override
    public Customer GetReportEntry(int ReportEntryID) {
        return null;
    }

    @Override
    public boolean SaveReportEntry(Report_Entry ReportEntry) {
        return false;
    }

    @Override
    public boolean UpdateReportEntry(Report_Entry ReportEntry) {
        return false;
    }

    @Override
    public boolean DeleteReportEntry(int ReportEntryID) {
        return false;
    }

}
