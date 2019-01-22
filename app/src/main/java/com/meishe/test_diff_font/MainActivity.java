package com.meishe.test_diff_font;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mTextView2;
    private SpannableStringBuilder mMultiSpanBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);

        mMultiSpanBuilder = new SpannableStringBuilder("");

        CharSequence charSequence1 = "美摄SDK111";
        mMultiSpanBuilder.append(charSequence1);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.YELLOW);

        Typeface font1 = null;
        font1 = Typeface.createFromAsset(getAssets(),"font.ttf");

        mMultiSpanBuilder.setSpan(colorSpan1,
                0,
                charSequence1.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mMultiSpanBuilder.setSpan(new MyTypefaceSpan(font1),
                0,
                charSequence1.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence charSequence2 = "美摄SDK222";
        mMultiSpanBuilder.append(charSequence2);
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.RED);
        mMultiSpanBuilder.setSpan(colorSpan2,
                charSequence1.length(),
                charSequence1.length() + charSequence2.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextView.setText(mMultiSpanBuilder);

        mTextView2.setText("美摄SDK111");
    }
}
