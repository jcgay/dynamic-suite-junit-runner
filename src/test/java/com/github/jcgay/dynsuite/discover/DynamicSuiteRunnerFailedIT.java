package com.github.jcgay.dynsuite.discover;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.fakeTestClasses.FailingTest;
import com.github.jcgay.dynsuite.fakeTestClasses.FirstTest;
import com.github.jcgay.dynsuite.fakeTestClasses.SecondTest;
import com.github.jcgay.dynsuite.runner.DynamicSuiteRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean-Christophe Gay
 */
@RunWith(DynamicSuiteRunner.class)
public class DynamicSuiteRunnerFailedIT {

    @IncludeClasses
    public List<Class<?>> findClasses() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(FirstTest.class);
        classes.add(FailingTest.class);
        classes.add(SecondTest.class);
        return classes;
    }
    
}
