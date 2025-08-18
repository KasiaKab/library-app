package com.kasiakab.exam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class Name {

    @GetMapping("/upperCase")
    public String toUpperCase(@RequestParam String name) {
            return name.toUpperCase();
        }
    }

