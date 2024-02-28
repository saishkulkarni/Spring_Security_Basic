package org.jsp.security_sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/user")
    public String loadUser() {
        return "<h1>Hello User, Welcome</h1>";
    }

    @GetMapping("/admin")
    public String loadAdmin() {
        return "<h1>Hello Admin, Welcome</h1>";
    }

    @GetMapping("/")
    public String load() {
        return "<h1>Hello , Welcome</h1>";
    }

}
