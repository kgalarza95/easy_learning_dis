package com.example.proyfragmentmodal.juego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.proyfragmentmodal.R;

public class DibujoMove extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    private Region mLinesRegion;
    private boolean mIsDrawingInsideLines;
    private int mScore;

    private ShapeDrawable mSquare;

    private Region mLetterRegion;
    private boolean mIsDrawingInsideLetter;
    private boolean mIsDrawingInsideSquare;

    private static final int SQUARE_SIZE = 500;

    public DibujoMove(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Cargar imagen
        //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_a);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_a);
        mBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);

        // Inicializar variables para dibujar
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);

        // Crear región para las líneas de la imagen
        //mLinesRegion = createLinesRegion();

        // Crear cuadrado para delimitar el área de pintado
        /*int left = (getWidth() - SQUARE_SIZE) / 2;
        int top = (getHeight() - SQUARE_SIZE) / 2;
        int right = left + SQUARE_SIZE;
        int bottom = top + SQUARE_SIZE;
        RectShape squareShape = new RectShape();
        squareShape.resize(SQUARE_SIZE, SQUARE_SIZE);
        mSquare = new ShapeDrawable(squareShape);
        mSquare.setBounds(left, top, right, bottom);

        Rect rect = mSquare.getBounds();
        mLetterRegion = new Region(rect.left, rect.top, rect.right, rect.bottom);*/

        // Crear rectángulo para la zona permitida para pintar
       /* Rect rect = new Rect(getWidth() / 2 - 100, getHeight() / 2 - 100, getWidth() / 2 + 100, getHeight() / 2 + 100);
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);*/
        // mCanvas.drawRect(rect, rectPaint);


       // mLetterRegion = new Region(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Dibujar imagen
        canvas.drawBitmap(mBitmap, 0, 0, null);

        // Dibujar trazo
        canvas.drawPath(mPath, mPaint);

/*
        //dibujar cuadrado
        Rect rect = new Rect(getWidth() / 2 - 100, getHeight() / 2 - 100, getWidth() / 2 + 100, getHeight() / 2 + 100);
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);
        canvas.drawRect(rect, rectPaint);
        mLetterRegion = new Region(rect.left, rect.top, rect.right, rect.bottom);

        // Definir las coordenadas de la letra "A"
        Path pathA = new Path();
        pathA.moveTo(0, 100);
        pathA.lineTo(50, 0);
        pathA.lineTo(100, 100);
        pathA.moveTo(25, 50);
        pathA.lineTo(75, 50);

        // Crear la región a partir de las coordenadas
        Region regionA = new Region();
        Region clip = new Region(0, 0, 500, 500); // Establecer un clip
        regionA.setPath(pathA, clip);

        // Definir el Paint
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // Dibujar la región en el Canvas
        canvas.drawPath(regionA.getBoundaryPath(), paint);*/


      /*  // Crear la región
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(100, 0);
        path.lineTo(50, 50);
        path.lineTo(0, 0);
        path.moveTo(50, 0);
        path.lineTo(50, 100);

        Region region = new Region();
        region.setPath(path, new Region(0, 0, 100, 100));


        // Dibujar la región en el Canvas
        canvas.drawPath(region.getBoundaryPath(), paint);*/
    }


    /*@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar imagen
        canvas.drawBitmap(mBitmap, 0, 0, null);

        // Dibujar trazo
        canvas.drawPath(mPath, mPaint);

        // Comprobar si el trazo intersecta las líneas
        Region pathRegion = createPathRegion();
        if (pathRegion.op(mLinesRegion, Region.Op.INTERSECT)) {
            Log.i("====================> ", "El trazo intersecta las líneas, el usuario está dibujando dentro de ellas");
            // El trazo intersecta las líneas, el usuario está dibujando dentro de ellas
            if (!mIsDrawingInsideLines) {
                Log.i("==================> ", "El usuario ha vuelto a dibujar dentro de las líneas");
                // El usuario ha vuelto a dibujar dentro de las líneas
                // después de haberse salido, así que sumar puntos
                mScore++;
            }
            mIsDrawingInsideLines = true;
        } else {
            Log.i("==================> ", "El trazo no intersecta las líneas, el usuario se está saliendo");
            // El trazo no intersecta las líneas, el usuario se está saliendo
            mIsDrawingInsideLines = false;
        }
    }*/





   /* @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar imagen
        canvas.drawBitmap(mBitmap, 0, 0, null);

        // Dibujar trazo
        canvas.drawPath(mPath, mPaint);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
         /*   case MotionEvent.ACTION_UP:// alza el dedo
                // Finalizar el trazo
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();

                // Crea la región a partir del path y obtiene los límites
                Region region = new Region();
                region.setPath(mPath, new Region(0, 0, getWidth(), getHeight()));
                Rect bounds = region.getBounds();
                Log.d("TAG", "Path: " + mPath.toString());
                Log.d("TAG", "Region bounds: " + bounds.toString());
                return true;
                //break;*/

            case MotionEvent.ACTION_DOWN:// se toca la pantalla
                mPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);

                Region pathRegion_ = createPathRegion();
                if (pathRegion_.op(mLetterRegion, Region.Op.INTERSECT)) {
                    //Log.i("TAG", "Está dentro del cuadro");
                } else {
                    //Log.i("TAG", "Está fuera del cuadro");
                }

              /*  // Crear región para el trazo actual
                Region pathRegion = createPathRegion();

                // Comprobar si la región del trazo actual intersecta con la región del cuadrado
                if (pathRegion.op(mSquare.getBounds(), Region.Op.INTERSECT)) {
                    // El trazo intersecta con el cuadrado
                    if (!mIsDrawingInsideSquare) {
                        Log.i("========>", "El usuario ha vuelto a dibujar dentro del cuadrado");
                        // El usuario ha vuelto a dibujar dentro del cuadrado
                        // después de haberse salido, así que sumar puntos
                        mScore++;
                    }
                    mIsDrawingInsideSquare = true;
                } else {
                    Log.i("========>", "El trazo no intersecta con el cuadrado");
                    // El trazo no intersecta con el cuadrado
                    mIsDrawingInsideSquare = false;
                }*/

                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    private Region createPathRegion() {
       /* Region region = new Region();
        Rect bounds = new Rect(getLeft(), getTop(), getRight(), getBottom());
        region.setPath(mPath, new Region(bounds));

        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);
        Canvas canvas = new Canvas();
        canvas.drawRect(bounds, rectPaint);*/

        RectF bounds = new RectF();
        mPath.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(mPath, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));


        return region;
    }


    private Button clearButton;

    public void setClearButton(Button button) {
        clearButton = button;
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPath.reset();
                invalidate();
            }
        });
    }

    public void clearLinea() {
        //mCanvas.drawColor(Color.WHITE);
        // Dibujar imagen
//       mCanvas.drawBitmap(mBitmap, 0, 0, null);
       // mCanvas = new Canvas(mBitmap);


       // mCanvas.setBitmap(mBitmap);

        // Dibujar imagen
      /*  Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_a);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        mCanvas.drawBitmap(backgroundBitmap, null, rect, null);
*/
        mPath.reset();
        invalidate();
    }


}
