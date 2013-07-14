package Entities;

import android.graphics.Bitmap;

/**
 * Created by Vitali on 14.07.13.
 * Stellt einen Kunden dar
 */
public class Customer {

    private int mID;
    private String mName;
    private String mDescription;
    private Bitmap mLogo;

    public Customer(int id,
                    String name,
                    String description,
                    Bitmap logo) {

        mID = id;
        mName = name;
        mDescription = description;
        mLogo = logo;

    }

    // Liefert eine interene ID
    public int ID() {
        return mID;
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
