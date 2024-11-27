package com.dissertationproject.plant_stories.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dissertationproject.plant_stories.bean.CommentForm;
import com.dissertationproject.plant_stories.bean.EditProfileDTO;
import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Posts;
import com.dissertationproject.plant_stories.dao.UserRepository;
import com.dissertationproject.plant_stories.model.Users;
import com.dissertationproject.plant_stories.service.HomeServiceImpl;
import com.dissertationproject.plant_stories.service.UserServiceImpl;

import jakarta.validation.Valid;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private HomeServiceImpl homeServiceImpl;
	
	@GetMapping("/")
	private ModelAndView showHomePage(@RequestParam(defaultValue = "0") int page) {
        System.out.println("Inside showHomePage method");
        
        int pageSize = 3; // Show 3 posts per page
        
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
        }
        
        int totalNoOfPages = homeServiceImpl.getTotalNoOfPosts(pageSize);
        
        ArrayList<FeedPostMediaDTO> feedPosts = homeServiceImpl.getAllPosts(page, pageSize);
        mv.addObject("feedPosts", feedPosts);
        mv.addObject("commentForm", new CommentForm());
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", totalNoOfPages);
		mv.setViewName("home.html");
		return mv;
	}
	
	@GetMapping("/addPost")
	private ModelAndView showAddPost() {
        System.out.println("Inside showAddPost method");
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's name
        
        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);

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
		
		Long userId = null;
		
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);

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
	
	@GetMapping("/showComment")
	public ModelAndView showCommentPage(@RequestParam("postId") Long postId) {
		ModelAndView mv = new ModelAndView();
		System.out.println("postId: "+postId);
		FeedPostMediaDTO feedPost = homeServiceImpl.getThisPostInfo(postId);
        mv.addObject("feedPost", feedPost);
        mv.addObject("commentForm", new CommentForm());
		mv.setViewName("commentpage.html");
		return mv;
	}
	
	@PostMapping("/posts/addComment")
	public ModelAndView addComment(@RequestParam("postId") Long postId, @ModelAttribute("commentForm") CommentForm commentForm,
			RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		if(commentForm.getCommentText().isBlank() && commentForm.getSelectedReactions().isEmpty()) {
	        redirectAttributes.addFlashAttribute("errorMessage","Please write a comment or select a reaction to submit your comment!");
			mv.setViewName("redirect:/");
			return mv;
		}

		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);
        
		System.out.println(commentForm.toString());
		homeServiceImpl.addComment(commentForm, postId, user.getId(), user.getUsername());
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/deletePost")
	public ModelAndView deletePost(@RequestParam("postId") Long postId,
			RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		FeedPostMediaDTO feedPostMediaDTO = homeServiceImpl.getPost(postId);
		System.out.println(feedPostMediaDTO.toString());
		
		homeServiceImpl.deletePost(feedPostMediaDTO);
		
		mv.setViewName("redirect:/profile");
		return mv;
	}
	
	@GetMapping("/showEditProfile")
	public ModelAndView showEditProfile() {
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);
        
        EditProfileDTO profileDTO = new EditProfileDTO();
        profileDTO.setUsername(user.getUsername());
        profileDTO.setBio(user.getBio());
		ModelAndView mv = new ModelAndView();
		mv.addObject("profileDTO", profileDTO);
		mv.addObject("userName", user.getUsername());
		mv.setViewName("editprofile.html");
		return mv;
	}
	
	@PostMapping("/editProfile")
	public ModelAndView editProfile(@Valid @ModelAttribute("profileDTO") EditProfileDTO profileDTO) {
		System.out.println("Inside editProfile method");
		System.out.println("userForm toString: "+ profileDTO.toString());
		ModelAndView mv = new ModelAndView();
        
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);
        System.out.println("userID:"+ user.getId());
        userServiceImpl.editprofile(user.getId(), profileDTO.getBio());
        
		mv.addObject("userName", user.getUsername());
		mv.setViewName("redirect:/profile");
		return mv;
	}
	
	@GetMapping("/showEditPost")
	public ModelAndView showEditPost(@RequestParam("postId") Long postId) {
		System.out.println("Inside showEditPost method");
		System.out.println("postId: "+ postId);
		ModelAndView mv = new ModelAndView();
        FeedPostMediaDTO post = homeServiceImpl.getThisPostInfo(postId);
        System.out.println("post: "+post.toString());
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);
        System.out.println("userID:"+ user.getId());
        
        mv.addObject("post", post);
		mv.addObject("userName", user.getUsername());
		mv.setViewName("editpost.html");
		return mv;
	}

	@GetMapping("/deleteMedia")
	public ModelAndView deleteMedia(@RequestParam("mediaId") Long mediaId, @RequestParam("postId") Long postId) {
		System.out.println("Inside deleteMedia method");
		ModelAndView mv = new ModelAndView();
        homeServiceImpl.deleteMedia(mediaId);
        
		mv.setViewName("redirect:/showEditPost?postId=" + postId);
		return mv;
	}
	
	@PostMapping("/editThisPost")
	public ModelAndView editThisPost(@Valid @ModelAttribute("post") FeedPostMediaDTO feedPost,
			@RequestParam("mediaData") ArrayList<MultipartFile> mediaData,
			@RequestParam("postId") Long postId,
			@RequestParam("totalMediaCount") int totalMediaCount,
			RedirectAttributes redirectAttributes) throws IOException {
		System.out.println("Inside editThisPost method");
		ModelAndView mv = new ModelAndView();
		
		if(totalMediaCount>5) {
			redirectAttributes.addFlashAttribute("mediaErrorMsg","Not more than 5 media(Images/Videos) files can be uploaded per post.");
			mv.setViewName("redirect:/showEditPost?postId=" + postId);
			return mv;
		}
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);
        System.out.println("userID:"+ user.getId());
        
		
		Posts post = new Posts();
		post.setPostId(postId);
		post.setCareRoutine(feedPost.getCareRoutine());
		if(mediaData!=null) {
			System.out.println("mediaData size: "+mediaData.size());
			if(mediaData.size()>0) {
				for (MultipartFile multipartFile : mediaData) {
					System.out.println("multipartFile.getSize(): "+multipartFile.getSize()); 
					if(multipartFile.getSize()>0) {
						post.setMediaData(mediaData);
					}
				}
			}
		}
		post.setMood(feedPost.getMood());
		post.setPostDescription(feedPost.getPostDescription());
		post.setPostTitle(feedPost.getPostTitle());
		post.setPreviousStoryAchievements(feedPost.getPreviousStoryAchievements());
		post.setReflection(feedPost.getReflection());
		post.setWeather(feedPost.getWeather());
		

        homeServiceImpl.createPost(user.getId(), post);
		mv.setViewName("redirect:/showEditPost?postId=" + postId);
		return mv;
	}
	
	@GetMapping("/showMembers")
	public ModelAndView showMembers() {
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();  // Get the logged-in user's email
	
	    // Fetch the user entity from the database using the email
	    Users user = userRepository.findByEmail(username);
	    
	    ArrayList<Users> usersList = (ArrayList<Users>) userRepository.findAll();
	
	    // Add the first name, last name, and role to the model
	    if (user != null) {
	        mv.addObject("userName", user.getUsername());
	    }
	    if(!usersList.isEmpty()) {
	    	mv.addObject("users", usersList);
	    }
		mv.setViewName("members.html");
		return mv;
	}
	
	@GetMapping("/showAbout")
	public ModelAndView showAbout() {
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
        }
		mv.setViewName("about.html");
		return mv;
	}
	
	@GetMapping({"/ask4help","/projectIdeas","/projectTracker","/inventory"})
	public ModelAndView notImplementedYet() {
		System.out.println("Inside deleteMedia method");
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        Users user = userRepository.findByEmail(username);

        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
        }
        
		mv.setViewName("notimplementedyet.html");
		return mv;
	}
}
