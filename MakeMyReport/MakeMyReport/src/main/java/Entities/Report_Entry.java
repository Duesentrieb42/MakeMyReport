package Entities;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Vitali on 14.07.13.
 * Stellt einen Report Eintrag dar
 */
public class Report_Entry {

    private int mID;
    private int mReportID;
    private int mOrderNo;
    private String mEntryDescription;
    private String mEntryImagePath;

    public Report_Entry(int id,
                        int ReportID,
                        int OrderNo,
                        String EntryDescription,
                        String EntryImagePath) {

        mID = id;
        mReportID = ReportID;
        mOrderNo = OrderNo;
        mEntryDescription = EntryDescription;
        mEntryImagePath = EntryImagePath;
    }

    // Liefert die interne ID des Eintrages
    public int ID() {
        return mID;
    }

    // Liefert die interne ID des Berichtes
    public int ReportID(){
        return mReportID;
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
    public String EntryImagePath() {
        return mEntryImagePath;
    }

    // Setzt das Bild des Eintrages
    public void EntryImagePath(String EntryImagePath) {
        mEntryImagePath = EntryImagePath;
    }
}
