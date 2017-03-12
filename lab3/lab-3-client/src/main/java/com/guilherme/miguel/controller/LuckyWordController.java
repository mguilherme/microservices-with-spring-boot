package com.guilherme.miguel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

/**
 * @author Miguel Guilherme
 */
@RestController
public class LuckyWordController {

    @Value("${lucky-word}")
    String luckyWord;

    @RequestMapping("/lucky-word")
    public String showLuckyWord() {
        return format("The lucky word is: %s", luckyWord);
    }

}
