package com.example;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

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
@SupportedAnnotationTypes("com.example.SingleClick")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SingleClickProcessor extends AbstractProcessor {
    public static final String SUFFIX = "AutoGenerate";
    public static final String PREFIX = "My_";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement te : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
                SingleClick annotation = element.getAnnotation(SingleClick.class);
            }
        }
        return true;
    }
}
