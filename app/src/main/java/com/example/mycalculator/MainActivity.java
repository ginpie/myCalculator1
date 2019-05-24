package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {//implements View.OnClickListener {

    private TextView display;
    private String expression = "";
    private boolean clear_flag = false;
    private int countOperate = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
    }

    public void doSubmit(View view) {
        int id = view.getId();
        String now_show = display.getText().toString();
        String exp = "";
        switch (id){
            case R.id.button0:
                display.setText(now_show + "0");
                break;
            case R.id.button1:
                display.setText(now_show + "1");
                break;
            case R.id.button2:
                display.setText(now_show + "2");
                break;
            case R.id.button3:
                display.setText(now_show + "3");
                break;
            case R.id.button4:
                display.setText(now_show + "4");
                break;
            case R.id.button5:
                display.setText(now_show + "5");
                break;
            case R.id.button6:
                display.setText(now_show + "6");
                break;
            case R.id.button7:
                display.setText(now_show + "7");
                break;
            case R.id.button8:
                display.setText(now_show + "8");
                break;
            case R.id.button9:
                display.setText(now_show + "9");
                break;
            case R.id.buttonMultiply:
                display.setText(now_show + "*");
                break;
            case R.id.buttonDivide:
                display.setText(now_show + "/");
                if(clear_flag){
                    clear_flag = false;
                    now_show = "";
                    display.setText("");
                }
                display.setText(now_show + ((Button) view).getText().toString());
                break;
            case R.id.buttonPlus:
                display.setText(display.getText().toString()+"+");
                break;
            case R.id.buttonMinus:
                display.setText(display.getText().toString()+"-");
                break;
            case R.id.buttonDot:
                display.setText(display.getText().toString()+".");
                if(clear_flag){
                    clear_flag = false;
                    display.setText("");
                }
                display.setText(now_show + ((Button) view).getText().toString());
                break;
            case R.id.buttonEqual:
                display.setText("=");
                getResult();
                break;
            case R.id.buttonBackspace:
                display.setText("del");
                if(clear_flag){
                    clear_flag = false;
                    display.setText("");
                }else if(now_show != null || !now_show.equals("")){
                    display.setText(now_show.substring(0,now_show.length()-1));
                }
                break;
            case R.id.buttonClear:
                display.setText("c");
                clear_flag = false;
                display.setText("");
                break;
        }
    }

    private void getResult() {
        String exp = display.getText().toString();
        if(exp==null||exp.equals(""))
            return;
        if(!exp.contains(""))
            return;
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result = 0;
        //运算符前的数字
        String s1 = exp.substring(0,exp.indexOf(" "));
        //运算符
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //运算符后的数字
        String s2 = exp.substring(exp.indexOf(" ")+3);
        if(!s1.equals("")&&!s2.equals("")){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if(op.equals("+")){
                result = d1+d2;
            }else if(op.equals("-")){
                result = d1-d2;
            }else if(op.equals("*")){
                result = d1*d2;
            }else if(op.equals("/")){
                if(d2==0){
                    result = 0;
                }else{
                    result = d1/d2;
                }
            }
            if(!s1.contains(".")&& !s2.contains(".")&&!op.equals("/")){
                int r =(int) result;
                display.setText(r+"");
            }else{
                display.setText(result+"");
            }
        }else if(!s1.equals("")&&s2.equals("")){
            display.setText(exp);
        }else if(s1.equals("")&& !s2.equals("")){
            double d2 = Double.parseDouble(s2);
            if(op.equals("+")){
                result = 0+d2;
            }else if(op.equals("-")){
                result = 0-d2;
            }else if(op.equals("*")){
                result = 0;
            }else if(op.equals("/")){
                result = 0;
            }
            if(!s1.contains(".") && !s2.contains(".")){
                int r = (int)result;
                display.setText(r+"");
            }else{
                display.setText(result+"");
            }
        }else{
            display.setText("");
        }
    }

}
