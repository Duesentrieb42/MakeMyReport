package com.project.makemyreport;

import Adapters.Adapter_MainMenu;
import DAL.DL;
import Entities.Customer;
import Entities.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Vitali on 14.07.13.
 */
public class Activity_Reports extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        int CustomerID = getIntent().getIntExtra("CustomerID", 0);

        InitMenu();
        InitCustomerDate(CustomerID);


    }

    private void InitReports(int CustomerID) {

    }

    private void InitCustomerDate(int CustomerID) {

        Customer customer = DL.GetDL().getCustomer(CustomerID);

        TextView CustomerName = (TextView) findViewById(R.id.reports_customername);
        TextView CustomerDescription = (TextView) findViewById(R.id.reports_customerdescription);
        ImageView CustomerLogo = (ImageView) findViewById(R.id.reports_customer_logo);

        CustomerName.setText(customer.Name());
        CustomerDescription.setText(customer.Description());
        CustomerLogo.setImageBitmap(customer.Logo());

    }

    private void InitMenu() {

        ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
        int count = 0;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_add_report),
                this.getString(R.string.Menu_New_Report),
                this.getString(R.string.Menu_New_Report_Description),
                MenuItem.MenuType.New_Report));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_search),
                this.getString(R.string.Menu_Search),
                this.getString(R.string.Menu_Search_Description),
                MenuItem.MenuType.Search));
        count += 1;

        MenuItems.add(count, new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_settings),
                this.getString(R.string.Menu_Settings),
                this.getString(R.string.Menu_Settings_Description),
                MenuItem.MenuType.Settings));


        ListView listMainMenu = (ListView) findViewById(R.id.home_menulist);
        listMainMenu.setAdapter(new Adapter_MainMenu(Activity_Reports.this, MenuItems));

        listMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem item = (MenuItem) parent.getItemAtPosition(position);
                OnItemClick(item.MenuType());
            }
        });


    }

    private void OnItemClick(MenuItem.MenuType menutype) {

        switch (menutype) {
            case New_Report:

                Intent intent = new Intent(this, Activity_EditReport.class);
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