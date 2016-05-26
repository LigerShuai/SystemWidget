package com.liger.myapplication;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Liger on 2016/5/25.
 */
public class MyTopBar extends RelativeLayout {

    private Button mLeftButton,mRightButton;
    private TextView mTitleView;

    //布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams,mRightParams,mTitleParams;

    //标题的属性值，即在attrs中定义的属性
    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;

    //左按钮的属性值
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private TopbarClickListener topbarClickListener;

    public MyTopBar(Context context) {
        super(context);
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取自定义属性值，将attrs中定义的所有属性存储到TypedArray中
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        //从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);

        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);

        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_myTitleTextColor, 0);
        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_myTitleTextSize, 10);
        mTitle = typedArray.getString(R.styleable.TopBar_myTitle);

        //获取完TypedArray属性值后，调用recyle方法来完成资源的回收，避免重新创建的时候的错误
        typedArray.recycle();

        /* 组合控件 */
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组建元素赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setText(mTitle);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton,mLeftParams);//添加view到viewgroup

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);

        /**
         * 为按钮增加点击事件，不做具体的实现，只调用接口的方法
         */
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topbarClickListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topbarClickListener.rightClick();
            }
        });
    }

    /**
     * 提供公共方法动态修改UI
     * @param id 区分按钮
     * @param flag 区分是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        switch (id) {
            case 0:
                if (flag) {
                    mLeftButton.setVisibility(VISIBLE);
                } else {
                    mLeftButton.setVisibility(GONE);
                }
                break;
            case 1:
                if (flag) {
                    mRightButton.setVisibility(VISIBLE);
                } else {
                    mRightButton.setVisibility(GONE);
                }
                break;
        }
    }


    /**
     * 暴露接口给调用者，以便调用者注册接口
     * @param topbarClickListener
     */
    public void setOnTopbarClickListener(TopbarClickListener topbarClickListener) {
        this.topbarClickListener = topbarClickListener;
    }

    /* 定义接口 */
    public interface TopbarClickListener{
        //按钮点击事件
        void leftClick();

        void rightClick();
    }
}
