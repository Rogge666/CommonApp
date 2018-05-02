package com.rogge.cone.bus.find_frag;

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
public class FindModule {
    public FindContract.View view;

    public FindModule(FindContract.View view) {
        this.view = view;
    }

    @Provides
    public FindContract.View providerFindContractView() {
        return view;
    }
}
