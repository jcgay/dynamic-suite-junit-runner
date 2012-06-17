package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.test.classes.FailingTestClass;
import com.github.jcgay.dynsuite.test.classes.FirstTestClass;
import com.github.jcgay.dynsuite.test.classes.SecondTestClass;
import com.github.jcgay.dynsuite.test.classes.ThrowingExceptionTestClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Jean-Christophe Gay
 */
public class SuiteWithThrowingExceptionClassTest {

    @RunWith(DynamicSuiteRunner.class)
    public static class ThrowingExceptionSuite {

        @IncludeClasses
        public List<Class<?>> findClasses() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(ThrowingExceptionTestClass.class);
            classes.add(FirstTestClass.class);
            return classes;
        }
    }

    @Test
    public void suite_should_failed_when_at_least_one_test_throw_exception() {

        Result result = JUnitCore.runClasses(ThrowingExceptionSuite.class);

        assertThat(result.getFailureCount(), is(1));
        assertThat(result.getRunCount(), is(2));
    }
}
