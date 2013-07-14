package Entities;

import android.graphics.Bitmap;

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

}
