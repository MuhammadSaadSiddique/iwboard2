package com.example.iwboard.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.iwboard.R;

import java.util.ArrayList;

public class signup_student extends AppCompatActivity {
    Button signup_stu_next;

    Spinner spinner;
    ArrayList<String> section=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        signup_stu_next=findViewById(R.id.signup_stu_next);
        signup_stu_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup_student.this,signup_teacher.class);
                startActivity(intent);
            }
        });



        spinner = findViewById(R.id.section);
        section = new ArrayList<String>();

        section.add("Select Your Section");
        section.add("ITA");
        section.add("ITB");
        section.add("ITC");




//I don't want to have a prompt like the latter

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, section);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
    }
    }
