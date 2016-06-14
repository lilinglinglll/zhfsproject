package lll.lab516view;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import lll.test.R;

public class Lab516View extends View implements View.OnClickListener {

    private Canvas canvas516;
    private Context mContext;

    private float  ratio= (float) 1.19;

    float width=1290;
    float height=1160;

    float r1_width=490;
    float r1_height=410;

    float r2_width=490;
    float r2_height=750;

    float r3_width=800;
    float r3_height=770;

    float r4_width=800;
    float r4_height=390;
    private RectF room1  = new RectF(0, r2_height / ratio+80, r1_width / ratio, height / ratio+80);
    private RectF room2 = new RectF(0, 80, r2_width / ratio, r2_height / ratio+80);
    private RectF room3 = new RectF(r1_width / ratio, r4_height / ratio+80, width / ratio, height / ratio+80);
    private RectF room4 = new RectF(r1_width / ratio, 80, width / ratio, r4_height / ratio+80);

    public RectF getRoom1() {
        return room1;
    }

    public RectF getRoom4() {
        return room4 ;
    }

    public RectF getRoom3() {
        return room3 ;
    }

    public RectF getRoom2() {
        return room2 ;
    }

    public Lab516View(Context context) {
        super(context);

    }
    public Lab516View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Lab516View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//        int actualheightsize=heightSize-80;
//        if(width<widthSize & height<actualheightsize){ratio=1;}
//        else {ratio=width/widthSize;}
//
//        System.out.println("===================================labratio" + ratio);

        int contentwidth= (int) (width/ratio);
        int contentheight= (int) (height/ratio);
        setMeasuredDimension(contentwidth, contentheight + 80);
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed,left,top,right,bottom);
    }

    @Override
        protected void onDraw(Canvas canvas) {
        mContext=getContext();
        canvas516=canvas;
        super.onDraw(canvas516);
        // 把整张画布绘制成白色
        canvas516.drawColor(Color.WHITE);
        Paint paint = new Paint();
        // 去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // 绘制圆形
        //外边界
        canvas516.drawRect(0, 80, width / ratio, height / ratio + 80, paint);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(getResources().getColor(R.color.room2));
        canvas516.drawRect(room2, paint);
        paint.setColor(getResources().getColor(R.color.room1));
        canvas516.drawRect(room1, paint);
        paint.setColor(getResources().getColor(R.color.room3));
        canvas516.drawRect(room3, paint);
        paint.setColor(getResources().getColor(R.color.room4));
        canvas516.drawRect(room4, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas516.drawText("room1", room1.centerX(), room1.centerY() , paint);
        canvas516.drawText("room2", room2.centerX(), room2.centerY() , paint);
        canvas516.drawText("room3", room3.centerX(), room3.centerY() , paint);
        canvas516.drawText("room4", room4.centerX(), room4.centerY() , paint);

        canvas516.drawText("516实验室", 150, 60, paint);
    }
    @Override
    public void onClick(View v) {

    }
}
