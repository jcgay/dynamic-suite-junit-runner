package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.test.classes.FailingTestClass;
import com.github.jcgay.dynsuite.test.classes.FirstTestClass;
import com.github.jcgay.dynsuite.test.classes.SecondTestClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Jean-Christophe Gay
 */
public class SuiteWithFailedClassesTest {

    @RunWith(DynamicSuiteRunner.class)
    public static class FailedSuite {

        @IncludeClasses
        public List<Class<?>> findClasses() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(FirstTestClass.class);
            classes.add(FailingTestClass.class);
            classes.add(SecondTestClass.class);
            return classes;
        }
    }

    @Test
    public void suite_should_failed_when_at_least_one_test_fail() {

        Result result = JUnitCore.runClasses(FailedSuite.class);

        assertThat(result.getFailureCount(), is(1));
        assertThat(result.getRunCount(), is(3));
    }
}
