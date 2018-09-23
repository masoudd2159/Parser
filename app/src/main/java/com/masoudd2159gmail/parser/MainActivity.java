package com.masoudd2159gmail.parser;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<Flower> flowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FlowerXmlPullParser flowerXmlPullParser = new FlowerXmlPullParser();
        flowers = flowerXmlPullParser.parseXML(MainActivity.this);*/

        /*FlowerJDOMParser flowerJDOMParser = new FlowerJDOMParser();
        flowers = flowerJDOMParser.parseXML(MainActivity.this);*/

        InputStream inputStream = getResources().openRawResource(R.raw.flowers_json);
        String jsonString = inputStreamToString(inputStream);
        FlowerJSONParser parser = new FlowerJSONParser();
        flowers = parser.parserJSON(jsonString);

        ArrayAdapter<Flower> adapter = new ArrayAdapter<Flower>(this, android.R.layout.simple_list_item_1, flowers);
        setListAdapter(adapter);
    }

    private String inputStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Flower currentFlower = flowers.get(position);
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("FlowerName", currentFlower.getName());
        intent.putExtra("FlowerPhoto", currentFlower.getPhoto());
        intent.putExtra("FlowerInstructions", currentFlower.getInstructions());

        startActivity(intent);
    }
}
