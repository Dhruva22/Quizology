package com.example.quizology.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.quizology.R;
import com.example.quizology.custom.CustomTextView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.ivapp_icon)
    ImageView ivappIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvPlay)
    CustomTextView tvPlay;
    @BindView(R.id.content_home)
    RelativeLayout contentHome;
    @BindView(R.id.btnfbConnect)
    LoginButton btnfbConnect;
    @BindView(R.id.tvfbConnect)
    CustomTextView tvfbConnect;
    @BindView(R.id.tvHome)
    CustomTextView tvHome;

    CallbackManager callbackManager;
    boolean fbFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        fbFlag = getSharedPreferences("QUIZOLOGY", 0).getBoolean("fbFlag", true);
        Log.i("fbFlag", fbFlag + "");

        if (fbFlag == false)
        {
            tvfbConnect.setVisibility(View.GONE);
        }
        else
        {
            callbackManager = CallbackManager.Factory.create();
            btnfbConnect.setReadPermissions("email");
            btnfbConnect.registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            Log.i("access token", loginResult.getAccessToken().getUserId());
                            tvfbConnect.setVisibility(View.GONE);
                            fbFlag = false;
                            saveFbFlag("fbFlag", fbFlag);
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(HomeActivity.this, "User pressed cancel...", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException error) {
                            Toast.makeText(HomeActivity.this, "Error connecting with facebook...\n Try again later!!!", Toast.LENGTH_SHORT).show();
                        }
                    });

            btnfbConnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LoginManager.getInstance().logInWithReadPermissions(HomeActivity.this, Arrays.asList("public_profile"));
                }
            });
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        AppEventsLogger.activateApp(getApplication());
    }

    public void play(View v) {
        Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    public void fbConnect(View v) {
        if (AccessToken.getCurrentAccessToken() != null) {

        } else {

        }
    }

    public void dialogForExit() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.item_exit_alert);
        Button btnAlertOk = (Button) dialog.findViewById(R.id.btnAlertExit);
        Button btnAlertCancle = (Button) dialog.findViewById(R.id.btnAlertCancle);
        btnAlertOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAlertCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void saveFbFlag(String key, boolean fbFlag) {
        SharedPreferences preferences = this.getSharedPreferences("QUIZOLOGY", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, fbFlag);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        dialogForExit();
    }

}
