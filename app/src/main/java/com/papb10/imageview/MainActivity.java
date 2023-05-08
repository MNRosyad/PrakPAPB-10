package com.papb10.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Canvas theCanvas;
    private Paint thePaint = new Paint();
    private Paint paintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);

    private Bitmap theBitmap;
    private ImageView imageView;
    private Rect rect = new Rect();
    private Rect bounds = new Rect();

    private static final int OFFSET = 120;
    private int theOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int colorBackground;
    private int colorRectangle;
    private int colorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        colorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        colorRectangle = ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);
        colorAccent = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);

        thePaint.setColor(colorBackground);
        paintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        paintText.setTextSize(70);

        imageView = findViewById(R.id.theImage);
        imageView.setOnClickListener(this::drawSomething);

        FloatingActionButton btnActivity = findViewById(R.id.btnActivity);
        btnActivity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    public void drawSomething(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        if (theOffset == OFFSET) {
            theBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            imageView.setImageBitmap(theBitmap);
            theCanvas = new Canvas(theBitmap);

            theCanvas.drawColor(colorBackground);
            theCanvas.drawText(getString(R.string.keepTaping), 100, 100, paintText);
            theOffset += OFFSET;
        } else {
            if (theOffset < halfWidth && theOffset < halfHeight) {
                thePaint.setColor(colorRectangle - MULTIPLIER * theOffset);
                rect.set(theOffset, theOffset, width - theOffset, height - theOffset);
                theCanvas.drawRect(rect, thePaint);
                theOffset += OFFSET;
            } else {
                thePaint.setColor(colorAccent - MULTIPLIER * theOffset);
                theCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, thePaint);
                theOffset += OFFSET;

//                String text = getString(R.string.done);
//                paintText.getTextBounds(text, 0, text.length(), bounds);
//                int x = halfWidth - bounds.centerX();
//                int y = halfHeight - bounds.centerY();
//                theCanvas.drawText(text, x, y, paintText);

                Point a = new Point(halfWidth - 50, halfHeight - 50);
                Point b = new Point(halfWidth + 50, halfHeight - 50);
                Point c = new Point(halfWidth, halfHeight + 250);

                Path thePath = new Path();
                thePath.setFillType(Path.FillType.EVEN_ODD);
                thePath.lineTo(a.x, a.y);
                thePath.lineTo(b.x, b.y);
                thePath.lineTo(c.x, c.y);
                thePath.lineTo(a.x, a.y);
                thePath.close();

                thePaint.setColor(colorRectangle - MULTIPLIER * theOffset);
                theCanvas.drawPath(thePath, thePaint);
            }
        }
        view.invalidate();
    }
}