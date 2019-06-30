package com.liangyue.spring.boot.blog.controller;

import com.liangyue.spring.boot.blog.service.CommentService;
import com.liangyue.spring.boot.blog.domain.Blog;
import com.liangyue.spring.boot.blog.domain.Comment;
import com.liangyue.spring.boot.blog.domain.User;
import com.liangyue.spring.boot.blog.service.BlogService;
import com.liangyue.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.liangyue.spring.boot.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String listComments(@RequestParam(value = "blogId",required = true) Long blogId, Model model){
        Blog blog = blogService.getBlogById(blogId);
        List<Comment> comments = blog.getComments();

        String commentOwner = "";
        if(SecurityContextHolder.getContext().getAuthentication()!=null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
            !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal!=null){
                commentOwner =principal.getUsername();
            }
        }
        model.addAttribute("commentOwner",commentOwner);
        model.addAttribute("comments",comments);
        return "/userspace/blog :: #mainContainerRepleace";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Response> createComment(Long blogId,String commentContent){
        try{
            blogService.createComment(blogId,commentContent);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }catch (Exception e){
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功",null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteBlog(@PathVariable("id") Long id,Long blogId){
        boolean isOwner = false;
        User user = commentService.getCommentById(id).getUser();

        if(SecurityContextHolder.getContext().getAuthentication()!=null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
            User principal =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal!=null && user.getUsername().equals(principal.getUsername())){
                isOwner = true;
            }
        }
        if(!isOwner){
            return ResponseEntity.ok().body(new Response(false,"没有操作权限"));
        }
        try{
            blogService.removeComment(blogId,id);
            commentService.removeComment(id);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }catch (Exception e){
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功",null));
    }
}
