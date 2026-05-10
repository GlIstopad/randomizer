package com.rimark.randomizer;
import org.springframework.web.bind.annotation.*;
@RestController
public class RimarkController {
    @GetMapping("/")
    public String home() {
        return "Добро пожаловать на сайт!";
    }
}
