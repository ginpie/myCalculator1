package com.example.mycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {//implements View.OnClickListener {

    private TextView display;
    private String expression = "";
    private boolean clear_flag = false;
    private int countOperate = 2;
    public Map<R.id,String> map_btn_str = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
    }

    public void doSubmit(View view) {
        int id = view.getId();
        if (clear_flag && id!=R.id.buttonEqual){
            clear_flag = false;
            display.setText(" ");
        }
        String now_show = display.getText().toString();
//        // check if negative number exists
//        for (int i = 0; i < now_show.length(); i++){
//            if (now_show.charAt(i)=='-'){
//                if (i==0 || Character.isDigit(now_show.charAt(i-1))){
//                    break;
//                }
//            }
//        }

        if (id==R.id.buttonEqual && !clear_flag) {
            Tokenizer tokenizer = new Tokenizer();
            Parser parser = new Parser(tokenizer);
            tokenizer.setBuffer(now_show);
            try {
                Exp exp = parser.parse();
                double v = exp.value();
                String vstr = v+"";
                if (Math.round(v)==v){
                    vstr=vstr.substring(0,vstr.length()-2);
                }
                display.setText(exp.toString() + " = " + vstr);
            } catch (IllegalArgumentException e){
                display.setText("Syntax Error!");
            } catch (ArithmeticException e){
                display.setText("Math Error!");
            }
            clear_flag = true;
            return;
        }

        switch (id) {
            case R.id.button0:
                display.setText(now_show + "0");break;
            case R.id.button1:
                display.setText(now_show + "1");break;
            case R.id.button2:
                display.setText(now_show + "2");break;
            case R.id.button3:
                display.setText(now_show + "3");break;
            case R.id.button4:
                display.setText(now_show + "4");break;
            case R.id.button5:
                display.setText(now_show + "5");break;
            case R.id.button6:
                display.setText(now_show + "6");break;
            case R.id.button7:
                display.setText(now_show + "7");break;
            case R.id.button8:
                display.setText(now_show + "8");break;
            case R.id.button9:
                display.setText(now_show + "9");break;
            case R.id.buttonDot:
                display.setText(now_show + ".");break;

            case R.id.buttonMultiply:
                display.setText(now_show + "x");break;
            case R.id.buttonDivide:
                display.setText(now_show + "÷");break;
            case R.id.buttonPlus:
                display.setText(now_show + "+");break;
            case R.id.buttonMinus:
                display.setText(now_show + "-");break;

            case R.id.buttonBackspace:
                if (!now_show.isEmpty()) {
                    if (now_show.charAt(now_show.length()-1) == 'n' || now_show.charAt(now_show.length()-1) == 's' ||
                            now_show.charAt(now_show.length()-1) == 't'){
                        now_show = now_show.substring(0, now_show.length() - 3);
                    } else {
                        now_show = now_show.substring(0, now_show.length() - 1);
                    }
                    display.setText(now_show);
                }
                break;
            case R.id.buttonClear:
                now_show = "";
                clear_flag=false;
                display.setText(now_show);break;

        }
    }

    public void doswitch(View view) {
        Intent n = new Intent();
        n.setClass(MainActivity.this, ScientificActivity.class);
        startActivity(n);
    }

}
