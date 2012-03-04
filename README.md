# Dynamic Suite JUnit Runner

This is a runner to define tests suite at runtime.

## Usage
***

#### Add dependency with your favorite build tool.

Example with *Maven*:

	<project>
  		...
  		<repositories>
    		<repository>
      			<id>jcgay-releases</id>
      			<url>https://repository-jcgay.forge.cloudbees.com/release/</url>
    		</repository>
  		</repositories>
  		...
  		<dependencies>
  			<dependency>
	    		<groupId>com.github.jcgay</groupId>
      			<artifactId>dynamic-suite-junit-runner</artifactId>
      			<version>0.3</version>
      			<scope>test</scope>
    		</dependency>
  		</dependencies>
  		...
	</project>

#### Write a suite.

- Create a class and configure it to be executed with the runner `com.github.jcgay.dynsuite.runner.DynamicSuiteRunner`.  
- Write a method to find your tests (it should be public and returning a `java.util.List` of `java.lang.Class`) and annotate it with `com.github.jcgay.dynsuite.annotation.IncludeClasses`.

Example:

	@RunWith(DynamicSuiteRunner.class)
	public class UnitTestSuite {

    	@IncludeClasses
    	public List<Class<?>> findClasses() {

        	List<Class<?>> classes = new ArrayList<Class<?>>();
        	classes.add(FirstTest.class);
        	classes.add(SecondTest.class);
        	return classes;
    	}
	}

#### Execute your suite.