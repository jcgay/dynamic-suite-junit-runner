package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.test.classes.IgnoredTestClass;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Jean-Christophe GAY
 */
public class SuiteWithIgnoredClassesTest {

    @RunWith(DynamicSuiteRunner.class)
    public static class IgnoredSuite {

        @IncludeClasses
        public List<Class<?>> aMethodReturningTestWithIgnoreAnnotation() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(IgnoredTestClass.class);
            return classes;
        }
    }

    @Test
    public void ignored_test_should_not_be_ran() {

        Result result = JUnitCore.runClasses(IgnoredSuite.class);

        assertThat(result.getIgnoreCount(), is(1));
        assertThat(result.getRunCount(), is(0));
    }
}
