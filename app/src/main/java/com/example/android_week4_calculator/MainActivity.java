package com.example.android_week4_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;
    String num1, num2;
    Double result;

    // 숫자 버튼 10개의 객체를 저장할 배열
    Button[] numButtons = new Button[10];
    // 숫자 버튼 0~9의 리소스 ID를 저장할 배열
    int[] numBtnIDs = {
            R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4,
            R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9
    };
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기(수정)");

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);

        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        btnRem = (Button) findViewById(R.id.BtnRem);

        textResult = (TextView) findViewById(R.id.TextResult);

        // 숫자 버튼 10개와 XML의 버튼 ID 연결
        for (i = 0; i < numButtons.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        // 숫자 버튼 10개에 클릭 이벤트 설정
        for (i = 0; i < numButtons.length; i++) {
            numButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;
                    EditText target;
                    if (edit1.isFocused()) {
                        target = edit1;
                    } else if (edit2.isFocused()) {
                        target = edit2;
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 기존 입력값 뒤에 클릭한 버튼의 숫자 추가 후 커서를 마지막으로 이동
                    target.setText(target.getText().toString() + clickedButton.getText().toString());
                    target.setSelection(target.length());
                }
            });
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (isEmpty()) return;
                result = Double.parseDouble(num1) + Double.parseDouble(num2);
                textResult.setText("계산 결과 : " + result.toString());
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (isEmpty()) return;
                result = Double.parseDouble(num1) - Double.parseDouble(num2);
                textResult.setText("계산 결과 : " + result.toString());
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (isEmpty()) return;
                result = Double.parseDouble(num1) * Double.parseDouble(num2);
                textResult.setText("계산 결과 : " + result.toString());
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (isEmpty() || isZero()) return;
                result = Double.parseDouble(num1) / Double.parseDouble(num2);
                textResult.setText("계산 결과 : " + result.toString());
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (isEmpty() || isZero()) return;
                result = Double.parseDouble(num1) % Double.parseDouble(num2);
                textResult.setText("계산 결과 : " + result.toString());
            }
        });
    }

    // 값을 입력하지 않았거나 숫자가 아니면 토스트 메시지로 오류 표시
    boolean isEmpty() {
        if (num1.trim().equals("") || num2.trim().equals("")) {
            Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_SHORT).show();
            return true;
        }
        try {
            Double.parseDouble(num1);
            Double.parseDouble(num2);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "숫자를 올바르게 입력하세요", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    // 0으로 나누면 토스트 메시지를 나타내고 계산하지 않음
    boolean isZero() {
        if (Double.parseDouble(num2) == 0) {
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
