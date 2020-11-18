package com.fiap.lejourchallenge.service;

import com.fiap.lejourchallenge.dashboard.WeddingResume;
import com.fiap.lejourchallenge.hardcode.Uris;
import com.fiap.lejourchallenge.models.Wedding;
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
public class WeddingBuscaService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Object> getWeddings() {

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)
                .path(Uris.WEDDING_URI)
                .build().encode().toUri();

        Object[] weddings = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> weddingObjects = Arrays.asList(weddings);

        return  weddingObjects;
    }

    public Object getWeddingsByParams(Wedding wedding) {

        Util util = new Util();

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)  // Build the base link
                .path(Uris.WEDDING_URI)
                .queryParamIfPresent("ID", Optional.ofNullable(wedding.getId()))
                .queryParamIfPresent("OWNER_ID", Optional.ofNullable(wedding.getOwnerId()))
                .queryParamIfPresent("BUDGET", Optional.ofNullable(wedding.getBudget()))
                .queryParamIfPresent("WEDDING_DATE", Optional.ofNullable(util.validString(wedding.getWeddingDate())))
                .queryParamIfPresent("NUMBER_OF_GUESTS", Optional.ofNullable(wedding.getNumberOfGuests()))
                .queryParamIfPresent("STYLE", Optional.ofNullable(util.validString(wedding.getStyle())))
                .build().encode().toUri();

        Object[] weddings = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> weddingObjects = Arrays.asList(weddings);
        Gson gson = new Gson();
        String json = gson.toJson(weddingObjects);

        String output = json.replace("[", "").replace("]", "");

        if(weddingObjects.toString().equals("[]")){
            return "Nenhum casamento foi encontrado";
        }else {
            return output;
        }
    }

    public long numberOfWeddings() {
        return getWeddings().size();
    }

    public long numberTypeOfWedding(String type) {

        List<Object> weddings = getWeddings();

        String toTest = type;
        int count = 0;
        for(int i=0; i < weddings.size(); i++) {
            String s = String.valueOf(weddings.get(i));
            if(s.contains(toTest)) count++;
        }
        return count;
    }

    public long numberWeddingsMonthAndYear(String monthYear) {

        List<Object> weddings = getWeddings();

        String toTest = monthYear;
        int count = 0;
        for(int i=0; i < weddings.size(); i++) {
            String s = String.valueOf(weddings.get(i));
            if(s.contains(toTest)) count++;
        }
        return count;
    }

    public WeddingResume retornarWeddingResume() {

        WeddingResume weddingResume = new WeddingResume();

        weddingResume.setNumberWeddings(numberOfWeddings());
        weddingResume.setNumberStyleModerno(numberTypeOfWedding("moderno"));
        weddingResume.setNumberStyleRustico(numberTypeOfWedding("rustico"));
        weddingResume.setNumberStyleClassico(numberTypeOfWedding("classico"));

        return weddingResume;
    }
}
