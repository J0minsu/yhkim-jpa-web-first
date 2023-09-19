package jpa.web.part.first.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class HomeController {

    @GetMapping(value = {"", "/", "/home"})
    public String home(Model model) {

        log.info("homePC.home");

        model.addAttribute("date", LocalDateTime.now());

        return "page/home";
    }
}
