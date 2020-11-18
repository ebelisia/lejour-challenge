package com.fiap.lejourchallenge.service;

import com.fiap.lejourchallenge.hardcode.Uris;
import com.fiap.lejourchallenge.models.Invoice;
import com.fiap.lejourchallenge.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceBuscaService {

    RestTemplate restTemplate = new RestTemplate();

    public String getInvoices() {

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)
                .path(Uris.INVOICE_URI)
                .build().encode().toUri();

        Object[] weddings = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> weddingObjects = Arrays.asList(weddings);
        Gson gson = new Gson();
        String json = gson.toJson(weddingObjects);

        String output = json.replace("[", "").replace("]", "");

        return  output;
    }

    public Object getWeddingsByParams(Invoice invoice) {

        Util util = new Util();

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)  // Build the base link
                .path(Uris.INVOICE_URI)
                .queryParamIfPresent("ID", Optional.ofNullable(invoice.getId()))
                .queryParamIfPresent("WEDDING_ID", Optional.ofNullable(invoice.getWeddingId()))
                .queryParamIfPresent("VENDOR_ID", Optional.ofNullable(invoice.getVendorId()))
                .queryParamIfPresent("AMOUNT", Optional.ofNullable(util.validString(invoice.getAmount())))
                .queryParamIfPresent("VENDOR_AMOUNT", Optional.ofNullable(util.validString(invoice.getVendorAmount())))
                .queryParamIfPresent("CREATED_AT", Optional.ofNullable(util.validString(invoice.getCreatedAt())))
                .queryParamIfPresent("ACCEPTED", Optional.ofNullable(util.validString(invoice.getAccepted())))
                .queryParamIfPresent("VENDOR_CATEGORY", Optional.ofNullable(util.validString(invoice.getVendorCategory())))
                .build().encode().toUri();

        Object[] invoices = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> invoiceObjects = Arrays.asList(invoices);
        Gson gson = new Gson();
        String json = gson.toJson(invoiceObjects);

        String output = json.replace("[", "").replace("]", "");

        if(invoiceObjects.toString().equals("[]")){
            return "Nenhuma venda foi encontrada";
        }else {
            return output;
        }
    }










}
