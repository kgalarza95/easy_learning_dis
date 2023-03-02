package com.example.proyfragmentmodal.estudiante;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CustomView extends View {

    private Paint paint;
    private Path path;
    private Button clearButton;
    private float lastX;
    private float lastY;
    private float startX, startY;
    private Tool currentTool = Tool.PENCIL;
    Canvas canvas;

    enum Tool {
        PENCIL,
        LINE,
        CIRCLE,
        RECTANGLE
    }


    //
    String action = "accion";

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();

        // Configuramos el tamaño de la vista personalizada
        setMinimumWidth(500);
        setMinimumHeight(500);
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        this.canvas = canvas;// canvas.drawLine(startX, startY, endX, endY, paint);

      /*  if (action.equals("down")){
            path.moveTo(x,y);
        }else  if (action.equals("move")){
            path.lineTo(x,y);
        }*/
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //dibujarCircular(event);
        dibujarLineal(event);
        //dibujarLinealTool(event);


        //dibujarBien(event);
        invalidate();// permite refescar y dibujar
        return true;
    }

    float x, y;

    private boolean dibujarBien(MotionEvent event) {
        Log.i("dibujarLineal==============>  ", "init");
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://solo click en la pantalla
                action = "down";
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                action = "move";
                path.lineTo(x, y);
                Log.i("MotionEvent==============>  ", "ACTION_MOVE");
                break;
            default:
                return false;
        }
        return true;
    }


    private boolean dibujarLinealTool(MotionEvent event) {
        Log.i("dibujarLineal==============>  ", "init");
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (currentTool == Tool.PENCIL) {
                    path.moveTo(x, y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentTool == Tool.PENCIL) {
                    path.lineTo(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentTool == Tool.LINE) {
                    canvas.drawLine(startX, startY, x, y, paint);
                } else if (currentTool == Tool.CIRCLE) {
                    float radius = (float) Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                } else if (currentTool == Tool.RECTANGLE) {
                    float left = Math.min(startX, x);
                    float top = Math.min(startY, y);
                    float right = Math.max(startX, x);
                    float bottom = Math.max(startY, y);
                    canvas.drawRect(left, top, right, bottom, paint);
                }
                break;
        }
        return true;
    }

    private boolean dibujarLineal(MotionEvent event) {
        Log.i("dibujarLineal==============>  ", "init");
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("MotionEvent==============>  ", "ACTION_DOWN");
                path.moveTo(x, y);
                /*lastX = x;
                lastY = y;*/
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i("MotionEvent==============>  ", "ACTION_MOVE");
                /*path.quadTo(lastX, lastY, (lastX + x) / 2, (lastY + y) / 2);
                lastX = x;
                lastY = y;*/

                /* path.reset();*/
                // path.moveTo(startX, startY);
                path.lineTo(x, y);
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean dibujarCircular(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            default:
                return false;
        }
        return true;
    }

  /*  private boolean dibujarLineal(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float lastX = path.isEmpty() ? x : path.get().x;
        float lastY = path.isEmpty() ? y : path.getLastPoint().y;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(lastX, lastY, (lastX + x) / 2, (lastY + y) / 2);
                break;
            default:
                return false;
        }
        return true;
    }*/
/*
    private boolean dibujarLineal(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                lastX = x;
                lastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                // traza una línea desde la última posición hasta la nueva posición
                path.lineTo(x, y);
                lastX = x;
                lastY = y;
                break;
            default:
                return false;
        }
        return true;
    }*/

    public void setClearButton(Button button) {
        clearButton = button;
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                path.reset();
                invalidate();
            }
        });
    }

    public void clearLinea() {
        path.reset();
        invalidate();
    }
}

