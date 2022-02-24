package com.reservaonline.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// import javax.validation.Valid;

@Controller
public class NegadoControle {

    @GetMapping("/negado")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView("/negado");
        return mv;
    }

}
