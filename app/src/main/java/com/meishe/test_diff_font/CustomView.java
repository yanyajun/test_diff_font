package com.meishe.test_diff_font;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CustomView extends View {
    private final String TAG = "CustomView";
    private Paint mPaint;
    private TextPaint mTextPaint;
    private Bitmap mCanvasBitmap;
    private Typeface mTypeface;

    private SpannableStringBuilder mMultiSpanBuilder;
    private int mTextLength = 0;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.RED);
//        mPaint.setTextSize(70);
//        mPaint.setTypeface(Typeface.DEFAULT);
//        Typeface font = null;

        mTypeface = Typeface.createFromAsset(context.getAssets(),"font.ttf");

        mTextPaint =new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(Typeface.DEFAULT);
        mTextPaint.setTextSize(80);
        mTextPaint.setColor(Color.GREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextPaint.setLetterSpacing(0.03f);
        }


        mMultiSpanBuilder = new SpannableStringBuilder("");

        CharSequence charSequence1 = "美摄SDK111";
        mTextLength += charSequence1.length();
        mMultiSpanBuilder.append(charSequence1);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.BLUE);
        mMultiSpanBuilder.setSpan(colorSpan1,
                0,
                mTextLength,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mMultiSpanBuilder.setSpan(new MyTypefaceSpan(mTypeface),
                0,
                charSequence1.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(150);
        mMultiSpanBuilder.setSpan(sizeSpan,
                0,
                charSequence1.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence charSequence2 = "美摄SDK222";
        mTextLength += charSequence2.length();
        mMultiSpanBuilder.append(charSequence2);
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.RED);
        mMultiSpanBuilder.setSpan(colorSpan2,
                charSequence1.length(),
                mTextLength,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        StaticLayout staticLayout = new StaticLayout(mMultiSpanBuilder.subSequence(0, mTextLength),mTextPaint,ScreenUtils.getScreenWidth(context),
                Layout.Alignment.ALIGN_NORMAL,
                1,0,true);
        mCanvasBitmap = Bitmap.createBitmap(staticLayout.getWidth(), staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mCanvasBitmap);
        staticLayout.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {


//        canvas.drawText(mMultiSpanBuilder.subSequence(0, mTextLength).toString(), 10, 500, mPaint);

        canvas.drawBitmap(mCanvasBitmap, 10, 500, mPaint);

        super.onDraw(canvas);
    }
}
