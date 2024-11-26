package com.dissertationproject.plant_stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String token) {
    	System.out.println("Inside sendPasswordResetEmail");
    	
    	// Generate the root URL dynamically
        String resetUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/reset-password")
                .queryParam("token", token)
                .toUriString();
    	
        System.out.println(resetUrl);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Plant Stories App: Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetUrl);

        try {
            mailSender.send(message);
            System.out.println("Password reset email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("Error sending password reset email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}