# Project to demonstrate knowledge in tools such as Selenium + Java + Cucumber + Junit + Docker + Grid (Parallel Testing) + Jenkins + Report

For this project, it was took into account the Windows as main environment!

Local testing execution example
![Local testing execution example](example_filed_test_with_report.gif)

Parallel testing execution example with Docker Selenium
![Parallel testing execution example with Docker Selenium](selenium-grid-execution.gif)

## Languages and Frameworks

This project using the following languages and frameworks:

* [Java 8](https://openjdk.java.net/projects/jdk8/) as the programming language
* [JUnit 4](https://junit.org/junit4/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework
* [Cucumber](https://cucumber.io/) as the tool that supports Behaviour-Driven Development(BDD) making out tests more describable
* [Cluecumber-Report-Plugin](https://github.com/trivago/cluecumber-report-plugin) as the testing report strategy
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as the Selenium binaries management
* [Owner](http://owner.aeonbits.org/) to minimize the code to handle the properties file
* [Cucable Plugin](https://github.com/trivago/cucable-plugin) to run Cucumber Scenarios in Parallel with Maven
* [Maven](https://maven.apache.org/) as the Java build tool
* [Eclipse](https://www.eclipse.org/) as the IDE


## Test architecture

We know that any automation project starting with a good test architecture.
You will see the following items in this architecture:

* [Page Objects pattern](#page-objects-pattern)
* [Execution types](#execution-types)
* [Configuration files](#configuration-files)
* [Sequential execution](#sequential-execution)
* [Parallel execution](#parallel-execution)
* [Pipeline as a code](#pipeline-as-a-code)

Do you have any other items to add to this test architecture? Please do a pull request or open an issue to discuss.

### Page Objects pattern
I will not explain the Page Object pattern because you can find a lot of good explanations and examples on the internet. 
Instead, I will explain what exactly about page objects I'm using in this project.

#### AbstractPageObject
This class has a protected constructor to remove the necessity to init the elements using the Page Factory. 

All the Page Object classes should extend the `AbstractPageObject`.
It also tries to remove the `driver` object from the Page Object class as much as possible.
Also all the common methods that implements Selenium that can be used by any Page Object class are gathered in this class

As much as possible avoid this strategy to not get an `ElementNotFoundException` or `StaleElementReferenceException`.
Use this approach if you know that the page does not refresh.

### Execution types

There are two execution types: **local** and **remote**.
The `TargetFactory` class will resolve the target execution based on the `target` property value located on `general.properties` file.
Its usage is placed on the `CucumberRunnerTest` class before all test execution.

#### Local execution
This execution type uses [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) class to instantiate the web browsers.
When the `target` is `local` the `createDriver()` method is used from the `BrowserFactory` class to return the browser instance.

The browser used in the test is placed on the `browser` property in the `local.properties` file.

#### Remote execution
This execution is based on any Selenium Grid approach to execute the tests in remote machines (local or remote/cloud grid).
When the `target` is `remote` the `getOptions` method is used from the `BrowserFactory` to return the browser option 
class as the remote execution needs the browser capability.
The `DriverFactory` class has an internal method `createRemoteInstance` to return a `RemoteWebDriver` instance based on 
the browser capability.

You must pay attention to the two required information regarding the remote execution: the `grid.url` and `grid.port`
property values on the `grid.properties` file. You must update these values before the start.

If you are using the `docker-compose.yml` file to start the Docker Selenium grid, the values on the `grid.properties` file should work.

Please take a look at the [Parallel Execution](#parallel-execution) section.

#### BrowserFactory class
This Factory class is a Java enum that has all implemented browsers to use during the test execution.
Each browser is an enum, and each enum implements two methods:
* `createDriver()`: creates the browser instance for the local execution. The browser driver is automatically managed by the WebDriverManager library
* `getOptions()`: creates a new browser Options setting some specific configurations. It's used for the remote executions

You can see that the `createDriver()` method used the `getOptions()` to use specific configuration, as starting the browser maximized and others.
The `getOptions()` is also used for the remote execution as it is a subclass of the `AbstractDriverOptions` and can be 
automatically accepted as either a `Capabilities` or `MutableCapabilities` class, which is required by the `RemoteWebDriver` class.

#### DriverManager class
The class [DriverManager](https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/master/src/main/java/com/eliasnogueira/driver/DriverManager.java) 
create a `ThreadLocal` for the WebDriver instance, to make sure there's no conflict when we run it in parallel.

### Sequential execution
If you want to execute your selenium web tests in a stardand way, all you have to do is to run the class 'CucumberRunnerTest' as 'JUnit Tests'. This class implements the calling to Cucumber Plugin and its 'Steps Classes'. Moreover, has the pre (setup) and post (teardown) conditions. 

The 'Step Classes' itself only calls the methods of Page Object Classes. 

The pre-condition uses `@BeforeClass` from JUnit creates the browser instance based on the values passed either local or remote execution.
The post-condition uses `@AfterClass` to close the browser instance.

Another way to run it, its through Maven based on command: `mvn tests`

### Parallel execution
The parallel test execution is based on plugin [cucable](https://github.com/trivago/cucable-plugin).

As it's designed to Cucumber, to operate correctly, make sure to add to your pom.xml all the three plugins bellow:

* maven-failsafe-plugin
* build-helper-maven-plugin
* cucable-plugin

In 'cucable-plugin', make sure to point to the correct directory that evidences the 'features' as well as to create the class 'CucableJavaTemplate' that will manage all the logic behind the parallel execution.

On this way, to execute the tests in parallel mode, use the command bellow in Maven:

`verify -Dthreads=8` (As threads is passed by a parameter to maven-failsafe-plugin. Also 8 is the maximmum number of threads that you can do open locally to execute the tests at the same time).


#### Execution with Docker Selenium Distributed
This project has the `docker-compose.yml` file to run the tests in a parallel way using Docker Selenium.
To be able to run it in parallel the file 

Please not you need the following before run it in parallel:
* Docker installed
* Start the Grid running the following command as for example:
  * `docker-compose up -d --scale chrome=2` 

In the case above, to see in fact the concept of parallel testing working on your machine locally, it creates 2 nodes (machines) - each one with a instance of a chrome - and both will be used.

### Configuration files
This project uses a library called [Owner](http://owner.aeonbits.org/). You can find the class related to the property 
file reader in the following classes:
* [Configuration](https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/master/src/main/java/com/eliasnogueira/config/Configuration.java)
* [ConfigurationManager](https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/master/src/main/java/com/eliasnogueira/config/ConfigurationManager.java)

There are 3 properties (configuration) files located on `src/test/java/resources/`:
* `general.properties`: general configuration as the target execution, base url, timeout, and faker locale
* `grid.properties`: url and port for the Selenium grid usage
* `local.properties`:  browser to use in the local execution

The properties were divided into three different ones to better separate the responsibilities and enable the changes easy 
without have a lot of properties inside a single file.

### Pipeline as a code

The two files of the pipeline as a code are inside `pipeline_as_code` folder.

* GitHub Actions to use it inside the GitHub located at `.github\workflows`
* Jenkins: `Jenkinsfile` to be used on a Jenkins pipeline located at `pipeline_as_code`
