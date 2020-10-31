package com.example.gomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ValidateActivity extends AppCompatActivity {

    boolean hasCard;
    boolean hasOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        ImageView card_photo = findViewById(R.id.owner_photo);
        ImageView owner_photo = findViewById(R.id.owner_photo);
        card_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });
        owner_photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });
        Button submit = findViewById(R.id.submit_btn);
        Button back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!hasOwner||!hasCard)Toast.makeText(ValidateActivity.this, "", Toast.LENGTH_LONG).show();
                else {

                }
            }
        });

    }

}
