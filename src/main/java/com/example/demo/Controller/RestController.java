package com.example.demo.Controller;


import com.example.demo.entity.Todo;
import com.example.demo.todoDAO.todoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
@CrossOrigin(origins="https://nevin-10.github.io/")

public class RestController {

    //Constructor injection, as we need to need the object of the class BlogDAO here.as constructor allows this
    private final todoDAO dao;

    @Autowired

    public RestController(todoDAO dao) {
        this.dao = dao;
    }





    //Always think what POSTMAN needs with this API also what it needs
    @PostMapping("/create")
    //ResponseEntity is an object of http, return response to postman as an entity(object)
    public ResponseEntity<String> create(@RequestBody Todo blog) {//Getting Blog object from body, accepted by entity only!

        dao.createBlog(blog);
        return ResponseEntity.ok("Success");



    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> get(@PathVariable int id) { //getting ID from path
        Todo blog=dao.readBlog(id);
        if(blog!=null) {
            return ResponseEntity.ok(blog);

        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Todo>> getAll(){
        //List<Blog> List of the class
        List<Todo> blogs=dao.readAllBlogs();
        return ResponseEntity.ok(blogs);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Todo newblog) { //getting ID from path and the body as Blog object
        Todo blog=dao.readBlog(id);
        if(blog!=null) {
            blog.setEntry(newblog.getEntry());
            dao.updateBlog(blog);
            return ResponseEntity.ok("Success");
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        dao.deleteBlog(id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/done/{id}")
    public ResponseEntity<String> done(@PathVariable int id) {
        Todo blog=dao.readBlog(id);
        if(blog!=null) {
            blog.setDone(true);
        }
        dao.updateBlog(blog);
        return ResponseEntity.ok("Success");
    }
}
