package com.example.quizology.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.quizology.R;
import com.google.android.gms.ads.InterstitialAd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity
{

    private static int SPLASH_TIME_OUT = 3000;
    CountDownTimer waitTimer;
    InterstitialAd mInterstitialAd;
    @BindView(R.id.content_splash)
    LinearLayout contentSplash;

    PackageInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        /*try
        {
            info = this.getPackageManager().getPackageInfo("com.example.quizology", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        }
        catch (PackageManager.NameNotFoundException e1)
        {
            Log.e("name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            Log.e("no such an algorithm", e.toString());
        }
        catch (Exception e)
        {
            Log.e("exception", e.toString());
        }*/

        waitTimer = new CountDownTimer(SPLASH_TIME_OUT, 1000)
        {

            public void onTick(long millisUntilFinished)
            {
                //called every 300 milliseconds, which could be used to
                //send messages or some other action
            }

            public void onFinish()
            {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }.start();

    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

}
