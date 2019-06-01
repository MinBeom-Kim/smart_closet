package com.example.smart_closet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ImageInsertActivity extends LinearLayout {
    public ImageInsertActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageInsertActivity(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_insert,this,true);
    }
}
