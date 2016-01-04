package com.pancheng.barrageview.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.pancheng.barrageview.bean.BarrageItemBeanText;
import com.pancheng.barrageview.bean.BaseBarrageItemBean;
import com.pancheng.barrageview.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pancheng on 2015/11/18.
 */
public class BarrageViewText extends AbsBarrageView {

    private ArrayList<String> contentList;
    private ArrayList<BarrageItemBeanText> barrageItemBeanList;

    public BarrageViewText(Context context) {
        super(context);
    }

    public BarrageViewText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarrageViewText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarrageViewText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean isPosInBarrage(float x, float y, BaseBarrageItemBean barrageItem) {

        BarrageItemBeanText barrageItemN = (BarrageItemBeanText)barrageItem;

        return  barrageItemN.mPosX <= x &&
                x <= barrageItemN.mPosX + barrageItemN.bitmap.getWidth() &&
                barrageItemN.mPosY <= y &&
                y <= barrageItemN.mPosY + barrageItemN.bitmap.getHeight();


    }

    public void initWithData(Context context, ArrayList<String> contentList){
        this.contentList = contentList;
        this.init(context);
        this.mWidth  = this.getWidth();
        this.mHeight = this.getHeight();

        barrageItemBeanList = new ArrayList<>();

    }


    @Override
    protected void removeAllBarrage() {
        super.removeAllBarrage();
    }




}
