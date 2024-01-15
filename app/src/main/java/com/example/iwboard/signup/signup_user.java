package com.example.iwboard.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwboard.R;

public class signup_user extends AppCompatActivity {
    Button btn_signup_user_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);
        btn_signup_user_next=findViewById(R.id.btn_signup_user_next);
        btn_signup_user_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup_user.this,signup_student.class);
                startActivity(intent);
            }
        });
    }
}