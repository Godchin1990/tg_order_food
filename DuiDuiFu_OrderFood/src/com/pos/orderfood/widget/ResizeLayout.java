package com.pos.orderfood.widget;
import com.pos.orderfood.util.DataUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ResizeLayout extends RelativeLayout {  
  
	   private int width;
       protected OnSizeChangedListenner onSizeChangedListenner;
       private boolean sizeChanged  = false; //变化的标志
       private int height;
       private int screenWidth; //屏幕宽度
       private int screenHeight; //屏幕高度

       public ResizeLayout(Context paramContext,
                       AttributeSet paramAttributeSet) {
               super(paramContext, paramAttributeSet);
               this.screenWidth =DataUtil.getScreenWidth(paramContext);
               this.screenHeight = DataUtil.getScreenHeight(paramContext);
       }


       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
               this.width = widthMeasureSpec;
               this.height = heightMeasureSpec;
               super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       }
       
       @Override
       public void onSizeChanged(int w, int h, int oldw,
                       int oldh) {
               //监听不为空、宽度不变、当前高度与历史高度不为0 
               if ((this.onSizeChangedListenner!= null) && (w == oldw) && (oldw != 0)
                               && (oldh != 0)) {
                       if ((h >= oldh)
                                       || (Math.abs(h - oldh) <= 1 * this.screenHeight / 4)) {
                               if ((h <= oldh)
                                               || (Math.abs(h - oldh) <= 1 * this.screenHeight / 4))
                                       return;
                               this.sizeChanged  = false;
                       } else {
                               this.sizeChanged  = true;
                       }
                       this.onSizeChangedListenner.onSizeChange(this.sizeChanged ,oldh, h);
                       measure(this.width - w + getWidth(), this.height
                                       - h + getHeight());
               }
       }
       /**
        * 设置监听事件
        * @param paramonSizeChangedListenner
        */
       public void setOnSizeChangedListenner(
                       ResizeLayout.OnSizeChangedListenner paramonSizeChangedListenner) {
               this.onSizeChangedListenner = paramonSizeChangedListenner;
       }
       /**
        * 大小改变的内部接口
        * @author junjun
        *
        */
       public abstract interface OnSizeChangedListenner {
               public abstract void onSizeChange(boolean paramBoolean, int w,int h);
       }
}
