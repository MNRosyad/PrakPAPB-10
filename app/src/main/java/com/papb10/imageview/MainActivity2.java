package com.papb10.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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

    private int firstColor, secondColor, thirdColor, fourthColor, skyColor;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        firstColor = ResourcesCompat.getColor(getResources(), R.color.firstColor, null);
        secondColor = ResourcesCompat.getColor(getResources(), R.color.secondColor, null);
        thirdColor = ResourcesCompat.getColor(getResources(), R.color.thirdColor, null);
        fourthColor = ResourcesCompat.getColor(getResources(), R.color.fourthColor, null);
        skyColor = ResourcesCompat.getColor(getResources(), R.color.blueSky, null);

        mainImage = findViewById(R.id.theseImage);

        FloatingActionButton btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        mainImage.setOnClickListener(view -> {
            startDrawing(view, count);
            count += 1;
        });
    }

    public void startDrawing(View view, int plCount) {
        int width = view.getWidth();
        int height = view.getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        if (plCount == 0) {
            justBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mainImage.setImageBitmap(justBitmap);
            justCanvas = new Canvas(justBitmap);
            justCanvas.drawColor(skyColor);
        } else if (plCount == 1) {
            // Draw snowman's body
            firstPaint.setColor(firstColor);
            justCanvas.drawCircle(halfWidth, halfHeight + 400, 300, firstPaint);
            justCanvas.drawCircle(halfWidth, halfHeight, 210, firstPaint);
            justCanvas.drawCircle(halfWidth, halfHeight - 270, 160, firstPaint);
        } else if (plCount == 2) {
            // Draw snowman's eyes
            secondPaint.setColor(secondColor);
            justCanvas.drawCircle(halfWidth - 60, halfHeight - 300, 20, secondPaint);
            justCanvas.drawCircle(halfWidth + 60, halfHeight - 300, 20, secondPaint);
        } else if (plCount == 3) {
            // Draw snowman's nose
            Path nosePath = new Path();
            nosePath.moveTo(halfWidth, halfHeight - 280);
            nosePath.lineTo(halfWidth - 25, halfHeight - 230);
            nosePath.lineTo(halfWidth + 25, halfHeight - 230);
            nosePath.close();
            thirdPaint.setColor(thirdColor);
            justCanvas.drawPath(nosePath, thirdPaint);
        } else if (plCount == 4) {
            // Draw snowman's buttons
            fourthPaint.setColor(fourthColor);
            justCanvas.drawCircle(halfWidth, halfHeight - 120, 15, fourthPaint);
            justCanvas.drawCircle(halfWidth, halfHeight - 60, 15, fourthPaint);
            justCanvas.drawCircle(halfWidth, halfHeight, 15, fourthPaint);
        } else if (plCount == 5) {
            // Draw snowman's arms
            secondPaint.setStrokeWidth(25);
            secondPaint.setStrokeCap(Paint.Cap.ROUND);
            justCanvas.drawLine(halfWidth - 180, halfHeight, halfWidth - 300, halfHeight - 170, secondPaint);
            justCanvas.drawLine(halfWidth + 180, halfHeight, halfWidth + 300, halfHeight - 170, secondPaint);
        } else if (plCount == 6) {
            // Draw snowman's hat
            justCanvas.drawRect(halfWidth - 100, halfHeight - 550, halfWidth + 100, halfHeight - 390, fourthPaint);
            justCanvas.drawRect(halfWidth - 100, halfHeight - 490, halfWidth + 100, halfHeight - 470, thirdPaint);
            justCanvas.drawRect(halfWidth - 150, halfHeight - 450, halfWidth + 150, halfHeight - 390, fourthPaint);
        }
        view.invalidate();
    }
}