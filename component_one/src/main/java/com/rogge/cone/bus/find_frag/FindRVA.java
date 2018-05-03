package com.rogge.cone.bus.find_frag;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rogge.cone.R;
import com.rogge.cone.bean.FindDataBean;

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
public class FindRVA extends BaseQuickAdapter<FindDataBean, BaseViewHolder> {

    public FindRVA(List<FindDataBean> data) {
        super(R.layout.item_find, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FindDataBean findDataBean) {
        baseViewHolder.setText(R.id.item_find_title, findDataBean.getTitle())
                .setText(R.id.item_find_time, findDataBean.getCreated_at());
    }

}
