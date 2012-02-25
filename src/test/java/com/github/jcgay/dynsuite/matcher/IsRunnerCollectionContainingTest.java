package com.github.jcgay.dynsuite.matcher;

import com.github.jcgay.dynsuite.fakeTestClasses.FirstTest;
import com.github.jcgay.dynsuite.fakeTestClasses.SecondTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static com.github.jcgay.dynsuite.matcher.IsRunnerCollectionContaining.hasRunnerForTest;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * @author Jean-Christophe Gay
 */
public class IsRunnerCollectionContainingTest {

    private Runner firstTestRunner;
    private Runner secondTestRunner;

    @Before
    public void initializeTest() throws Exception{
        firstTestRunner = new BlockJUnit4ClassRunner(FirstTest.class);
        secondTestRunner = new BlockJUnit4ClassRunner(SecondTest.class);
    }
    
    @Test
    public void should_not_fail_when_a_collection_is_containing_a_runner() throws Exception{

        assertThat(Arrays.asList(firstTestRunner), hasRunnerForTest(FirstTest.class));
        assertThat(Arrays.asList(firstTestRunner, secondTestRunner), hasRunnerForTest(SecondTest.class));
    }

    @Test(expected = NullPointerException.class)
    public void should_fail_if_argument_is_null() throws Exception {

        assertThat(Arrays.asList(firstTestRunner), hasRunnerForTest(null));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_when_a_collection_is_not_containing_a_runner() throws Exception {

        assertThat(Arrays.asList(firstTestRunner), hasRunnerForTest(String.class));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_if_collection_is_empty() throws Exception {

        assertThat(new ArrayList<Runner>(), hasRunnerForTest(String.class));
    }

    @Test(expected = AssertionError.class)
    public void should_fail_if_collection_is_null() throws Exception {

        assertThat(null, hasRunnerForTest(String.class));
    }

    @Test
    public void test_failing_message_should_contains_runner_name_not_found() throws Exception {

        try {
            assertThat(Arrays.asList(firstTestRunner), hasRunnerForTest(String.class));
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("a collection containing a Junit Runner for \"java.lang.String\""));
        }
    }

    @Test
    public void should_allow_to_verify_a_collection_against_presence_of_multiple_runners() throws Exception {

        assertThat(Arrays.asList(firstTestRunner, secondTestRunner), hasRunnerForTest(SecondTest.class));
        assertThat(Arrays.asList(firstTestRunner, secondTestRunner), allOf(hasRunnerForTest(FirstTest.class), hasRunnerForTest(SecondTest.class)));
    }
}
