package com.liger.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Liger on 2016/5/25 18:27.
 */
public class CircleView extends View {

    //圆
    private Paint mCirclePaint;
    private float mCircleXY;
    private float mRadius;

    //椭圆
    private Paint mArcPaint;
    private RectF mArcRectf;
    private float mSweepAngel;
    private float mSweepValue = 66;

    //文字
    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;

    private int mMeasureWidth,mMeasureHeight;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        canvas.drawArc(mArcRectf,0,mSweepAngel,false,mArcPaint);
        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY,mCircleXY+(mShowTextSize/4),mTextPaint);
    }

    private void initView() {
        float length;//length取宽/高的最小值
        if (mMeasureHeight >= mMeasureWidth) {
            length = mMeasureWidth;
        }else{
            length = mMeasureHeight;
        }

        mCircleXY = length / 2;//圆心
        mRadius = (float) (length * 0.5 / 2);//半径
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth((float) (length * 0.07));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        //绘制弧线，需要指定其椭圆的外接矩形
        mArcRectf = new RectF((float) (length*0.1),(float) (length*0.1),(float) (length*0.9),(float) (length*0.9));
//        mSweepAngel = (mSweepValue / 100f) * 360f;//扫过的角度
        mSweepAngel = 90;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.05));//画笔宽度
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }
}
