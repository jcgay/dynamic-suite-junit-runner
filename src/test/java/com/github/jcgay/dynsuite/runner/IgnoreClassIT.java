package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.fakeTestClasses.IgnoredTest;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean-Christophe GAY
 */
@RunWith(DynamicSuiteRunner.class)
public class IgnoreClassIT {

    @IncludeClasses
    public List<Class<?>> aMethodReturningTestWithIgnoreAnnotation() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(IgnoredTest.class);
        return classes;
    }
}
