package org.example.springboot_web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        System.out.println("home method called");
        return "index.jsp";
    }

    @RequestMapping("add")
    public String add(int num1, int num2 , Model model){
        //public String add(@RequestParam("num1")int num, @RequestParam("num2")int num209 , HttpSession session){
        /**
         * public String add(@RequestParam("num1")int num, @RequestParam("num2")int num209 , HttpSession session)
         *
         * when we want to use any other name than the name going in the url of the browser we can do like this
         * we can assign this variable is holding the value of this url parameter using @RequestParam
         *
         * for travel from jsp to controller we have use model here
         */

        int c =num1+ num2;

       model.addAttribute("result" , c);

       return "return.jsp";
    }

}