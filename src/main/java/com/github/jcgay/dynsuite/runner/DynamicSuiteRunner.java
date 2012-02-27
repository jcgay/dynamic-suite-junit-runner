package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.exception.IllegalConfigurationException;
import com.github.jcgay.dynsuite.exception.TechnicalException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * a JUnit runner to discover test classes at runtime. <br />
 * Test to run should be defined in a method annotated with {@link IncludeClasses}
 *
 * @see IncludeClasses
 * @author Jean-Christophe Gay
 */
public class DynamicSuiteRunner extends ParentRunner<Runner> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSuiteRunner.class);
    private List<Runner> runners = new ArrayList<Runner>();
    private Class<?> testClass;

    public DynamicSuiteRunner(Class<?> testClass) throws InitializationError{
        super(testClass);
        LOGGER.info("Running [{}] with dynamic suite runner.", testClass.getName());
        this.testClass = testClass;
        this.runners = createRunnerFor(eachTestClassesToRun());
    }
    
    @Override
    protected List<Runner> getChildren() {
        return runners;
    }

    @Override
    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(Runner child, RunNotifier notifier) {
        child.run(notifier);
    }

    private List<Runner> createRunnerFor(List<Class<?>> testClasses) {
        List<Runner> runners = new ArrayList<Runner>(testClasses.size());
        for (Class<?> aTestClass : testClasses) {
            try {
                runners.add(new BlockJUnit4ClassRunner(aTestClass));
                LOGGER.info("Create a runner for test class [{}].", aTestClass.getName());
            } catch (InitializationError e) {
                LOGGER.warn("Class [{}] is not valid, ignoring it...", testClass.getName(), e);
            }
        }
        return runners;
    }

    private List<Class<?>> eachTestClassesToRun() {

        return runFor(suite(), methodAnnotatedWithIncludeClasses());
    }

    private Method methodAnnotatedWithIncludeClasses() {

        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            IncludeClasses annotation = method.getAnnotation(IncludeClasses.class);
            if (annotation != null) {
                LOGGER.info("Found method [{}] defining test classes to execute.", method.getName());
                return method;
            }
        }
        throw new IllegalConfigurationException("Can't find a method annotated with @IncludeClasses");
    }

    private List<Class<?>> runFor(Object suite, Method method) {

        try {
            return (List<Class<?>>) method.invoke(suite);
        } catch (IllegalAccessException e) {
            throw new TechnicalException(String.format("Can't access Method [%s] in class [%s].", method.getName(), testClass.getName()), e);
        } catch (InvocationTargetException e) {
            throw new TechnicalException(String.format("Method [%s] in class [%s] has thrown an exception.", method.getName(), testClass.getName()), e);
        }
    }

    private Object suite() {
        try {
            return testClass.newInstance();
        } catch (InstantiationException e) {
            throw new TechnicalException(String.format("Can't instantiate runner class [%s]", testClass.getName()), e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException(String.format("Can't access runner class [%s]", testClass.getName()), e);
        }
    }
}
