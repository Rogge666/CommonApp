package com.example.rogge.bus.home_fragment;

import dagger.Module;
import dagger.Provides;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/1 0001.
 * @since 1.0.0
 */
@Module
public class HomeModule {
    private HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    HomeContract.View providerHomeContractView(){
        return view;
    }
}
