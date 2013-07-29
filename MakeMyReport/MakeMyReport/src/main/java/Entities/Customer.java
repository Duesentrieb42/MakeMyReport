package Entities;

import android.graphics.Bitmap;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Created by Vitali on 14.07.13.
 * Stellt einen Kunden dar
 */
public class Customer {

    private int mCustomerID;
    private String mName;
    private String mDescription;
    private Bitmap mLogo;

    public Customer(int CustomerID,
                    String Name,
                    String Description,
                    Bitmap Logo) {

        mCustomerID = CustomerID;
        mName = Name;
        mDescription = Description;
        mLogo = Logo;

    }

    // Liefert eine interene ID
    public int CustomerID() {
        return mCustomerID;
    }

    // Liefert den Namen des Kunden
    public String Name() {
        return mName;
    }

    // Liefert eine Beschreibung zu dem Kunden
    public String Description() {
        return mDescription;
    }

    // Liefert das Kundenlogo
    public Bitmap Logo() {
        return mLogo;
    }


    public static class Adapter_Customer_EventArgs extends EventObject {
        private int mCustomerID;

        public int CustomerID(){
            return mCustomerID;
        }

        public Adapter_Customer_EventArgs(Object source) {
            super(source);
            mCustomerID = 0;
        }
        public Adapter_Customer_EventArgs(Object source, int CustomerID) {
            super(source);
            mCustomerID = CustomerID;
        }
    }

    public interface EditCustomerListener extends EventListener {
        void CustomerEdit(Adapter_Customer_EventArgs e);
    }

    public interface CustomerEventgenerator {
        void addEditCustomerListener(EditCustomerListener listener);
    }

}
