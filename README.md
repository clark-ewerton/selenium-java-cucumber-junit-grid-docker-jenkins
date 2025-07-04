# [OLD PROJECT] Project to demonstrate knowledge in tools such as Selenium + Java 8 + Cucumber + Junit 4 + Docker + Grid (Parallel Testing) + Jenkins (Windows) + Cucumber Report

Don't forget to give this project a ⭐

⚠️ Considerations: this project is out to date because is using old technologies such as Junit 4 and Java 8 (this project will only work with these versions) okay? Moreover, it also uses a deprecated library called Cucable to run in parallel the Cucumber Runners (nowadays there're ways more simples to run a e2e testing using cucumber in paralell). Anyway it'll be here as a model.

For this project, it was took into account the Windows as main environment! Also I'm using a testing ecommerce Website: https://adasdsacarrinhomagico.mycartpanda.com/collections/all

Local testing execution example
![Local testing execution example](example_local_testing_execution.gif)

Cucumber Report being generated after the test execution above
![Local testing execution example](cucumber-report.gif)

Parallel testing execution example with Docker Selenium
![Parallel testing execution example with Docker Selenium](selenium-grid-execution.gif)

## Project Structure
```bash
selenium-java-cucumber-junit-grid-docker-jenkins/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ com/
│  │  │  │  │  ├─ clark/
│  │  │  │  │  │  ├─ config/
│  │  │  │  │  │  │  ├─ Configuration.java
│  │  │  │  │  │  │  ├─ ConfigurationManager.java
│  │  │  │  │  │  ├─ driver/
│  │  │  │  │  │  │  ├─ BrowserFactory.java
│  │  │  │  │  │  │  ├─ DriverManager.java
│  │  │  │  │  │  │  ├─ TargetFactory.java
│  │  │  │  │  │  ├─ exceptions/
│  │  │  │  │  │  │  ├─ HeadlessNotSupportedException.java
│  │  │  │  │  │  │  ├─ TargetNotValidException.java
│  │  │  │  │  │  ├─ page/
│  │  │  │  │  │  │  ├─ ecommerce/
│  │  │  │  │  │  │  │  ├─ HomePage.java
│  │  │  │  │  │  │  │  ├─ CarrinhoComprasPage.java
│  │  │  │  │  │  │  ├─ AbstractPageObject.java
│  │  ├─ resources/
│  │  │  ├─ log4j2.properties
│  ├─ test/
│  │  ├─ java/
│  │  │  ├─ com/
│  │  │  │  ├─ clark/
│  │  │  │  │  │  ├─ runner/
│  │  │  │  │  │  │  ├─ parallel
│  │  │  │  │  │  │  |  ├─ CucableJavaTemplate.java
│  │  │  │  │  │  │  ├─ CucumberRunnerTest.java
│  │  │  │  │  │  ├─ stepDefinitions/
│  │  │  │  │  │  │  ├─ CarrinhoCompras_Steps.java
│  │  │  │  │  │  │  ├─ Home_Steps.java
│  │  │  │  │  │  │  ├─ Hooks.java
│  │  ├─ resources/
│  │  │  ├─ features/
│  │  │  │  ├─ eCommerceScenarios.feature
│  │  │  │  ├─ eCommerceScenarios2.feature
│  │  │  ├─ general.properties
│  │  │  ├─ grid.properties
│  │  │  ├─ local.properties
├─ .gitignore
├─ docker-compose.yml
├─ Jenkinsfile
├─ pom.xml
├─ README.md
```

## Languages and Frameworks

This project using the following languages and frameworks:

* [Java 8](https://openjdk.java.net/projects/jdk8/) as the programming language (make sure you have this version installed on your machine and IDE)
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
* [Logging](#logging)
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

#### Logging
All the log is done by the Log4J using the `@Log4j2` annotation.

The `log4j2.properties` has two strategies: console and file. A file with all the log information will be automatically created on the user folder with `test_automation.log` filename. If you want to change it, update the `appender.file.fileName` property value.

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


#### Screenshots
After the test's execution, if there's an error in any scenario, all the screenshots's images related to that one will be saved on "Screenshots" directory of the project. The login behind this is on `"Hooks" step class`.


#### Execution with Docker Selenium Distributed
This project has the `docker-compose.yml` file to run the tests in a parallel way using Docker Selenium.
To be able to run it in parallel the file 

Please not you need the following before run it in parallel:
* Docker installed
* Open CMD and go to the directory where the docker file is.
* Start the Grid running the following command as for example:
  * `docker-compose up -d --scale chrome=2` 

In the case above, to see in fact the concept of parallel testing working on your machine locally, it creates 2 nodes (machines) - each one with a instance of a chrome - and both will be used. You can open the URL http://localhost:4444/ui/index.html#/sessions to see it happening.

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

* Jenkins: `Jenkinsfile` to be used on a Jenkins pipeline. 
Bear in mind that this jenkins file is considering to use Windows as environment, if you try to set up a jenkins from Docker using Linux, it may not run (because the latest image of Jenkins from Docker considers Java 17, in this case it wouldn't work with this project okay?). So just download and install manually Jenkins to your Windows env. On Jenkins is very important to configure JDK as 8 and also Maven. Moreover, you need to create a pipeline that points to the jenkins file, there are tutorials on internet teaching how to create a pipeline from SCM. On top of that, you also need to install a plugin from Cucumber Report on the Jenkins.
![pipeline_jenkins_example](pipeline_jenkins_example.png)
