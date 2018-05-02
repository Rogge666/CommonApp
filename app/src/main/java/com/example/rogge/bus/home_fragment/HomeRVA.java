package com.example.rogge.bus.home_fragment;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.rogge.R;
import com.example.rogge.bean.HomeDataBean;
import com.rogge.utils.GlideUtils;

import java.util.List;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2016/1/5.
 * @since 1.0.0
 */
public class HomeRVA extends BaseQuickAdapter<HomeDataBean, BaseViewHolder> {

    public HomeRVA(List<HomeDataBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeDataBean homeDataBean) {
        ImageView lImageView = baseViewHolder.getView(R.id.item_home_img);
        GlideUtils.loadImage(lImageView.getContext(), homeDataBean.getUrl(), lImageView);
        baseViewHolder.setText(R.id.item_home_des, homeDataBean.getDesc())
                .setText(R.id.item_home_create_time, homeDataBean.getCreatedAt());
    }

}
