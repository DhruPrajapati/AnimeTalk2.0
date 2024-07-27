package com.AnimeTalk.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandler(){
        return "this is home Contoller";
    }

    @GetMapping("/home")
    public String homeControllerHandler2(){
        return "this is home Contoller2";
    }
}
