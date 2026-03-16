package org.example.springboot_web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // Import for @RequestParam

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
 * - View Resolver (configured via `spring.mvc.view.prefix` and `spring.mvc.view.suffix` in `application.properties`):
 *   - Theory: The View Resolver is a crucial component in Spring MVC that translates a logical view name (a String returned by a controller method, e.g., "index") into a physical view resource (e.g., `/view/index.jsp`).
 *   - Why use it: It decouples the controller from the exact location and file extension of the view. This makes the application more maintainable and flexible. If you change the location of your JSPs or switch view technologies, you only update the properties, not every controller method.
 *   - Flow: When a controller returns "index", the View Resolver, using the configured prefix (`/view/`) and suffix (`.jsp`), constructs the full path `/view/index.jsp` to find the actual JSP file.
 */
@Controller
public class HomeController {

    /**
     * Handles the root ("/") request.
     *
     * <p>When to use:
     * - Use this to serve the initial landing page of your application.
     *
     * <p>View Resolution:
     * - Returns the logical view name "index".
     * - Due to the `spring.mvc.view.prefix=/view/` and `spring.mvc.view.suffix=.jsp` configuration in `application.properties`,
     *   Spring's View Resolver will look for the physical file at `/view/index.jsp`.
     */
    @RequestMapping("/")
    public String home(){
        System.out.println("home method called"); // Helpful for debugging to see when the method is hit.
        return "index"; // Returns the logical view name "index".
    }

    /**
     * Handles the "/add" request, typically submitted from an HTML form.
     * This method demonstrates how to receive parameters and pass data to a JSP using Spring's Model.
     *
     * <p>Theory and Flow:
     * 1. The form in `index.jsp` submits data to the "/add" URL (e.g., `/add?num1=10&num2=20`).
     * 2. Spring automatically binds the request parameters "num1" and "num2" to the `int num1` and `int num2` method arguments.
     *    - Alternative (`@RequestParam`): If the parameter name in the URL differs from the method argument name (e.g., `num1` in URL, `myNum1` in method), you would use `@RequestParam("num1") int myNum1`.
     *      Example: `public String add(@RequestParam("num1") int num, @RequestParam("num2") int num209, Model model)`
     * 3. The method performs the addition.
     * 4. `Model` object: This is a Spring mechanism to pass data from the controller to the view.
     *    - `model.addAttribute("result", c)`: Stores the calculated sum `c` under the attribute name "result". This "result" will be accessible in the `return.jsp` file.
     *    - Contrast with `HttpSession`: While `HttpSession` (as used previously) can also store data, `Model` is preferred for passing data to the *current* view, as it's designed for request-scoped attributes and is more testable. `HttpSession` is for data that needs to persist across multiple requests for a specific user.
     * 5. Returns the logical view name "return".
     * 6. The View Resolver (configured in `application.properties`) locates the physical file at `/view/return.jsp`.
     *
     * @param num1 The first number, automatically bound from the request parameter "num1".
     * @param num2 The second number, automatically bound from the request parameter "num2".
     * @param model The Spring Model object, used to pass data to the view.
     * @return The logical view name "return" to render.
     */
    @RequestMapping("add")
    public String add(@RequestParam("num1") int num1, @RequestParam("num2") int num2 , Model model){
        // The @RequestParam annotation is explicitly used here for clarity,
        // though Spring can often infer it if parameter names match request parameter names.
        // It ensures that the 'num1' and 'num2' from the URL are correctly mapped to these integer variables.

        int c = num1 + num2; // Perform the addition.

        // Add the result to the Model. This makes the 'c' value available to the JSP
        // under the name "result".
        model.addAttribute("result" , c);

        // Return the logical view name "return".
        // The View Resolver will combine this with the prefix and suffix to find "return.jsp".
        return "return";
    }

}
