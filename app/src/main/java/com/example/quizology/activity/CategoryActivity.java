package com.example.quizology.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.quizology.R;
import com.example.quizology.custom.CustomButton;
import com.example.quizology.custom.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.tvCategory)
    CustomTextView tvCategory;
    @BindView(R.id.ivapp_icon)
    ImageView ivappIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_category)
    RelativeLayout contentCategory;
    @BindView(R.id.btnHangman)
    CustomButton btnHangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

    }

    public void hangMan() {
        Intent hangmanIntent = new Intent(CategoryActivity.this, HangmanActivity.class);
        startActivity(hangmanIntent);
    }

    @OnClick(R.id.btnHangman)
    public void onClick() {
    }
}
