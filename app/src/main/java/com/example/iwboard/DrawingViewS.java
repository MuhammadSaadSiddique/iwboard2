package com.example.iwboard;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.*;
import java.util.ArrayList;

public class DrawingViewS extends View implements Serializable {

    private ArrayList<DrawnPath> paths;
    private Path currentPath;
    private Paint drawPaint;

    public DrawingViewS(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paths = new ArrayList<>();
        currentPath = new Path();

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (DrawnPath path : paths) {
            canvas.drawPath(path.getPath(), path.getPaint());
        }

        canvas.drawPath(currentPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startDrawing(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                continueDrawing(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                stopDrawing();
                break;
        }

        invalidate();
        return true;
    }

    private void startDrawing(float x, float y) {
        currentPath = new Path();
        currentPath.moveTo(x, y);
    }

    private void continueDrawing(float x, float y) {
        currentPath.lineTo(x, y);
    }

    private void stopDrawing() {
        paths.add(new DrawnPath(new Path(currentPath), new Paint(drawPaint)));
        currentPath.reset();
    }

    public void clearDrawing() {
        paths.clear();
        invalidate();
    }

    public void saveDrawingState(Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput("drawing_state.ser", Context.MODE_PRIVATE)
            );
            outputStream.writeObject(paths);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDrawingState(Context context) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    context.openFileInput("drawing_state.ser")
            );
            paths = (ArrayList<DrawnPath>) inputStream.readObject();
            inputStream.close();
            invalidate();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
