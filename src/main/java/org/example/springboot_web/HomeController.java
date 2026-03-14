package org.example.springboot_web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 *
 * <p>Purpose:
 * - Acts as a Spring MVC controller that handles web requests and returns a view name to be rendered.
 * - This controller handles the root URL ("/") to show the home page and the "/add" URL to process a form.
 *
 * <p>Key Concepts and Theory:
 * - @Controller: This annotation marks the class as a Spring MVC controller. During application startup, Spring's component scanning detects it and creates an instance (a bean) in the application context.
 * - Request Mapping: The process of tying an incoming HTTP URL (like "/add") to a specific Java method. The DispatcherServlet uses this mapping to route requests.
 * - Servlet API: Even though we are using Spring Boot, underlying it all is the Java Servlet API. This is why we can use `HttpServletRequest` and `HttpSession`.
 *
 * <p>Execution Flow:
 * 1. The client (browser) sends an HTTP request to the server (Tomcat).
 * 2. The Spring DispatcherServlet receives the request.
 * 3. The DispatcherServlet looks at the URL and finds the matching method in this Controller (based on @RequestMapping).
 * 4. The method executes the business logic.
 * 5. The method returns a String representing the view name (e.g., "return.jsp").
 * 6. The View Resolver locates the JSP file and the container renders it to HTML.
 * 7. The HTML is returned as an HTTP response to the client.
 */
@Controller // Marks the class as a Spring MVC controller so Spring can detect and manage it.
public class HomeController {

    /**
     * Handles the root ("/") request.
     * 
     * <p>When to use:
     * - Use this to serve the initial landing page of your application.
     */
    @RequestMapping("/")
    public String home(){
        System.out.println("home method called"); // Helpful for debugging to see when the method is hit.
        return "index.jsp"; // Returns the view name. The server will look for this file in the webapp folder.
    }

    /**
     * Handles the "/add" request, typically submitted from an HTML form.
     *
     * <p>Theory and Flow:
     * 1. The form in index.jsp submits data to the "/add" URL using HTTP GET (default).
     * 2. The URL looks like: /add?num1=10&num2=20
     * 3. This method uses HttpServletRequest to extract the parameters "num1" and "num2" from the URL.
     * 4. It converts them from Strings to integers and calculates the sum.
     * 5. It uses HttpSession to store the result. The session acts as a temporary memory area specific to that user's browser session.
     * 6. It redirects the user to "return.jsp" which will display the result stored in the session.
     *
     * <p>When to use this pattern:
     * - This pattern (using HttpServletRequest and HttpSession directly) is the "classic" Servlet way of doing things.
     * - In modern Spring Boot, you'd typically use @RequestParam to bind parameters and a Model object to pass data to the view, which is cleaner and easier to test than using the raw Servlet API.
     *
     * @param req The HTTP request object, used to get form parameters.
     * @param session The HTTP session object, used to store data across multiple requests from the same user.
     * @return The name of the view to render (return.jsp).
     */
    @RequestMapping("add") // Maps the "/add" URL to this method.
    public String add(HttpServletRequest req , HttpSession session){
       // 1. Extract parameters from the request. They always come in as Strings.
       // We use Integer.parseInt to convert the String to an integer for math operations.
       int a = Integer.parseInt(req.getParameter("num1"));
       int b = Integer.parseInt(req.getParameter("num2"));
       
       // 2. Perform the business logic (addition).
       int c = a + b;
       
       // 3. Store the result in the session.
       // "result" is the key (name) we will use to retrieve it in the JSP.
       // 'c' is the value.
       session.setAttribute("result", c); 

       // 4. Return the view that will display the result.
       return "return.jsp";
    }

}