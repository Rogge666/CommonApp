package com.rogge.cone.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rogge.cone.R;
import com.rogge.cone.bean.ServiceBean;
import com.rogge.utils.GlideUtils;

import java.util.List;

public class ServiceRVA extends BaseQuickAdapter<ServiceBean, BaseViewHolder> {

    public ServiceRVA(List<ServiceBean> data) {
        super(R.layout.fragment_service_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceBean data) {
        switch (data.getServiceLinkId()) {
            case 1:
                helper.setBackgroundRes(R.id.iv_service_img, R.drawable.num_statment);
                break;
            case 2:
                helper.setBackgroundRes(R.id.iv_service_img, R.drawable.customer_detail);
                break;
            case 3:
                helper.setBackgroundRes(R.id.iv_service_img, R.drawable.speed_search);
                break;
            case 4:
                helper.setBackgroundRes(R.id.iv_service_img, R.drawable.know_base);
                break;
            case 5:
                GlideUtils.displayImage(helper.getConvertView().getContext(), data.getRemarkOne(), (ImageView) helper.getView(R.id.iv_service_img));
                break;
        }
        helper.setText(R.id.tv_service_title, data.getServiceLinkName());

    }

}
