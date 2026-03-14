<%--
index.jsp

Purpose:
- Acts as the main view template for the home page.
- It displays a welcome message and a form to collect two numbers from the user.

Key concepts and theory:
- JSP (JavaServer Pages): A technology that helps software developers create dynamically generated web pages based on HTML.
- HTML Form: The <form> element is used to create an HTML form for user input.
- Form Action: The 'action="add"' attribute specifies that when the form is submitted, the data should be sent to the "/add" URL on the server.
- Form Input: The <input> elements are used to create interactive controls for web-based forms in order to accept data from the user.
- 'name' attribute: The 'name' attribute of the input fields ("num1", "num2") is crucial. It becomes the key for the parameter sent to the server.

Execution flow:
1. The user navigates to the root URL ("/").
2. The HomeController's home() method returns "index.jsp".
3. The server renders this JSP file into HTML and sends it to the user's browser.
4. The user sees the form, enters two numbers, and clicks the "Add" button.
5. The browser creates a new request to the URL specified in the form's action attribute: /add?num1=<value1>&num2=<value2>
6. This request is then handled by the add() method in the HomeController.
--%>

<%@page language="java" %>
<!-- This is a page directive. 'language="java"' tells the JSP container that the scripting language is Java. -->

<html>
<link rel="stylesheet" type="text/css" href="style.css" />
   <body>
      <h2> Hello world!</h2>

    <!-- This form will send a GET request to the 'add' URL when submitted. -->
    <form action="add">
       <label for="num1">Number 1:</label>
       <!-- The 'name' attribute is used by the server to identify the parameter. -->
       <input type="text" id="num1" name="num1"><br><br>

       <label for="num2">Number 2:</label>
       <input type="text" id="num2" name="num2"><br><br>

       <!-- The submit button triggers the form action. -->
       <input type="submit" value="Add">
    </form>

   </body>
</html>
