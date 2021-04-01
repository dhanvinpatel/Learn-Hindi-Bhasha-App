package com.bhasha.learnhindibhasha.models;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * HindiTextView class displays the text in Devanagari font.
 */
public class HindiTextView extends androidx.appcompat.widget.AppCompatTextView {
    public HindiTextView(@NonNull Context context) {
        super(context);
        hindiTypeface(context);
    }

    public HindiTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        hindiTypeface(context);
    }

    public HindiTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        hindiTypeface(context);
    }

    private void hindiTypeface(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "NotoSans-Regular.ttf");
        this.setTypeface(typeface);
    }
}
