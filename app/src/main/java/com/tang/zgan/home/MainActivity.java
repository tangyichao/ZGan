package com.tang.zgan.home;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tang.zgan.C;
import com.tang.zgan.R;
import com.tang.zgan.bean.AndroidArticle;
import com.tang.zgan.bean.Meizi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        mViewList.add(view1);
        mViewList.add(view2);
        /***********************/
        final OkHttpClient.Builder okHttpClient=new OkHttpClient().newBuilder();
        okHttpClient.connectTimeout(5, TimeUnit.SECONDS);
       final Retrofit retrofit=new Retrofit.Builder().baseUrl(C.API)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MeiziService meiziService= retrofit.create(MeiziService.class);
        Observable<Meizi> call=meiziService.getMeizi("40","1");
        Request.Builder request=new Request.Builder();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Meizi>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final Meizi meizi) {
                        Glide.with(MainActivity.this).load(meizi.getResults().get(0).getUrl()).into(mIv);
                        //添加到View集合

                        RecyclerView rv= (RecyclerView) view2.findViewById(R.id.rv_meizi);
                        MyRecyclerViewAdapter myRecyclerAdapter=new MyRecyclerViewAdapter(MainActivity.this,meizi.getResults());
                        final StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                        rv.setLayoutManager(manager);
                        rv.setAdapter(myRecyclerAdapter);
                        myRecyclerAdapter.setListener(new MyRecyclerViewAdapter.OnItemListener() {
                            @Override
                            public void onItemClick(View view, Object obj) {
                                Log.i("TAG","<-----------");
                                MyHolderView myHolderView= (MyHolderView) obj;
                                Intent intent=new Intent();
                                intent.setClass(MainActivity.this,MeiziActivity.class);
                                ActivityCompat.startActivity(MainActivity.this,intent,ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, myHolderView.mIvMeizi, "meizinihao").toBundle());

                            }
                        });
                        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                manager.invalidateSpanAssignments(); //防止第一行到顶部有空白区域
                            }
                        });

                        //添加标题集合
                        mTitles.add("安卓");
                        mTitles.add("妹子");

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
                                Random random=new Random();
                                int randomNum=random.nextInt(meizi.getResults().size());
                                Glide.with(MainActivity.this).load(meizi.getResults().get(randomNum).getUrl()).into(mIv);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }
                });



                /***********************/
        AndroidService androidService= retrofit.create(AndroidService.class);
        Call<AndroidArticle> androidArticleCall= androidService.getAndroid("30","1");
        androidArticleCall.enqueue(new Callback<AndroidArticle>() {
            @Override
            public void onResponse(Call<AndroidArticle> call, Response<AndroidArticle> response) {
                RecyclerView rv= (RecyclerView) view1.findViewById(R.id.rv_android);
                MyAndroidRecyclerViewAdapter myRecyclerAdapter=new MyAndroidRecyclerViewAdapter(MainActivity.this,response.body().getResults());
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(myRecyclerAdapter);
                rv.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                myRecyclerAdapter.setListener(new MyAndroidRecyclerViewAdapter.OnItemListener() {
                    @Override
                    public void onItemClick(View view, String str) {
                        Intent intent=new Intent();
                        intent.setClass(MainActivity.this,WebViewActivity.class);
                        intent.putExtra("weburl",str);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<AndroidArticle> call, Throwable t) {

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
    static class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyHolderView> implements View.OnClickListener {
        private Context context;
        private List<Meizi.ResultsBean> results;
        private OnItemListener listener;
        public MyRecyclerViewAdapter(Context context, List<Meizi.ResultsBean> results){
            this.context=context;
            this.results=results;
        }
        @Override
        public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
           View view= View.inflate(context,R.layout.item_card,null);
            MyHolderView holderView=new MyHolderView(view);
            view.setOnClickListener(this);
            return holderView;
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        @Override
        public void onBindViewHolder(MyHolderView holder, int position) {
            Log.i("TAG",position+results.get(position).getUrl());
            holder.itemView.setTag(holder);
           // Glide.with(context).load(results.get(position).getUrl()).into(holder.mIvMeizi);
            holder.mIvMeizi.setImageResource(R.mipmap.bg_splash);
        }


        @Override
        public void onClick(View v) {
            if(listener!=null){
                Log.i("TAG","-------->");
                listener.onItemClick(v,v.getTag());
            }
        }
        interface  OnItemListener{
            void onItemClick(View view,Object obj);
        }


        public void setListener(OnItemListener listener) {
            this.listener = listener;
        }
    }
    static class MyHolderView extends RecyclerView.ViewHolder {
        AppCompatImageView mIvMeizi;
        public MyHolderView(View itemView) {
            super(itemView);
            mIvMeizi= (AppCompatImageView) itemView.findViewById(R.id.iv_meizi);
        }
    }




    static class MyAndroidRecyclerViewAdapter extends RecyclerView.Adapter<MyAandroidHolderView>{
        private Context context;
        private List<AndroidArticle.ResultsBean> results;





        private OnItemListener listener;
        public MyAndroidRecyclerViewAdapter(Context context, List<AndroidArticle.ResultsBean> results){
            this.context=context;
            this.results=results;
        }
        @Override
        public MyAandroidHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= View.inflate(context,R.layout.item_android,null);
            MyAandroidHolderView holderView=new MyAandroidHolderView(view);
            return holderView;
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        @Override
        public void onBindViewHolder(MyAandroidHolderView holder, final int position) {
            String desc=results.get(position).getDesc();
            String name=results.get(position).getWho();
            List<String> list=results.get(position).getImages();
            if(desc!=null){
                holder.mTvDesc.setText(desc);
            }
            if(name!=null)
            {
                holder.mTvName.setText(name);
            }
            if(list!=null&&list.size()>0)
            {
                Glide.with(context).load(list.get(0)+"?imageView2/0/w/200")
                        .into(holder.mIv);
            }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener!=null)
                        {
                            listener.onItemClick(v,results.get(position).getUrl());
                        }
                    }
                });

        }
        interface OnItemListener{
            void onItemClick(View view,String str);
        }
        public void setListener(OnItemListener listener) {
            this.listener = listener;
        }

    }
    static class MyAandroidHolderView extends RecyclerView.ViewHolder{
        TextView mTvDesc;
        TextView mTvName;
        ImageView mIv;
        public MyAandroidHolderView(View itemView) {
            super(itemView);
            mTvDesc= (TextView) itemView.findViewById(R.id.tv_desc);
            mTvName= (TextView) itemView.findViewById(R.id.tv_name);
            mIv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }

}
