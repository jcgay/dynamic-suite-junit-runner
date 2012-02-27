package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.exception.IllegalConfigurationException;
import com.github.jcgay.dynsuite.fakeTestClasses.CustomRunner;
import com.github.jcgay.dynsuite.fakeTestClasses.CustomTestUsingCustomRunner;
import com.github.jcgay.dynsuite.fakeTestClasses.FirstTest;
import com.github.jcgay.dynsuite.fakeTestClasses.InvalidTest;
import org.junit.Test;
import org.junit.runner.Runner;

import java.util.ArrayList;
import java.util.List;

import static com.github.jcgay.dynsuite.matcher.IsRunnerCollectionContaining.hasRunnerForTest;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Jean-Christophe Gay
 */
public class DynamicSuiteRunnerTest {

    public static class AnnotatedMethod {

        @IncludeClasses
        public List<Class<?>> aMethodReturningTest() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(FirstTest.class);
            return classes;
        }
    }

    public static class NotAnnotatedMethod {

        public List<Class<?>> aMethodReturningNothing() {
            return null;
        }
    }
    
    public static class InvalidTestMethod {

        @IncludeClasses
        public List<Class<?>> aMethodReturningInvalidTest()  {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(InvalidTest.class);
            return classes;
        }
    }

    public static class CustomRunnerMethod {

        @IncludeClasses
        public List<Class<?>> aMethodReturningTestWithCustomRunner() {
            List<Class<?>> classes = new ArrayList<Class<?>>();
            classes.add(CustomTestUsingCustomRunner.class);
            return classes;
        }
    }

    @Test
    public void runner_initialization_should_find_one_class_to_run() throws Exception{

        DynamicSuiteRunner runner = new DynamicSuiteRunner(AnnotatedMethod.class);
        
        assertThat(runner.getChildren(), hasRunnerForTest(FirstTest.class));
    }

    @Test(expected = IllegalConfigurationException.class)
    public void runner_should_not_be_able_to_initialize_a_suite_if_no_method_is_annotated() throws Exception {

        new DynamicSuiteRunner(NotAnnotatedMethod.class);
    }

    @Test
    public void an_invalid_test_class_should_be_reported_by_a_warning_message () throws Exception {

        new DynamicSuiteRunner(InvalidTestMethod.class);
    }

    @Test
    public void a_discovered_test_class_should_use_its_custom_runner_instead_of_the_default_one() throws Exception {

        List<Runner> runners = new DynamicSuiteRunner(CustomRunnerMethod.class).getChildren();

        assertThat(runners, hasRunnerForTest(CustomTestUsingCustomRunner.class));
        assertThat(runners.get(0), is(instanceOf(CustomRunner.class)));
    }
}
