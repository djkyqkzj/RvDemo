package com.example.tony.rvdemo;


import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 *
 * @author qdafengzi
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private Context mContext;


    private List<Bean.DatasBean> mList;

    private Parcelable recyclerViewState;
    private RvvAdapter mRvAdapter;


    public RvAdapter(Context context, List<Bean.DatasBean> list) {
        mContext = context;
        mList = list;
        for (int i = 0; i < mList.size(); i++) {
            System.out.println("条目的信息：" + mList.get(i).toString());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mRvAdapter = new RvvAdapter(mContext, mList.get(position).getJarInfo(), position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rvItemItem.setLayoutManager(layoutManager);

//        recyclerViewState = holder.rvItemItem.getLayoutManager().onSaveInstanceState();
//        holder.rvItemItem.getLayoutManager().onRestoreInstanceState(recyclerViewState);

        holder.rvItemItem.setAdapter(mRvAdapter);
    }

    /**
     * 刷新
     */
    public void refresh(int position, int tag) {
        System.out.println("刷新视图");
        notifyDataSetChanged();
//        mRvAdapter.notifyItemChanged(tag);

//        notifyItemRangeChanged(0, getItemCount());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_item)
        RecyclerView rvItemItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}