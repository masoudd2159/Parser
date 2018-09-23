package com.masoudd2159gmail.parser;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FlowerXmlPullParser {

    private static final String FLOWER_PRODUCT_ID = "productId";
    private static final String FLOWER_CATEGORY = "category";
    private static final String FLOWER_NAME = "name";
    private static final String FLOWER_INSTRUCTIONS = "instructions";
    private static final String FLOWER_PRICE = "price";
    private static final String FLOWER_PHOTO = "photo";

    ArrayList<Flower> flowers;
    private String currantTag = null;
    private Flower currantFlower = null;

    public ArrayList<Flower> parseXML(Context context) {
        flowers = new ArrayList<Flower>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            XmlPullParser parser = factory.newPullParser();
            InputStream inputStream = context.getResources().openRawResource(R.raw.flowers_xml);

            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    handleStartTag(parser.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    handleTextTag(parser.getText());
                } else if (eventType == XmlPullParser.END_TAG) {
                    currantTag = null;
                }
                eventType = parser.next();
            }
            return flowers;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleTextTag(String text) {
        String xmlText = text;

        if (currantFlower != null && currantTag != null) {
            if (currantTag.equals(FLOWER_PRODUCT_ID)) {
                currantFlower.setProductId(Integer.parseInt(xmlText));
            } else if (currantTag.equals(FLOWER_CATEGORY)) {
                currantFlower.setCategory(xmlText);
            } else if (currantTag.equals(FLOWER_NAME)) {
                currantFlower.setName(xmlText);
            } else if (currantTag.equals(FLOWER_INSTRUCTIONS)) {
                currantFlower.setInstructions(xmlText);
            } else if (currantTag.equals(FLOWER_PRICE)) {
                currantFlower.setPrice(Double.parseDouble(xmlText));
            } else if (currantTag.equals(FLOWER_PHOTO)) {
                currantFlower.setPhoto(xmlText);
            }

        }
    }

    private void handleStartTag(String name) {
        if (name.equals("product")) {
            currantFlower = new Flower();
            flowers.add(currantFlower);
        } else {
            currantTag = name;
        }
    }
}

