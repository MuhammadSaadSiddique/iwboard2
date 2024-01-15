package com.example.iwboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ColorPickerView extends View {

    private Paint paint;
    private int color = Color.BLACK;

    public ColorPickerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // Draw the color spectrum
        for (int x = 0; x < viewWidth; x++) {
            int hue = (int) ((x / (float) viewWidth) * 360);
            paint.setColor(Color.HSVToColor(new float[]{hue, 1.0f, 1.0f}));
            canvas.drawLine(x, 0, x, viewHeight, paint);
        }

        // Draw the selected color
        paint.setColor(color);
        paint.setStrokeWidth(5);
        canvas.drawLine(viewWidth, 0, viewWidth, viewHeight, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (x < getWidth()) {
            // Update the selected color based on the touch position
            int hue = (int) ((x / (float) getWidth()) * 360);
            color = Color.HSVToColor(new float[]{hue, 1.0f, 1.0f});
            invalidate();
        }

        return true;
    }

    public int getColor() {
        return color;
    }
}
