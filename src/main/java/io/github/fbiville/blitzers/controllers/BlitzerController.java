package io.github.fbiville.blitzers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlitzerController {

    @RequestMapping("/")
    public String start() {
        return "index";
    }
}
