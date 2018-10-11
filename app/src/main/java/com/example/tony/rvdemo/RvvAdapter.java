package com.example.tony.rvdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qdafengzi
 */
public class RvvAdapter extends RecyclerView.Adapter<RvvAdapter.ViewHolder> {
    private Context mContext;
    private List<Bean.DatasBean.JarInfoBean> mList;

    int mPosition;

    public RvvAdapter(Context context, List<Bean.DatasBean.JarInfoBean> list, int position) {
        mContext = context;
        mList = list;
        mPosition = position;
        System.out.println("子条目的信息：" + mList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setTag(position);
        holder.mTextView.setText(mList.get(position).getJarNum());

        /*if (mSelectedPos == position) {
            holder.mTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            holder.mTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.root));
        }*/

        if (mList.get(position).isSelected()) {
            holder.mTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        }


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    /**
     * 选中的项目
     */
    private int mSelectedPos = -1;

    /**
     * 设置选中项目
     *
     * @param position
     */
    public void setSelection(int position) {
        this.mSelectedPos = position;
//        System.out.println("单选："+position);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text)
        TextView mTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("大条目信息：" + mPosition + "长按条目信息：" + v.getTag());
                    Toast.makeText(mContext, "大条目信息：" + mPosition + "长按条目信息：" + v.getTag(), Toast.LENGTH_SHORT).show();
//                    mTextView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.root));
                    ((MainActivity) mContext).notifyChangeColorAll(mPosition, (int) v.getTag());


//                    ((MainActivity)mContext).notifyChangeColorAll();

//                    setSelection(-1);
                }
            });

            mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    System.out.println("大条目信息：" + mPosition + "长按条目信息：" + v.getTag());
//                    mTextView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorAccent));
//                    setSelection((int)v.getTag());
                    mList.get((int) v.getTag()).setSelected(true);

                    ((MainActivity) mContext).notifyChangeColor(mPosition, (int) v.getTag());

                    return true;
                }
            });

        }
    }
}