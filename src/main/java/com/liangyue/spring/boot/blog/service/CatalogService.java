package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.domain.Catalog;
import com.liangyue.spring.boot.blog.domain.User;

import java.util.List;

public interface CatalogService {

    Catalog saveCatalog(Catalog catalog);

    void removeCatalog(Long id);

    Catalog getCatalogById(Long id);

    List<Catalog> listCatalogs(User user);
}
