package com.example.aoplibrary;

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
 * @author Created by Rogge on 2017/8/12 0012.
 * @since 1.0.0
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface RSingleClick {
}
