package com.example;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/3/16.
 * @since 1.0.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@ListenerClass(
        targetType = "android.view.View",
        setter = "setOnClickListener",
        type = "DebouncingOnClickListener",
        method = @ListenerMethod(
                name = "doClick",
                parameters = "android.view.View"
        )
)
public @interface SingleClick {
    int[] value() default { View.NO_ID };
}
