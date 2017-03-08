package com.tang.zgan.home.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tang.zgan.R;
import com.tang.zgan.home.adapter.AndroidRecyclerViewAdapter;
import com.tang.zgan.home.adapter.MeiziRecyclerViewAdapter;
import com.tang.zgan.home.adapter.MyPagerAdapter;
import com.tang.zgan.home.adapter.OnItemListener;
import com.tang.zgan.home.model.vo.AndroidArticle;
import com.tang.zgan.home.model.vo.Meizi;
import com.tang.zgan.home.presenter.impl.AndroidPresenterImpl;
import com.tang.zgan.home.presenter.impl.MeiZiPresenterImpl;
import com.tang.zgan.home.view.AndroidView;
import com.tang.zgan.home.view.MeiziView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MeiziView ,AndroidView,OnItemListener, NavigationView.OnNavigationItemSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mIv;
    private LayoutInflater mInflater;
    private ArrayList<String> mTitles = new ArrayList<>();//页卡标题集合
    private View viewAndroid, viewMeizi;
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合

    private  MeiZiPresenterImpl meiZiPresenter;
    private AndroidPresenterImpl androidPresenter;
    private ProgressDialog dialog;
    private RecyclerView mRvMeizi;
    private RecyclerView mRvAndroid;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //去掉标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mIv= (ImageView) findViewById(R.id.iv);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mInflater = LayoutInflater.from(this);
        viewAndroid = mInflater.inflate(R.layout.item_view, null);
        viewMeizi = mInflater.inflate(R.layout.item_meizi, null);
        mViewList.add(viewAndroid);
        mViewList.add(viewMeizi);

        //添加标题集合
        mTitles.add("安卓");
        mTitles.add("妹子");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));
        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList,mTitles);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。

        mRvMeizi= (RecyclerView) viewMeizi.findViewById(R.id.rv_meizi);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mRvAndroid= (RecyclerView) viewAndroid.findViewById(R.id.rv_android);
        mRvAndroid.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRvAndroid.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));

        mRvMeizi.setLayoutManager(manager);
        dialog=new ProgressDialog(this);
        dialog.setTitle("加载中...");
        meiZiPresenter=new MeiZiPresenterImpl(this);
        meiZiPresenter.getMeizi(30,1);
        androidPresenter=new AndroidPresenterImpl(this);
        androidPresenter.getAndroid(30,1);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void closeLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError() {
        dialog.dismiss();
    }

    @Override
    public void showAndroid(AndroidArticle androidArticle) {
        AndroidRecyclerViewAdapter androidRecyclerAdapter=new AndroidRecyclerViewAdapter(MainActivity.this,androidArticle.getResults());
        mRvAndroid.setAdapter(androidRecyclerAdapter);
        androidRecyclerAdapter.setListener(this);
    }

    @Override
    public void showMeizi(Meizi meizi) {
        MeiziRecyclerViewAdapter meiziRecyclerAdapter=new MeiziRecyclerViewAdapter(MainActivity.this,meizi.getResults());
        mRvMeizi.setAdapter(meiziRecyclerAdapter);
        meiziRecyclerAdapter.setListener(this);

    }

    @Override
    public void onItemClick(View view, Object obj) {

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
