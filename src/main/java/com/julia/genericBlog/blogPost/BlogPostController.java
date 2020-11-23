package com.julia.genericBlog.blogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostRepository blogPostRepository;

	@GetMapping(value = "/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("title", "Welcome to Generic Blog");
		model.addAttribute("posts", blogPostRepository.findAll(Sort.by("createdAt").descending()));
		return "blog/index";
	}

	private BlogPost blogPost;

	@PostMapping(value = "/blog")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
		model.addAttribute("title", "Generic Blog | Submission");
		return "blog/result";
	}

	@GetMapping(value = "/blog/new")
	public String newBlog(BlogPost blogPost, Model model) {
		model.addAttribute("title", "Generic Blog | Add New Post");
		return "blog/new";
	}

	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if (post.isPresent()) {
			BlogPost actualPost = post.get();
			model.addAttribute("blogPost", actualPost);
			model.addAttribute("title", "Generic Blog | Edit Post");
		}
		return "blog/edit";
	}

	@RequestMapping(value = "/blog/update/{id}")
	public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
		Optional<BlogPost> post = blogPostRepository.findById(id);
		if (post.isPresent()) {
			BlogPost actualPost = post.get();
			actualPost.setTitle(blogPost.getTitle());
			actualPost.setAuthor(blogPost.getAuthor());
			actualPost.setBlogEntry(blogPost.getBlogEntry());
			blogPostRepository.save(actualPost);
			model.addAttribute("blogPost", actualPost);
			model.addAttribute("title", "Generic Blog | Update Post");
		}
		return "blog/result";
	}

	@RequestMapping(value = "blog/delete/{id}")
	public String deletePostById(@PathVariable Long id, BlogPost blogPost, Model model) {
		blogPostRepository.deleteById(id);
		model.addAttribute("title", "Generic Blog | Delete Post");
		return "blog/delete";
	}
}
