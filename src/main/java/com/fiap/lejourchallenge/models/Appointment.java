package com.fiap.lejourchallenge.models;

public class Appointment {

    private Long id;

    private Long weddingId;

    private Long vendorId;

    private String status;

    private String vendorCategory;

    private String beginsAt;

    public Appointment(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Long weddingId) {
        this.weddingId = weddingId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorCategory() {
        return vendorCategory;
    }

    public void setVendorCategory(String vendorCategory) {
        this.vendorCategory = vendorCategory;
    }

    public String getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(String beginsAt) {
        this.beginsAt = beginsAt;
    }
}
