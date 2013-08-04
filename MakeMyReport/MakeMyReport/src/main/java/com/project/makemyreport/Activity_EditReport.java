package com.project.makemyreport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.View;
import android.widget.ImageView;
import java.util.Date;
import DAL.DL;
import Entities.Report;

/**
 * Created by Vitali on 15.07.13.
 */
public class Activity_EditReport extends Activity {

    ImageView mIssuePicture;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editreport);

        // -1 = Neuer Report ansonsten interne ID des Reports

        int args[] = new int[2];
        args = getIntent().getIntArrayExtra("args");

        int CustomerID = args[0];
        int ReportsID = args[1];

        if (ReportsID < 0){
            Date date  = new Date();
            String name = date.toString();

            DL.GetDL(this).SaveReport(new Report(ReportsID,CustomerID,name,date,date));
        }

        InitIssue();
        InitNavigationButtons(null);
    }

    private void InitNavigationButtons(Report report){

    }

    private void InitIssue(){

        mIssuePicture = (ImageView) findViewById(R.id.issuePicture);

        mIssuePicture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(1);
            }

        });
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);
    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        mIssuePicture.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            handleSmallCameraPhoto(data);
        }
    }

}