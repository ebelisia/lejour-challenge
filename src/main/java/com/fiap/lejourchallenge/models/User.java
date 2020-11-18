package com.fiap.lejourchallenge.models;

import org.springframework.lang.NonNull;
import reactor.util.annotation.Nullable;

import java.util.Optional;

public class User {

    private Long id;

    private String created_at;

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
