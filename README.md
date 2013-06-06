Fibonacci
=========

This sample fibonacci REST service is built using SpringFramework v3.2, JDK 1.6, Servlet 3.x.

It generates fibonacci series as output based on a given number. The result can be outputted in the form of XML or JSON. XML is the default.

<h3>API Details:</h3>

<table  border="1">
<tr>
<td bgcolor=grey><b>Verb</b></td> <td><b>URI</b></td> <td><b>Description</b></td>
</tr>
<tr>
<td>GET</td> <td>fibonacci?seqNumber=integer&format=xml|json</td> <td>Get fibonacci series</td>
</tr>
</table>


<h3>Execute</h3>
<pre >
curl -i http://localhost:8080/sample/fibonacci?seqNumber=5
</pre>

Input

<table>
<tr>
<td><b>Name</b></td> <td><b>Style</b></td> <td><b>Type</b></td> <td><b>Description</b></td>
</tr>
<tr>
<td>seqNumber</td> <td>Query Parameter</td> <td>Integer</td><td>Should be a valid positive number</td>
</tr>
<tr>
<td>format</td> <td>Query Parameter</td> <td>String</td><td>Acceptable values "xml", "json"</td>
</tr>
</table>



Output

<table>
<tr>
<td><b>Success/Failure</b></td> <td><b>StatusCode</b></td> <td><b>Description</b></td>
</tr>
<tr>
<td>Success</td> <td>200</td> <td>Returns fibonacci series</td>
</tr>
<tr>
<td>Failure</td> <td>400</td> <td>Returns bad request (in case of negative sequence number)</td>
</tr>
<tr>
<td>Failure</td> <td>500</td> <td>Other processing errors</td>
</tr>
</table>

<h3>Example Output</h3>

<b>Success XML Response</b>
<textarea><?xml version="1.0" encoding="UTF-8" standalone="yes"?><fibonacci><value index="0">0</value><value index="1">1</value><value index="2">1</value><value index="3">2</value><value index="4">3</value></fibonacci></textarea>


<b>Failure XML Response</b>
```xml
<text<?xml version="1.0" encoding="UTF-8" standalone="yes"?><badRequest code="400"><message>Bad Request</message><details>Negative number is not allowed. Please check your input and retry.</details></badRequest>
```



<h3>Usage</h3>
<b>Clone and Build</b>
<pre>
git clone git@github.com:enavin/fibonacci.git
mvn package
</pre>


This would generate the war file "fibonacci-0.0.1-SNAPSHOT.war" under target folder. 
Rename the war file to "sample.war" and deploy to any of the J2EE Application Server.
This app has been tested in Tomcat 6.x and SpringSource tc Server 2.1. 

Refer application server install guide for deployment.  After deploying the app, start the app server and invoke below url to validate if the service is working fine or not.
<pre>http://hostname:port/sample</pre>
Tomcat app server runs on 8080 port by default, so the url could look like <pre>"http://localhost:8080/sample"</pre>

You should see the message "Fibonacci Sample app is up and running...."
If not, please send the error detail to my id "idmvoice@gmail.com". I will get back to you as soon as I can.


<h3>Summary</h3>
While the sample is trivial, this should provide enough detail to get you up and running RESTful service using SpringFramework. You can download the code and modify it as you see fit. Enjoy!


