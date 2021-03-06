package com.project.makemyreport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import DAL.DL;
import Entities.Report;
import Entities.Report_Entry;

/**
 * Created by Vitali on 15.07.13.
 */
public class Activity_EditReport extends Activity {

    ImageView mEntryPicture;
    TextView mEntryText;
    List<Report_Entry> mReport_Entries;

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

        mReport_Entries = DL.GetDL(this).GetReportEntries(ReportsID);

        InitEntry();
        InitNavigationButtons(null);
    }

    private void InitNavigationButtons(Report report){

        ImageButton btnFirstItem  = (ImageButton) findViewById(R.id.btnFirstItem);

        btnFirstItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirstItem_Click();
            }

        });

        ImageButton btnPreviousItem  = (ImageButton) findViewById(R.id.btnPreviousItem);

        btnPreviousItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                PreviousItem_Click();
            }

        });

        ImageButton btnNextItem  = (ImageButton) findViewById(R.id.btnNextItem);

        btnNextItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                NextItem_Click();
            }

        });

        ImageButton btnLastItem  = (ImageButton) findViewById(R.id.btnLastItem);

        btnLastItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LastItem_Click();
            }

        });

    }

    private void InitEntry(){

        mEntryPicture = (ImageView) findViewById(R.id.EntryPicture);
        mEntryText = (TextView) findViewById(R.id.EntyText);

        mEntryPicture.setOnClickListener(new View.OnClickListener(){

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
        mEntryPicture.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            handleSmallCameraPhoto(data);
        }
    }

    private void SetEntry(Report_Entry entry){

        mEntryPicture.setImageBitmap(BitmapFactory.decodeFile(entry.EntryImagePath()));
        mEntryText.setText(entry.EntryDescription());

    }

    private void FirstItem_Click(){
        Toast.makeText(getApplicationContext(), "FirstItem Bearbeitung.", Toast.LENGTH_SHORT).show();
    }

    private void PreviousItem_Click(){
        Toast.makeText(getApplicationContext(), "PreviousItem Bearbeitung.", Toast.LENGTH_SHORT).show();
    }

    private void NextItem_Click(){
        Toast.makeText(getApplicationContext(), "NextItem Bearbeitung.", Toast.LENGTH_SHORT).show();
    }

    private void LastItem_Click(){
        Toast.makeText(getApplicationContext(), "LastItem Bearbeitung.", Toast.LENGTH_SHORT).show();
    }

}

