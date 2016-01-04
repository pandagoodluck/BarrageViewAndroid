package com.pancheng.sample_barrageview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pancheng.barrageview.ui.BarrageViewWithImg;
import com.pancheng.barrageview.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pancheng on 2015/12/18.
 */
public class MyAdapter extends RecyclerView.Adapter {
    Context mContext;
    ArrayList<Integer> posArray = new ArrayList<>();
    public ArrayList<String> data = new ArrayList<>();
    public ArrayList<String> urlList = new ArrayList<>();

    public MyAdapter(ArrayList<String> dataIn, ArrayList<String> urlList){
        this.data = dataIn;
        this.urlList = urlList;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        if(!posArray.contains(position)) {
            myViewHolder.barrageViewWithImg.initWithData(mContext, data, urlList);
            //first frame
            myViewHolder.barrageViewWithImg.setFrame(800,800);
            posArray.add(position);
        }
        myViewHolder.simpleDraweeView.setImageURI(Uri.parse("http://b.picphotos.baidu.com/album/s%3D1600%3Bq%3D90/sign=b5243094e6cd7b89ed6c3e853f1479d6/faf2b2119313b07eaba360560cd7912397dd8c55.jpg"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BarrageViewWithImg barrageViewWithImg ;
        SimpleDraweeView simpleDraweeView;
        public MyViewHolder(View itemView) {
            super(itemView);
            barrageViewWithImg = (BarrageViewWithImg) itemView.findViewById(R.id.barrageviewwithimg);
            simpleDraweeView =(SimpleDraweeView) itemView.findViewById(R.id.content_image);
        }
    }


}
