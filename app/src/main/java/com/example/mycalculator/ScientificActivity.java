package com.example.mycalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomActivity extends AppCompatActivity {
    private TextView display;
    private String expression = "";
    private boolean clear_flag = false;
    private int countOperate = 2;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
     if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        display = findViewById(R.id.textView);
    }
    public void doSubmit(View view) {
        int id = view.getId();
        if (clear_flag == true){
            clear_flag = false;
            display.setText("");
        }
        String now_show = display.getText().toString();

        if (id==R.id.buttonEqual) {
            Tokenizer tokenizer = new Tokenizer();
            Parser parser = new Parser(tokenizer);
            tokenizer.setBuffer(now_show);
            Exp exp = parser.parse();
            display.setText(exp.toString() + " = " + exp.value());
            clear_flag = true;
            return;
        }

        switch (id) {
            case R.id.button0:
                display.setText(now_show + " 0");break;
            case R.id.button1:
                display.setText(now_show + " 1");break;
            case R.id.button2:
                display.setText(now_show + " 2");break;
            case R.id.button3:
                display.setText(now_show + " 3");break;
            case R.id.button4:
                display.setText(now_show + " 4");break;
            case R.id.button5:
                display.setText(now_show + " 5");break;
            case R.id.button6:
                display.setText(now_show + " 6");break;
            case R.id.button7:
                display.setText(now_show + " 7");break;
            case R.id.button8:
                display.setText(now_show + " 8");break;
            case R.id.button9:
                display.setText(now_show + " 9");break;
            case R.id.buttonDot:
                display.setText(now_show + " .");break;

            case R.id.buttonMultiply:
                display.setText(now_show + " *");break;
            case R.id.buttonDivide:
                display.setText(now_show + " /");break;
            case R.id.buttonPlus:
                display.setText(now_show + " +");break;
            case R.id.buttonMinus:
                display.setText(now_show + " -");break;

            case R.id.buttonBackspace:
                now_show = now_show.substring(0,now_show.length()-1);
                display.setText(now_show);break;
            case R.id.buttonClear:
                now_show = "";
                display.setText(now_show);break;

            case R.id.buttonSin:
                display.setText(now_show + " Sin");break;
            case R.id.buttonCos:
                display.setText(now_show + " Cos");break;
            case R.id.buttonTan:
                display.setText(now_show + " Tan");break;
            case R.id.buttonPower:
                display.setText(now_show + "^");break;
            case R.id.buttonFactor:
                display.setText(now_show + "!");break;
        }
    }
    public void doswitch(View view) {
        Intent n = new Intent(WelcomActivity.this,MainActivity.class);
        startActivity(n);
    }
}
