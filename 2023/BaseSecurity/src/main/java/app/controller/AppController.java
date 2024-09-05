package app.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/public")
    public String pub() {
        return "public";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @Secured("ADMIN")
    public String admin() {
        return "admin";
    }
}
