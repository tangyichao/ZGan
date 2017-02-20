package com.tang.zgan.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by tangyc on 2017/2/15.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViewList;
    private ArrayList<String> mTitles;
    public MyPagerAdapter(ArrayList<View> mViewList, ArrayList<String> mTitles) {
        this.mViewList = mViewList;
        this.mTitles=mTitles;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
