package com.stady.keyboardxml;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.util.ArrayMap;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by stady on 2017/3/3.
 * 1.xml布局点击事件和key数值对应
 * 2.xml按键点击回调
 */

public class KeyboardXmlPopManager implements View.OnClickListener {
    private Activity mContext;
    private HashMap<Integer,Integer> keyCodeMap;
    private View.OnClickListener keyClickCallBackListener;
    private  KeyboardView.OnKeyboardActionListener keyboardActionListener;



   public void init(){
       if (keyCodeMap==null){
           keyCodeMap = new HashMap<Integer, Integer>();
       }
   }
    public void setKeyCodeMap(HashMap<Integer,Integer> keyCodeMap){
        this.keyCodeMap = keyCodeMap;
        // refresh
    }

    @Override
    public void onClick(View v) {
        int keyViewId =v.getId();
        int keyCode = keyCodeMap.get(keyViewId);
        keyboardActionListener.onKey();
        if (keyClickCallBackListener!=null){
            keyClickCallBackListener.onClick(v);
        }

    }
}
