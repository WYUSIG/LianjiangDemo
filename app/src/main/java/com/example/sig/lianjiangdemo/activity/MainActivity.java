package com.example.sig.lianjiangdemo.activity;

/**
 * Created by 钟显东 on 2018/6/6.
 * 演示toolbar渲染的活动
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.sig.lianjiangdemo.DIYView.ObservableScrollView;


public class MainActivity extends BaseActivity  implements ObservableScrollView.ScrollViewListener {

    private ObservableScrollView scrollView;

    private ImageView imageView;

    private LinearLayout textView;

    private TextView top_center;

    private int imageHeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
        imageView = (ImageView) findViewById(R.id.imageview);
        textView = (LinearLayout) findViewById(R.id.textview);
        top_center=(TextView)findViewById(R.id.top_center);
        initListeners();

    }

    /*
    * 跳转到toolbar演示活动的函数
     */
    public void toMain2(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    /*
    *  获取顶部图片(也可以任意一控件)高度后，设置滚动监听
     */
    private void initListeners() {
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = imageView.getHeight();

                scrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    /*
    * scrollView : 滚动控件实例
    * x，y : 页面x/y轴的位移,最终传给安卓ScrollView基类的onScrollChanged方法，所以不用太在意
     */

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // Log.i("TAG", "y--->" + y + "    height-->" + height);
        if (y <= 0) {
            textView.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imageHeight-textView.getHeight()) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            textView.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
            top_center.setText("");
        } else {
            textView.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
            top_center.setText("廉江软设");
        }
    }
}
