package com.example.qiyue.materialdesignadvance.demo.nine_gride_password;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/10/24.
 */
public class NineGridPassword extends View {
    private Paint mPaint;
    private static final int COUNT = 3;
    Cell [][] cell;
    int [][] selectedCell;
    List<Cell> cells = new ArrayList<>();
    int RADIUS ,OFFSET;
    int ScreenWidth,ScreenHeight;
    int startX,startY,selectedCount;
    List<Cell>rightlist = new ArrayList<>();
    /**
     * 用来增加用户体验，保存拖动中的点
     */
    int lastX,lastY;
    boolean isError = false;
    public NineGridPassword(Context context) {
        super(context);
        init(context);
    }

    public NineGridPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NineGridPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.STROKE);

        //获取屏幕的宽度和高度
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        cell = new Cell[3][3];
        selectedCell = new int[3][3];
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
        RADIUS = ScreenWidth / 12; //半径
        OFFSET = RADIUS*3; //点之间的间距 3个半径

        startX = OFFSET; //起始点横坐标，其他一次增加3个r
        startY = (ScreenHeight - OFFSET * 2) / 2; //起始点纵坐标

        initCell();
        Cell cell1 = new Cell();
        cell1.setX(cell[1][1].getX());
        cell1.setY(cell[1][1].getY());
        rightlist.add(cell1);

        Cell cell2 = new Cell();
        cell2.setX(cell[1][2].getX());
        cell2.setY(cell[1][2].getY());
        rightlist.add(cell2);
        Cell cell3 = new Cell();
        cell3.setX(cell[2][1].getX());
        cell3.setY(cell[2][1].getY());
        rightlist.add(cell3);

        Cell cell4 = new Cell();
        cell4.setX(cell[2][2].getX());
        cell4.setY(cell[2][2].getY());
        rightlist.add(cell4);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawLine(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tmpIndex;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //drawFinish = false;
                initCell();
                cells.clear();
                isError = false;
                inWhichCircle((int)event.getX(),(int)event.getY());
                this.postInvalidate();

                break;
            case MotionEvent.ACTION_MOVE:
               // if(drawFinish == false){
                   inWhichCircle((int)event.getX(),(int)event.getY());
              //  }
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                this.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:

                //drawFinish = true;
                lastX = lastY = 0;
                selectedCount = 0;
              //  initCell();
                validateRight();
                //this.postInvalidate();
                break;

        }
        return true;
    }

    private void validateRight() {
        /**
         * 可以设置回调进行校验
        */
        boolean right = false;
        int rightNumber = 0;
        if (cells.size()==rightlist.size()) {
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getX() == rightlist.get(i).getX() &&
                        cells.get(i).getY() == rightlist.get(i).getY()) {
                    rightNumber ++;
                }

            }
        }
        if (rightNumber==4){
            L.i("rightrightright");
        }else{
            isError = true;
        }
        postInvalidate();

    }

    private void initCell() {
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                cell[i][j] = new Cell();
                cell[i][j].setIsSelected(false);
                cell[i][j].setX(startX + OFFSET * j);
                cell[i][j].setY(startY + OFFSET * i);

            }
        }
    }

    /**
     * 判断x,y在那个圆内,如果在就变为选中状态，加到集合
     * * @param x
     * @param y
     * @return
     */
    private void inWhichCircle(int x, int y){
        for(int i = 0 ; i < 3 ; i++){
            for(int j =0;j< 3; j++){
                if (cell[i][j].isSelected()==false) {
                    if ((Math.abs(x - cell[i][j].getX()) < RADIUS) && Math.abs(y - cell[i][j].getY()) < RADIUS) {
                        cell[i][j].setIsSelected(true);
                        cells.add(cell[i][j]);
                    }
                }
            }
        }
    }


    private void drawCircle(Canvas canvas) {
        for(int i = 0 ; i < 3 ; i++){
            for (int j = 0;j <3; j++){
                if (cell[i][j].isSelected()){
                    if (isError){
                        mPaint.setColor(Color.RED);
                    }else {
                        mPaint.setColor(Color.GREEN);
                    }
                    mPaint.setStrokeWidth(20);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    //画圆
                    canvas.drawCircle(cell[i][j].getX(),cell[i][j].getY(),RADIUS,mPaint);
                    mPaint.setStrokeWidth(30);
                    //画点
                    canvas.drawPoint(cell[i][j].getX(),cell[i][j].getY(),mPaint);
                }else{
                    mPaint.setColor(Color.WHITE);
                    mPaint.setStrokeWidth(5);
                    //画圆
                    canvas.drawCircle(cell[i][j].getX(),cell[i][j].getY(),RADIUS,mPaint);
                    //画点
                    canvas.drawPoint(cell[i][j].getX(),cell[i][j].getY(),mPaint);
                }
            }
        }
    }


    private void drawLine(Canvas canvas) {
        if(isError) {
            mPaint.setColor(Color.RED);
        }else {
            mPaint.setColor(Color.GREEN);
        }
        mPaint.setStrokeWidth(20);
        for(int i = 1 ; i < cells.size() ; i++){
            Cell lastCell = cells.get(i-1);
            Cell thisCell = cells.get(i);
            canvas.drawLine(lastCell.getX(), lastCell.getY(), thisCell.getX(), thisCell.getY(), mPaint);
        }

        if(cells.size() !=0 &&(lastX !=0 || lastY != 0)){
            canvas.drawLine(cells.get(cells.size()-1).getX(), cells.get(cells.size()-1).getY(), lastX, lastY, mPaint);
        }
    }
}
