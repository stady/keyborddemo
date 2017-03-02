package com.stady.keyboardnum;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

/**
 * Created by zhuanghongji on 2015/12/10.
 */
public class MyKeyboardView extends KeyboardView {

    private Drawable mKeyBgDrawable;
    private Drawable mOpKeyBgDrawable;
    private Drawable mOpKeyUnAbleDrawable;
    private Resources mRes;

    private boolean mConfirmButtonEnable =true;

    public MyKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context);
    }

    private void initResources(Context context) {
        mRes = context.getResources();
        mKeyBgDrawable=DeprecatedMethodUtils.deResources_getDrawable(mRes,R.drawable.btn_keyboard_key,getContext().getTheme());
        mOpKeyBgDrawable= DeprecatedMethodUtils.deResources_getDrawable(mRes,R.drawable.btn_keyboard_opkey,getContext().getTheme());
        mOpKeyUnAbleDrawable = DeprecatedMethodUtils.deResources_getDrawable(mRes,R.drawable.btn_keyboard_key,getContext().getTheme());
    }


    private int mKeyWith =-1;
    private int mKeyHeight =-1;
    @Override
    public void onDraw(Canvas canvas) {
        //每次都重新绘制  可以根据不同的xml 绘制不同布局
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (int i=0;i<keys.size();i++) {
            Keyboard.Key key =keys.get(i);
            Log.d("MyKeyboardView",key.codes[0]+"====width=="+key.width+"=====x======="+key.x);
            int row = rowByCount(i+1);
            int num =numByCount(i+1);
            key.x = xByRowNum(row,num,key.gap);
            key.width =widthByRowNum(row,num,key.gap);
            key.height=heightByRowNum(row,num,key.gap);

            canvas.save();

            //end
            //从0开始画
            int offsetY = 0;
            if (key.y == 0) {
                offsetY = 1;
            }
            int initDrawY = key.y + offsetY;
            Rect rect = new Rect(key.x, initDrawY, key.x + key.width, key.y + key.height);
            canvas.clipRect(rect);

            int primaryCode = -1;
            if (null != key.codes && key.codes.length != 0) {
                primaryCode = key.codes[0];
            }


            Drawable drawable = null;
            //-3 confirm -5 del
            if (primaryCode ==-4) {

                if (mConfirmButtonEnable){
                    drawable = mOpKeyBgDrawable;
                }else{
                    drawable = mOpKeyUnAbleDrawable;
                }
            } else if (primaryCode != -1) {
                drawable = mKeyBgDrawable;
            }

            if (null != drawable) {
                int[] state = key.getCurrentDrawableState();
                drawable.setState(state);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(50);
            //-3 confirm -5 del
            if (primaryCode ==-4) {
                paint.setColor(mRes.getColor(R.color.n_keyboard_confirm_text_color));
            }else{
                paint.setColor(mRes.getColor(R.color.black));
            }


            if (key.label != null) {
                canvas.drawText(
                        key.label.toString(),
                        key.x + (key.width / 2),
                        initDrawY + (key.height + paint.getTextSize() - paint.descent()) / 2,
                        paint
                );
            } else if (key.icon != null) {
                int intriWidth = key.icon.getIntrinsicWidth();
                int intriHeight = key.icon.getIntrinsicHeight();

                final int drawableX = key.x + (key.width - intriWidth) / 2;
                final int drawableY = initDrawY + (key.height - intriHeight) / 2;

                key.icon.setBounds(drawableX, drawableY,
                        drawableX + intriWidth, drawableY + intriHeight);
                key.icon.draw(canvas);
            }

            canvas.restore();
        }
    }


    protected int xByRowNum(int row,int num,int gap){
        return (num-1)*widthByRowNum(row,num,gap)+(num-1)*gap;
    }
    protected int widthByRowNum(int row,int num,int gap){
        int minWith =  getKeyboard().getMinWidth();
        return (minWith-(gap*3))/4;
    }
    protected int heightByRowNum(int row,int num,int gap){
        int minHeight =  getKeyboard().getHeight();
        int baseHeight =  (minHeight-(gap*3))/4;
        int okButtonHeight =(minHeight-gap*1)/2;
        if (row==1&&num==4){
            return okButtonHeight;
        }
        if (row==3&&num==4){
            return okButtonHeight;
        }
        return baseHeight;
    }

    protected int numByCount(int count){
        //当前项目 第一行 4 第二行 3 第三行 4 第四行 3
        if (count<=4){
            return count;
        }else if (count<=7){
            return count-4;
        }else if (count<=11){
            return count-7;
        }else{
            return count-11;
        }
    }

    protected int rowByCount(int count){
        //当前项目 第一行 4 第二行 3 第三行 4 第四行 3
        if (count<=4){
            return 1;
        }else if (count<=7){
            return 2;
        }else if (count<=11){
            return 3;
        }else{
            return 4;
        }
    }

    public void setConfirmButtonEnable(boolean confirmButtonEnable) {
        this.mConfirmButtonEnable = confirmButtonEnable;
    }
    public boolean getConfirmButtonEnable(){
        return this.mConfirmButtonEnable;
    }
}
