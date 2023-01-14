package org.example.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CharUtil {

    public char getBeforeAlpha(char currentChar) {
        return (char) (currentChar - 1);
    }
}
