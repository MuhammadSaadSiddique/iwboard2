package com.example.iwboard.Whiteboard;

/*import static androidx.constraintlayout.motion.widget.MotionLayout.MyTracker.me;*/

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwboard.R;

import java.util.Random;

/*import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;*/

public class Whiteboard extends AppCompatActivity {

    /*DrawableView drawableView;
    DrawableViewConfig config;*/
    Button increase, decrease, color, undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);

        //initiaselayout();
    }

    /*private void initiaselayout() {

        // initialise the layout
        drawableView = findViewById(R.id.paintView);
        increase = findViewById(R.id.increase);
        decrease = findViewById(R.id.decrease);
        color = findViewById(R.id.color);
        undo = findViewById(R.id.undo);
        config = new DrawableViewConfig();

        // set stroke color as black initially
        config.setStrokeColor(getResources().getColor(android.R.color.black));

        // If the view is bigger than canvas,
        // with this the user will see the bounds (Recommended)
        config.setShowCanvasBounds(true);

        // set width as 20
        config.setStrokeWidth(20.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);

        // set canvas height
        config.setCanvasHeight(1080);

        // set canvas width
        config.setCanvasWidth(1920);
        drawableView.setConfig(config);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // increase the stroke width by 10
                config.setStrokeWidth(config.getStrokeWidth() + 10);
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // decrease stroke width by 10
                config.setStrokeWidth(config.getStrokeWidth() - 10);
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialise Random
                Random random = new Random();
                // set the color using random
                config.setStrokeColor(Color.rgb(255, random.nextInt(256), random.nextInt(256)));
            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // undo the most recent changes
                drawableView.undo();
            }
        });
    }*/
}