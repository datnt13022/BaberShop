package com.DoAn.HairStyle.api;

import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.BlogEntity;
import com.DoAn.HairStyle.service.BlogService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/post")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<blogResponse> getAllBlog(){
        return blogService.loadBlog();
    }
    @GetMapping(path = "/page/{numPage}")
    public List<blogResponse> getBlogByPage(@PathVariable("numPage") int numPage){
        return blogService.loadBlogByPage(numPage);
    }
    @GetMapping(path = "/totalPage")
    public responseCountPage getCountPage(){
        return blogService.getCountPage();
    }
    @GetMapping(path = "/all")
    public List<blogResponse> getAllBlogAdmin(){
        return blogService.loadBlogAdmin();
    }
    @GetMapping(path = "{id}")
    public blogResponse getBlogByID(@PathVariable("id") String id){
        return blogService.getBlogByID(id);
    }
    @GetMapping(path = "/tag/{tag}")
    public List<blogResponse> getBlogByTag(@PathVariable("tag") String tag){
        return blogService.getBlogByTag(tag);
    }
    @PostMapping(path = "{token}")
    public Response addNewPost(@Valid @NonNull @RequestBody PostRequest newPost ,@PathVariable("token") String token){
        return blogService.addNewPost(newPost,token);
    }
    @DeleteMapping(path = "/delete/{idPost}/{token}")
    public Response deletePost(@PathVariable("token") String token, @PathVariable("idPost") Long idPost){
        return blogService.deletePostById(token,idPost);
    }
    @PutMapping(path = "/update/{idPost}/{token}")
    public Response updatePost(@Valid @NonNull @RequestBody UpdatePostRequest updatePostRequest , @PathVariable("token") String token,@PathVariable("idPost") Long idPost){
        return blogService.updatePostById(token,idPost,updatePostRequest);
    }

}
