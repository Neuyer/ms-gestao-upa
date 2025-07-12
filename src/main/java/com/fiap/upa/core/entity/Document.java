package com.fiap.upa.core.entity;

public abstract class Document {
    private String value;

    protected Document() {
    }

    protected void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
