package com.github.jcgay.dynsuite.test.classes;

import com.github.jcgay.dynsuite.exception.TechnicalException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean-Christophe Gay
 */
public class CustomRunner extends ParentRunner<Runner> {

    private Class<?> testClass;
    private List<Runner> runners = new ArrayList<Runner>();

    /**
     * Constructs a new {@code ParentRunner} that will run {@code @TestClass}
     *
     * @throws org.junit.runners.model.InitializationError
     *
     */
    public CustomRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        runners.add(new BlockJUnit4ClassRunner(testClass));
        try {
            testClass.getDeclaredField("verifyThatDummyRunnerHasBeenUsed").set(null, "run.by.CustomRunner");
        } catch (IllegalAccessException e) {
            throw new TechnicalException(e);
        } catch (NoSuchFieldException e) {
            throw new TechnicalException(e);
        }
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
}
