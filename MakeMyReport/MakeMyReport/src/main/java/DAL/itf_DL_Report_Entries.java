package DAL;

import java.util.ArrayList;

import Entities.Column;
import Entities.Customer;
import Entities.Report;
import Entities.Report_Entry;

/**
 * Created by Kristina on 22.07.13.
 */
public interface itf_DL_Report_Entries {

    ArrayList<Report_Entry> GetReportEntries(int ReportID);
    Customer GetReportEntry(int ReportEntryID);
    boolean SaveReportEntry(Report_Entry ReportEntry);
    boolean UpdateReportEntry(Report_Entry ReportEntry);
    boolean DeleteReportEntry(int ReportEntryID);

    public static class ReportEntryTable{

        public static String GetCreateString (){
            return "CREATE TABLE " + TableName + "("
                    + ReportEntryID.Create() +","
                    + ReportEntryText.Create()+","
                    + ReportEntryImage.Create()+")";
        }

        public static String TableName =  "tblReportEntries";
        public static Column ReportEntryID = new Column("ReportEntryID", "INTEGER PRIMARY KEY");
        public static Column ReportEntryText = new Column("EntryText","Text");
        public static Column ReportEntryImage =new Column("Image","BLOB");

    }
}


