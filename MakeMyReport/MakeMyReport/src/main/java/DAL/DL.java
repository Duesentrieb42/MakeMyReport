package DAL;

import Entities.Customer;
import Entities.Report;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import com.project.makemyreport.R;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali on 14.07.13.
 */
public class DL {

    private final String APP_PATH = "APP_PATH";
    private final String CUSTOMER_DATA_PATH = "Customer_Data";

    static private DL dl;

    // TODO MUSS ENTFERNT WERDEN WENN DL FERTIG IST
    private ArrayList<Customer> mCustomers;

    public static DL GetDL() {
        if (dl == null) {
            dl = new DL();
        }
        return dl;
    }


    public ArrayList<Customer> GetCustomers(Context context) {

        // TODO FAKEDATEN
        ArrayList<Customer> Customers = new ArrayList<Customer>();
        int count = 0;

        // Testdaten
        Customers.add(count, new Customer(count,
                "Samsung",
                "Eine kurze Beschreibung zu dem Kunden. Vll die KundenNr",
                BitmapFactory.decodeResource(context.getResources(), R.drawable.customer)));
        count += 1;

        Customers.add(count, new Customer(count,
                "RWH",
                "Eine kurze Beschreibung zu dem Kunden. Vll die KundenNr",
                BitmapFactory.decodeResource(context.getResources(), R.drawable.customer)));
        count += 1;

        Customers.add(count, new Customer(count,
                "Opel",
                "Eine kurze Beschreibung zu dem Kunden. Vll die KundenNr",
                BitmapFactory.decodeResource(context.getResources(), R.drawable.customer)));
        count += 1;

        Customers.add(count, new Customer(count,
                "Nintendo",
                "Eine kurze Beschreibung zu dem Kunden. Vll die KundenNr",
                BitmapFactory.decodeResource(context.getResources(), R.drawable.customer)));
        count += 1;


        for (int index = 1; index <= DL.GetDL().getCustomerCount(); index++) {

            File file = new File(Environment.getExternalStorageDirectory(), APP_PATH + "/" + CUSTOMER_DATA_PATH + "/" + index + ".png");

            Customers.add(count, new Customer(count,
                    "Test",
                    "Ein echter Datensatz",
                    BitmapFactory.decodeFile(file.toString())));

            count += 1;

        }


        // TODO MUSS ENTFERNT WERDEN WENN DL FERTIG IST
        mCustomers = Customers;
        return Customers;
    }

    public Customer getCustomer(int Customerid) {

        // TODO FAKEDATEN
        return mCustomers.get(Customerid);

    }

    public ArrayList<Report> GetReports(int CustomerID, Context context) {

        // TODO FAKEDATEN
        ArrayList<Report> Reports = new ArrayList<Report>();

        for (int Index = 0; Index < 50; Index++) {
            Reports.add(Index, new Report(Index, CustomerID, "Report_" + Index));
        }

        return Reports;
    }

    public enum CustomerSaveResult {
        ExternalStorageNotWritable,
        CanNotCreatDirectory,
        Error,
        Success
    }

    // Prüft ob Schreibrechte auf der SD vorhanden sind
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public CustomerSaveResult SaveNewCustomer(Customer customer) {

        try {

            if (!isExternalStorageWritable()) {
                return CustomerSaveResult.ExternalStorageNotWritable;
            }

            File App_Directory = new File(Environment.getExternalStorageDirectory(), APP_PATH);
            if (!App_Directory.exists()) {
                if (!App_Directory.mkdir()) {
                    return CustomerSaveResult.CanNotCreatDirectory;
                }
            }

            File Customer_Directory = new File(Environment.getExternalStorageDirectory(), APP_PATH + "/" + CUSTOMER_DATA_PATH);
            if (!Customer_Directory.exists()) {
                if (!Customer_Directory.mkdir()) {
                    return CustomerSaveResult.CanNotCreatDirectory;
                }
            }

            File file = new File(Environment.getExternalStorageDirectory(), APP_PATH + "/" + CUSTOMER_DATA_PATH + "/" + customer.CustomerID() + ".png");

            OutputStream os = new FileOutputStream(file);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            customer.Logo().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            os.write(byteArray);
            os.close();

            return CustomerSaveResult.Success;

        } catch (IOException e) {

            Log.w("ExternalStorage", "Error writing ", e);
            return CustomerSaveResult.Error;
        }


    }

    public int getCustomerCount() {

        File Customer_Directory = new File(Environment.getExternalStorageDirectory(), APP_PATH + "/" + CUSTOMER_DATA_PATH);
        if (!Customer_Directory.exists()) {
            if (!Customer_Directory.mkdir()) {
                return -1;
            }
        }

        return Customer_Directory.listFiles().length;

    }

}
