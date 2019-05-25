package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                display.setText(now_show + "*");break;
            case R.id.buttonDivide:
                display.setText(now_show + "/");break;
            case R.id.buttonPlus:
                display.setText(now_show + "+");break;
            case R.id.buttonMinus:
                display.setText(now_show + "-");break;

            case R.id.buttonBackspace:
                now_show = now_show.substring(0,now_show.length()-1);
                display.setText(now_show);break;
            case R.id.buttonClear:
                now_show = "";
                display.setText(now_show);break;

            case R.id.buttonSin:
                display.setText(now_show + "Sin");break;
            case R.id.buttonCos:
                display.setText(now_show + "Cos");break;
            case R.id.buttonTan:
                display.setText(now_show + "Tan");break;
            case R.id.buttonCot:
                display.setText(now_show + "Cot");break;
            case R.id.buttonPower:
                display.setText(now_show + "^");break;
            case R.id.buttonFactor:
                display.setText(now_show + "!");break;
        }
    }

}
