package com.pancheng.barrageview.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.pancheng.barrageview.bean.BaseBarrageItemBean;

import java.util.ArrayList;

/**
 * Created by pancheng on 2015/11/18.
 */
public abstract class AbsBarrageView extends RelativeLayout {

    /**
     * Height of Barrage View
     */
    protected int mHeight;


    /**
     * Width of Barrage View
     */
    protected int mWidth;


    /**
     * Switch
     */
    protected Boolean isOpen = true;

    protected Boolean isPaused =false;


    /**
     * Context of this layout
     */
    protected Context mContext;

    /**
     * Content Data List
     */
    protected ArrayList<BaseBarrageItemBean> barrageList;

    /**
     * Detector for Gesture
     */
    protected GestureDetectorCompat mDetector;

    protected ArrayList<Integer> clickList;

    protected LayoutInflater mInflater;



    /**
     * @param context
     */
    public AbsBarrageView(Context context) {
        super(context);
    }


    /**
     * @param context
     * @param attrs
     */
    public AbsBarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public AbsBarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbsBarrageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    protected void init(Context context){
        barrageList = new ArrayList<>();
        clickList = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDetector = new GestureDetectorCompat(mContext, new BVGestureListener());
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
            }
        });


        setWillNotCacheDrawing(false);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public class BVGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
            clickList.clear();
            for (int i = barrageList.size() - 1; i >= 0; --i) {
               if(isPosInBarrage(x,y,barrageList.get(i)))
                    clickList.add(i);

            }
            int clickIndex = -1;
            if (clickList.size() !=0 ){
                int maxAlpha =0;
                for(int i=0;i <clickList.size();++i){
                    if (barrageList.get(clickList.get(i)).curAlpha > maxAlpha){
                        maxAlpha = barrageList.get(clickList.get(i)).curAlpha;
                        clickIndex = clickList.get(i);
                    }
                }
            }
            if (clickIndex == -1) {

            }

            else
                ;
            return true;

        }
    }


    protected boolean isPosInBarrage(float x, float y, BaseBarrageItemBean barrageItem){
        return true;
    }


    protected void removeAllBarrage(){}



}
