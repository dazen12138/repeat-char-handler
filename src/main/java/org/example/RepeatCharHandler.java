package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.Setter;
import org.example.model.RepeatCharPosition;


import static org.example.util.CharUtil.getBeforeAlpha;
import static org.example.util.StringUtil.getStringBuilderOfString;

@Setter
public class RepeatCharHandler {

    private final String placeholder = "$";

    private int repeatThreshold = 3;

    public String clear(@NonNull String input) {
        return handle(input, false);
    }

    public String clearAndReplace(@NonNull String input) {
        return handle(input, true);
    }

    public String handle(@NonNull String input, boolean replaceToBeforeAlpha) {
        StringBuilder stringBuilder = getStringBuilderOfString(input);

        while (true) {
            List<RepeatCharPosition> repeatCharPositions = findAllRepeatCharPositions(stringBuilder);

            if (repeatCharPositions.isEmpty()) {
                break;
            }

            for (RepeatCharPosition repeatCharPosition : repeatCharPositions) {
                replaceRepeatCharToPlaceholder(stringBuilder, repeatCharPosition, replaceToBeforeAlpha);
            }

            clearAllPlaceholder(stringBuilder);
        }

        return stringBuilder.toString();
    }

    private List<RepeatCharPosition> findAllRepeatCharPositions(StringBuilder stringBuilder) {
        List<RepeatCharPosition> repeatCharPositions = new ArrayList<>();

        for (int index = 0; index < stringBuilder.length(); index++) {
            char currentChar = stringBuilder.charAt(index);
            int repeatCount = 1;
            int lastIndex = index + 1;

            for (; lastIndex < stringBuilder.length(); lastIndex++) {
                char nextChar = stringBuilder.charAt(lastIndex);

                if (currentChar == nextChar) {
                    repeatCount++;
                } else {
                    break;
                }
            }

            if (isGreaterThanRepeatThreshold(repeatCount)) {
                repeatCharPositions.add(new RepeatCharPosition(currentChar, index, lastIndex - 1));
            }
        }

        return repeatCharPositions;
    }

    private void replaceRepeatCharToPlaceholder(StringBuilder stringBuilder, RepeatCharPosition repeatCharPosition, boolean replaceToBeforeAlpha) {
        for (int i = repeatCharPosition.getStartIndex(); i <= repeatCharPosition.getEndIndex(); i++) {
            stringBuilder.replace(i, i + 1, placeholder);
        }

        if (isAvailableReplaceToBeforeAlpha(replaceToBeforeAlpha, repeatCharPosition)) {
            stringBuilder.replace(repeatCharPosition.getStartIndex(),
                    repeatCharPosition.getStartIndex() + 1,
                    String.valueOf(getBeforeAlpha(repeatCharPosition.getCharacter())));
        }
    }

    private void clearAllPlaceholder(StringBuilder stringBuilder) {
        while (true) {
            int placeholderPosition = stringBuilder.indexOf(placeholder);
            if (placeholderPosition < 0) {
                break;
            }

            stringBuilder.deleteCharAt(placeholderPosition);
        }
    }


    private boolean isAvailableReplaceToBeforeAlpha(boolean needReplace, RepeatCharPosition repeatCharPosition) {
        return needReplace && repeatCharPosition.getCharacter() > 'a';
    }

    private boolean isGreaterThanRepeatThreshold(int repeatCount) {
        return repeatCount >= repeatThreshold;
    }
}
