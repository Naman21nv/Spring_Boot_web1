package org.example.springboot_web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // Import for @RequestParam
import org.springframework.web.servlet.ModelAndView;

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
     * @ModelAttribute at the Method Level
     *
     * <p>Theory and Flow:
     * - When `@ModelAttribute` is placed on a METHOD (not a parameter), it means:
     *   "Execute this method BEFORE any @RequestMapping method in this controller, and add its return value to the Model."
     * 
     * - In this specific case:
     *   1. A request comes in (e.g., to "/" or "/add" or "/addAlien").
     *   2. BEFORE executing `home()`, `add()`, or `Alien_add()`, Spring executes `courseName()`.
     *   3. It takes the return value ("Java") and automatically adds it to the Model using the key specified in the annotation ("course").
     *   4. So, behind the scenes, it's doing something like: `model.addAttribute("course", "Java");`
     * 
     * <p>Why use this?
     * - Use this when you have common data that needs to be displayed on *every single view* returned by this controller.
     * - For example, if every page has a header that displays the course name, instead of writing `model.addAttribute("course", "Java")` inside `home()`, `add()`, and `addAlien()`, you write it once here.
     * - In any JSP returned by this controller, you can now write `${course}` and it will print "Java".
     */
    @ModelAttribute("course")
    public String courseName(){
        return "Java";
    }

    /**
     * Handles the "/add" request, typically submitted from an HTML form.
     * This method demonstrates how to receive parameters and pass data to a JSP using Spring's ModelAndView.
     *
     * <p>Theory and Flow:
     * 1. The form in `index.jsp` submits data to the "/add" URL (e.g., `/add?num1=10&num2=20`).
     * 2. Spring automatically binds the request parameters "num1" and "num2" to the method arguments.
     *    - @RequestParam: We use this to tell Spring explicitly which URL parameter maps to which variable.
     *    - 'defaultValue="0"': This is CRITICAL. If the user submits the form with empty boxes, the URL will be `/add?num1=&num2=`.
     *      Without `defaultValue`, Spring tries to parse an empty string "" into an `int`, fails with a NumberFormatException, and throws a 404 error.
     *      `defaultValue="0"` tells Spring: "If the parameter is missing or empty, use the integer 0 instead." This prevents the 404 crash.
     * 3. The method performs the addition.
     * 4. `ModelAndView` object: This combines both the Data (Model) we want to pass to the view, AND the Name of the View itself into one object.
     *    - `mv.addObject("result", c)`: Stores the calculated sum `c` under the attribute name "result". This "result" will be accessible in the JSP file.
     *    - `mv.setViewName("return")`: Sets the logical name of the view to render. NOTE: Changed back to "return" to match your return.jsp file.
     * 5. Returns the `ModelAndView` object.
     * 6. The View Resolver locates the physical file at `/view/return.jsp`.
     *
     * @param num1 The first number, bound from the "num1" request parameter. Defaults to 0 if missing/empty.
     * @param num2 The second number, bound from the "num2" request parameter. Defaults to 0 if missing/empty.
     * @param mv The Spring ModelAndView object, injected by Spring.
     * @return The populated ModelAndView object containing the result and the view name.
     */
    @RequestMapping("add")
    public ModelAndView add(
            @RequestParam(value = "num1", defaultValue = "0") int num1, 
            @RequestParam(value = "num2", defaultValue = "0") int num2, 
            ModelAndView mv){

        // The @RequestParam annotation now includes defaultValue="0".
        // This is what fixes the 404 error when empty values are submitted.

        int c = num1 + num2; // Perform the addition.

        // Add the result to the ModelAndView. This makes the 'c' value available to the JSP
        // under the name "result".
        mv.addObject("result", c);

        
        // Set the logical view name. It must match the name of your JSP file in the /view/ folder.
        // I changed this from "result" back to "return" because your file is named return.jsp
        mv.setViewName("return"); 
        
        return mv;
    }

    @RequestMapping("addAlien")
    public String Alien_add(
           Alien alien
    ){

        System.out.println(alien);

        return "return";
    }
    /**
     * here i am not using @RequestParam and still my code works becoz its optional
     * and also
     */

}