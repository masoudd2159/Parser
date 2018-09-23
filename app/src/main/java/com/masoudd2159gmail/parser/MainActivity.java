package com.masoudd2159gmail.parser;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<Flower> flowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FlowerXmlPullParser flowerXmlPullParser = new FlowerXmlPullParser();
        flowers = flowerXmlPullParser.parseXML(MainActivity.this);*/

        FlowerJDOMParser flowerJDOMParser = new FlowerJDOMParser();
        flowers = flowerJDOMParser.parseXML(MainActivity.this);

        ArrayAdapter<Flower> adapter = new ArrayAdapter<Flower>(this, android.R.layout.simple_list_item_1, flowers);
        setListAdapter(adapter);
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
