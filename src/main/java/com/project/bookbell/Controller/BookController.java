package com.project.bookbell.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String books(ModelMap map) {
        map.addAttribute("books", List.of());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable(name = "id") Long id, ModelMap map){
        map.addAttribute("books",null);
        return "books/detail";
    }
}
