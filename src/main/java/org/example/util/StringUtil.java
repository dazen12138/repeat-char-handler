package org.example.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static StringBuilder getStringBuilderOfString(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input.toCharArray());
        return stringBuilder;
    }
}
