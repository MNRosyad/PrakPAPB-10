package com.papb10.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {
    private Canvas justCanvas;
    private Paint firstPaint = new Paint();
    private Paint secondPaint = new Paint();
    private Paint thirdPaint = new Paint();
    private Paint fourthPaint = new Paint();

    private ImageView mainImage;
    private Bitmap justBitmap;
    private Rect getRect = new Rect();

    private static final int OFFSET = 120;
    private int theOffset = OFFSET;
    private static final int MULTIPLIER = 100;
    private int width;
    private int height;

    private int firstColor;
    private int secondColor;
    private int thirdColor;
    private int fourthColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        firstColor = ResourcesCompat.getColor(getResources(), R.color.firstColor, null);
        secondColor = ResourcesCompat.getColor(getResources(), R.color.secondColor, null);
        thirdColor = ResourcesCompat.getColor(getResources(), R.color.thirdColor, null);
        fourthColor = ResourcesCompat.getColor(getResources(), R.color.fourthColor, null);

        mainImage = findViewById(R.id.theseImage);

        FloatingActionButton btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDrawing(view);
            }
        });
    }

    public void startDrawing(View view) {
        width = view.getWidth();
        height = view.getHeight();

        justBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        mainImage.setImageBitmap(justBitmap);
        justCanvas = new Canvas(justBitmap);

        // Draw snowman's body
        firstPaint.setColor(firstColor - MULTIPLIER * theOffset);
        justCanvas.drawCircle(250, 400, 100, firstPaint);
        justCanvas.drawCircle(250, 270, 70, firstPaint);
        justCanvas.drawCircle(250, 170, 50, firstPaint);

        // Draw snowman's eyes
        secondPaint.setColor(secondColor - MULTIPLIER * theOffset);
        justCanvas.drawCircle(230, 160, 7, secondPaint);
        justCanvas.drawCircle(270, 160, 7, secondPaint);

        // Draw snowman's nose
        Path nosePath = new Path();
        nosePath.moveTo(250, 180);
        nosePath.lineTo(245, 190);
        nosePath.lineTo(255, 190);
        nosePath.close();
        thirdPaint.setColor(thirdColor - MULTIPLIER * theOffset);
        justCanvas.drawPath(nosePath, thirdPaint);

        // Draw snowman's buttons
        justCanvas.drawCircle(250, 250, 5, fourthPaint);
        justCanvas.drawCircle(250, 270, 5, fourthPaint);
        justCanvas.drawCircle(250, 290, 5, fourthPaint);

        // Draw snowman's arms
        secondPaint.setStrokeWidth(10);
        justCanvas.drawLine(200, 270, 150, 230, secondPaint);
        justCanvas.drawLine(300, 270, 350, 230, secondPaint);

        // Draw snowman's hat
        fourthPaint.setColor(fourthColor - MULTIPLIER * theOffset);
        justCanvas.drawRect(200, 120, 300, 140, fourthPaint);
        justCanvas.drawRect(220, 80, 280, 120, fourthPaint);

        theOffset += 1;
        view.invalidate();
    }
}