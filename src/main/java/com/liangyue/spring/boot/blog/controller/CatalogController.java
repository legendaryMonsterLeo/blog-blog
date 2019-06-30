package com.liangyue.spring.boot.blog.controller;

import com.liangyue.spring.boot.blog.domain.Catalog;
import com.liangyue.spring.boot.blog.domain.User;
import com.liangyue.spring.boot.blog.service.CatalogService;
import com.liangyue.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.liangyue.spring.boot.blog.vo.CatalogV0;
import com.liangyue.spring.boot.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public String listComments(@RequestParam(value="username",required=true) String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.listCatalogs(user);

        // 判断操作用户是否是分类的所有者
        boolean isOwner = false;

        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal !=null && user.getUsername().equals(principal.getUsername())) {
                isOwner = true;
            }
        }

        model.addAttribute("isCatalogsOwner", isOwner);
        model.addAttribute("catalogs", catalogs);
        return "/userspace/u :: #catalogRepleace";
    }

    @PostMapping
    @PreAuthorize("authentication.name.equals(#catalogVO.username)")// 指定用户才能操作方法
    public ResponseEntity<Response> create(@RequestBody CatalogV0 catalogVO) {

        String username = catalogVO.getUsername();
        Catalog catalog = catalogVO.getCatalog();

        User user = (User)userDetailsService.loadUserByUsername(username);

        try {
            catalog.setUser(user);
            catalogService.saveCatalog(catalog);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name.equals(#username)")  // 指定用户才能操作方法
    public ResponseEntity<Response> delete(String username, @PathVariable("id") Long id) {
        try {
            catalogService.removeCatalog(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }


    @GetMapping("/edit")
    public String getCatalogEdit(Model model) {
        Catalog catalog = new Catalog(null, null);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }

    @GetMapping("/edit/{id}")
    public String getCatalogById(@PathVariable("id") Long id, Model model) {
        Catalog catalog = catalogService.getCatalogById(id);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }

}
