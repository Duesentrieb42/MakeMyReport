package com.project.makemyreport;

import Adapters.Adapter_Customer;
import DAL.DL;
import Entities.Customer;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__home, menu);
        return true;
    }

    private void Init() {

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

    private void OnCustomerClick(Customer customer) {

        Intent intent = new Intent(this, Activity_Reports.class);

        intent.putExtra("CustomerID", customer.CustomerID());
        intent.putExtra("CustomerName", customer.Name());
        intent.putExtra("CustomerDescription", customer.Description());
        intent.putExtra("CustomerLogo", customer.Logo());

        startActivity(intent);

    }

}
