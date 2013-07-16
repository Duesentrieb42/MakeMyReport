package com.project.makemyreport;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Vitali on 15.07.13.
 */
public class Activity_EditReport extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editreport);

        // -1 = Neuer Report ansonsten interne ID des Reports
        int ReportID = getIntent().getIntExtra("ReportID", -1);

        TextView reportid = (TextView) findViewById(R.id.editreport_reportid);
        reportid.setText("Report_ID" + ReportID);


    }
}