package com.kkraljic.shortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelpController.class);

    @GetMapping("/help")
    public String helpPage() {
        LOGGER.info("Request to open /help page.");

        return "help";
    }

}
