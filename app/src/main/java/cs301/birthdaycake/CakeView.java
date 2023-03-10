package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint locationPaint = new Paint();

    Paint balloonColor = new Paint();

    Paint balloonString = new Paint();
    Paint checkPaint1 = new Paint();
    Paint checkPaint2 = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 160.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    public static final float checkWidth = 75.0f;

    private CakeModel data;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFFFC7E8);  //violet-red, now pink
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        checkPaint1.setColor(0xFF92CD7E); //Green
        checkPaint1.setStyle(Paint.Style.FILL);
        checkPaint2.setColor(0xFF861818); //Red
        checkPaint2.setStyle(Paint.Style.FILL);
        balloonString.setColor(0xFF000000);
        balloonColor.setColor(0xFF1B78DB);

        locationPaint.setColor(Color.RED);
        setBackgroundColor(Color.WHITE);  //better than black default

        data = new CakeModel();

    }

    public CakeModel dataGetter(){
        return data;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if(data.hasCandles == true) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);


            if (data.candlesLit == true) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        for(int i = 1; i <= data.numCandles; ++i) {
            drawCandle(canvas, cakeLeft + i * cakeWidth / (data.numCandles + 1) - candleWidth / 2, cakeTop);
        }
        if (data.touched == true) {
            canvas.drawRect(data.x - checkWidth, data.y - checkWidth, data.x, data.y, checkPaint1);
            canvas.drawRect(data.x, data.y - checkWidth, data.x + checkWidth, data.y, checkPaint2);
            canvas.drawRect(data.x, data.y, data.x + checkWidth, data.y + checkWidth, checkPaint1);
            canvas.drawRect(data.x - checkWidth, data.y, data.x, data.y + checkWidth, checkPaint2);

            //drawCandle(canvas, cakeLeft + 2 * cakeWidth / 3 - candleWidth / 2, cakeTop);
            locationPaint.setTextSize(100);
            canvas.drawText(data.x + ", " + data.y, 1700F, 900F, locationPaint);

        }

        if(data.touched == true){
            canvas.drawCircle(data.x-10,data.y-30,60,balloonColor);
            canvas.drawOval(data.x-60,data.y-80,data.x+40,data.y+60,balloonColor);
            canvas.drawLine(data.x-10,data.y+60,data.x-10,data.y+160,balloonString);
        }
    }//onDraw

}//class CakeView

