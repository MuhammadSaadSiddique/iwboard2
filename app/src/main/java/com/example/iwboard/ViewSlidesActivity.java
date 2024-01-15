package com.example.iwboard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ViewSlidesActivity extends AppCompatActivity {
    private DrawingViewS drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slides);
        drawingView = findViewById(R.id.drawingView);
    }


    // Called when the user clicks the "Save" button
    public void onSaveButtonClick(View view) {
        drawingView.saveDrawingState(this);
        drawingView.clearDrawing();
    }

    // Called when the user clicks the "Load" button
    public void onLoadButtonClick(View view) {
        drawingView.loadDrawingState(this);
    }
}