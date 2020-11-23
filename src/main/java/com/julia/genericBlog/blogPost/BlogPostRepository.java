package com.julia.genericBlog.blogPost;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{
	Iterable<BlogPost> findAll(Sort sort);
}
