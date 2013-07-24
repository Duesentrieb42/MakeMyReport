package Entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Vitali on 14.07.13.
 * Stellt einen Report dar
 */
public class Report {

    private int mReportID;
    private int mCustomerID;
    private String mName;

    private Date mCreateTime;
    private Date mChangeTime;

    private List<Report_Entry> mEntries;

    public Report(int ReportID,
                  int CustomorID,
                  String Name,
                  Date CreateTime,
                  Date ChangeDate) {

        mReportID = ReportID;
        mCustomerID = CustomorID;
        mName = Name;
        mCreateTime = CreateTime;
        mChangeTime = ChangeDate;

    }

    // Liefert die interne ID des Reports
    public int ReportID() {
        return mReportID;
    }

    // Liefert die interne ID des Kunden
    public int CustomerID() {
        return mCustomerID;
    }

    // Liefert den Namen des Reports
    public String Name() {
        return mName;
    }

    // Liefert das Erstelldatum
    public Date CreateTime(){
        return mCreateTime;
    }

    // Liefert das Änderungsdatum
    public Date ChangeTime(){
        return mChangeTime;
    }

    // Liefert alle Einträge des Reports
    public List<Report_Entry> Entries() {
        return mEntries;
    }

    // Setzt alle Einträge des Reports
    public void Entries(List<Report_Entry> Entries) {
        mEntries = Entries;
    }

    // Fügt einen Eintrag am Ende der Liste hinzu
    public void AddNewEntryAtEnd() {

        int id = mEntries.size() + 1;
        mEntries.add(id, new Report_Entry(id,0, id, "", null));

    }

    // Fügt einen Eintrag an einer bestimmten Position ein
    public void AddNewEntryAtPosition(int Position) {
        // Ein Eintrag wird mitten drin eingefügt. Alle folgenden Einträge müssen angepasst werden
        throw new UnsupportedOperationException();
    }
}
