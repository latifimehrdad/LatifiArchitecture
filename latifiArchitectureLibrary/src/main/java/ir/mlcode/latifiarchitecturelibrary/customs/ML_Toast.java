package ir.mlcode.latifiarchitecturelibrary.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import ir.mlcode.latifiarchitecturelibrary.R;


public class ML_Toast extends LinearLayout {

    private Context context;

    private TextView textView;

    private ImageView imageView;

    private Drawable toastBack;

    private static View view;

    private static Runnable runnable;

    private static Handler handler;


    private int textColor;
    private int textSize;
    private int fontFamilyId;
    private int minWidth;
    private int maxWidth;

    private int imageWidth;
    private int imageHeight;

    //______________________________________________________________________________________________ ML_Toast
    public ML_Toast(Context context) {
        super(context);
        this.context = context;
    }
    //______________________________________________________________________________________________ ML_Toast


    //______________________________________________________________________________________________ ML_Toast
    public ML_Toast(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ML_Toast);

        toastBack = ta.getDrawable(R.styleable.ML_Toast_toast_Back);
        textColor = ta.getColor(R.styleable.ML_Toast_toast_TextColor, 0);
        textSize = (int) (ta.getDimension(R.styleable.ML_Toast_toast_TextSize, 0) / getResources().getDisplayMetrics().density);
        fontFamilyId = ta.getResourceId(R.styleable.ML_Toast_fontFamily, 0);
        minWidth = (int) (ta.getDimension(R.styleable.ML_Toast_toast_minWidth, 0));
        maxWidth = (int) (ta.getDimension(R.styleable.ML_Toast_toast_maxWidth, 0));

        imageWidth = (int) (ta.getDimension(R.styleable.ML_Toast_toast_imgWidth, 0));
        imageHeight = (int) (ta.getDimension(R.styleable.ML_Toast_toast_imgHeight, 0));


        setPadding(10, 10, 10, 10);

        setBackground(toastBack);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        configText();
        configIcon();
    }
    //______________________________________________________________________________________________ ML_Toast


    //______________________________________________________________________________________________ configEditText
    private void configText() {
        textView = new TextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setTextColor(textColor);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(textSize);
        textView.setBackgroundColor(context.getResources().getColor(R.color.ML_Transparent));
        textView.setLayoutParams(params);
        textView.setMinWidth(minWidth);
        textView.setMaxWidth(maxWidth);
        textView.setPadding(20, 4, 20, 4);
        if (fontFamilyId > 0)
            textView.setTypeface(ResourcesCompat.getFont(getContext(), fontFamilyId));
        addView(textView, params);
    }
    //______________________________________________________________________________________________ configEditText


    //______________________________________________________________________________________________ configIcon
    private void configIcon() {
        imageView = new ImageView(context);
        LayoutParams params = new LayoutParams(imageWidth, imageHeight);
        imageView.setLayoutParams(params);
        imageView.setVisibility(VISIBLE);
        addView(imageView, params);
    }
    //______________________________________________________________________________________________ configIcon


    //______________________________________________________________________________________________ mackToast
    public void mackToast(String message, Drawable icon, int iconTintColor) {
        textView.setText(message);
        imageView.setImageDrawable(icon);
        imageView.setColorFilter(iconTintColor);
    }
    //______________________________________________________________________________________________ mackToast


    //______________________________________________________________________________________________ showToast
    public static void showToast(Context context, ConstraintLayout viewParent, String message, Drawable icon, int iconTintColor) {

        hide(viewParent);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.toast, null);
        viewParent.addView(view);
        ConstraintLayout.LayoutParams parent = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        parent.leftToRight = viewParent.getId();
        parent.bottomToBottom = viewParent.getId();
        parent.bottomMargin = 10;
        view.setVisibility(GONE);

        ML_Toast ml_toast = view.findViewById(R.id.ml_Toast);
        ml_toast.mackToast(message, icon, iconTintColor);
        view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        view.setVisibility(VISIBLE);
        runnable = () -> show(context, message);
        handler = new Handler();
        handler.postDelayed(runnable, 700);

    }
    //______________________________________________________________________________________________ showToast



    //______________________________________________________________________________________________ hide
    public static void hide(ConstraintLayout viewParent) {

        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }

        if (view != null) {
            view.setAnimation(null);
            view.setVisibility(GONE);
            viewParent.removeView(view);
            view = null;

        }
    }
    //______________________________________________________________________________________________ hide


    //______________________________________________________________________________________________ show
    private static void show(Context context, String message) {

        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }

        int delay;
        int titleLength = message.length();
        if (titleLength > 8)
            titleLength = titleLength / 8;
        else
            titleLength = 1;
        delay = 1000 * titleLength;
        if (delay < 1500)
            delay = 2500;

        runnable = () -> {
            if (view.getVisibility() == View.VISIBLE) {
                view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_left));
                view.setVisibility(GONE);
                if (handler != null && runnable != null) {
                    handler.removeCallbacks(runnable);
                    handler = null;
                    runnable = null;
                }
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, delay);

    }
    //______________________________________________________________________________________________ show


}
