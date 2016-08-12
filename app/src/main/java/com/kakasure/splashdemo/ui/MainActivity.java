package com.kakasure.splashdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kakasure.splashdemo.R;
import com.kakasure.splashdemo.manager.ActManager;
import com.kakasure.splashdemo.manager.AnimatorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 *
 * @author dashentao
 * @date 2015 9-16
 * @since V 1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = MainActivity.this.getClass().getSimpleName();
    private ViewPager viewPager;
    private Button login;
    private Button register;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayoutFirst;
    private RelativeLayout relativeLayoutSecond;
    private RelativeLayout relativeLayoutThrid;
    private FrameLayout splashbg;
    private List<ImageView> imgList = new ArrayList<ImageView>();
    private List<Fragment> fragList = new ArrayList<Fragment>();
    private int currentPosition = -1;
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            splashbg.setVisibility(View.GONE);
            updateIndicatorStatus(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActManager.getInstance().pushActivity(this);
        initView();
        initListener();
        initAdapter();
        init();
    }

    public void initListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        relativeLayoutFirst = (RelativeLayout) findViewById(R.id.relativelayout_first);
        relativeLayoutSecond = (RelativeLayout) findViewById(R.id.relativelayout_second);
        relativeLayoutThrid = (RelativeLayout) findViewById(R.id.relativelayout_third);
        splashbg = (FrameLayout) findViewById(R.id.splash_bg);
    }

    /**
     * 初始化适配器<br/>
     */
    public void initAdapter() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyAdapter myAdapter = new MyAdapter(fragmentManager);
        for (int i = 0; i < 3; i++) {
            SplashFragment splashFragment = SplashFragment.newInstance(getResources().getStringArray(R.array.titles)[i]);
            fragList.add(splashFragment);
        }
        myAdapter.list.clear();
        myAdapter.list.addAll(fragList);
        viewPager.setAdapter(myAdapter);
    }

    public void init() {
        for (int i = 0; i < fragList.size(); i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(imageView, layoutParams);
            imgList.add(imageView);
        }
        List<View> view = new ArrayList<View>();
        view.add(relativeLayoutFirst);
        view.add(relativeLayoutSecond);
        view.add(relativeLayoutThrid);
        AnimatorManager.getInstance().addAll(view);
        myHandler.sendEmptyMessageDelayed(1, 2 * 1000);
    }

    public void updateIndicatorStatus(int position) {
        currentPosition = position;
        AnimatorManager.getInstance().startAnimator(position);
        switch (position) {
            case 0:
                imgList.get(0).setBackgroundResource(R.mipmap.yindao_down);
                imgList.get(1).setBackgroundResource(R.mipmap.yindao_on);
                imgList.get(2).setBackgroundResource(R.mipmap.yindao_on);
                break;
            case 1:
                imgList.get(1).setBackgroundResource(R.mipmap.yindao_down);
                imgList.get(0).setBackgroundResource(R.mipmap.yindao_on);
                imgList.get(2).setBackgroundResource(R.mipmap.yindao_on);
                break;
            case 2:
                imgList.get(2).setBackgroundResource(R.mipmap.yindao_down);
                imgList.get(1).setBackgroundResource(R.mipmap.yindao_on);
                imgList.get(0).setBackgroundResource(R.mipmap.yindao_on);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                // 登录状态
                break;
            case R.id.register:
                // 用户注册
                break;
            default:
                Log.i(TAG, "It will not happened!");
                break;
        }
    }

    /**
     * 滑动页面监视器
     */
    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.i(TAG, "positionOffset is =" + positionOffset);
//            Log.i(TAG, "positionOffsetPixels is = " + positionOffsetPixels);
            Log.i(TAG, "position = " + position);
            boolean flag = false;
            if (position == currentPosition) {
                flag = true;
            } else {
                flag = false;
            }
            AnimatorManager.getInstance().withChangeAnimator(currentPosition, positionOffset, positionOffsetPixels, flag);
        }

        @Override
        public void onPageSelected(int position) {
            updateIndicatorStatus(position);
            Log.i(TAG, "onPageSelected is called, position = " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    /**
     * ViewPager适配器
     */
    public class MyAdapter extends FragmentPagerAdapter {
        List<Fragment> list = new ArrayList<Fragment>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
