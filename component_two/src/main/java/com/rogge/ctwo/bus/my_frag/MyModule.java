package com.rogge.ctwo.bus.my_frag;

import dagger.Module;
import dagger.Provides;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/2 0002.
 * @since 1.0.0
 */
@Module
public class MyModule {
    public MyContract.View view;

    public MyModule(MyContract.View view) {
        this.view = view;
    }

    @Provides
    public MyContract.View providerFindContractView() {
        return view;
    }
}
