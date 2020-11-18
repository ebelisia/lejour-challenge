package com.fiap.lejourchallenge.service;

import com.fiap.lejourchallenge.dashboard.AppointmentResume;
import com.fiap.lejourchallenge.hardcode.Uris;
import com.fiap.lejourchallenge.models.Appointment;
import com.fiap.lejourchallenge.models.Wedding;
import com.fiap.lejourchallenge.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class AppointmentBuscaService {

    RestTemplate restTemplate = new RestTemplate();

    public Object getAppointmentsByParams(Appointment appointment) {

        Util util = new Util();

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)  // Build the base link
                .path(Uris.APPOINTMENT_URI)
                .queryParamIfPresent("ID", Optional.ofNullable(appointment.getId()))
                .queryParamIfPresent("WEDDING_ID", Optional.ofNullable(appointment.getWeddingId()))
                .queryParamIfPresent("VENDOR_ID", Optional.ofNullable(appointment.getVendorId()))
                .queryParamIfPresent("STATUS", Optional.ofNullable(util.validString(util.validString(appointment.getStatus()))))
                .queryParamIfPresent("VENDOR_CATEGORY", Optional.ofNullable(util.validString(util.validString(appointment.getVendorCategory()))))
                .queryParamIfPresent("BEGINS_AT", Optional.ofNullable(util.validString(util.validString(appointment.getBeginsAt()))))
                .queryParamIfPresent("CREATED_AT", Optional.ofNullable(util.validString(util.validString(appointment.getCreatedAt()))))
                .build().encode().toUri();

        Object[] weddings = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> appointmentsObjects = Arrays.asList(weddings);
        Gson gson = new Gson();
        String json = gson.toJson(appointmentsObjects);

        String output = json.replace("[", "").replace("]", "");

        if(appointmentsObjects.toString().equals("[]")){
            return "Nenhum agendamento foi encontrado";
        }else {
            return output;
        }
    }

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
