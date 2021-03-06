package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.BlogEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.respositiry.BlogRespository;
import com.DoAn.HairStyle.respositiry.UserRespository;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BlogService {
    @Autowired
    private  BlogRespository blogRespository;
    @Autowired
    private UserRespository userRespository;

    public  List<blogResponse> loadBlog() {
        List<BlogEntity> list= blogRespository.findAll();
        String[] abc= new String[]{"Blog-highlight","Blog"};
        List<String> active = Arrays.asList(abc);
        List<blogResponse> myList = new ArrayList<>();
        for (final BlogEntity blog : list) {
            if (active.contains(blog.getTag())) {
                blogResponse perBlog = new blogResponse();
                perBlog.setTitle(blog.getTitle());
                perBlog.setBlogID(blog.getBlogID());
                perBlog.setDescription(blog.getDescription());
                perBlog.setLink(blog.getLink());
                perBlog.setTag(blog.getTag());
                perBlog.setThumbnail(blog.getThumbnail());
                perBlog.setTime(blog.getTime());
                perBlog.setContent(blog.getContent());
                perBlog.setBy(blog.getUser().getFullName());
                myList.add(perBlog);
            }
        }
        return myList;
    }

    public Response addNewPost(PostRequest newPost, String token) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                BlogEntity blog = new BlogEntity();
                blog.setTitle(newPost.getTitle());
                blog.setContent(newPost.getContent());
                blog.setDescription(newPost.getDescription());
                blog.setThumbnail(newPost.getThumbnail());
                blog.setTag(newPost.getTag());
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date check = new Date();

                String now=dateFormat.format(check);
                Date timeWtrite = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(now).toDate();

                blog.setTime(timeWtrite);
                blog.setLink(newPost.getLink());
                blog.setUser(users.get());
                blogRespository.save(blog);
                Response response = new Response();
                response.setStatus("Thêm thành công!");
                return response;
            }
            Response response = new Response();
            response.setStatus("Người dùng không có quyền thực hiện thao tác này!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại !");
        return response;
    }



    public Response deletePostById(String token, Long idPost) {

        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                blogRespository.deleteByBlogID(idPost);
                Response response = new Response();
                response.setStatus("Xoá Thành Công!");
                return response;
            }
            Response response = new Response();
            response.setStatus("Người dùng không có quyền thực hiện thao tác này!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại !");
        return response;
    }

    public Response updatePostById(String token, Long idPost, UpdatePostRequest updatePostRequest) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                BlogEntity blog = new BlogEntity();
                blog.setBlogID(idPost);
                blog.setTitle(updatePostRequest.getTitle());
                blog.setContent(updatePostRequest.getContent());
                blog.setDescription(updatePostRequest.getDescription());
                blog.setThumbnail(updatePostRequest.getThumbnail());
                blog.setTag(updatePostRequest.getTag());
                String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Date check = DateTimeFormat.forPattern("dd-MM-yyyy").parseDateTime(localDate).toDate();
                blog.setTime(check);
                blog.setLink(updatePostRequest.getLink());
                blog.setUser(users.get());
                blogRespository.save(blog);
                Response response = new Response();
                response.setStatus("Cập nhật thành công!");
                return response;
            }
            Response response = new Response();
            response.setStatus("Người dùng không có quyền thực hiện thao tác này!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại !");
        return response;
    }

    public List<blogResponse>getBlogByTag(String tag) {
        List<BlogEntity> list= blogRespository.findAllByTag(tag);
        List<blogResponse> myList = new ArrayList<>();
        for (final BlogEntity blog : list) {
            blogResponse perBlog = new blogResponse();
            perBlog.setTitle(blog.getTitle());
            perBlog.setBlogID(blog.getBlogID());
            perBlog.setDescription(blog.getDescription());
            perBlog.setLink(blog.getLink());
            perBlog.setTag(blog.getTag());
            perBlog.setThumbnail(blog.getThumbnail());
            perBlog.setTime(blog.getTime());
            perBlog.setContent(blog.getContent());
            perBlog.setBy(blog.getUser().getFullName());
            myList.add(perBlog);
        }

        return myList;
    }

    public blogResponse getBlogByID(String id) {

        Optional<BlogEntity> blog = blogRespository.findById(Long.valueOf(id));
        blogResponse perBlog = new blogResponse();
        perBlog.setTitle(blog.get().getTitle());
        perBlog.setBlogID(blog.get().getBlogID());
        perBlog.setDescription(blog.get().getDescription());
        perBlog.setLink(blog.get().getLink());
        perBlog.setTag(blog.get().getTag());
        perBlog.setThumbnail(blog.get().getThumbnail());
        perBlog.setTime(blog.get().getTime());
        perBlog.setContent(blog.get().getContent());
        perBlog.setBy(blog.get().getUser().getFullName());
        return perBlog;

    }

    public List<blogResponse> loadBlogAdmin() {
                List<BlogEntity> list= blogRespository.findAll();
        List<blogResponse> myList = new ArrayList<>();
        for (final BlogEntity blog : list) {
            blogResponse perBlog = new blogResponse();
            perBlog.setTitle(blog.getTitle());
            perBlog.setBlogID(blog.getBlogID());
            perBlog.setDescription(blog.getDescription());
            perBlog.setLink(blog.getLink());
            perBlog.setTag(blog.getTag());
            perBlog.setThumbnail(blog.getThumbnail());
            perBlog.setTime(blog.getTime());
            perBlog.setContent(blog.getContent());
            perBlog.setBy(blog.getUser().getFullName());
            myList.add(perBlog);

    }
        return myList;

}

    public List<blogResponse> loadBlogByPage(int numPage) {
        Pageable paging = PageRequest.of(numPage, 5,Sort.by(Sort.Order.desc("blogID")));
        Page<BlogEntity> list= blogRespository.findAll(paging);
        String[] abc= new String[]{"Blog-highlight","Blog"};
        List<String> active = Arrays.asList(abc);
        List<blogResponse> myList = new ArrayList<>();
        for (final BlogEntity blog : list) {
            if (active.contains(blog.getTag())) {
                blogResponse perBlog = new blogResponse();
                perBlog.setTitle(blog.getTitle());
                perBlog.setBlogID(blog.getBlogID());
                perBlog.setDescription(blog.getDescription());
                perBlog.setLink(blog.getLink());
                perBlog.setTag(blog.getTag());
                perBlog.setThumbnail(blog.getThumbnail());
                perBlog.setTime(blog.getTime());
                perBlog.setContent(blog.getContent());
                perBlog.setBy(blog.getUser().getFullName());
                myList.add(perBlog);
            }
        }
        return myList;

    }

    public responseCountPage getCountPage() {
        Pageable paging = PageRequest.of(0, 5);
        Page<BlogEntity> list= blogRespository.findAll(paging);
        responseCountPage res= new responseCountPage();
        res.setTotalPage(list.getTotalPages());
        return res;
    }
}
