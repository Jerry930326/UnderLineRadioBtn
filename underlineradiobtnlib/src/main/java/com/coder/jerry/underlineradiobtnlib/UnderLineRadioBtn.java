package com.coder.jerry.underlineradiobtnlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;


/**
 * @创建者: Jerry
 * @创建时间: 2018-11-20  14:31
 * @包名: com.coder.jerry.underlineradiobtnlib
 * @工程名: UnderLineRadioBtn
 * @描述: 底部有下划线的RadioButton
 */
public class UnderLineRadioBtn extends AppCompatRadioButton {
    /*-------------内部需要使用的值-------------*/
    private Paint mLinePaint;
    // 控件本身宽高
    private int   mWidth, mHeight;
    // 如果没有Button的情况下，线会合文字贴在一起，这里可以加一点空隙
    private       float mSpaceHeight       = 0f;
    private final int   COLOR_202020       = Color.parseColor("#202020");
    // 内部的默认状态文字、下划线颜色
    private final int   DEFAULT_TEXT_COLOR = COLOR_202020, DEFAULT_LINE_COLOR = COLOR_202020;
    // 内部的选中状态文字、下划线颜色
    private final int CHECK_TEXT_COLOR = COLOR_202020, CHECK_LINE_COLOR = Color.parseColor("#ff1819");
    /*-------------内部需要使用的值-------------*/

    /*-------------来自外部传入的值-------------*/
    // 线的宽度、高度
    private float mLineWidth, mLineHeight;
    // 文字和下划线的默认颜色、选中状态颜色
    private int mTextDefaultColor = -0x001, mTextCheckColor = -0x001, mLineDefaultColor, mLineCheckColor;
    // 下划线的圆角
    private float mLineRadius;
    private @SuppressLint("DrawAllocation")
    RectF mRectF;
    /*-------------来自外部传入的值-------------*/

    public UnderLineRadioBtn(Context context) {
        super(context);
        init(context, null);
    }

    public UnderLineRadioBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UnderLineRadioBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /*初始化*/
    @SuppressLint("PrivateResource")
    private void init(Context context, AttributeSet attrs) {
        if (mLinePaint == null)
            mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setAntiAlias(true);
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderLineRadioBtn);
        try {
            mLineWidth = typedArray.getDimension(R.styleable.UnderLineRadioBtn_lineW, 0f);
        } catch (Exception e) {
            mLineWidth = typedArray.getFloat(R.styleable.UnderLineRadioBtn_lineW, 0f);
        }
        mLineHeight = typedArray.getDimension(R.styleable.UnderLineRadioBtn_lineH, 0f);
        //如果没有给文字设置颜色
        mTextDefaultColor = typedArray.getColor(R.styleable.UnderLineRadioBtn_textDefaultColor, DEFAULT_TEXT_COLOR);
        mTextCheckColor = typedArray.getColor(R.styleable.UnderLineRadioBtn_textCheckColor, CHECK_TEXT_COLOR);
        mLineDefaultColor = typedArray.getColor(R.styleable.UnderLineRadioBtn_lineDefaultColor, DEFAULT_LINE_COLOR);
        mLineCheckColor = typedArray.getColor(R.styleable.UnderLineRadioBtn_lineCheckColor, CHECK_LINE_COLOR);
        mLineRadius = typedArray.getDimension(R.styleable.UnderLineRadioBtn_lineRadius, 0f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //触发原有的测量过程
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        if (mLineHeight > 0) {
            // 下划线的高度合并进控件高度
            mHeight += mLineHeight;
            //这里还要判断是否有按钮，没有按钮的时候线会和文字紧紧贴在一起，不美观
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getButtonDrawable() == null) {
                    mSpaceHeight = 20f;
                }
            }
            mHeight += mSpaceHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 在super.onDraw(canvas)后绘制，有关绘制顺序可查看：https://hencoder.com/ui-1-5/，感谢扔物线大佬

        if (mTextCheckColor != -0x001 && mTextDefaultColor != -0x001) {
            setTextColor(isChecked() ? mTextCheckColor : mTextDefaultColor);
        }

        // 线的高度小于、等于0，不绘制线了
        if (mLineHeight <= 0)
            return;
        if (mLineWidth <= 0 || mLineWidth == 1) {
            //这里说明用户没有手动设置宽度或者是设置了百分比为1，那么就将其设置为控件本身的宽度
            mLineWidth = mWidth;
        } else if (mLineWidth < 1) {
            //设置的百分比，计算出宽度
            mLineWidth = mWidth * mLineWidth;
        }
        //开始画线
        //1、计算线的起点和终点
        float left, top = mHeight - mLineHeight, right, bottom = mHeight;
        if (mLineWidth < mWidth) {
            //线的宽度比控件本身宽度小，那要让线居中，所以要进行计算
            left = (mWidth - mLineWidth) / 2;
            right = (mWidth - mLineWidth) / 2 + mLineWidth;
        } else {
            //线的宽度不比控件本身宽度小
            left = 0f;
            right = mLineWidth;
        }
        //2、设置线的颜色
        mLinePaint.setColor(isChecked() ? mLineCheckColor : mLineDefaultColor);
        //3、绘制
        if (mLineRadius > 0) {
            //画圆角矩形
            if (mRectF == null)
                mRectF = new RectF(left, top, right, bottom);
            else
                mRectF.set(left, top, right, bottom);
            canvas.drawRoundRect(mRectF, mLineRadius, mLineRadius, mLinePaint);
        } else {
            //画矩形
            canvas.drawRect(left, top, right, bottom, mLinePaint);
        }
    }

    /**
     * 设置下划线的圆角
     */
    public void setLineRadius(float LineRadius) {
        mLineRadius = LineRadius;
        postInvalidate();
    }

    /**
     * 设置下划线的默认/选中颜色
     *
     * @param LineDefaultColor 下划线默认颜色
     * @param LineCheckColor   下划线选中颜色
     */
    public void setLineColor(int LineDefaultColor, int LineCheckColor) {
        mLineDefaultColor = LineDefaultColor;
        mLineCheckColor = LineCheckColor;
        postInvalidate();
    }

    /**
     * 设置下划线的选中颜色
     */
    public void setLineCheckColor(int LineCheckColor) {
        setLineColor(mLineDefaultColor, LineCheckColor);
    }

    /**
     * 设置下划线的默认颜色
     */
    public void setLineDefaultColor(int LineDefaultColor) {
        setLineColor(LineDefaultColor, mLineCheckColor);
    }

    /**
     * 设置文字的默认/选中颜色
     *
     * @param TextDefaultColor 文字默认颜色
     * @param TextCheckColor   文字选中颜色
     */
    public void setTextColor(int TextDefaultColor, int TextCheckColor) {
        mTextDefaultColor = TextDefaultColor;
        mTextCheckColor = TextCheckColor;
        postInvalidate();
    }

    /**
     * 设置文字的选中颜色
     */
    public void setTextCheckColor(int TextCheckColor) {
        setTextColor(mTextDefaultColor, TextCheckColor);
    }

    /**
     * 设置文字的默认颜色
     */
    public void setTextDefaultColor(int TextDefaultColor) {
        setTextColor(TextDefaultColor, mTextCheckColor);
    }

    /**
     * 设置线的宽度
     */
    public void setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        postInvalidate();
    }

    /**
     * 设置线的高度
     */
    public void setLineHeight(float lineHeight) {
        mLineHeight = lineHeight;
        postInvalidate();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        postInvalidate();
    }
}