package com.masoudd2159gmail.parser;

import android.content.Context;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerJDOMParser {

    private static final String FLOWER_TAG = "product";

    private static final String FLOWER_PRODUCT_ID = "productId";
    private static final String FLOWER_CATEGORY = "category";
    private static final String FLOWER_NAME = "name";
    private static final String FLOWER_INSTRUCTIONS = "instructions";
    private static final String FLOWER_PRICE = "price";
    private static final String FLOWER_PHOTO = "photo";

    public ArrayList<Flower> parseXML(Context context) {
        ArrayList<Flower> flowers = new ArrayList<Flower>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.flowers);

        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(inputStream);
            Element rootNode = document.getRootElement();
            List<Element> nodes = rootNode.getChildren(FLOWER_TAG);

            for (Element node : nodes) {
                Flower object = new Flower();

                object.setProductId(Integer.parseInt(node.getChildText(FLOWER_PRODUCT_ID)));
                object.setCategory(node.getChildText(FLOWER_CATEGORY));
                object.setName(node.getChildText(FLOWER_NAME));
                object.setInstructions(node.getChildText(FLOWER_INSTRUCTIONS));
                object.setPhoto(node.getChildText(FLOWER_PHOTO));
                object.setPrice(Double.parseDouble(node.getChildText(FLOWER_PRICE)));

                flowers.add(object);
            }
            return flowers;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

