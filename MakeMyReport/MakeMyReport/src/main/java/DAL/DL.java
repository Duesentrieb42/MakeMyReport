package DAL;

import Entities.Customer;
import android.content.Context;
import android.graphics.BitmapFactory;
import com.project.makemyreport.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali on 14.07.13.
 */
public class DL {

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

        // TODO MUSS ENTFERNT WERDEN WENN DL FERTIG IST
        mCustomers = Customers;
        return Customers;
    }

    public Customer getCustomer(int Customerid) {

        // TODO FAKEDATEN
        return mCustomers.get(Customerid);

    }

}
