package com.lm.ldar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;


/**
 * Created by fanshengyue on 2018/1/14
 */
public class PictureTagLayout extends RelativeLayout implements View.OnTouchListener{
    private static final int CLICKRANGE = 5;
    int startX = 0;
    int startY = 0;
    int startTouchViewLeft = 0;
    int startTouchViewTop = 0;
    private View touchView,clickView;
    private Context mContext;
    private long time;
    public PictureTagLayout(Context context) {
        super(context, null);
        this.mContext=context;
    }
    public PictureTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }
    private void init(){
        this.setOnTouchListener(this);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        long exitTime=0 ;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                exitTime= System.currentTimeMillis();
                time=exitTime;
                Log.i("开始时间",exitTime+"");
                touchView = null;
                if(clickView!=null){
                    ((PictureTagView)clickView).setStatus(PictureTagView.Status.Normal);
                    clickView = null;
                }
                startX = (int) event.getX();
                startY = (int) event.getY();
                if(hasView(startX,startY)){
                    startTouchViewLeft = touchView.getLeft();
                    startTouchViewTop = touchView.getTop();
                }
                else{
                    addItem(startX,startY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                moveView((int) event.getX(),
                        (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.i("停止时间", "----------" + System.currentTimeMillis());
                Log.i("Time",time+"");
                long ss= System.currentTimeMillis()-time;
                Log.i("时间差",ss+"");

                int endX = (int) event.getX();
                int endY = (int) event.getY();
                //如果挪动的范围很小，则判定为单击
                if(touchView!=null&& Math.abs(endX - startX)<CLICKRANGE&& Math.abs(endY - startY)<CLICKRANGE){
                    if(ss>1000){
                        ((PictureTagView)touchView).setStatus(PictureTagView.Status.LongClick);
                    }else{
//                        当前点击的view进入编辑状态
                        ((PictureTagView)touchView).setStatus(PictureTagView.Status.Edit);
                    }
                    clickView = touchView;
                }
                touchView = null;
                break;
        }
        return true;
    }

    public void ChangeTag(String TAG){
        PictureTagView.TAG_LEFT=TAG;
    }


    public void initTag(){
        PictureTagView.TAG_LEFT="F";
        PictureTagView.TAG_RIGHT=1;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("确定要删除这个标签吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((PictureTagView)touchView).setStatus(PictureTagView.Status.LongClick);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void addItem(int x,int y){
        View view = null;
        LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if(x>getWidth()*0.5){
            params.leftMargin = x - PictureTagView.getViewWidth();
            view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
        }
        else{
            params.leftMargin = x;
            view = new PictureTagView(getContext(), PictureTagView.Direction.Left);
        }

        params.topMargin = y;
        //上下位置在视图内
        if(params.topMargin<0)params.topMargin =0;
        else if((params.topMargin+PictureTagView.getViewHeight())>getHeight())params.topMargin = getHeight() - PictureTagView.getViewHeight();
        this.addView(view, params);
        PictureTagView.TAG_RIGHT++;
    }
    private void moveView(int x,int y){
        if(touchView == null) return;
        LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = x - startX + startTouchViewLeft;
        params.topMargin = y - startY + startTouchViewTop;
        //限制子控件移动必须在视图范围内
        if(params.leftMargin<0||(params.leftMargin+touchView.getWidth())>getWidth())params.leftMargin = touchView.getLeft();
        if(params.topMargin<0||(params.topMargin+touchView.getHeight())>getHeight())params.topMargin = touchView.getTop();
        touchView.setLayoutParams(params);
    }
    private boolean hasView(int x,int y){
        //循环获取子view，判断xy是否在子view上，即判断是否按住了子view
        for(int index = 0; index < this.getChildCount(); index ++){
            View view = this.getChildAt(index);
            int left = (int) view.getX();
            int top = (int) view.getY();
            int right = view.getRight();
            int bottom = view.getBottom();
            Rect rect = new Rect(left, top, right, bottom);
            boolean contains = rect.contains(x, y);
            //如果是与子view重叠则返回真,表示已经有了view不需要添加新view了
            if(contains){
                touchView = view;
                touchView.bringToFront();
                return true;
            }
        }
        touchView = null;
        return false;
    }
    /**
     *
     * @param cmt  Chronometer控件
     * @return 小时+分钟+秒数  的所有秒数
     */
    public  static String getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if(string.length()==7){

            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS = Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return String.valueOf(totalss);
        }

        else if(string.length()==5){

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS = Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);


    }

}

