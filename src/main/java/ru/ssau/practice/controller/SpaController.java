package ru.ssau.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Single Page Application (SPA) controller contains action that delegates routing control to
 * Vue.js Router by redirecting to index.html.
 */
@Controller
public class SpaController
{
    @RequestMapping(value = {"/{regex:\\w+}", "/**/{regex:\\w+}"})
    public String redirect()
    {
        return "forward:/";
    }
}
