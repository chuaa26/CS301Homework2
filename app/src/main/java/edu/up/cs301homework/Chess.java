package edu.up.cs301homework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;


public class Chess extends SurfaceView {

    private Paint greenPaint;
    private Paint redPaint;
    private Paint yellowPaint;
    private DrawableElement selectedElement;
    private List<DrawableElement> elements;

    private final float x = 250.0f;
    private final float y = 250.0f;
    private final float radius = 20.0f;

    public Chess(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        greenPaint = new Paint();
        greenPaint.setColor(0xFF228B22);
        greenPaint.setStyle(Paint.Style.FILL);

        redPaint = new Paint();
        redPaint.setColor(0xFFFF0000);
        redPaint.setStyle(Paint.Style.FILL);

        yellowPaint = new Paint();
        yellowPaint.setColor(0xFFFFDF22);
        yellowPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(0xFFFFFFFF);

        elements = new ArrayList<>();


        elements.add(new DrawableElement("Green pawn and Red rook", 500, 100, Color.RED));
        elements.add(new DrawableElement("PawnBase", x - (float) 60 / 2, y + 80, android.R.color.holo_green_dark, (int) (x + (float) 60 / 2), y + 80 + 60));
        elements.add(new DrawableElement("PawnBody", x - (float) 40 / 2, y, android.R.color.holo_green_dark, x + (float) 40 / 2, y + 80));
        elements.add(new DrawableElement("PawnHead", x, y - 30, 30));

        elements.add(new DrawableElement("RookBase", 350 - (float) 60 / 2, 200 + 80, android.R.color.holo_red_dark, 350 + (float) 60 / 2, 200 + 80 + 20));
        elements.add(new DrawableElement("RookBody", 350 - (float) 40 / 2, 200, android.R.color.holo_red_dark, 350 + (float) 40 / 2, 200 + 80));
        elements.add(new DrawableElement("RookTop", 350 - 60 / 2, 200 - 20, android.R.color.holo_red_dark, 350 + (float) 60 / 2, 200));



//        // Fixed dimensions for pawn elements
//        int headRadius = 30;       // Head's radius
//        int bodyWidth = 40;        // Body's width
//        int bodyHeight = 80;
//        int baseWidth = 60;
//        int baseHeight = 20;
//
//        // Draw the base of the pawn (rectangle)
//        canvas.drawRect(x - baseWidth / 2, y + bodyHeight, x + baseWidth / 2, y + bodyHeight + baseHeight, greenPaint);
//
//        // Draw the body of the pawn (rectangle)
//        canvas.drawRect(x - bodyWidth / 2, y, x + bodyWidth / 2, y + bodyHeight, greenPaint);
//
//        // Draw the head of the pawn (circle)
//        canvas.drawCircle(x, y - headRadius, headRadius, greenPaint);
//
//        int rookBaseWidth = 60;
//        int rookBaseHeight = 20;
//        int rookBodyWidth = 40;
//        int rookBodyHeight = 80;
//        int rookTopWidth = 60;
//        int rookProngWidth = 10;
//        int rookProngHeight = 20;
//
//        // Position for rook (shifted over a bit)
//        int rookX = 350;
//        int rookY = 200;

//        // Draw rook base (rectangle)
//        canvas.drawRect(rookX - rookBaseWidth / 2, rookY + rookBodyHeight, rookX + rookBaseWidth / 2, rookY + rookBodyHeight + rookBaseHeight, redPaint);
//
//        // Draw rook body (rectangle)
//        canvas.drawRect(rookX - rookBodyWidth / 2, rookY, rookX + rookBodyWidth / 2, rookY + rookBodyHeight, redPaint);
//
//        // Draw rook top (rectangle for prongs area)
//        canvas.drawRect(rookX - rookTopWidth / 2, rookY - rookProngHeight, rookX + rookTopWidth / 2, rookY, redPaint);
//
//        // Draw rook prongs (3 prongs on top)
//        canvas.drawRect(rookX - rookTopWidth / 2, rookY - rookProngHeight, rookX - rookTopWidth / 2 + rookProngWidth, rookY, greenPaint);  // Left prong
//        canvas.drawRect(rookX - rookProngWidth / 2, rookY - rookProngHeight, rookX + rookProngWidth / 2, rookY, greenPaint);              // Middle prong
//        canvas.drawRect(rookX + rookTopWidth / 2 - rookProngWidth, rookY - rookProngHeight, rookX + rookTopWidth / 2, rookY, greenPaint);  // Right prong


    }

    protected void onDraw (Canvas canvas) {

        super.onDraw(canvas);

        for (DrawableElement element : elements) {
            Paint paint = null;
            paint.setColor(element.getColor());

            if (element.getShapeType() == DrawableElement.ShapeType.CIRCLE) {
                // Draw a circle
                canvas.drawCircle(element.getX(), element.getY(), element.getRadius(), paint);
            } else if (element.getShapeType() == DrawableElement.ShapeType.RECTANGLE) {
                // Draw a rectangle
                canvas.drawRect(element.getX(), element.getY(),
                        element.getX() + element.getWidth(),
                        element.getY() + element.getHeight(), paint);
            }
        }



    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (DrawableElement element : elements) {
                if (element.isTapped(event.getX(), event.getY())) {
                    selectedElement = element;
                    // Notify main activity that an element was selected
                    ((MainActivity) getContext()).onElementSelected(selectedElement);
                    invalidate();
                    return true;

                }
            }
        }


        return super.onTouchEvent(event);

    }


}
