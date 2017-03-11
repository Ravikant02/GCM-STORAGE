package com.example.ravikant.gcm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://gcmfirebaseproject-22ce5.appspot.com/");
        StorageReference storageRef = storage.getReference();
        StorageReference islandRef = storageRef.child("Profile pic.jpg");
        final ImageView imageView = (ImageView) findViewById(R.id.image);

        /*** THIS IS TO GET IMAGE FROM STORAGE USING URI AND LOAD IT TO IMAGE VIEW ***/
        /*islandRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("TAG", uri.toString());
                Picasso.with(Main2Activity.this).load(uri).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/

        /*** THIS IS TO LOAD IMAGE TO IMAGE VIEW DIRECTLY FROM REFERENCE ***/
       /* Glide.with(this *//* context *//*)
                .using(new FirebaseImageLoader())
                .load(islandRef)
                .into(imageView);*/

        /*** THIS IS TO DOWNLOAD IMAGE TO MEMORY ***/
       final long ONE_MEGABYTE = 2048 * 2048;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 500, 500, false));
                // Data for "images/island.jpg" is returns, use this as needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("TAG", exception.getMessage());
            }
        });
    }
}
