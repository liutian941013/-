package com.example.liufan.jingdongapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.liufan.jingdongapplication.R;

public class CompileActivity extends AppCompatActivity {

    private EditText addr_te;
    private EditText mobile_te;
    private EditText name_te;
    private Button compile_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        addr_te = findViewById(R.id.addr_te);
        mobile_te = findViewById(R.id.mobile_te);
        name_te = findViewById(R.id.name_te);
        compile_bt = findViewById(R.id.Compile_bt);
        //确认的点击事件
        compile_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String addr = addr_te.getText().toString();
                 String mobile =mobile_te.getText().toString();
                 String name = name_te.getText().toString();
                Intent intent = new Intent(CompileActivity.this,IndentActivity.class);
                intent.putExtra("addr",addr);
                intent.putExtra("mobile",mobile);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        });
    }
}
