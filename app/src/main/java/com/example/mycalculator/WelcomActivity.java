package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WelcomActivity extends AppCompatActivity {
    private TextView display;
    private String expression = "";
    private boolean clear_flag = false;
    private int countOperate = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        display = findViewById(R.id.textView);
    }
    public void doswitch(View view) {
        Intent n = new Intent(WelcomActivity.this,MainActivity.class);
        startActivity(n);
    }
}
