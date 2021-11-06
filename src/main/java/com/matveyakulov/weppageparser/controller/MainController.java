package com.matveyakulov.weppageparser.controller;

import com.matveyakulov.weppageparser.parser.html.HtmlParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    public String startPage(){

        return "startView";
    }

    @RequestMapping("/showWord")
    public String showWord(@RequestParam("URI") String path, Model model){

        Map<String, Integer> words = HtmlParser.parseFromURI(path);
        model.addAttribute("words", words);


        return "index";
    }
}
