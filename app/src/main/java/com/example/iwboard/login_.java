package com.example.iwboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iwboard.Whiteboard.Whiteboard;
import com.example.iwboard.documents.documents;
import com.example.iwboard.signup.signup_user;

public class login_ extends AppCompatActivity {
    Button btn_login,create_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_);

        btn_login=findViewById(R.id.login);
        create_account=findViewById(R.id.create_account);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_.this, signup_user.class);
                startActivity(intent);
            }
        });


      btn_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i=new Intent(login_.this, documents.class);
              startActivity(i);
          }
      });
    }
}