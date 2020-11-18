package com.fiap.lejourchallenge.util;

import com.fiap.lejourchallenge.models.User;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    public Date transformarEmDate(Optional<String> str) throws ParseException {

        java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .parse(String.valueOf(str));

        return temp;
    }

    public String validString(String s) {
        if (s == "") {
            s = null;
        }
        return s;
    }

    public int contaOcorrencias(String json, String word) {

        int occurance = StringUtils.countOccurrencesOf("json", "word");

        return occurance;

    }
}

