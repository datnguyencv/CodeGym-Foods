package com.example.codegymfoods.service.blog;

import com.example.codegymfoods.model.blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Page<Blog> getBlog (String name,Pageable pageable);
    void save (Blog blog);
    void delete (int id);
    void update (Blog blog);
    Blog findById (int id);
}
