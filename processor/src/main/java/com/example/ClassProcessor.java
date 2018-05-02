package com.example;

import java.lang.annotation.Documented;
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
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ClassProcessor {
    String value() default "我是初始值";
}
