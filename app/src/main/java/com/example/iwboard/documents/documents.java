package com.example.iwboard.documents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwboard.MainActivity;
import com.example.iwboard.R;

import java.util.ArrayList;

public class documents extends AppCompatActivity {
    LinearLayout lnr_file, lnr_new_file;
    Spinner spnr_subject, spnr_lecture, spnr_class;
    ArrayList<String> lst_Subject = new ArrayList<>();
    ArrayList<String> lst_lecture = new ArrayList<>();
    ArrayList<String> lst_class = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        lnr_file = findViewById(R.id.lnr_file);
        lnr_new_file = findViewById(R.id.lnr_new_file);
        lnr_file.setOnClickListener(v -> {
            String Title = "Class: " + spnr_class.getSelectedItem().toString() + "\n" +
                    "Subject: " + spnr_subject.getSelectedItem().toString() + "\n" +
                    "Lect: " + spnr_lecture.getSelectedItem().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Title", Title);
            startActivity(intent);
        });
        lnr_new_file.setOnClickListener(v -> {
            String Title = "Class: " + spnr_class.getSelectedItem().toString() + "\n" +
                    "Subject: " + spnr_subject.getSelectedItem().toString() + "\n" +
                    "Lect: " + spnr_lecture.getSelectedItem().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Title", Title);
            startActivity(intent);
        });
        spnr_subject = findViewById(R.id.spnr_subject);
        spnr_lecture = findViewById(R.id.spnr_lecture);
        spnr_class = findViewById(R.id.spnr_class);

        lst_Subject = new ArrayList<String>();
        lst_lecture = new ArrayList<String>();
        lst_class = new ArrayList<String>();

        setSubject();
        setLecture();
        setClass();
    }

    void setSubject() {
        lst_Subject.add("Select Your Subject");
        lst_Subject.add("PF");
        lst_Subject.add("DSA");
        lst_Subject.add("OOP");
        lst_Subject.add("E-COM");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lst_Subject);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_subject.setAdapter(spinnerAdapter);
    }

    void setLecture() {
        lst_class.add("Select Your Class");
        lst_class.add("Bsit-7A");
        lst_class.add("Bscs 1A");
        lst_class.add("Bsit 8A");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lst_class);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_class.setAdapter(spinnerAdapter);
    }

    void setClass() {
        lst_lecture.add("Select Your Lectures");
        lst_lecture.add("Lecture_1_2");
        lst_lecture.add("Lecture_3_4");
        lst_lecture.add("Lecture_5_6");
        lst_lecture.add("Lecture_7_8");
        lst_lecture.add("Lecture_9_10");
        lst_lecture.add("Lecture_11_12");
        lst_lecture.add("Lecture_13_14");
        lst_lecture.add("Lecture_15_16");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lst_lecture);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_lecture.setAdapter(spinnerAdapter);
    }
}