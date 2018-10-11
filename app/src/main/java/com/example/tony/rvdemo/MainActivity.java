package com.example.tony.rvdemo;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RvAdapter mRvAdapter;
    private Bean mBean;
    private Parcelable recyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        mBean = new Bean();
        List<Bean.DatasBean> datas = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            Bean.DatasBean datasBean = new Bean.DatasBean();
            List<Bean.DatasBean.JarInfoBean> jarInfo = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Bean.DatasBean.JarInfoBean jarInfoBean = new Bean.DatasBean.JarInfoBean();
                jarInfoBean.setJarNum("罐：" + j + "--" + i);
                jarInfo.add(jarInfoBean);
            }
            datasBean.setJarInfo(jarInfo);
            datasBean.setFixPlantNum("车间号" + j);
            datas.add(datasBean);
        }

        mBean.setDatas(datas);
        mBean.setMessage("hhhhh");

        mRvAdapter = new RvAdapter(this, mBean.getDatas());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
//        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);

        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setAdapter(mRvAdapter);
    }

    private void initListener() {
    }

    public void notifyChangeColor(int position, int tag) {
        List<Bean.DatasBean> datas = mBean.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            if (i == position) {
                List<Bean.DatasBean.JarInfoBean> jarInfo = datas.get(i).getJarInfo();
                for (int j = 0; j < jarInfo.size(); j++) {
                    if (j == tag) {
                        jarInfo.get(j).setSelected(true);
                    } else {
                        jarInfo.get(j).setSelected(false);
                    }
                }
            } else {
                List<Bean.DatasBean.JarInfoBean> jarInfo = datas.get(i).getJarInfo();
                for (int j = 0; j < jarInfo.size(); j++) {
                    jarInfo.get(j).setSelected(false);
                }
            }
        }
//        mRvAdapter.notifyDataSetChanged();
        mRvAdapter.refresh(position, tag);

    }


    /**
     * 单击
     *
     * @param position
     * @param tag
     */
    public void notifyChangeColorAll(int position, int tag) {
        List<Bean.DatasBean> datas = mBean.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            List<Bean.DatasBean.JarInfoBean> jarInfo = datas.get(i).getJarInfo();
            for (int j = 0; j < jarInfo.size(); j++) {
                jarInfo.get(j).setSelected(false);
            }
        }
//        mRvAdapter.notifyDataSetChanged();
        mRvAdapter.refresh(position, tag);
    }
}
