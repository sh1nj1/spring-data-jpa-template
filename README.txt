README file.

This is common settings module for SpringFramework + Spring-Data + Hibernate project.

Usage
===============

Add maven dependency to your project.

Xml Configuration:

com.trumpia.springframework.test.XmlConfigurationTest

Copy src/main/resources/local/* to your configuration classpath.
and modify the configuration if necessary.

If you are use xml based configuration,

Copy src/main/resources/xml/* to your configuration classpath
and modify the configuration if necessary.

TODO:
Annotation configuration(when packaged, it is not work)

com.trumpia.springframework.test.AnnotationConfigurationTest


Libraries
===============

Spring Framework
Spring-Data
JPA2
Hibernate3 (migrating to Hibernate4 is not yet ready, because Framework module uses hibernate3)

To use IDE
===============

For Eclipse setting.
--------------------

Need to set Hibernate Annotation Processor in Eclipse, if you use that.

Reference:

http://docs.jboss.org/hibernate/validator/4.2/reference/en-US/html/validator-annotation-processor.html

Do the following to use the annotation processor within the Eclipse IDE:

Right-click your project, choose "Properties"
Go to "Java Compiler" and make sure, that "Compiler compliance level" is set to "1.6". Otherwise the processor won't be activated
Go to "Java Compiler - Annotation Processing" and choose "Enable annotation processing"
Go to "Java Compiler - Annotation Processing - Factory Path" and add the following JARs:
select "Add Variable" (I assume that you have already set M2_REPO variable which points to maven local repository)
and select M2_REPO variable and extend it to select below file.
M2_REPO/org/hibernate/hibernate-jpamodelgen/1.2/hibernate-jpamodelgen-1.2.0.Final.jar
Confirm the workspace rebuild

For IntelliJ
--------------------
No need to specific settings.

