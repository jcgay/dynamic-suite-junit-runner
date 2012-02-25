package com.github.jcgay.dynsuite.example;

import com.github.jcgay.dynsuite.fakeTestClasses.FirstTest;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jean-Christophe Gay
 */
@RunWith(DiscoverRunnerImplementationTest.TestRunner.class)
public class DiscoverRunnerImplementationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoverRunnerImplementationTest.class);

    public static class TestRunner extends ParentRunner<Runner> {

        List<Runner> runners = new ArrayList<Runner>();

        public TestRunner(Class<? extends TestRunner> clazz) throws InitializationError{
            super(clazz);
            runners.add(new BlockJUnit4ClassRunner(FirstTest.class));
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
}
