Welcome to the source code for our book, Pro JSF and Ajax: Building Rich Internet Components.

You will find that the code is laid out with a separate directory per chapter, making it easy for you to find the relevant code as you read each chapter in the book.

The code is already setup as a workspace for Oracle JDeveloper 10.1.3 Studio, available for download from http://www.oracle.com/technology/software/products/jdev/index.html - we highly recommend it!

In addition, each directory also contains a pre-built WAR that can be deployed directly to your favorite J2EE 1.4 application server, or imported as a project into a your Java IDE.

  README.txt          -  this file
  ProJSFandAjax.jws   -  JDeveloper workspace
  chapter01/
    chapter01.jpr     -  JDeveloper project
    chapter01.deploy  -  JDeveloper WAR deployment profile
    src/
      java/           -  Chapter 1 Java sources
      webapp/         -  Chapter 1 Web application root
    deploy/
      chapter01.war   -  Chapter 1 WAR
  chapter02/
    ...
    ...
  chapter10/
    chapter10.jpr     -  JDeveloper project
    chapter10.deploy  -  JDeveloper WAR deployment profile
    src/
      java/           -  Chapter 10 Java sources
      webapp/         -  Chapter 10 Web application root
    deploy/
      chapter10.war   -  Chapter 10 WAR

We hope that you enjoy reading the source code as much as we enjoyed writing it!

Jonas & John.

Note:  Chapter 10 contains the binary version of ADF Faces runtime donated to Apache Software Foundation under the Apache 2.0 License.

KNOWN ISSUES
There is currently an issue with rendering of XUL to Firefox. Deploying to OC4J partially solve this, allowing you to hit refresh in the browser a couple of times and the component will show up. This has an impact on Chapter 8 and 10. We are currently investigating this and we will hopefully have a solution available as soon as possible. If you have questions or are running into issues please send an email to projsf@gmail.com