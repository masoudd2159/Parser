package com.masoudd2159gmail.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlowerJSONParser {

    private static final String FLOWER_TAG = "product";

    private static final String FLOWER_PRODUCT_ID = "productId";
    private static final String FLOWER_CATEGORY = "category";
    private static final String FLOWER_NAME = "name";
    private static final String FLOWER_INSTRUCTIONS = "instructions";
    private static final String FLOWER_PRICE = "price";
    private static final String FLOWER_PHOTO = "photo";

    public ArrayList<Flower> parserJSON(String jsonString) {
        ArrayList<Flower> flowers = new ArrayList<Flower>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Flower flower = new Flower();

                flower.setProductId(jsonObject.getInt(FLOWER_PRODUCT_ID));
                flower.setCategory(jsonObject.getString(FLOWER_CATEGORY));
                flower.setName(jsonObject.getString(FLOWER_NAME));
                flower.setInstructions(jsonObject.getString(FLOWER_INSTRUCTIONS));
                flower.setPrice(jsonObject.getDouble(FLOWER_PRICE));
                flower.setPhoto(jsonObject.getString(FLOWER_PHOTO));

                flowers.add(flower);
            }

            return flowers;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
