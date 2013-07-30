package com.project.makemyreport;

import Adapters.Adapter_MainMenu;
import Adapters.Adapter_Report;
import DAL.DL;
import Entities.Customer;
import Entities.MenuItem;
import Entities.Report;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Vitali on 14.07.13.
 */
public class Activity_Reports extends Activity {

    int mCustomerID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reports);

        int mCustomerID = getIntent().getIntExtra("CustomerID", 0);
        InitMenu(mCustomerID);

    }

    @Override
    protected void onResume() {
        super.onResume();

        int mCustomerID = getIntent().getIntExtra("CustomerID", 0);
        InitCustomerDate(mCustomerID);
        InitReports(mCustomerID);
    }

    private void InitReports(int CustomerID) {

        ArrayList<Report> Reports = DL.GetDL(Activity_Reports.this).GetReports(CustomerID);

        GridView gridReports = (GridView) findViewById(R.id.reports_reports);
        gridReports.setAdapter(new Adapter_Report(Activity_Reports.this, Reports));

        gridReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Report report = (Report) parent.getItemAtPosition(position);
                OnReportClick(report);
            }
        });

        gridReports.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                Report report = (Report) parent.getItemAtPosition(position);
                OnReportLongClick(report);
                return true;
            }
        });
    }

    private void OnReportClick(Report report) {

        Intent intent = new Intent(this, Activity_EditReport.class);

        int args[] = new int[2];
        args[0] = report.CustomerID();
        args[1] = report.ReportID();
        intent.putExtra("args", args);

        startActivity(intent);

    }

    private void OnReportLongClick(Report report) {

        Intent intent = new Intent(this, Activity_ReportOptions.class);

        int args[] = new int[2];
        args[0] = report.CustomerID();
        args[1] = report.ReportID();
        intent.putExtra("args", args);

        startActivity(intent);

    }

    private void InitCustomerDate(final int CustomerID) {

        Customer customer = DL.GetDL(Activity_Reports.this).GetCustomer(CustomerID);

        TextView CustomerName = (TextView) findViewById(R.id.reports_customername);
        TextView CustomerDescription = (TextView) findViewById(R.id.reports_customerdescription);
        ImageView CustomerLogo = (ImageView) findViewById(R.id.reports_customer_logo);

        CustomerName.setText(customer.Name());
        CustomerDescription.setText(customer.Description());
        CustomerLogo.setImageBitmap(customer.Logo());

    }

    private void InitMenu(final int CustomerID) {

        ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
        int count = 0;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_add_report),
                this.getString(R.string.Menu_New_Report),
                this.getString(R.string.Menu_New_Report_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.New_Report,
                true));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_search),
                this.getString(R.string.Menu_Search),
                this.getString(R.string.Menu_Search_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.Search,
                true));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cancel),
                this.getString(R.string.Menu_Delete_Report),
                this.getString(R.string.Menu_Delete_report_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.Delete_Report,
                true));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_settings),
                this.getString(R.string.Menu_Settings),
                this.getString(R.string.Menu_Settings_Description),
                R.layout.home_menuitem,
                MenuItem.MenuType.Settings,
                true));


        ExpandableListView listMainMenu = (ExpandableListView) findViewById(R.id.reports_menu);
        listMainMenu.setAdapter(new Adapter_MainMenu(Activity_Reports.this, MenuItems));

        listMainMenu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                MenuItem item = (MenuItem) expandableListView.getItemAtPosition(i);
                OnItemClick(item.MenuType(),CustomerID);
                return false;
            }
        });


    }

    private void OnItemClick(MenuItem.MenuType menutype, int CustomerID) {

        switch (menutype) {
            case New_Report:

                Intent intent = new Intent(this, Activity_EditReport.class);

                int args[] = new int[2];
                args[0] = CustomerID;
                args[1] = -1;
                intent.putExtra("args", args);

                startActivity(intent);

                break;

            case Search:
                Toast.makeText(getApplicationContext(), "In Bearbeitung.", Toast.LENGTH_SHORT).show();
                break;
            case Settings:
                Toast.makeText(getApplicationContext(), "In Bearbeitung.", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}