package com.reservaonline.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// import javax.validation.Valid;

@Controller
public class LoginControle {

    @GetMapping("/login")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }

}
