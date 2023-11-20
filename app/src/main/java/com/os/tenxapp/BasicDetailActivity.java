package com.os.tenxapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BasicDetailActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detail);
        imageView = findViewById(R.id.imageView7);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        findViewById(R.id.get_otp).setOnClickListener(view -> {
            View promptView = layoutInflater.inflate(R.layout.dialog_otp_verification, null);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            Button btnAdd1 = promptView.findViewById(R.id.button7);
            btnAdd1.setOnClickListener(v -> alertD.dismiss());
            alertD.setView(promptView);
            alertD.show();
        });
        findViewById(R.id.round_accou).setOnClickListener(view -> {
            View promptView = layoutInflater.inflate(R.layout.dialog_upload_photo, null);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            CardView btnAdd1 = promptView.findViewById(R.id.cardClose);
            btnAdd1.setOnClickListener(v -> alertD.dismiss());
            promptView.findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    alertD.dismiss();
                }
            });
            promptView.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    alertD.dismiss();
                }
            });
            alertD.setView(promptView);
            alertD.show();
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
}
