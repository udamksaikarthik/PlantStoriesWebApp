package com.dissertationproject.plant_stories.bean;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Represents the form data for a comment with reactions.
 */
public class CommentForm {

    /**
     * The text of the comment.
     */
    private String commentText;

    /**
     * List of selected reactions.
     */
    private List<String> selectedReactions;

    // Getters and Setters

    /**
     * Gets the comment text.
     * 
     * @return the comment text
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment text.
     * 
     * @param commentText the text to set
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets the list of selected reactions.
     * 
     * @return the list of reactions
     */
    public List<String> getSelectedReactions() {
        return selectedReactions;
    }

    /**
     * Sets the list of selected reactions.
     * 
     * @param selectedReactions the reactions to set
     */
    public void setSelectedReactions(List<String> selectedReactions) {
        this.selectedReactions = selectedReactions;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "commentText='" + commentText + '\'' +
                ", selectedReactions=" + selectedReactions +
                '}';
    }
}

