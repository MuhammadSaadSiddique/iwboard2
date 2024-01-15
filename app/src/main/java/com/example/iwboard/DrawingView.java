package com.example.iwboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DrawingView extends View {
    public DrawingViewListener drawingViewListener;
    public Paint paint;
    public Path path;
    private Bitmap bitmap;
    public Canvas canvas;
    public ArrayList<Path> paths;
    private Stack<Path> undonePaths;
    public ArrayList<Paint> paints;
    private Stack<Paint> undonePaints;
    private int currentColor;
    private int strokeWidth;
    private List<Bitmap> savedBitmaps;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        path = new Path();
        paths = new ArrayList<>();
        undonePaths = new Stack<>();
        paints = new ArrayList<>();
        undonePaints = new Stack<>();
        currentColor = Color.BLACK;
        strokeWidth = 5;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);

        paths.add(path);
        paints.add(paint);
        savedBitmaps = new ArrayList<>();
    }

    public void setColor(int color) {
        currentColor = color;
        paint.setColor(currentColor);
    }

    public void setStrokeWidth(int width) {
        strokeWidth = width;
        paint.setStrokeWidth(strokeWidth);
    }

    public void undo() {
        if (!paths.isEmpty()) {
            undonePaths.push(paths.remove(paths.size() - 1));
            undonePaints.push(paints.remove(paints.size() - 1));
            invalidate();
        }
    }

    public void redo() {
        if (!undonePaths.isEmpty()) {
            paths.add(undonePaths.pop());
            paints.add(undonePaints.pop());
            invalidate();
        }
    }

    public void clear() {
        path.reset();
        paths.clear();
        undonePaths.clear();
        paints.clear();
        undonePaints.clear();
        invalidate();
    }

    public void loadBitmap(Bitmap bitmap) {
        savedBitmaps.add(bitmap);
        invalidate();
    }

    public int getSavedBitmapCount() {
        return savedBitmaps.size();
    }

    public Bitmap getSavedBitmap(int index) {
        if (index >= 0 && index < savedBitmaps.size()) {
            return savedBitmaps.get(index);
        }
        return null;
    }

    public Bitmap getCanvasBitmap() {
        return bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        savedBitmaps.add(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawingViewListener.onDrawingStartedCompleted("Started X: " + decimalFormat.format(x) + "| Y: " + decimalFormat.format(y));
                path = new Path();
                path.moveTo(x, y);
                paths.add(path);
                undonePaths.clear();
                Paint newPaint = new Paint(paint);
                paints.add(newPaint);
                undonePaints.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                drawingViewListener.onDrawingCompleted("Ending  X: " + decimalFormat.format(x) + "| Y: " + decimalFormat.format(y));
                break;
        }

        invalidate();
        return true;
    }

    public void setDrawingViewListener(DrawingViewListener listener) {
        this.drawingViewListener = listener;
    }

    public interface DrawingViewListener {
        void onDrawingStartedCompleted(String drawingResult);

        void onDrawingCompleted(String drawingResult);
    }
}