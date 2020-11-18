package com.fiap.lejourchallenge.controllers;

import com.fiap.lejourchallenge.models.Wedding;
import com.fiap.lejourchallenge.service.UserBuscaService;
import com.fiap.lejourchallenge.service.WeddingBuscaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WeddingController {

    private final WeddingBuscaService weddingBuscaService;

    public WeddingController(WeddingBuscaService weddingBuscaService) {
        this.weddingBuscaService = weddingBuscaService;
    }



    @RequestMapping(value = "/weddings", method = RequestMethod.GET)
    public String WeddingForm() {

        return "weddingForm";
    }

    @RequestMapping(value = "/weddings/search", method = RequestMethod.POST)
    public String WeddingsSearch(Wedding wedding,
                              Model model) {

        model.addAttribute("weddingResult", weddingBuscaService.getWeddingsByParams(wedding));

        return "weddingForm";
    }

//    @RequestMapping(value = "/weddings/month", method = RequestMethod.POST)
//    public String NumberWeddingMonth(Wedding wedding,
//                              Model model) {
//
//        //DO SOMETHING
//
//        return "wedding";
//    }

}
