package ir.mlcode.latifiarchitecturelibrary.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.cunoraz.gifview.library.GifView;

import ir.mlcode.latifiarchitecturelibrary.R;

public class ML_Button extends LinearLayout {

    private TextView textView;
    private Context context;
    private GifView gifView;
    private ImageView imageView;

    private Drawable normalBack;
    private Drawable waitBack;

    private int imageWidth;
    private int imageHeight;
    private Drawable imageSrc;
    private int imageTint;
    private int gifSrc;

    private int textColor;
    private String text;
    private int textSize;
    private int fontFamilyId;
    private String waitText;


    private boolean click;


    //______________________________________________________________________________________________ ML_Button
    public ML_Button(Context context) {
        super(context);
        this.context = context;
        click = false;
    }
    //______________________________________________________________________________________________ ML_Button


    //______________________________________________________________________________________________ ML_Button
    public ML_Button(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        click = false;

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ML_Button);

        normalBack = ta.getDrawable(R.styleable.ML_Button_connectBack);
        waitBack = ta.getDrawable(R.styleable.ML_Button_waitBack);

        imageWidth = (int) (ta.getDimension(R.styleable.ML_Button_img_width, 0));
        imageHeight = (int) (ta.getDimension(R.styleable.ML_Button_img_height, 0));
        imageSrc = ta.getDrawable(R.styleable.ML_Button_img_src);
        imageTint = ta.getColor(R.styleable.ML_Button_img_tint, 0);
        gifSrc = ta.getResourceId(R.styleable.ML_Button_gif_src, 0);

        textColor = ta.getColor(R.styleable.ML_Button_textColor, 0);
        text = ta.getString(R.styleable.ML_Button_textValue);
        textSize = (int) (ta.getDimension(R.styleable.ML_Button_textValueSize, 0) / getResources().getDisplayMetrics().density);
        fontFamilyId = ta.getResourceId(R.styleable.ML_Button_fontFamily, 0);
        waitText = ta.getString(R.styleable.ML_Button_waitText);

        setBackground(normalBack);
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
        configText();
        configIcon();
        configGif();


    }
    //______________________________________________________________________________________________ ML_Button



    //______________________________________________________________________________________________ configEditText
    private void configText() {
        if (text != null) {
            textView = new TextView(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            textView.setTextColor(textColor);
            textView.setGravity(Gravity.CENTER);
            textView.setText(text);
            textView.setTextSize(textSize);
            textView.setBackgroundColor(context.getResources().getColor(R.color.ML_Transparent));
            textView.setLayoutParams(params);
            textView.setPadding(20, 4, 20, 4);
            if (fontFamilyId > 0)
                textView.setTypeface(ResourcesCompat.getFont(getContext(), fontFamilyId));
            addView(textView, params);
        }
    }
    //______________________________________________________________________________________________ configEditText


    //______________________________________________________________________________________________ configIcon
    private void configIcon() {
        if (imageSrc != null) {
            imageView = new ImageView(context);
            LayoutParams params = new LayoutParams(imageWidth, imageHeight);
            imageView.setLayoutParams(params);
            imageView.setImageDrawable(imageSrc);
            imageView.setColorFilter(imageTint);
            imageView.setVisibility(VISIBLE);
            addView(imageView, params);
        }
    }
    //______________________________________________________________________________________________ configIcon


    //______________________________________________________________________________________________ configIcon
    private void configGif() {
        if (gifSrc > 0) {
            gifView = new GifView(context);
            LayoutParams params = new LayoutParams(imageWidth, imageHeight);
            gifView.setLayoutParams(params);
            gifView.setGifResource(gifSrc);
            gifView.setVisibility(GONE);
            addView(gifView, params);
        }
    }
    //______________________________________________________________________________________________ configIcon




    //______________________________________________________________________________________________ startLoading
    public void startLoading() {
        click = true;
        if (gifSrc > 0) {
            gifView.setVisibility(VISIBLE);
            imageView.setVisibility(GONE);
        }

        if (waitBack != null)
            setBackground(waitBack);

        if (waitText != null) {
            if (waitText.length() < text.length()) {
                setMinimumWidth(getWidth());
            }
            textView.setText(waitText);
        }

    }
    //______________________________________________________________________________________________ startLoading


    //______________________________________________________________________________________________ stopLoading
    public void stopLoading() {

        click = false;
        if (gifSrc > 0) {
            gifView.setVisibility(GONE);
            imageView.setVisibility(VISIBLE);
        }
        if (normalBack != null)
            setBackground(normalBack);

        if (text != null)
            textView.setText(text);

    }
    //______________________________________________________________________________________________ stopLoading


    //______________________________________________________________________________________________ isClick
    public boolean isClick() {
        return click;
    }
    //______________________________________________________________________________________________ isClick


}
