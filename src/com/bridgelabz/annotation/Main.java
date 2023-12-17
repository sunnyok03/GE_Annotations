package com.bridgelabz.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
    @Override
    @MethodInfo(author = "Narayan", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "Overridden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    public static void main(String[] args) {
        try {
            for (Method method : Main.class.getMethods()) {
                if (method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in method " + method + " is " + annotation);
                        }

                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = " + method);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}