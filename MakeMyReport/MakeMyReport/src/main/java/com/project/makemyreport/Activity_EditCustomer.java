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

        int CustomerID = getIntent().getIntExtra("CustomerID", -1);

        if (CustomerID>=0){
            initEdit(CustomerID);
        } else{
            initNew();
        }

    }

    public void initNew(){

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

    public void initEdit(final int CustomerID){

        Customer customer = DL.GetDL(this).GetCustomer(CustomerID);

        mLogo=Bitmap.createScaledBitmap(customer.Logo(), 800,800, true);
        ImageView imageView = (ImageView) findViewById(R.id.editcustomer_logo);
        imageView.setImageBitmap(mLogo);

        TextView name = (TextView) findViewById(R.id.editcustomer_name);
        TextView description = (TextView) findViewById(R.id.editcustomer_description);

        name.setText(customer.Name());
        description.setText(customer.Description());

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
                UpdateCustomer(CustomerID);
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
                    mLogo,0))) {

                this.finish();
            } else {
                Toast.makeText(Activity_EditCustomer.this, ":/", Toast.LENGTH_LONG);
            }

        } else {
            Toast.makeText(Activity_EditCustomer.this, "Kein Bild", Toast.LENGTH_LONG);
        }

    }

    private void UpdateCustomer(int CustomerID) {

        if (mLogo != null) {

            TextView name = (TextView) findViewById(R.id.editcustomer_name);
            TextView description = (TextView) findViewById(R.id.editcustomer_description);

            if (DL.GetDL(Activity_EditCustomer.this).UpdateCustomer(new Customer(CustomerID,
                    name.getText().toString(),
                    description.getText().toString(),
                    mLogo,0))) {

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

            mLogo=Bitmap.createScaledBitmap(mLogo, 200,200, true);
            imageView.setImageBitmap(mLogo);
        }
    }


}