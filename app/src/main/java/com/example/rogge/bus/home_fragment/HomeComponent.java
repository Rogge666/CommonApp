package com.example.rogge.bus.home_fragment;

import com.rogge.dagger.PerFragment;

import dagger.Component;

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

@PerFragment
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(FragmentHome fragmentHome);
}
