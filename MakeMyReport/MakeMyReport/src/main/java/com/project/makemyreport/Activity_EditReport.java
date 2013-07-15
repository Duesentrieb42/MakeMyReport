package com.project.makemyreport;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Vitali on 15.07.13.
 */
public class Activity_EditReport extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editreport);
    }
}