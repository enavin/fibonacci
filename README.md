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



<pre >
curl -i http://localhost:8080/sample/fibonacci?seqNumber=-1
</pre>

<table>
<tr>
<td>Success/Failure</td> <td>StatusCode</td> <td>Description</td>
</tr>
<tr>
<td>Success</td> <td>200</td> <td>Returns fibonacci series</td>
</tr>
<tr>
<td>Failure</td> <td>400</td> <td>Returns bad request</td>
</tr>
<tr>
<td>Failure</td> <td>500</td> <td>Other processing errors</td>
</tr>
</table>

