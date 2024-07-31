package com.example.demo.todoDAO;

import com.example.demo.entity.Todo;

import java.util.List;

public interface todoDAO {
    void createBlog(Todo blog); //Since we are passing the blog object for creation.
    Todo readBlog(int id); //Returning blog object
    List<Todo> readAllBlogs();

    void updateBlog(Todo blog);
    void deleteBlog(int id);//Delete always with ID
}
