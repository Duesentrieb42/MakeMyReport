package com.project.makemyreport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vitali on 14.07.13.
 */
public class Activity_Reports extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        TextView CustomerName = (TextView) findViewById(R.id.reports_customername);
        TextView CustomerDescription = (TextView) findViewById(R.id.reports_customername);

        CustomerName.setText(getIntent().getStringExtra("CustomerName"));
        CustomerDescription.setText(getIntent().getStringExtra("CustomerDescription"));

    }
}