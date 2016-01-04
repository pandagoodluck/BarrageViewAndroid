package com.pancheng.barrageview.util;

/**
 * Created by pancheng on 2015/11/18.
 */
public class Config {

    /**
     * Barrage View Config
     */
    public static int BARRAGE_VIEW_TYPE_TEXT = 0;
    public static int BARRAGE_VIEW_TYPE_WITH_IMG = 1;





    /**
     * Barrage Config
     */

    public static final int BARRAGE_NUM_MAX_FOR_QUERY = 200;
    public static final int BARRAGE_NUM_MAX_FOR_ONCE = 5;
    public static final int BARRAGE_NUM_MAX_FOR_DISPLAY = 20;
    public static final int BARRAGE_DUR_TIME = 3000;
    public static final int FADE_STEP = 100;
    public static final int ALPHA_STEP = 255 / (BARRAGE_DUR_TIME / FADE_STEP);
    public static final int MAX_ALPHA = 600;
    public static final int ALPHA_START_STEP = 100;

}
