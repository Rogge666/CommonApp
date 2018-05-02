package com.example.rogge.bus.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.rogge.BuildConfig;
import com.example.rogge.R;
import com.example.rogge.bus.home_fragment.FragmentHome;
import com.rogge.api.ComponentOneAPI;
import com.rogge.base.AppManager;
import com.rogge.base.BaseActivity;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private FragmentManager mFragmentManager;
    private FragmentHome fragmentHome;
    private Fragment findFragment;
    private FragmentHome fragmentHome2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        showToolbar(false);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.axh, "").setInactiveIconResource(R.drawable.axg).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axf, "").setInactiveIconResource(R.drawable.axe).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axj, "").setInactiveIconResource(R.drawable.axi).setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        //初始化Fragment
        mFragmentManager = getSupportFragmentManager();
        fragmentHome = (FragmentHome) mFragmentManager.findFragmentByTag("home_fragment");
        findFragment = mFragmentManager.findFragmentByTag("find_fragment");
        fragmentHome2 = (FragmentHome) mFragmentManager.findFragmentByTag("my_fragment");
        if (fragmentHome == null) {
            fragmentHome = new FragmentHome();
            addFragment(R.id.main_container, fragmentHome, "home_fragment");
        }
        if (findFragment == null) {
            if (BuildConfig.isPlugin){
                findFragment = (Fragment) ARouter.getInstance().build(ComponentOneAPI.FIND_FRAGMENT).navigation();
            }else {
                findFragment = new FragmentHome();
            }
            addFragment(R.id.main_container, findFragment, "find_fragment");
        }
        if (fragmentHome2 == null) {
            fragmentHome2 = new FragmentHome();
            addFragment(R.id.main_container, fragmentHome2, "my_fragment");
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            default:
                mFragmentManager.beginTransaction()
                        .hide(findFragment)
                        .hide(fragmentHome2)
                        .show(fragmentHome)
                        .commitAllowingStateLoss();
                break;
            case 1:
                mFragmentManager.beginTransaction()
                        .hide(fragmentHome)
                        .hide(fragmentHome2)
                        .show(findFragment)
                        .commitAllowingStateLoss();
                break;
            case 2:
                mFragmentManager.beginTransaction()
                        .hide(fragmentHome)
                        .hide(findFragment)
                        .show(fragmentHome2)
                        .commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void addFragment(int containerViewId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("您确定要退出当前应用吗？")
                .setCancelText("再留一会")
                .setConfirmText("狠心退出")
                .showCancelButton(true)
                .setConfirmClickListener((sweetAlertDialog) -> AppManager.getAppManager().finishAllActivity())
                .show();
    }

}
