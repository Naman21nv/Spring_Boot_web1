<%--
index.jsp

Purpose:
- Acts as a view template that is rendered and returned to the client (browser) by the server.
- Used in traditional Spring MVC/Servlet applications to combine HTML presentation with dynamic Java code.

Key concepts and theory:
- JSP (JavaServer Pages): A technology that helps software developers create dynamically generated web pages based on HTML, XML, or other document types.
- A JSP file is essentially an HTML page with interspersed Java code.
- At runtime, the Servlet container (e.g., Tomcat) translates the JSP into a Java Servlet, compiles it, and executes it to generate the final HTML output.

Execution flow:
1. The client sends an HTTP request mapped to the controller method (e.g., '/').
2. The controller returns a logical view name or directly points to this file ('index.jsp').
3. The view resolver locates 'index.jsp'.
4. If it's the first time this page is requested, the container translates it into a Servlet class and compiles it.
5. The container executes the Servlet's service method, which generates HTML text.
6. The HTML text is sent as an HTTP response to the client.

Detailed explanation of elements:
--%>

<%--
Page Directive: <%@page ... %>
- Used to provide instructions to the JSP container that apply to the entire JSP page.
- 'language="java"': Indicates that the scripting language used in the JSP page is Java.
- Other common attributes include 'contentType' (to specify the MIME type), 'import' (to import Java packages/classes), etc.
--%>
<%@page language="java" %>
<!-- this tag means we have some java code also and this is of type page-->

<html>
   <body>
      <h2> Hello world!</h2>
   </body>
</html>
