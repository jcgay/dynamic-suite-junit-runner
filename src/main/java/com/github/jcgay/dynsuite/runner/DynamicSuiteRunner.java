package com.github.jcgay.dynsuite.runner;

import com.github.jcgay.dynsuite.annotation.IncludeClasses;
import com.github.jcgay.dynsuite.exception.IllegalConfigurationException;
import com.github.jcgay.dynsuite.exception.TechnicalException;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
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
        this.runners = createRunnerFor(eachTestClassToRun());
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
        if (child.getDescription().getTestClass().isAnnotationPresent(Ignore.class)) {
            notifier.fireTestIgnored(child.getDescription());
        } else {
            child.run(notifier);
        }
    }

    private List<Runner> createRunnerFor(List<Class<?>> testClasses) {
        List<Runner> runners = new ArrayList<Runner>(testClasses.size());
        for (Class<?> aTestClass : testClasses) {
            try {
                Runner runner = defineRunner(aTestClass);
                LOGGER.info("Creating runner [{}] for test class [{}].", runner.getClass().getName(), aTestClass.getName());
                runners.add(runner);
            } catch (InitializationError e) {
                LOGGER.warn("Class [{}] is not valid, ignoring it...", testClass.getName(), e);
            }
        }
        return runners;
    }
    
    private Runner defineRunner(Class<?> aTestClass) throws InitializationError{

        RunWith annotation = aTestClass.getAnnotation(RunWith.class);
        if (annotation != null) {
            try {
                return annotation.value().getConstructor(Class.class).newInstance(aTestClass);
            } catch (NoSuchMethodException e) {
                throw new TechnicalException(String.format("Cannot find constructor for runner [%s]", annotation.value().getName()), e);
            } catch (InvocationTargetException e) {
                throw new TechnicalException(String.format("Constructor for runner [%s] has thrown an exception.", annotation.value().getName()), e);
            } catch (InstantiationException e) {
                throw new TechnicalException(String.format("Class [%s] is abstract.", annotation.value().getName()), e);
            } catch (IllegalAccessException e) {
                throw new TechnicalException(String.format("Constructor for runner [%s] is not accessible.", annotation.value().getName()), e);
            }
        }

        return new BlockJUnit4ClassRunner(aTestClass);
    }

    private List<Class<?>> eachTestClassToRun() {

        return executeMethodOn(suite(), methodAnnotatedWithIncludeClasses());
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

    private List<Class<?>> executeMethodOn(Object suite, Method method) {

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
