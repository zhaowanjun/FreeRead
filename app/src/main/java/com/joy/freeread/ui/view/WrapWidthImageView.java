package com.joy.freeread.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WrapWidthImageView extends ImageView {

    public WrapWidthImageView(Context context) {
        super(context);
    }

    public WrapWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  
        Drawable d = getDrawable();  
  
        if(d!=null){  
            //宽度根据高度充满屏幕而计算得到
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int width = (int) Math.ceil((float) height * (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight());
            setMeasuredDimension(width, height);  
        }else{  
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
        }  
    }  
  
}  