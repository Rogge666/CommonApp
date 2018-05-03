package com.rogge.ctwo.bus.my_frag;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rogge.ctwo.R;
import com.rogge.ctwo.bean.MyBean;

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
public class MyRVA extends BaseQuickAdapter<MyBean.DetailBean, BaseViewHolder> {

    public MyRVA(List<MyBean.DetailBean> data) {
        super(R.layout.item_my, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyBean.DetailBean detailBean) {
        baseViewHolder.setText(R.id.item_my_desc, detailBean.getDesc())
                .setText(R.id.item_my_time, detailBean.getCreatedAt());
    }

}
