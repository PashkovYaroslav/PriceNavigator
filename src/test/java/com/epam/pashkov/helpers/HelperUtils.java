package com.epam.pashkov.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yaroslav on 01.06.2015.
 */
public class HelperUtils {
    public static double selectPriceOnly(String price) {
        price = price.replace("грн", "").replace(" ", "");
        return Double.valueOf(price);
    }

    public static String nameOfProduct(String name) {
        Pattern pattern = Pattern.compile("([^/]+)");
        Matcher matcher = pattern.matcher(name);
        String correctName = "";
        while (matcher.find()) {
            correctName = matcher.group(1).replace("&amp;", "&").trim();
            break;
        }
        return correctName;
    }

    public static String descriptionField(String description){
        String correctDescription = description.replace("  "," ");
        return correctDescription;
    }
}
