package Entities;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Vitali on 14.07.13.
 * Stellt einen Report Eintrag dar
 */
public class Report_Entry {

    private int mID;
    private int mOrderNo;
    private String mEntryDescription;
    private Bitmap mEntryImage;

    public Report_Entry(int id,
                        int OrderNo,
                        String EntryDescription,
                        Bitmap EntryImage) {

        mID = id;
        mOrderNo = OrderNo;
        mEntryDescription = EntryDescription;
        mEntryImage = EntryImage;
    }

    // Liefert die interne ID des Eintrages
    public int ID() {
        return mID;
    }

    // Liefert die Position im Report des Eintrages
    public int OrderNo() {
        return mOrderNo;
    }

    // Liefert den Text des Eintrages
    public String EntryDescription() {
        return mEntryDescription;
    }

    // Setzt den Text des Eintrages
    public void EntryDescription(String EntryDescription) {
        mEntryDescription = EntryDescription;
    }

    // Liefert das Bild des Eintrages
    public Bitmap EntryImage() {
        return mEntryImage;
    }

    // Setzt das Bild des Eintrages
    public void EntryImage(Bitmap EntryImage) {
        mEntryImage = EntryImage;
    }
}
