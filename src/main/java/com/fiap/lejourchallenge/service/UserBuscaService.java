package com.fiap.lejourchallenge.service;

import com.fiap.lejourchallenge.dashboard.UserResume;
import com.fiap.lejourchallenge.hardcode.Uris;
import com.fiap.lejourchallenge.models.User;
import com.fiap.lejourchallenge.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UserBuscaService {

    RestTemplate restTemplate = new RestTemplate();

    public long getNumberOfUsers() {

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)
                .path(Uris.USER_URI)
                .build().encode().toUri();

        Object[] users = restTemplate.getForObject(targetUrl, Object[].class);

        return  users.length;
    }

    public String getUsersByParams(User user) {

        Util util= new Util();

        URI targetUrl= UriComponentsBuilder.fromUriString(Uris.BASE_URI)  // Build the base link
                .path(Uris.USER_URI)
                .queryParamIfPresent("ID", Optional.ofNullable(user.getId()))
                .queryParamIfPresent("CREATED_AT", Optional.ofNullable(util.validString(util.validString(user.getCreated_at()))))
                .build().encode().toUri();

        Object[] users = restTemplate.getForObject(targetUrl, Object[].class);

        List<Object> userObjects = Arrays.asList(users);

        if(userObjects.toString().equals("[]")){
            return "Nenhum usuário foi encontrado";
        }else {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(userObjects);
            String output = jsonOutput.replace("[", "").replace("]", "");
            JSONObject jsonObj = new JSONObject(output);

            System.out.println(jsonObj.getLong("ID"));

            return jsonObj.toString(4);
        }
    }

    public UserResume retornarUserResume() {

        UserResume userResume = new UserResume();

        userResume.setNumberUser(getNumberOfUsers());

        return userResume;
    }

}
