package com.example.iwboard;

import android.graphics.Paint;
import android.graphics.Path;
import java.io.Serializable;

public class DrawnPath implements Serializable {

    private Path path;
    private Paint paint;

    public DrawnPath(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public Paint getPaint() {
        return paint;
    }
}

