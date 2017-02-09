package com.tang.zgan.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tang.zgan.C;
import com.tang.zgan.R;
import com.tang.zgan.bean.Meizi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mIv;
    private LayoutInflater mInflater;

    private ArrayList<String> mTitles = new ArrayList<>();//页卡标题集合
    private View view1, view2;
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
        mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.item_view, null);
        view2 = mInflater.inflate(R.layout.item_meizi, null);
        /***********************/
        OkHttpClient.Builder okHttpClient=new OkHttpClient().newBuilder();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(C.API)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeiziService meiziService= retrofit.create(MeiziService.class);
        retrofit2.Call<Meizi>  call=meiziService.getMeizi("10","1");
        Request.Builder request=new Request.Builder();
        call.enqueue(new retrofit2.Callback<Meizi>(){

            @Override
            public void onResponse(retrofit2.Call<Meizi> call, retrofit2.Response<Meizi> response) {
                Meizi meizi= response.body();
                Log.i("TAG",meizi.isError()+"---------");
                RecyclerView rv= (RecyclerView) view2.findViewById(R.id.rv_meizi);
                MyRecyclerViewAdapter myRecyclerAdpter=new MyRecyclerViewAdapter(MainActivity.this,meizi.getResults());
                rv.setAdapter(myRecyclerAdpter);
            }

            @Override
            public void onFailure(retrofit2.Call<Meizi> call, Throwable t) {

            }
        });
                /***********************/

        //添加到View集合
        mViewList.add(view1);
        mViewList.add(view2);

        //添加标题集合
        mTitles.add("妹子");
        mTitles.add("安卓");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));

        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        //mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if(position==0){
                        mIv.setImageResource(R.mipmap.bg_splash);
                    }else{
                        mIv.setImageResource(R.mipmap.bg_title);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Palette.Builder builder = Palette.from(BitmapFactory.decodeResource(getResources(), R.mipmap.bg_title));
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant != null) {

                }

                Palette.Swatch vibrantlight = palette.getLightVibrantSwatch();
                if (vibrantlight != null) {
                    //getWindow().setStatusBarColor(vibrantlight.getBodyTextColor());
                }

                Palette.Swatch vibrantdark = palette.getDarkVibrantSwatch();
                if (vibrantdark != null) {

                }


                Palette.Swatch muted = palette.getMutedSwatch();
                if (muted != null) {

                }


                Palette.Swatch mutedDark = palette.getDarkMutedSwatch();
                if (mutedDark != null) {

                }


                Palette.Swatch mutedLight = palette.getLightMutedSwatch();
                if (mutedLight != null) {

                }

            }
        });
    }



    class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> mViewList;

        public MyPagerAdapter(ArrayList<View> mViewList) {
            this.mViewList = mViewList;
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
    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyHolderView>{
        private Context context;
        private List<Meizi.ResultsBean> results;
        public MyRecyclerViewAdapter(Context context, List<Meizi.ResultsBean> results){

        }
        @Override
        public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public void onBindViewHolder(MyHolderView holder, int position) {

        }


    }
    class MyHolderView extends RecyclerView.ViewHolder{

        public MyHolderView(View itemView) {
            super(itemView);
        }
    }

}
