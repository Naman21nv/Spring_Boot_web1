package org.example.springboot_web;

// Import Spring annotation that marks this class as a web controller component.
import org.springframework.stereotype.Controller;
// Import mapping annotation to map HTTP requests to handler methods.
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 *
 * <p>Purpose:
 * - Acts as a Spring MVC controller that handles web requests and returns a view name to be rendered.
 * - In a typical Spring Boot MVC application, controllers receive HTTP requests, interact with services
 *   (business logic), and return a view name or response body.
 *
 * <p>Key concepts and theory (beginner-friendly):
 * - Model-View-Controller (MVC): a design pattern that separates responsibilities:
 *   - Model: the data (domain objects)
 *   - View: the presentation layer (JSP, Thymeleaf templates, etc.)
 *   - Controller: receives HTTP requests, prepares the model, and selects the view to render
 * - @Controller: a Spring annotation that marks this class as a candidate for component scanning so Spring
 *   can detect and register it as a bean in the application context.
 * - @RequestMapping("/"): maps HTTP requests for the root path to the annotated method. It can handle all
 *   HTTP methods by default; for method-specific handlers use @GetMapping, @PostMapping, etc.
 *
 * <p>Why this approach is used:
 * - Using a controller with a view name decouples the request handling from presentation. The controller focuses
 *   on choosing what to show; the view layer (JSP or template engine) focuses on rendering HTML.
 * - Spring Boot auto-configuration reduces setup: if JSP and appropriate dependencies are present, Spring Boot will
 *   configure a view resolver and embedded servlet container for you.
 *
 * <p>Dependencies and what they do (project-level notes):
 * - spring-boot-starter-web: provides Spring MVC, embedded Tomcat (servlet container), and Jackson for JSON.
 * - If using JSP views, you typically need additional dependencies in the POM:
 *   - tomcat-embed-jasper (or tomcat-jasper) — JSP compilation support for embedded Tomcat.
 *   - jstl — JSTL tags if the JSPs use them.
 *   Without JSP support, JSP pages may be downloaded as raw text by the browser instead of being rendered.
 * - spring-boot-starter-thymeleaf (alternative): preferred for Spring Boot apps because Thymeleaf integrates smoothly
 *   and does not require JSP compilation. It is often simpler for modern Spring Boot apps.
 *
 * <p>Alternatives and when to use them:
 * - Return a logical view name (e.g., "index") and configure a view resolver with prefix/suffix (recommended).
 *   This decouples controller code from the exact filename and keeps view naming consistent.
 * - Use template engines (Thymeleaf, FreeMarker, Mustache): prefer these over JSP for new Spring Boot applications.
 * - REST APIs: for JSON responses, use @RestController or @ResponseBody and return domain objects; no view rendering.
 * - Use @GetMapping("/") instead of @RequestMapping("/") when the handler should only respond to HTTP GET requests —
 *   this is clearer and more RESTful.
 *
 * <p>Execution flow (how this controller fits into the app lifecycle):
 * 1. Application start (SpringApplication.run) triggers component scanning starting from the main class package.
 * 2. Spring finds this class (annotated with @Controller) and registers it as a bean in the application context.
 * 3. Embedded Tomcat starts and registers Spring's DispatcherServlet to handle incoming HTTP requests.
 * 4. When an HTTP request arrives at path "/", DispatcherServlet consults handler mappings and routes it to the
 *    {@link #home()} method.
 * 5. The controller returns a view name (here the JSP file). The configured view resolver locates the JSP and
 *    renders HTML to send back to the client.
 *
 * <p>Notes about view names and file locations:
 * - Returning "index.jsp" returns a literal file name. A cleaner approach is to return a logical name like "index"
 *   and let the view resolver add prefix/suffix, e.g., prefix="/WEB-INF/views/", suffix=".jsp". This keeps controllers
 *   independent from file layout.
 * - JSPs are typically placed under {@code src/main/webapp/WEB-INF/views/} for classic servlet setups. If you use
 *   an alternative template engine, template files usually go under {@code src/main/resources/templates/}.
 *
 * <p>Complexity and performance notes:
 * - Time complexity: each request to this controller executes a constant amount of work (O(1)) for the method body
 *   (printing and returning a string). The overall request time depends on view rendering cost and any service calls.
 * - Space complexity: negligible per-request, aside from objects created during rendering and the memory consumed by
 *   the application context (which grows with number of beans).
 */
@Controller // Marks the class as a Spring MVC controller so Spring can detect and manage it.
public class HomeController {
    /**
     * Controller method returning the application's home view.
     *
     * <p>Detailed per-line explanation (important lines annotated inline below):
     */


    // This method is responsible for handling HTTP requests to the root path of the web app.
    // The @RequestMapping annotation above the method maps the path "/" to this method. If no HTTP method is
    // specified, it will match any HTTP method; prefer @GetMapping for GET-only handlers.
    @RequestMapping("/")
    public String home(){
        // Print a log line to the console (use a logger in production code instead of System.out.println).
        // This is useful during development to quickly verify the method was invoked.
        System.out.println("home method called");

        // Return the view name. Returning "index.jsp" points to a JSP file.
        // If a view resolver with prefix/suffix is configured, returning just "index" is recommended.
        return "index.jsp";
    }

}
