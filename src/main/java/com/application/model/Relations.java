package com.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Relations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relation", updatable = false, nullable = false)
    private Integer id_relation;

    private Integer id_user;

    private Integer id_contact;

    private String status;

    public void setId_relation(Integer id_relation) {
        this.id_relation = id_relation;
    }

    public Integer getId_relation() {
        return id_relation;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_contact(Integer id_contact) {
        this.id_contact = id_contact;
    }

    public Integer getId_contact() {
        return id_contact;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
