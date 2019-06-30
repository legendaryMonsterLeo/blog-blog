package com.liangyue.spring.boot.blog.vo;

import com.liangyue.spring.boot.blog.domain.Catalog;

import java.io.Serializable;

public class CatalogV0 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    private Catalog catalog;

    public CatalogV0(){

    }
}
