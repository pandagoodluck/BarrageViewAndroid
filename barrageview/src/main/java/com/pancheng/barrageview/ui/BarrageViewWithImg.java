package com.pancheng.barrageview.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pancheng.barrageview.R;
import com.pancheng.barrageview.bean.BarrageItemBeanText;
import com.pancheng.barrageview.bean.BarrageItemBeanWithImg;
import com.pancheng.barrageview.bean.BaseBarrageItemBean;
import com.pancheng.barrageview.util.Config;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by pancheng on 2015/11/18.
 */
public class BarrageViewWithImg extends AbsBarrageView {

    private ArrayList<String> contentList;
    private LayoutInflater mInflater;
    private ArrayList<BarrageItemBeanWithImg> barrageItemBeanList;

    public BarrageViewWithImg(Context context) {
        super(context);
    }

    public BarrageViewWithImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarrageViewWithImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarrageViewWithImg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected boolean isPosInBarrage(float x, float y, BaseBarrageItemBean barrageItem) {

        BarrageItemBeanWithImg barrageItemN = (BarrageItemBeanWithImg)barrageItem;

        return  barrageItemN.mPosX <= x &&
                x <= barrageItemN.mPosX + barrageItemN.contentView.getWidth() &&
                barrageItemN.mPosY <= y &&
                y <= barrageItemN.mPosY + barrageItemN.contentView.getHeight();


    }

    public void setFrame(int mWidth, int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    public void initWithData(Context context, ArrayList<String> contentList,ArrayList<String> imgUrlList){
        this.contentList = contentList;
        this.init(context);
        this.mWidth  = this.getWidth();
        this.mHeight = this.getHeight();

        mInflater = LayoutInflater.from(context);
        barrageItemBeanList = new ArrayList<>();

        for(int i =0 ; i <contentList.size(); ++i){
            BarrageItemBeanWithImg barrageItemBean = createOneBarrage(contentList.get(i), imgUrlList.get(i));
            barrageItemBean.curAlpha = -i * Config.ALPHA_START_STEP;
            barrageItemBeanList.add(barrageItemBean);
        }

        this.setWillNotDraw(false);
        //start
        postInvalidate();

    }

    private BarrageItemBeanWithImg createOneBarrage(String content, String headrUrl) {
        RelativeLayout barrageItemRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.barrage_view_item,this,false);

        //set content of barrage
        SimpleDraweeView drawee = (SimpleDraweeView) barrageItemRelativeLayout.findViewById(R.id.id_drawee_barrage);
        drawee.setImageURI(Uri.parse(headrUrl));
        TextView txtView = (TextView) barrageItemRelativeLayout.findViewById(R.id.barrageItemContent);
        txtView.setText(content);
        // txtView.setMaxLines(3);
        txtView.getBackground().setAlpha(150);

        //set layout params
        int startY = getRandomPosY();
        int startX = getRandomPosX();
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlParams.setMargins(startX, startY, 0, 0);
        barrageItemRelativeLayout.setLayoutParams(rlParams);

        //init barrage item bean
        BarrageItemBeanWithImg barrageItemBean = getBarrageItemBean(content, headrUrl, barrageItemRelativeLayout, startY, startX);


        return barrageItemBean;
    }


    private BarrageItemBeanWithImg getBarrageItemBean(String content, String headrUrl, RelativeLayout barrageItemRelativeLayout, int startY, int startX) {
        BarrageItemBeanWithImg barrageItemBean = new BarrageItemBeanWithImg();
        barrageItemBean.mHeadUrl = headrUrl;
        barrageItemBean.mContent = content;
        barrageItemBean.curAlpha = 0;  //change in the loop
        barrageItemBean.mIsFadeIn = true;
        barrageItemBean.contentView = barrageItemRelativeLayout;
        barrageItemBean.mPosX = startX;
        barrageItemBean.mPosY = startY;
        barrageItemRelativeLayout.setAlpha(1);
        addView(barrageItemRelativeLayout);
        return barrageItemBean;
    }
    @Override
    protected void removeAllBarrage() {
        super.removeAllBarrage();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!isOpen || isPaused)
            return;
        //barrage is null
        if (barrageItemBeanList == null || barrageItemBeanList.size() == 0)
            return;
        //Is fading in
        for (int i = 0; i < barrageItemBeanList.size(); ++i) {
            BarrageItemBeanWithImg curBean = barrageItemBeanList.get(i);
            if (curBean.mIsFadeIn) {
                if (curBean.curAlpha <= Config.MAX_ALPHA) {
                    int tmpAlpha = curBean.curAlpha < 0 ? 0 : curBean.curAlpha;
                    tmpAlpha = tmpAlpha >255? 255:tmpAlpha;
                    curBean.contentView.setAlpha(tmpAlpha/255.0f);
                    curBean.curAlpha += Config.ALPHA_STEP;

                }
                //Fading in over
                else {
                    curBean.mIsFadeIn = false;
                    curBean.curAlpha = 255;
                    curBean.contentView.setAlpha(curBean.curAlpha/255.0f);
                }
            }
            //Is fading out
            else {
                if (curBean.curAlpha >= 0) {
                    curBean.contentView.setAlpha(curBean.curAlpha/255.0f);
                    curBean.curAlpha -= Config.ALPHA_STEP;
                }
                //fading out over
                else {
                    curBean.curAlpha = 0;
                    curBean.mIsFadeIn = true;
                    curBean.contentView.setAlpha(curBean.curAlpha/255.0f);

                    curBean.mPosX = getRandomPosX();
                    curBean.mPosY = getRandomPosY();
                    curBean.contentView.setX(curBean.mPosX);
                    curBean.contentView.setY(curBean.mPosY);
                }
            }
        }
        postInvalidateDelayed(Config.FADE_STEP);
    }

    private int getRandomPosY() {
        double random_position = Math.random();
        return (int) (random_position * (double) (mHeight - 100));
    }
    private int getRandomPosX() {
        double random_position2 = Math.random();
        return (int) ((random_position2 * 0.6) * (double) mWidth);
    }

}
