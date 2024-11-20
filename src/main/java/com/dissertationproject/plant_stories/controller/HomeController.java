package com.dissertationproject.plant_stories.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Posts;
import com.dissertationproject.plant_stories.dao.UserRepository;
import com.dissertationproject.plant_stories.model.Users;
import com.dissertationproject.plant_stories.service.HomeServiceImpl;

import jakarta.validation.Valid;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private HomeServiceImpl homeServiceImpl;;
	
	@GetMapping("/")
	private ModelAndView showHomePage() {
        System.out.println("Inside showHomePage method");
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByUsername(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
        }
        
        ArrayList<FeedPostMediaDTO> feedPosts = homeServiceImpl.getAllPosts();
        mv.addObject("feedPosts", feedPosts);
		mv.setViewName("home.html");
		return mv;
	}
	
	@GetMapping("/addPost")
	private ModelAndView showAddPost() {
        System.out.println("Inside showAddPost method");
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByUsername(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
        }
		mv.addObject("post", new Posts());
		mv.setViewName("addpost.html");
		return mv;
	}
	
	@PostMapping("/posts/create")
	private String createPost(@Valid @ModelAttribute("post") Posts post,
            BindingResult bindingResult) {
        System.out.println("Inside showAddPost method");
		ModelAndView mv = new ModelAndView();
		
		Long userId = null;
		
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByUsername(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
        	userId = user.getId();
        }

        if (bindingResult.hasErrors()) {
        	System.out.println("bindingResult has Errors.");
        	System.out.println(bindingResult.getAllErrors());
            return "addpost";  // return to signup form if errors
        }else {
        	System.out.println("bindingResult has no Errors.");
        }
        System.out.println("post: "+post.toString());
        
        homeServiceImpl.createPost(userId, post);
        
        return "redirect:/";  
	}
}
