package com.project.makemyreport;

import Adapters.Adapter_Customer;
import Adapters.Adapter_MainMenu;
import Adapters.Adapter_Report;
import DAL.DL;
import Entities.Customer;
import Entities.MenuItem;
import Entities.Report;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Home extends Activity implements Customer.EditCustomerListener, Customer.DeleteCustomerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        InitMenu();
    }


    @Override
    protected void onResume() {
        super.onResume();

        InitCustomers();
        InitLastReports();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__home, menu);
        return true;
    }

    private void InitLastReports(){
        ArrayList<Report> Reports = DL.GetDL(this).GetLastReports(10);

        GridView gridReports = (GridView) findViewById(R.id.home_lastreports);
        gridReports.setAdapter(new Adapter_Report(this, Reports));

        gridReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Report report = (Report) parent.getItemAtPosition(position);
                OnReportClick(report);
            }
        });
    }

    private void InitCustomers() {

        ArrayList<Customer> Customers = DL.GetDL(Activity_Home.this).GetCustomers();

        GridView gridCustomers = (GridView) findViewById(R.id.home_customers);
        Adapter_Customer adapter = new Adapter_Customer(Activity_Home.this, Customers);

        adapter.addEditCustomerListener(this);
        adapter.addDeleteCustomerListener(this);
        gridCustomers.setAdapter(adapter);

        gridCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = (Customer) parent.getItemAtPosition(position);
                OnCustomerClick(customer);
            }
        });
    }

    private void InitMenu() {

        ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
        int count = 0;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_add_customer),
                this.getString(R.string.Menu_New_Customer),
                this.getString(R.string.Menu_New_Customer_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.New_Customer,
                true));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_settings),
                this.getString(R.string.Menu_Settings),
                this.getString(R.string.Menu_Settings_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.Settings,
                true));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_about),
                this.getString(R.string.Menu_About),
                this.getString(R.string.Menu_About_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.About,
                true));

        ExpandableListView listMainMenu = (ExpandableListView) findViewById(R.id.home_menulist);
        listMainMenu.setAdapter(new Adapter_MainMenu(Activity_Home.this, MenuItems));

        listMainMenu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                MenuItem item = (MenuItem) expandableListView.getItemAtPosition(i);
                OnItemClick(item.MenuType());
                return false;
            }
        });

        listMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                OnItemClick(item.MenuType());
            }
        });

    }

    private void OnCustomerClick(Customer customer) {

        Intent intent = new Intent(this, Activity_Reports.class);

        intent.putExtra("CustomerID", customer.CustomerID());

        startActivity(intent);

    }

    private void OnItemClick(MenuItem.MenuType menutype) {

        Intent intent;

        switch (menutype) {
            case New_Customer:


                intent = new Intent(this, Activity_EditCustomer.class);
                startActivity(intent);

                break;

            case New_Report:

                intent = new Intent(this, Activity_EditReport.class);
                startActivity(intent);

                break;

            case Search:
                Toast.makeText(getApplicationContext(), "In Bearbeitung.", Toast.LENGTH_SHORT).show();
                break;
            case Settings:
                Toast.makeText(getApplicationContext(), "In Bearbeitung.", Toast.LENGTH_SHORT).show();
                break;
            case About:
                Toast.makeText(getApplicationContext(), "In Bearbeitung.", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void OnReportClick(Report report) {

        Intent intent = new Intent(this, Activity_EditReport.class);

        int args[] = new int[2];
        args[0] = report.CustomerID();
        args[1] = report.ReportID();
        intent.putExtra("args", args);

        startActivity(intent);

    }

    @Override
    public void CustomerEdit(Customer.Adapter_Customer_EventArgs e) {
        Intent intent = new Intent(this, Activity_EditCustomer.class);

        intent.putExtra("CustomerID",e.CustomerID());
        startActivity(intent);
    }

    @Override
    public void CustomerDelete(Customer.Adapter_Customer_EventArgs e) {
        DL.GetDL(this).DeleteCustomer(e.CustomerID());
        DL.GetDL(this).DeleteReports(e.CustomerID());
        InitLastReports();
    }
}
