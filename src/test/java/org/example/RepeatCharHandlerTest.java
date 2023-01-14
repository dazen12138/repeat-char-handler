package org.example;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class RepeatCharHandlerTest {

    private final RepeatCharHandler repeatCharacter = new RepeatCharHandler();

    @Test
    public void shouldRemoveAllRepeatAlpha() {
        assertEquals("d", repeatCharacter.clear("aabcccbbad"));
        assertEquals("abbad", repeatCharacter.clear("abcccbad"));
        assertEquals("d", repeatCharacter.clear("aabccccbbad"));
        assertEquals("", repeatCharacter.clear("aabbbaccc"));
        assertEquals("", repeatCharacter.clear("cccccc"));
        assertEquals("bc", repeatCharacter.clear("aaccbbbccabc"));
        assertEquals("caab", repeatCharacter.clear("caabbbaaab"));
    }

    @Test
    public void shouldRemoveAllRepeatAlphaAndReplaceToBeforeAlpha() {
        assertEquals("d", repeatCharacter.clearAndReplace("aabcccbbad"));
        assertEquals("d", repeatCharacter.clearAndReplace("abcccbad"));
        assertEquals("d", repeatCharacter.clearAndReplace("aabccccbbad"));
        assertEquals("aabb", repeatCharacter.clearAndReplace("aabccc"));
        assertEquals("b", repeatCharacter.clearAndReplace("ccc"));
        assertEquals("aaccaccabc", repeatCharacter.clearAndReplace("aaccbbbccabc"));
        assertEquals("cb", repeatCharacter.clearAndReplace("caabbbaaab"));
        assertEquals("aababcbb", repeatCharacter.clearAndReplace("aacccabdddbb"));
        assertEquals("zzw", repeatCharacter.clearAndReplace("zzxxzzzyyabbba"));
    }
}
