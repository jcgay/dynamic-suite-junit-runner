package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.test.classes.FirstTestClass;
import com.github.jcgay.dynsuite.test.classes.SecondTestClass;
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
public class SuiteWithPassingClassesTest {

    @RunWith(DynamicSuiteRunner.class)
    public static class SuccessSuite {

        @IncludeClasses
        public List<Class<?>> findClasses() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(FirstTestClass.class);
            classes.add(SecondTestClass.class);
            return classes;
        }
    }

    @Test
    public void suite_should_succeed_when_all_test_are_ok() {

        Result result = JUnitCore.runClasses(SuccessSuite.class);

        assertThat(result.getFailureCount(), is(0));
        assertThat(result.getRunCount(), is(2));
    }
}
