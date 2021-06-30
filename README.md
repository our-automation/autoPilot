# README #


### What is this repository for? ###

this is a test framework and it supports 
* api testing using RESTAssurd
* ui testing using selenium(work in progess)


### How do I get set up? ###

* create a project(maven/gradle)
* Dependencies
    * maven
        ```
      <dependency>
            <groupId>com.github.mahe2421</groupId>
            <artifactId>automation-framework</artifactId>
            <version>pick from releases</version>
        </dependency>
   * gradle
     ```
     implementation 'com.github.mahe2421:automation-framework:pick from releases'

* Configuration
    * you must specify all properties files in properties folder and we can read properties by using ConfigManager as per data type of properties 
    ```   
         public Integer getInt(String key) {
               return Integer.parseInt(properties.getProperty(key));
           }
       
           public Long getLong(String key) {
               return Long.parseLong(properties.getProperty(key));
           }
       
           public Double getDouble(String key) {
               return Double.parseDouble(properties.getProperty(key));
           }
       
           public Float getFloat(String key) {
               return Float.parseFloat(properties.getProperty(key));
           }
       
           public Boolean getBoolean(String key) {
               return Boolean.parseBoolean(properties.getProperty(key));
           }
       
           public String getString(String key) {
               return properties.getProperty(key);
           }
  ``` 
  * will be reading base url as systom property or properties
    * System property 
        ```
      -DBASE_URL=base url
      ```
    *   properties
        ```
        base.url=https://www.google.co.in/
        ```
   * Extent Report      
        ```
           extent.report.path=report location
           extent.document.title=Report title
           extent.report.name=Report name
        ```
        it will have default values, if we not specifiy any value
   * Listeners
        * add below code in build.gradle
        ```
            listeners.add("com.automation.utils.listeners.TestExecutionListeners")
            listeners.add("com.automation.utils.listeners.RetryListener")
            listeners.add("com.automation.utils.listeners.TestExecutionListeners")
        ```
       
* Database configuration
* How to run tests
    ```
       # specific class
       gradle test --tests org.gradle.SomeTestClass
       
       # specific class and method
       gradle test --tests org.gradle.SomeTestClass.someSpecificMethod
       
       # method name containing spaces
       gradle test --tests "org.gradle.SomeTestClass.some method containing spaces"
       
       # all classes at specific package (recursively)
       gradle test --tests 'all.in.specific.package*'
       
       # specific method at specific package (recursively)
       gradle test --tests 'all.in.specific.package*.someSpecificMethod'
       
       gradle test --tests '*IntegTest'
       
       gradle test --tests '*IntegTest*ui*'
       
       gradle test --tests '*ParameterizedTest.foo*'
       
       # the second iteration of a parameterized test
       gradle test --tests '*ParameterizedTest.*[2]'
   ```
            
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact