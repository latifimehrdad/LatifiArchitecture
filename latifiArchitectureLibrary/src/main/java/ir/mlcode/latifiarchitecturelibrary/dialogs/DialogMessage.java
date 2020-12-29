package ir.mlcode.latifiarchitecturelibrary.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import ir.mlcode.latifiarchitecturelibrary.R;
import ir.mlcode.latifiarchitecturelibrary.databinding.DialogMessageBinding;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;

public class DialogMessage extends DialogFragment {

    private Context context;
    private String Title;
    private int color;
    private Drawable icon;
    private int tintColor;
    private PublishSubject<Byte> subject;

    Button DialogIgnor;

    TextView DialogTitle;

    LinearLayout layout;

    ImageView DialogImg;


    public DialogMessage(
            Context context,
            String title,
            int color,
            Drawable icon,
            int tintColor,
            PublishSubject<Byte> subject) {//______________________________________________________________________ Start DialogMessage
        this.context = context;
        Title = title;
        this.color = color;
        this.icon = icon;
        this.tintColor = tintColor;
        this.subject = subject;

    }//_____________________________________________________________________________________________ End DialogMessage


    public Dialog onCreateDialog(Bundle savedInstanceState) {//_____________________________________ Start onCreateDialog
        View view = null;
        DialogMessageBinding binding = DataBindingUtil
                .inflate(LayoutInflater
                                .from(this.context),
                        R.layout.dialog_message,
                        null,
                        false);
        binding.setTitel("");
        view = binding.getRoot();
        ButterKnife.bind(this, view);

        layout = view.findViewById(R.id.layout);
        layout.setBackgroundColor(color);
        DialogTitle = view.findViewById(R.id.DialogTitle);
        DialogTitle.setText(Title);
        DialogImg = view.findViewById(R.id.DialogImg);
        DialogImg.setImageDrawable(icon);
        DialogImg.setColorFilter(tintColor);
        DialogIgnor = view.findViewById(R.id.DialogIgnor);
        DialogIgnor.setOnClickListener(view1 -> {
            subject.onNext(StaticValues.ML_DialogClose);
            DialogMessage.this.dismiss();
            subject = null;
        });
        Dialog dialog = new AlertDialog.Builder(context).setView(view).create();
        Animation buttom = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
        view.setAnimation(buttom);
        return dialog;
    }//_____________________________________________________________________________________________ End onCreateDialog

}
