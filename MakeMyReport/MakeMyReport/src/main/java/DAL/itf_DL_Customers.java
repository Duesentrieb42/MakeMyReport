package DAL;

import java.util.ArrayList;

import Entities.Column;
import Entities.Customer;

/**
 * Created by Kristina on 22.07.13.
 */
public interface itf_DL_Customers {

    ArrayList<Customer> GetCustomers();
    Customer GetCustomer (int CustomerID);
    boolean SaveCustomer(Customer customer);
    boolean UpdateCustomer(Customer customer);
    boolean DeleteCustomer(int CustomerID);
    int GetReportCount(int CustomerID);

    public static class CustomerTable{

        public static String GetCreateString (){
            return "CREATE TABLE " + TableName + "("
                    + CustomerID.Create() + ","
                    + CustomerName.Create() + ","
                    + CustomerDescription.Create()+ ","
                    + CustomerLogo.Create() + ")";
        }

        public static String TableName =  "tblReports";
        public static Column CustomerID = new Column("ReportID", "INTEGER PRIMARY KEY");
        public static Column CustomerName = new Column("Name","Text");
        public static Column CustomerDescription = new Column("Description","Text");
        public static Column CustomerLogo =new Column("Logo", "BLOB");

    }
}


