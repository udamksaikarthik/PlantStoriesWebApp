package com.dissertationproject.plant_stories.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.bean.Users;
import com.dissertationproject.plant_stories.dao.UserRepository;
import com.dissertationproject.plant_stories.model.PasswordResetToken;
import com.dissertationproject.plant_stories.service.EmailService;
import com.dissertationproject.plant_stories.service.HomeServiceImpl;
import com.dissertationproject.plant_stories.service.UserServiceImpl;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private HomeServiceImpl homeServiceImpl;;
	
	@GetMapping("/login")
	private ModelAndView showLoginPage() {
        System.out.println("Inside showLoginPage method");
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new Users());
		mv.setViewName("login.html");
		return mv;
	}
	
	@PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("user") Users users,
                                BindingResult bindingResult) {
        System.out.println("Inside processLogin method");

        if (bindingResult.hasErrors()) {
        	System.out.println("bindingResult has Errors.");
            return "login";  // return to signup form if errors
        }else {
        	System.out.println("bindingResult has no Errors.");
        }
        return "/";  
    }
	
	@GetMapping("/signup")
	private ModelAndView showSignUpPage() {
        System.out.println("Inside showSignUpPage method");
		ModelAndView mv = new ModelAndView();
		mv.addObject("signupForm", new Users());
		mv.setViewName("signup.html");
		return mv;
	}
	
	@PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("signupForm") Users users,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println("Inside processSignup method");
        
        System.out.println("users: "+users.toString());
        System.out.println("users email: "+users.getEmail());
        
     // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "signup";  // Return signup page with errors
        }

        // If passwords don't match, add custom error
        if (!users.getPassword().equals(users.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.signupForm", "Passwords do not match");
            return "signup";  // Return signup page with error
        }
        
        Boolean flag = userServiceImpl.checkIfEmailAlreadyExists(users);
        
        if(flag) {
            bindingResult.rejectValue("email", "error.signupForm", "Email already exists with an account! Try again with a different email!");
            return "signup";
        }

        // Process the registration
        userServiceImpl.registerUser(users);
        
        redirectAttributes.addFlashAttribute("signup_success_message", "Account created successfully!");
        // Redirect to login after successful signup
        return "redirect:/login";
    }
	
	@GetMapping("/profile")
	public ModelAndView showUserProfile(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "USER_DEFAULT") String userName,
			@RequestParam(defaultValue = "0") int fragment) {
		

        int pageSize = 3; // Show 3 posts per page
        
        
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        // Fetch the user entity from the database using the email
        com.dissertationproject.plant_stories.model.Users user = userRepository.findByEmail(username);
        
        Long userId = 0L;
        // Add the first name, last name, and role to the model
        if (user != null) {
            mv.addObject("userName", user.getUsername());
            userId = user.getId();
            mv.addObject("userBio", user.getBio());
        }
        

        int totalNoOfPages = homeServiceImpl.getTotalNoOfPosts(userId, pageSize);
        
        ArrayList<FeedPostMediaDTO> feedPosts = homeServiceImpl.getAllPosts(userId, page, pageSize);
        mv.addObject("feedPosts", feedPosts);
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", totalNoOfPages);
		mv.setViewName("profile.html");
		return mv;
	}

	@GetMapping("/showThisUserProfile")
	public ModelAndView showThisUserProfile(@RequestParam(defaultValue = "0") Long userId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "USER_DEFAULT") String userName,
			@RequestParam(defaultValue = "0") int fragment) {
		

        int pageSize = 3; // Show 3 posts per page
        
		ModelAndView mv = new ModelAndView();
		// Get the logged-in user's email (username in this case)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the logged-in user's email

        com.dissertationproject.plant_stories.model.Users userFromUrl = userRepository.findByUsername(userName);
        
        com.dissertationproject.plant_stories.model.Users loggedInuser = userRepository.findByEmail(username);

        if(userId ==0) {
        	userId = userFromUrl.getId();
        }
        // Fetch the user entity from the database using the email
        Optional<com.dissertationproject.plant_stories.model.Users> user = userRepository.findById(userId);
      
        
        if(user.isPresent()) {
        	com.dissertationproject.plant_stories.model.Users existingUser = user.get();
            mv.addObject("selectedUsername", existingUser.getUsername());
            mv.addObject("userBio", existingUser.getBio());
        }
        mv.addObject("userName", loggedInuser.getUsername());
        

        int totalNoOfPages = homeServiceImpl.getTotalNoOfPosts(userId, pageSize);
        
        ArrayList<FeedPostMediaDTO> feedPosts = homeServiceImpl.getAllPosts(userId, page, pageSize);
        mv.addObject("feedPosts", feedPosts);
        mv.addObject("currentPage", page);
        mv.addObject("userId", userId);
        mv.addObject("totalPages", totalNoOfPages);
		mv.setViewName("selecteduserprofile.html");
		return mv;
	}
	
	@PostMapping("/forgot-password")
    public ModelAndView forgotPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {
		System.out.println("Inside forgotPassword method");
		ModelAndView mv = new ModelAndView();
        try {
            String token = userServiceImpl.generateResetToken(email);
            emailService.sendPasswordResetEmail(email, token);
            System.out.println("After sendPasswordResetEmail method!");
            String msg = "Password reset email sent successfully.";
            redirectAttributes.addFlashAttribute("passwordResetMailMsgSuccess", msg);
            System.out.println("msg:"+ msg);
            mv.setViewName("redirect:/login");
            return mv;
        } catch (Exception e) {
        	String msg = "Email not associated in our database. Please create an account!";
            redirectAttributes.addFlashAttribute("passwordResetMailMsgError", msg);
            System.out.println("msg:"+ msg);
            mv.setViewName("redirect:/login");
            return mv;
        }
    }
	
	@PostMapping("/reset-password")
	public ModelAndView resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword,
			RedirectAttributes redirectAttributes) {
		System.out.println("Inside Reset Password");
	    PasswordResetToken resetToken = userServiceImpl.findByToken(token);
	    System.out.println("resetToken: "+resetToken);
	    ModelAndView mv = new ModelAndView();

	    if (resetToken != null && resetToken.getExpiryDate().isAfter(LocalDateTime.now())) {
	        com.dissertationproject.plant_stories.model.Users user = resetToken.getUser();
	        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
	        userRepository.save(user);
	        userServiceImpl.delete(resetToken); // Invalidate the token
	        String msg = "Password has been resetted successfully!";
	        System.out.println("msg: "+msg);
	        redirectAttributes.addFlashAttribute("passwordResetMsgSuccess",msg);
	        mv.setViewName("redirect:/login");
	        return mv;
	    } else {
	        String msg = "Password hasn't been resetted! Try Again please!";
	        System.out.println("msg: "+msg);
	        redirectAttributes.addFlashAttribute("passwordResetMsgError",msg);
	        mv.setViewName("redirect:/login");
	        return mv;
	    }
	}
	
	@GetMapping("/showForgotPasswordPage")
	public ModelAndView showForgotPasswordPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forgotpasswordpage.html");
		return mv;
	}
	
	@GetMapping("/reset-password")
	public ModelAndView showResetPasswordPage(@RequestParam("token") String token) {
		System.out.println("Inside showResetPasswordPage");
		ModelAndView mv = new ModelAndView();
		mv.addObject("token", token);
		mv.setViewName("resetpasswordpage.html");
		return mv;
	}
}

