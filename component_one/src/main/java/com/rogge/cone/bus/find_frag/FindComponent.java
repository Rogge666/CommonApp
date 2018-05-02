package com.rogge.cone.bus.find_frag;

import dagger.Component;

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

@Component(modules = FindModule.class)
public interface FindComponent {
    void inject(FindFragment findFragment);
}
