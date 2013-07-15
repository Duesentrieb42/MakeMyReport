package com.project.makemyreport;

import Adapters.Adapter_Customer;
import Adapters.Adapter_MainMenu;
import DAL.DL;
import Entities.Customer;
import Entities.MenuItem;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();

        InitMenu();
        InitCustomers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__home, menu);
        return true;
    }

    private void InitCustomers() {

        ArrayList<Customer> Customers = DL.GetDL().GetCustomers(Activity_Home.this);

        GridView gridCustomers = (GridView) findViewById(R.id.home_customers);
        gridCustomers.setAdapter(new Adapter_Customer(Activity_Home.this, Customers));

        gridCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = (Customer) parent.getItemAtPosition(position);
                OnCustomerClick(customer);
            }
        });
    }

    private void InitMenu() {

        MenuItem MenuItems[] = new MenuItem[4];
        int count = 0;

        MenuItems[count] = new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_add_customer),
                this.getString(R.string.Menu_New_Customer),
                this.getString(R.string.Menu_New_Customer_Description),
                MenuItem.MenuType.New_Customer);
        count += 1;

        MenuItems[count] = new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_add_report),
                this.getString(R.string.Menu_New_Report),
                this.getString(R.string.Menu_New_Report_Description),
                MenuItem.MenuType.New_Report);
        count += 1;

        MenuItems[count] = new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_search),
                this.getString(R.string.Menu_Search),
                this.getString(R.string.Menu_Search_Description),
                MenuItem.MenuType.Search);
        count += 1;

        MenuItems[count] = new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.menu_settings),
                this.getString(R.string.Menu_Settings),
                this.getString(R.string.Menu_Settings_Description),
                MenuItem.MenuType.Settings);


        ListView listMainMenu = (ListView) findViewById(R.id.home_menulist);
        listMainMenu.setAdapter(new Adapter_MainMenu(Activity_Home.this, MenuItems));

        listMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void OnCustomerClick(Customer customer) {

        Intent intent = new Intent(this, Activity_Reports.class);

        intent.putExtra("CustomerID", customer.CustomerID());
        intent.putExtra("CustomerName", customer.Name());
        intent.putExtra("CustomerDescription", customer.Description());
        intent.putExtra("CustomerLogo", customer.Logo());

        startActivity(intent);

    }

}
