package com.project.makemyreport;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import DAL.DL;
import Entities.Report;

/**
 * Created by Kristina on 30.07.13.
 */
public class Activity_ReportOptions extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_report_options);

        int args[] = new int[2];
        args = getIntent().getIntArrayExtra("args");

        final int CustomerID = args[0];
        final int ReportsID = args[1];

        final Report report = DL.GetDL(this).GetReport(ReportsID);

        View delete = (View)findViewById(R.id.report_option_button_delete);
        View cancel = (View)findViewById(R.id.report_option_button_cancel);
        TextView name = (TextView)findViewById(R.id.report_option_name);

        name.setText(report.Name());
        final Context context = this;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DL.GetDL(context).DeleteReport(ReportsID);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}