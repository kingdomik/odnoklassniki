==================================
 ODNOKLASSNIKI PROFILE TEST SUITE
==================================

 STRUCTURE
===========
    bin                             Compiled classes output folder
    lib                             JAR files
    src                             
        META-INF                    TestNG listener registration
        ru/odnoklassniki/tests      Framework sources
            common                  Framework shared classes 
            runner                  Test runner
            ui/api                  Web API
    temp                            Temporary files, e.g. Selenium profile
    test-output                     TestNFG report folder, see html/index.html
    tests
        ru/odnoklassniki/tests/ui   Tests
    build.xml                       Ant build file
    logging.properties              Selenium logging settings
    testbox,log                     Framework log
    
    test-output.sample              Sample TestNFG report    
    output.sample                   Sample test run output
    testbox.log.sample              Sample test run log
    
    
 REQUIREMENTS
==============
    * Java 1.7 or higher    https://www.java.com/en/download/
    * Ant  1.8 or higher    http://ant.apache.org/bindownload.cgi
 
    Tested browsers       
    * Firefox 28.0
    * Chrome 34.0


 RUNNING
=========
    Run ant from root folder using one of commands
    
    Commands:
        ant
        ant -Dbrowser=firefox
        ant -Dbrowser=googlechrome
        ant -DproxyHost=HOST -DproxyPort=PORT
    
    Output:
        Buildfile: E:\Work\odnoklassniki\build.xml
        clean:
           [delete] Deleting directory E:\Work\odnoklassniki\bin
        prepare:
            [mkdir] Created dir: E:\Work\odnoklassniki\bin
        resources:
             [copy] Copying 6 files to E:\Work\odnoklassniki\bin
        compile:
            [javac] Compiling 47 source files to E:\Work\odnoklassniki\bin
        test:
             [echo] Browser firefox
           [testng] [TestNG] Running:
           [testng]   User Profile Tests
           [testng] jar:file:/E:/Work/odnoklassniki/lib/selenium-server-standalone-2.41.0.jar!/customProfileDirCUSTFFCHROME
           [testng]  PASSED : ru.odnoklassniki.tests.ui.profile.PersonalInfoEditTest.testBiirthCityLong
           ...
           [testng]  PASSED : ru.odnoklassniki.tests.ui.profile.PersonalInfoInvalidEditTest.testUnknownCity
           [testng] ===============================================
           [testng] User Profile Tests
           [testng] Total tests run: 78, Failures: 0, Skips: 0
           [testng] ===============================================
        all:
        BUILD SUCCESSFUL
        Total time: 3 minutes 3 seconds


    Report:
        test-output/html/index.html
       
    Log:
        testbox,log
        