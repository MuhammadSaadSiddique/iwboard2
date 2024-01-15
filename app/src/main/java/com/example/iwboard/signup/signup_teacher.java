package com.example.iwboard.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwboard.R;
import com.example.iwboard.login_;

public class signup_teacher extends AppCompatActivity {
    Button btn_signup_teacher_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);
        btn_signup_teacher_next=findViewById(R.id.signup_teacher_next);
        btn_signup_teacher_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup_teacher.this, login_.class);
                startActivity(intent);
            }
        });


    }
}