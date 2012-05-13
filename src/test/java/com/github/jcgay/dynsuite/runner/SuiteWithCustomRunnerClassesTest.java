package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.test.classes.CustomTestUsingCustomRunner;
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
public class SuiteWithCustomRunnerClassesTest {

    @RunWith(DynamicSuiteRunner.class)
    public static class CustomRunnerSuite {

        @IncludeClasses
        public List<Class<?>> findClasses() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(CustomTestUsingCustomRunner.class);
            return classes;
        }
    }

    @Test
    public void suite_should_use_test_custom_runner_when_run_with_annotation_is_used() {

        Result result = JUnitCore.runClasses(CustomRunnerSuite.class);

        assertThat(result.getRunCount(), is(1));
        assertThat(result.getFailureCount(), is(0));
    }
}
