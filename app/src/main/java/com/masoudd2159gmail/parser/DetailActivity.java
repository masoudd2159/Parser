package com.masoudd2159gmail.parser;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    String flowerName = null;
    String flowerPhoto = null;
    String flowerInstructions = null;

    TextView textViewName;
    TextView textViewInstructions;
    ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.containsKey("FlowerName")) {
                flowerName = bundle.getString("FlowerName");
                textViewName.setText(flowerName);
            }
            if (bundle.containsKey("FlowerPhoto")) {
                flowerPhoto = bundle.getString("FlowerPhoto");
                String fileName = flowerPhoto.substring(0, flowerPhoto.indexOf('.'));
                int resID = getResources().getIdentifier(getApplicationContext().getPackageName() + ":drawable/" + fileName, null, null);
                imageViewPhoto.setImageBitmap(BitmapFactory.decodeResource(getResources(), resID));
            }
            if (bundle.containsKey("FlowerInstructions")) {
                flowerInstructions = bundle.getString("FlowerInstructions");
                textViewInstructions.setText(flowerInstructions);
            }
        }
    }

    private void init() {
        textViewName = findViewById(R.id.textView_Name);
        textViewInstructions = findViewById(R.id.textView_Instructions);
        imageViewPhoto = findViewById(R.id.imageView_Photo);
    }
}
