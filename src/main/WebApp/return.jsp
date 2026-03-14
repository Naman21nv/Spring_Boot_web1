<!-- we use <% %> tag for writing java code inside jsp file -->
<%@page language="java" %>
<html>

<body>
<h1>Result is:  <%out.println(session.getAttribute("result"));%> </h1>
<h1>Result is:  <%=session.getAttribute("result")%> </h1>
<h1>Result is:  ${result} </h1> <!-- this is called jstl (java server pages tag library) way -->

<!-- all works and give same result -->
<!-- we no need to create obj for session everything is created by itself we can just use it , it does everything in backgroubnd-->

</body>
</html>