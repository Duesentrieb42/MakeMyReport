package com.project.makemyreport;

import DAL.DL;
import Entities.Customer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vitali on 16.07.13.
 */
public class Activity_EditCustomer extends Activity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private Bitmap mLogo = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editcustomer);


        TextView pickimage = (TextView) findViewById(R.id.editcustomer_pickimage);
        pickimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        View cancel = (View) findViewById(R.id.editcustomer_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        View save = (View) findViewById(R.id.editcustomer_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveCustomer();
            }
        });
    }

    private void SaveCustomer() {

        if (mLogo != null) {

            TextView name = (TextView) findViewById(R.id.editcustomer_name);
            TextView description = (TextView) findViewById(R.id.editcustomer_name);

            if (DL.GetDL(Activity_EditCustomer.this).SaveCustomer(new Customer(-1,
                    name.getText().toString(),
                    description.getText().toString(),
                    mLogo)) == DL.SaveResult.Success) {

                this.finish();
            } else {
                Toast.makeText(Activity_EditCustomer.this, ":/", Toast.LENGTH_LONG);
            }

        } else {
            Toast.makeText(Activity_EditCustomer.this, "Kein Bild", Toast.LENGTH_LONG);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.editcustomer_logo);
            mLogo = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(mLogo);
        }
    }


}