BACnet4J
========

BACnet4J is a pure Java implementation of the BACnet specification. Originally developed for supervisory use, it now includes support for many objects and so may be suitable for embedded use as well. Protocols supported include IPv4, IPv6, and MS/TP.

A discussion forum for this package can be found at http://infiniteautomation.com/forum/category/12/bacnet4j-general-discussion.

A public Maven Repository is now available with the latest builds add this to your pom.xml


```xml
<repositories>
    <repository>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <id>ias-snapshots</id>
        <name>Infinite Automation Snapshot Repository</name>
        <url>https://maven.mangoautomation.net/repository/ias-snapshot/</url>
    </repository>
    <repository>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>ias-releases</id>
        <name>Infinite Automation Release Repository</name>
        <url>https://maven.mangoautomation.net/repository/ias-release/</url>
    </repository>
</repositories>
```

The dependency information is:

```xml
<dependency>
    <groupId>com.serotonin</groupId>
    <artifactId>bacnet4j</artifactId>
    <version>4.0.1</version>
</dependency>
```

Releases
========
*Version 4.1.7*
- Add support for Relatime MS/TP linux realtime driver to handle token passing timing
- Change Vendor ID to 865 Infinite Automation Systems, Inc.

*Version 4.1.6*
- change http to https in JCenter Bintray repo in pom.xml

*Version 4.1.5*
- Reduce PropertyUtils.requestPropertiesFromDevice timeout log message to info as this message can be generated quite often

*Version 4.1.4*
- Fix for wrong loop condition on getting id for local device 
- Fixes for reading elements of priority array

*Version 3.2.4*
- Fixing bug in SerialPortWrapper where stop bits and data bits were reversed.

*Version 3.2.3
- Removed restriction on binding LocalDevice to 0.0.0.0
- Added code to ensure DefaultTransport thread can't die from a bad expire() call
- Added code to ServiceFutureImpl to allow using timeouts
- Using timeouts in DefaultTransport for ServiceFutures

*Version 3.2 release notes*
- Added BBMD support
- Much enhanced support for acting as a foreign device
- Improved test framework

*Version 3.0 release notes*
- The ANT build system has been replaced with Gradle
- Dependencies have been removed. BACnet4J now operates without any external libs
- Support for IPv6 added
- Ad hoc test code has begun to be replaced with JUnit tests 
- Blocking request calls have been replaced with non-blocking promises/callbacks
- Added implementations of many objects, including analog value, binary output, binary value, calendar, multistate value, notification classes, and schedules.
- Added intrinsic alarming for implemented objects
- Added COV reporting
- Many bug fixes and minor enhancements

*Version 2.0 release notes*

The networking package of this product has been pretty much entirely rewritten to support MS/TP. These changes implied many changes to the LocalDevice public interface, so if you were using version 1.x you will need to port some code to upgrade.

License
=======

This software is licensed under GPL. Commercial licensers can pay an upgrade fee to use this new version (2.x and later) commercially.
