package com.example.iwboard;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class Paths{
    ArrayList<Path> path=new ArrayList<>();

    public Paths( int slideNo,ArrayList<Path> path) {
        this.path = path;
        this.slideNo = slideNo;
    }

    int slideNo;
}
public class MainActivity extends AppCompatActivity implements DrawingView.DrawingViewListener {
    private DrawingView drawingView;
    List<DrawingView> lst_drawingView;
    ImageView img_home, img_color, img_strokeWidth, img_undo, img_redo, img_clear, img_save, img_back, img_new;
    private int selectedStrokeWidth = 5;
    TextView tv_Started_xy, tv_Ending_xy, tv_desc, tv_slide;
    Button btn_back, btn_next;
    LinearLayout lnr_xy;
    ArrayList<DrawingView> lstDrawing;
    ArrayList<Paint> savedPaints;
    ArrayList<Integer> CountsPaths;
    ArrayList<Integer> CountsPaint;
    ArrayList<Path> savedPaths;
    LinearLayout lnr_colorPicker;

    boolean showflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*NEW CODE ADDED FOR NEXT AND BACK*/
        lstDrawing = new ArrayList<>();
        savedPaints = new ArrayList<>();
        savedPaths = new ArrayList<>();
        CountsPaths = new ArrayList<>();
        CountsPaint = new ArrayList<>();
        /*NEW CODE ADDED FOR NEXT AND BACK*/
        lnr_colorPicker = findViewById(R.id.lnr_colorPicker);
        lnr_xy = findViewById(R.id.lnr_xy);
        lnr_xy.setVisibility(View.GONE);
        drawingView = findViewById(R.id.drawingView);
        lst_drawingView = new ArrayList<>();
        img_home = findViewById(R.id.img_home);
        img_color = findViewById(R.id.img_color);
        img_strokeWidth = findViewById(R.id.img_strokeWidth);
        img_undo = findViewById(R.id.img_undo);
        img_redo = findViewById(R.id.img_redo);
        img_clear = findViewById(R.id.img_clear);
        img_save = findViewById(R.id.img_save);
        tv_Started_xy = findViewById(R.id.tv_Started_xy);
        tv_Ending_xy = findViewById(R.id.tv_Ending_xy);
        tv_desc = findViewById(R.id.tv_desc);
        tv_slide = findViewById(R.id.tv_slide);
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        tv_desc.setText(getIntent().getStringExtra("Title"));
        tv_slide.setText("Slide " + _currentSlide);
        openColorPicker();
        lnr_colorPicker.setVisibility(View.GONE);
        btn_back.setOnClickListener(v -> {
            loadPreviousBitmap();
        });
        btn_next.setOnClickListener(v -> {
            loadNextBitmap();
        });
        img_color.setOnClickListener(v -> {
            if (showflag) {
                showflag = false;
                lnr_colorPicker.setVisibility(View.GONE);
            } else {
                showflag = true;
                lnr_colorPicker.setVisibility(View.VISIBLE);
            }
        });
        img_strokeWidth.setOnClickListener(v -> openStrokeWidthPicker());
        img_undo.setOnClickListener(v -> drawingView.undo());
        img_redo.setOnClickListener(v -> drawingView.redo());
        img_clear.setOnClickListener(v -> drawingView.clear());
        img_save.setOnClickListener(v -> saveDrawing());
        drawingView.setDrawingViewListener(this);
    }
//yehi tu
    int _currentSlide = 0;

    private void loadPreviousBitmap() {
        try {
            if (_currentSlide == savedPaints.size()) {
                if(!drawingView.paths.isEmpty()) {
                    savedPaths.addAll(drawingView.paths);
                    CountsPaths.add(_currentSlide, drawingView.paths.size());
                    savedPaints.add(new Paint(drawingView.paint));
                    CountsPaint.add(_currentSlide, savedPaints.size());
                }
                //drawingView.clear();
                drawingView.invalidate();
            }
            if (_currentSlide != 0) {
                _currentSlide--;
//                drawingView.clear();
                drawingView.invalidate();
                drawingView.paths = new ArrayList<>(savedPaths.subList(_currentSlide==0? 0: CountsPaths.get(_currentSlide-1),CountsPaths.get(_currentSlide)));//_currentSlide,_currentSlide + 1));//
                drawingView.paints = new ArrayList<>(savedPaints.subList(_currentSlide==0? 0: CountsPaint.get(_currentSlide-1),CountsPaint.get(_currentSlide)));//_currentSlide, _currentSlide + 1));
//                drawingView.invalidate();
                updateText();
            }


        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadNextBitmap() {
        try {

            if (_currentSlide == savedPaints.size()) {
                if (!drawingView.paths.isEmpty()) {

                    savedPaths.addAll(drawingView.paths);
                    CountsPaths.add(_currentSlide, drawingView.paths.size());
                    savedPaints.add(new Paint(drawingView.paint));
                    CountsPaint.add(_currentSlide, savedPaints.size());
//                    drawingView.clear();
                    _currentSlide++;
                    updateText();
                }

            } else if (savedPaints.size() - _currentSlide == 1) {
                Toast.makeText(this, "Current: " + _currentSlide + "| Total:" + savedPaths.size(), Toast.LENGTH_SHORT).show();
                savedPaints.clear();
                savedPaths.clear();
//                drawingView.clear();
            } else {
                _currentSlide++;
//                drawingView.clear();
                drawingView.paths = new ArrayList<>(savedPaths.subList(_currentSlide==0? 0: CountsPaths.get(_currentSlide-1),CountsPaths.get(_currentSlide)));//_currentSlide,_currentSlide + 1));//
                drawingView.paints = new ArrayList<>(savedPaints.subList(_currentSlide==0? 0: CountsPaint.get(_currentSlide-1),CountsPaint.get(_currentSlide)));//_currentSlide, _currentSlide + 1));
//                drawingView.invalidate();
                updateText();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    void updateText() {
        tv_slide.setText("Slide " + _currentSlide);
    }


    private void openColorPicker() {
        /*AlertDialog.Builder colorPickerBuilder = new AlertDialog.Builder(this);
        colorPickerBuilder.setTitle("Select Color");

        LayoutInflater inflater = getLayoutInflater();
        View colorPickerView = inflater.inflate(R.layout.custom_color_picker_dialog, null);
        GridView colorGrid = colorPickerView.findViewById(R.id.colorGrid);

        // Create an array of colors for the grid
        final int[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GRAY};

        final ColorGridAdapter colorGridAdapter = new ColorGridAdapter(this, colors);
        colorGrid.setAdapter(colorGridAdapter);

        colorGrid.setOnItemClickListener((parent, view, position, id) -> {
            int selectedColor = colors[position];
            drawingView.setColor(selectedColor);
        });

        colorPickerBuilder.setView(colorPickerView);
        colorPickerBuilder.setPositiveButton("OK", (dialog, which) -> {
            // Handle OK button click if needed
        });
        colorPickerBuilder.setNegativeButton("Cancel", null);

        colorPickerBuilder.show();*/

        GridView colorGrid = findViewById(R.id.colorGrid);

        // Create an array of colors for the grid
        final int[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GRAY};

        final ColorGridAdapter colorGridAdapter = new ColorGridAdapter(this, colors);
        colorGrid.setAdapter(colorGridAdapter);

        colorGrid.setOnItemClickListener((parent, view, position, id) -> {
            int selectedColor = colors[position];
            drawingView.setColor(selectedColor);
        });


    }

    private void openStrokeWidthPicker() {
        AlertDialog.Builder strokeWidthPickerBuilder = new AlertDialog.Builder(this);
        strokeWidthPickerBuilder.setTitle("Select Stroke Width");

        LayoutInflater inflater = getLayoutInflater();
        View strokeWidthPickerView = inflater.inflate(R.layout.stroke_width_picker_dialog, null);

        final SeekBar strokeWidthSeekBar = strokeWidthPickerView.findViewById(R.id.strokeWidthSeekBar);
        final TextView tv_text = strokeWidthPickerView.findViewById(R.id.tv_text);
        // Set the initial progress (stroke width) on the SeekBar
        strokeWidthSeekBar.setProgress(selectedStrokeWidth);
        tv_text.setText(selectedStrokeWidth + "");
        //strokeWidthButton.setText("Stroke: " + selectedStrokeWidth);

        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedStrokeWidth = progress;
                tv_text.setText(progress + "");
                //strokeWidthButton.setText("Stroke: " + selectedStrokeWidth);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }
        });

        strokeWidthPickerBuilder.setView(strokeWidthPickerView);
        strokeWidthPickerBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                drawingView.setStrokeWidth(selectedStrokeWidth);
            }
        });
        strokeWidthPickerBuilder.setNegativeButton("Cancel", null);

        strokeWidthPickerBuilder.show();
    }

    private void saveDrawing() {
        drawingView.setDrawingCacheEnabled(true);
        drawingView.buildDrawingCache();
        Bitmap drawingBitmap = Bitmap.createBitmap(drawingView.getDrawingCache());
        drawingView.setDrawingCacheEnabled(false);

        if (drawingBitmap != null) {
            saveImageToGallery(this, drawingBitmap);
        } else {
            Toast.makeText(this, "Failed to save drawing", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToGallery(Context context, Bitmap bitmap) {
        String fileName = "Drawing_" + System.currentTimeMillis() + ".png";

        // Create a directory to save images
        File imagesDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "YourAppName");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }

        // Create a file to save the image
        File imageFile = new File(imagesDir, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            // Add the image to the system's MediaStore
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION, "Drawing created with YourAppName");
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());

            context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            // Notify the gallery app to scan for new images
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(Uri.fromFile(imageFile));
                context.sendBroadcast(mediaScanIntent);
            } else {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
            }

            Toast.makeText(context, "Drawing saved to gallery", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to save drawing", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDrawingStartedCompleted(String drawingResult) {
        tv_Started_xy.setText(drawingResult);

    }

    @Override
    public void onDrawingCompleted(String drawingResult) {
        tv_Ending_xy.setText(drawingResult);
    }
}
