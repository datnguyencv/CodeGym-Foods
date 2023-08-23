package com.example.codegymfoods.service.blog.impl;

import com.example.codegymfoods.model.blog.Blog;
import com.example.codegymfoods.repository.blog.IBlogRepository;
import com.example.codegymfoods.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository iBlogRepository;
    @Override
    public Page<Blog> getBlog(String name,Pageable pageable) {
        return this.iBlogRepository.findAllByTitleContaining(name,pageable);
    }

    @Override
    public void save(Blog blog) {
        this.iBlogRepository.save(blog);
    }

    @Override
    public void delete(int id) {
        this.iBlogRepository.deleteById(id);
    }

    @Override
    public void update(Blog blog) {
        this.iBlogRepository.save(blog);
    }

    @Override
    public Blog findById(int id) {
        return this.iBlogRepository.findById(id).get();
    }
}
