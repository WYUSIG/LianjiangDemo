package com.example.sig.lianjiangdemo.activity;

/**
 * Created by 钟显东 on 2018/6/6.
 * 演示toolbar渲染的活动
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sig.lianjiangdemo.DIYView.ObservableScrollView;

public class Main2Activity extends BaseActivity implements ObservableScrollView.ScrollViewListener{
    private ObservableScrollView scrollView;

    private ImageView imageView;

    private int imageHeight;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Log.e("111","222");
        scrollView = (ObservableScrollView) findViewById(R.id.scrollview);
        imageView = (ImageView) findViewById(R.id.imageview);
        initListeners();
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

                scrollView.setScrollViewListener(Main2Activity.this);
            }
        });
    }

    /*
    * 设置toolbar的菜单
     */

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /*
    * toolbar菜单响应函数
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
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
            toolbar.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imageHeight-toolbar.getHeight()) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            toolbar.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {
            toolbar.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }
}
