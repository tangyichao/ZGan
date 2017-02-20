package com.tang.zgan.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tang.zgan.R;
import com.tang.zgan.home.model.vo.AndroidArticle;

import java.util.List;

/**
 * Created by tangyc on 2017/2/15.
 */
public class AndroidRecyclerViewAdapter extends RecyclerView.Adapter<AndroidRecyclerViewAdapter.AandroidHolderView>{
        private Context context;
        private List<AndroidArticle.ResultsBean> results;
        private OnItemListener listener;
        public AndroidRecyclerViewAdapter(Context context, List<AndroidArticle.ResultsBean> results){
            this.context=context;
            this.results=results;
        }
        @Override
        public AandroidHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= View.inflate(context,R.layout.item_android,null);
            AandroidHolderView holderView=new AandroidHolderView(view);
            return holderView;
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        @Override
        public void onBindViewHolder(AandroidHolderView holder, final int position) {
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

        public void setListener(OnItemListener listener) {
            this.listener = listener;
        }

    class AandroidHolderView extends RecyclerView.ViewHolder{
        TextView mTvDesc;
        TextView mTvName;
        ImageView mIv;
        public AandroidHolderView(View itemView) {
            super(itemView);
            mTvDesc= (TextView) itemView.findViewById(R.id.tv_desc);
            mTvName= (TextView) itemView.findViewById(R.id.tv_name);
            mIv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }

}
