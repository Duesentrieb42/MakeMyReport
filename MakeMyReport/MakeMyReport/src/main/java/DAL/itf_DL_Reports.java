package DAL;

import java.util.ArrayList;

import Entities.Column;
import Entities.Customer;
import Entities.Report;

/**
 * Created by Kristina on 22.07.13.
 */
public interface itf_DL_Reports {

    ArrayList<Report> GetReports(int CustomerID);
    Customer GetReport(int ReportsID);
    boolean SaveReport(Report report);
    boolean UpdateReport(Report report);
    boolean DeleteReport(int ReportID);

    public static class ReportTable{

        public static String GetCreateString (){
            return "CREATE TABLE " + TableName + "("
                    + ReportID.Create() +","
                    + ReportName.Create()+","
                    + ReportCreateDate.Create()+","
                    + ReportChangeeDate.Create()+")";
        }

        public static String TableName =  "tblCustomers";
        public static Column ReportID = new Column("CustomerID", "INTEGER PRIMARY KEY");
        public static Column ReportName = new Column("Name","Text");
        public static Column ReportCreateDate =new Column("CreateDate","DATETIME");
        public static Column ReportChangeeDate =new Column("ChangeDate","DATETIME");

    }
}


