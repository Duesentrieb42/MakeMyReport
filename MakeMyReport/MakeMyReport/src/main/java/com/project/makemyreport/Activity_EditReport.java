package com.project.makemyreport;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.View;
import android.widget.ImageView;

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
        int ReportID = getIntent().getIntExtra("ReportID", -1);

        InitIssue();

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