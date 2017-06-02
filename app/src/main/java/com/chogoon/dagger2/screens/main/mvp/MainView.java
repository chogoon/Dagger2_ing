package com.chogoon.dagger2.screens.main.mvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chogoon.dagger2.R;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by chogoon on 2017-05-31.
 */

@SuppressLint("ViewConstructor")
public class MainView extends FrameLayout {

    @BindView(R.id.main_text_)
    TextView mainText;

    @BindView(R.id.user_name_)
    EditText userNameEditText;

    @BindView(R.id.main_button_)
    Button button;

    @BindView(R.id.main_toolbar_)
    Toolbar toolbar;

    private final ProgressDialog progressDialog = new ProgressDialog(getContext());


    public MainView(Activity activity) {
        super(activity);
        inflate(getContext(), R.layout.activity_main, this);
        ButterKnife.bind(this);

        progressDialog.setMessage("Loading...");
    }

    public void setMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        mainText.setText(message);
    }

    public Observable<Void> observeButton(){
        return RxView.clicks(button);
    }

    public String getUserName(){
        return userNameEditText.getText().toString();
    }

    public void showLoading(boolean flag){
        if(flag){
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }
}
