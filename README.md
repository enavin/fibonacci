Fibonacci
=========

This sample fibonacci REST service is built using SpringFramework v3.2, JDK 1.6, Servlet 2.5.

It generates fibonacci series as output based on a given number. The result can be outputted in the form of XML or JSON. XML is the default.

<b>API Details:</b>

<table  border="1">
<tr>
<td bgcolor=grey>Verb</td> <td>URI</td> <td>Description</td>
</tr>
<tr>
<td>GET</td> <td>fibonacci?seqNumber=integer&format=xml|json</td> <td>Get fibonacci series</td>
</tr>
</table>


<b>Execute</b>
<pre >
curl -i http://localhost:8080/sample/fibonacci?seqNumber=5
</pre>

<h1>Usage</h1>
<b>Clone and Build</b>
<pre>
git clone git@github.com:enavin/fibonacci.git
mvn package
</pre>

<pre>
This would generate the war file "fibonacci-0.0.1-SNAPSHOT.war" under target folder. Rename the war file to "sample.war" and deploy to any of the J2EE Application Server.
This app has been tested in Tomcat 6.x and SpringSource tc Server 2.1. Refer application server install guide for deployment.
After deploying the app, start the app server and invoke below url to validate if the service is working fine or not.
http://<<host url>>:port/sample
Default Tomcat app server runs on 8080 port, so the url could look like "http://localhost:8080/sample"
You should see the message "Fibonacci Sample app is up and running...."
If not, please send the error detail to my id "idmvoice@gmail.com". I will get back to you as soon as I can.

</pre>


<table>
<tr>
<td>Name</td> <td>Style</td> <td>Type</td> <td>Description</td>
</tr>
<tr>
<td>seqNumber</td> <td>Query Parameter</td> <td>String</td><td>Should be a valid positive number</td>
</tr>
<tr>
<td>format</td> <td>Query Parameter</td> <td>String</td><td>Acceptable values "xml", "json"</td>
</tr>
</table>


<table>
<tr>
<td>Success/Failure</td> <td>StatusCode</td> <td>Description</td>
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


