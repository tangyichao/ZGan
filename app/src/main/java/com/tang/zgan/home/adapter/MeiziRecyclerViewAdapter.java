package com.tang.zgan.home.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tang.zgan.R;
import com.tang.zgan.home.model.vo.Meizi;

import java.util.List;

/**
 * Created by tangyc on 2017/2/15.
 */

public class MeiziRecyclerViewAdapter extends RecyclerView.Adapter<MeiziRecyclerViewAdapter.MeiziHolderView> implements View.OnClickListener {
        private Context context;
        private List<Meizi.ResultsBean> results;
        private OnItemListener listener;
        public MeiziRecyclerViewAdapter(Context context, List<Meizi.ResultsBean> results){
            this.context=context;
            this.results=results;
        }
        @Override
        public MeiziHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= View.inflate(context,R.layout.item_card,null);
            MeiziHolderView holderView=new MeiziHolderView(view);
            view.setOnClickListener(this);
            return holderView;
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        @Override
        public void onBindViewHolder(MeiziHolderView holder, int position) {
            holder.itemView.setTag(holder);
             Glide.with(context).load(results.get(position).getUrl()).into(holder.mIvMeizi);
        }


        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onItemClick(v,v.getTag());
            }
        }



        public void setListener(OnItemListener listener) {
            this.listener = listener;
        }

    class MeiziHolderView extends RecyclerView.ViewHolder {
        AppCompatImageView mIvMeizi;
        public MeiziHolderView(View itemView) {
            super(itemView);
            mIvMeizi= (AppCompatImageView) itemView.findViewById(R.id.iv_meizi);
        }
    }

}
