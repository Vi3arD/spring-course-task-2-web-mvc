package org.example.domain;

public enum OrderStatus {
    NEW_STATUS("new"),
    FINISHED_STATUS("finished");

    private String id;

    OrderStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
