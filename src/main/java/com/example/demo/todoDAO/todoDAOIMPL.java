package com.example.demo.todoDAO;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Todo;
import jakarta.persistence.EntityManager;

import java.util.List;
@Repository
public class todoDAOIMPL implements todoDAO {

        //Defining entityManger

        private final EntityManager entityManager;

        //Inject into constructor

        public todoDAOIMPL(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        @Transactional
        public void createBlog(Todo blog) {

            entityManager.persist(blog);


        }

        @Override
        public Todo readBlog(int id) {
            Todo blog = entityManager.find(Todo.class, id);
            return blog;
        }

        @Override
        public List<Todo> readAllBlogs() {
            List<Todo> blogs = entityManager.createQuery("from todo ", Todo.class).getResultList();
            return blogs;
        }

        @Override
        @Transactional
        public void updateBlog(Todo blog) {
            entityManager.merge(blog);

        }

        @Override
        @Transactional
        public void deleteBlog(int id) {
            Todo blog = entityManager.find(Todo.class, id);
            entityManager.remove(blog);

        }




}
