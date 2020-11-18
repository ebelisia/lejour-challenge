package com.fiap.lejourchallenge.controllers;

import com.fiap.lejourchallenge.models.Appointment;
import com.fiap.lejourchallenge.models.User;
import com.fiap.lejourchallenge.service.AppointmentBuscaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentController {

    private final AppointmentBuscaService appointmentBuscaService;

    public AppointmentController(AppointmentBuscaService appointmentBuscaService) {
        this.appointmentBuscaService = appointmentBuscaService;
    }

    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public String AppointmentForm() {

        return "appointmentForm";
    }

    @RequestMapping(value = "/appointment/search", method = RequestMethod.POST)
    public String UserSearch(Appointment appointment,
                             Model model) {

        model.addAttribute("appointmentResult", appointmentBuscaService.getAppointmentsByParams(appointment));

        return "appointmentForm";
    }
}
