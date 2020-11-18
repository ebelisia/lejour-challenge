package com.fiap.lejourchallenge.controllers;

import com.fiap.lejourchallenge.service.AppointmentBuscaService;
import com.fiap.lejourchallenge.service.InvoiceBuscaService;
import com.fiap.lejourchallenge.service.UserBuscaService;
import com.fiap.lejourchallenge.service.WeddingBuscaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    private final UserBuscaService userBuscaService;
    private final WeddingBuscaService weddingBuscaService;
    private final AppointmentBuscaService appointmentBuscaService;
    private final InvoiceBuscaService invoiceBuscaService;

    public DashboardController(UserBuscaService userBuscaService,
                               WeddingBuscaService weddingBuscaService,
                               AppointmentBuscaService appointmentBuscaService,
                               InvoiceBuscaService invoiceBuscaService) {
        this.userBuscaService = userBuscaService;
        this.weddingBuscaService = weddingBuscaService;
        this.appointmentBuscaService = appointmentBuscaService;
        this.invoiceBuscaService = invoiceBuscaService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){

        String invoices = invoiceBuscaService.getInvoices();

        model.addAttribute("user", userBuscaService.retornarUserResume());
        model.addAttribute("wedding", weddingBuscaService.retornarWeddingResume());
        model.addAttribute("appointment", appointmentBuscaService.retornarAppointResume());

        return "index";
    }

}
