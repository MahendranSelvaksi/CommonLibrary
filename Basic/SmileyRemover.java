package com.cds.foodordering.model.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Mindmade technologies
 */

public class SmileyRemover implements InputFilter {

    // this method disables the smiley on clicking edittext(removes smileys from keypad)
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            int type = Character.getType(source.charAt(i));
            if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                return "";
            }
        }
        return null;
    }
}
