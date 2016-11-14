package com.example.libo.Object_animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by libo on 2016/11/14.
 */

public class AnimationView extends View implements ValueAnimator.AnimatorUpdateListener {


    private Bitmap preBitmap;
    private Canvas preCanvas;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ObjectAnimator mAnimatorX;
    private ObjectAnimator mAnimatorY;
    private Paint paint;
    protected float mPhaseX = 1f;
    private  int mPhaseY = 1;
    private Paint clearPaint;
    private Paint alphaPaint;

    private float position[][];
    private float width;
    private float height;
    private float step;


    public AnimationView(Context context) {
        super(context);
        init();
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        preBitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_4444);
        preCanvas = new Canvas(preBitmap);

        mBitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);


        alphaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        alphaPaint.setStyle(Paint.Style.FILL);
        alphaPaint.setAlpha(225);


        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setStyle(Paint.Style.FILL);
        clearPaint.setColor(Color.WHITE);



    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("test", "onlayout" + mPhaseX);
        width = getWidth();
        height = getHeight();
        step = 15f;

        position = new float[500][2];
        for (int i = 0; i < 500; i++) {
            position[i][0] = (float) (width * Math.random());
            position[i][1] = (float) (height * Math.random());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCanvas.drawRect(0,0,mCanvas.getWidth(),mCanvas.getHeight(), clearPaint);
        mCanvas.drawBitmap(preBitmap, 0, 0, new Paint(Paint.ANTI_ALIAS_FLAG));
        for (int i = 0; i < 500; i++) {
            position[i][0] = position[i][0] + (float) (Math.random() - 0.5) * step;
            position[i][1] = position[i][1] + (float) (Math.random() - 0.5) * step;
            mCanvas.drawCircle(position[i][0],  position[i][1], 5, paint);
        }


//        position[0][0] = position[0][0] + (float) (Math.random() - 0.5) * step;
//        mCanvas.drawCircle(position[0][0],  position[0][1], 20, paint);

        Log.i("test", "" + position[0][0]);


//        float changeX;
//        float changeY;
//        if (mPhaseX < 1) {
//            changeX = mPhaseX * 500;
//            changeY = mPhaseX * 800;
//        } else {
//            changeX = 500;
//            changeY = 800;
//        }



        preCanvas.drawRect(0,0,mCanvas.getWidth(),mCanvas.getHeight(), clearPaint);
        preCanvas.drawBitmap(mBitmap, 0, 0, alphaPaint);



        canvas.drawBitmap(mBitmap, 0, 0, new Paint(Paint.ANTI_ALIAS_FLAG));

    }

    public void animateX() {
        mAnimatorX = ObjectAnimator.ofFloat(this, "phaseX", 0f, 1.5f);
        mAnimatorX.setInterpolator(new LinearInterpolator());//线性加速
        mAnimatorX.setDuration(10000);
        mAnimatorX.addUpdateListener(this);
        mAnimatorX.start();


    }

    public void animateY() {
        mAnimatorY = ObjectAnimator.ofInt(this, "phaseY",  0 , 1000);
        mAnimatorY.setDuration(1000);
        mAnimatorY.addUpdateListener(this);
        mAnimatorY.start();
    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

        invalidate();
    }


    public float getPhaseX() {
        return mPhaseX;
    }

    public void setPhaseX(float phase) {
        mPhaseX = phase;
    }

    public int getPhaseY() {
        return mPhaseY;
    }

    public void setPhaseY(int phase) {
        mPhaseY = phase;
    }
}
