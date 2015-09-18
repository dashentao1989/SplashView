package com.kakasure.splashdemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kakasure.splashdemo.R;

/**
 * Created by Administrator on 2015/9/16.
 *
 * @author dashentao
 * @date 2015 9-16
 * @since V 1.0
 */
public class SplashFragment extends Fragment {
    public static final String TYPE = "type";
    private View view;
    private TextView textView1;
    private TextView textView2;
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment, null);
        type = getArguments().getString(TYPE);
        initView();
        initListener();
        initAdapter();
        init();
        return view;
    }

    public void init() {
        if (!TextUtils.isEmpty(type)) {
            updateUI(type);
        }
    }

    public void initView() {
        textView1 = (TextView) view.findViewById(R.id.title1);
        textView2 = (TextView) view.findViewById(R.id.title2);
        TextPaint tp = textView1.getPaint();
        tp.setFakeBoldText(true);
    }

    public void initListener() {

    }

    public void initAdapter() {

    }

    private void updateUI(String result) {
        String[] titles = result.split("/");
        textView1.setText(titles[0]);
        textView2.setText(titles[1]);
    }

    public static SplashFragment newInstance(String type) {
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
