package com.github.jcgay.dynsuite.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.Runner;

/**
 * @author Jean-Christophe Gay
 */
public class IsRunnerCollectionContaining<T extends Runner> extends TypeSafeMatcher<Iterable<T>> {

    private Class<?> testClass;

    public IsRunnerCollectionContaining(Class<?> testClass) {
        this.testClass = testClass;
    }

    @Override
    public boolean matchesSafely(Iterable<T> items) {
        
        for (Runner item : items) {
            if (item.getDescription().getTestClass().equals(testClass)) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection containing a Junit Runner for ")
                   .appendValue(testClass.getName());
    }

    public static <T extends Runner> Matcher<Iterable<T>> hasRunnerForTest(Class<?> testClass) {
        if (testClass == null) {
           throw new NullPointerException("Runner under existence testing could not be null");
        }
        return new IsRunnerCollectionContaining<T>(testClass);
    }
}
