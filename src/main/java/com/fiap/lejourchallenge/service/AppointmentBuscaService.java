package com.fiap.lejourchallenge.service;

import com.fiap.lejourchallenge.dashboard.AppointmentResume;
import com.fiap.lejourchallenge.hardcode.Uris;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class AppointmentBuscaService {

    RestTemplate restTemplate = new RestTemplate();

    public long getNumberOfAppointments() {

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)
                .path(Uris.APPOINTMENT_URI)
                .build().encode().toUri();

        Object[] appointments = restTemplate.getForObject(targetUrl, Object[].class);

        return  appointments.length;
    }

    public long getNumberOfStatus(String status){

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)
                .path(Uris.APPOINTMENT_URI)
                .queryParamIfPresent("STATUS", Optional.ofNullable(status))
                .build().encode().toUri();

        Object[] appointments = restTemplate.getForObject(targetUrl, Object[].class);

        return  appointments.length;
    }


    public AppointmentResume retornarAppointResume() {

        AppointmentResume appointmentResume = new AppointmentResume();

        appointmentResume.setNumberAppointments(getNumberOfAppointments());
        appointmentResume.setNumberAppointmentsCreated(getNumberOfStatus("CREATED"));
        appointmentResume.setNumberAppointmentsVisited(getNumberOfStatus("VISITED"));
        appointmentResume.setNumberAppointmentsConfirmed(getNumberOfStatus("CONFIRMED"));
        appointmentResume.setNumberAppointmentsCanceled(getNumberOfStatus("CANCELED"));

        return appointmentResume;
    }

}
