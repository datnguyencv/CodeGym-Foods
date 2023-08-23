package com.example.codegymfoods.repository.blog;

import com.example.codegymfoods.model.blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Integer> {
    Page<Blog> findAllByTitleContaining(String name, Pageable pageable);
}
