package com.cdgo.demo_ydotlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cdgo.libydot.Constants;
import com.cdgo.libydot.Pen;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Pen.OnTouchListener {

    //step 1. create Pen;
    static private Pen _pen=new Pen();

    //step 2. 准备图像到 cameraBuf[]
    private String imagePath="/res/raw/a1.bmp";
    byte cameraBuf[]=_pen.getImgRawData(this,imagePath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step 3. 实现接口 Pen.OnTouchListener
        _pen.setOnTouchListener(this);
    }

    //step 3.1 接口函数
    @Override
    public void onTouch(boolean bTouch) {
        //Log.i(Constants.TAG,String.format(".%b",bTouch));
        //if(bTouch) //当点到纸面时蓝灯闪一下
        //   blueLightBlink();
    }

    //step 3.2 接口函数
    @Override
    public void onRecognized(long id) {
        //解码成功回调此函数及码=id
        String msg=String.format(Locale.US,"image decoded: %s ------> id: %d",imagePath,id);
        Log.i(Constants.TAG,msg);
        //点击辨识到码是 id
        //EditText e=findViewById(R.id.editText);
        //e.setText(msg);
    }


    public void onDecode(View view) {
        long t1=System.nanoTime();//currentTimeMillis();
        _pen.decode(cameraBuf);//开始解码
        long t2=System.nanoTime();

        String msg=String.format("decode timespan: %.3fms",1.0*(t2-t1)/1000/1000);
        Log.i(Constants.TAG,msg);

        EditText e=findViewById(R.id.editText);
        e.setText(msg);
    }
}
