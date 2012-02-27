package com.github.jcgay.dynsuite.fakeTestClasses;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author jcgay
 */
@RunWith(CustomRunner.class)
public class CustomTestUsingCustomRunner {

    public static String verifyThatDummyRunnerHasBeenUsed;

    @Test
    public void should_retrieve_member_value_set_by_the_runner() {
         assertThat(verifyThatDummyRunnerHasBeenUsed, is("run.by.CustomRunner"));
    }
}
