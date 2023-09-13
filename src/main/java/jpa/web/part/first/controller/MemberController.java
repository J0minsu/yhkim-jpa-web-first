package jpa.web.part.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class MemberController {

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("date", LocalDateTime.now());

        return "page/home";
    }
}
