package com.dissertationproject.plant_stories.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dissertationproject.plant_stories.bean.FeedPostMediaDTO;
import com.dissertationproject.plant_stories.model.CommentPost;
import com.dissertationproject.plant_stories.model.MediaPost;
import com.dissertationproject.plant_stories.model.Posts;
import com.dissertationproject.plant_stories.model.Users;

@Repository
public class HomeDao {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MediaPostsRepository mediaPostsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentPostRepository commentPostRepository;
	

	public void createPost(Posts post, ArrayList<MediaPost> mediaPosts) {
		// TODO Auto-generated method stub
		Posts savedPost = postRepository.save(post);
		
		Long postId = savedPost.getId();
		
		for (MediaPost mediaPost : mediaPosts) {
			mediaPost.setPostId(postId);
			mediaPostsRepository.save(mediaPost);
		}
	}


	public ArrayList<FeedPostMediaDTO> getAllPosts() {
		// TODO Auto-generated method stub
		ArrayList<FeedPostMediaDTO> feedPosts = new ArrayList<>();
		
		ArrayList<Posts> allPosts = (ArrayList<Posts>) postRepository.findAll().stream()
			    .sorted(Comparator.comparing(Posts::getCreatedDate).reversed()) // Descending order by createdDate
			    .collect(Collectors.toCollection(ArrayList::new));
		
		if(allPosts!=null) {
			for (Posts post : allPosts) {
				Optional<Users> user = userRepository.findById(post.getUserId());
				FeedPostMediaDTO feedPost = new FeedPostMediaDTO();
				feedPost.setPostId(post.getId());
				if(user.isPresent()) {
				    // Get the actual user object
				    Users existingUser = user.get();
					feedPost.setUsername(existingUser.getUsername());
				}
				feedPost.setPostTitle(post.getPostTitle());
				feedPost.setPostDescription(post.getPostDescription());
				feedPost.setCareRoutine(post.getCareRoutine());
				feedPost.setCreatedDate(post.getCreatedDate());
				feedPost.setMood(post.getMood());
				feedPost.setWeather(post.getWeather());
				feedPost.setPreviousStoryAchievements(post.getPreviousStoryAchievements());
				feedPost.setReflection(post.getReflection());
				
				ArrayList<MediaPost> allPostMedias = (ArrayList<MediaPost>) mediaPostsRepository.findByPostId(post.getId());

				ArrayList<FeedPostMediaDTO.MediaDTO> mediaDTOList = new ArrayList<>();
				if(allPostMedias!=null) {
					for (MediaPost postMedia : allPostMedias) {
						FeedPostMediaDTO.MediaDTO mediaDTO = new FeedPostMediaDTO.MediaDTO();
						mediaDTO.setFileName(postMedia.getFileName());
						mediaDTO.setMediaData(postMedia.getMediaData());
						mediaDTO.setBase64MediaData("data:" + postMedia.getMediaType() + ";base64," +
					            Base64.getEncoder().encodeToString(postMedia.getMediaData()));
						mediaDTO.setMediaType(postMedia.getMediaType());
						mediaDTO.setId(postMedia.getId());
						mediaDTOList.add(mediaDTO);
					}
					feedPost.setMediaList(mediaDTOList);
				}
				
				ArrayList<CommentPost> allpostComments = (ArrayList<CommentPost>) commentPostRepository.findAllByPostId(post.getId());

				ArrayList<FeedPostMediaDTO.CommentPostDTO> commentPostDTOList = new ArrayList<>();
				if(allpostComments!=null) {
					for (CommentPost allpostComment : allpostComments) {
						FeedPostMediaDTO.CommentPostDTO CommentPostDTO = new FeedPostMediaDTO.CommentPostDTO();
						CommentPostDTO.setCommentText(allpostComment.getCommentText());
						CommentPostDTO.setUsername(allpostComment.getUsername());
						CommentPostDTO.setSelectedReactions(allpostComment.getSelectedReactions());
						CommentPostDTO.setCreatedDate(allpostComment.getCreatedDate());
						commentPostDTOList.add(CommentPostDTO);
					}
					feedPost.setCommentList(commentPostDTOList);
				}
				System.out.println("feedPost toString: "+feedPost.toString());
				feedPosts.add(feedPost);
			}
		}
		return feedPosts;
	}


	public FeedPostMediaDTO getThisPostInfo(Long postId) {
		// TODO Auto-generated method stub
		FeedPostMediaDTO feedPost = new FeedPostMediaDTO();
		Optional<Posts> post = postRepository.findById(postId);
		if(post.isPresent()) {
		    // Get the actual user object
		    Posts existingPost = post.get();

			Optional<Users> user = userRepository.findById(existingPost.getUserId());
			if(user.isPresent()) {
			    // Get the actual user object
			    Users existingUser = user.get();
				feedPost.setUsername(existingUser.getUsername());
			}
			feedPost.setPostId(existingPost.getId());
		    feedPost.setPostTitle(existingPost.getPostTitle());
			feedPost.setPostDescription(existingPost.getPostDescription());
			feedPost.setCareRoutine(existingPost.getCareRoutine());
			feedPost.setCreatedDate(existingPost.getCreatedDate());
			feedPost.setMood(existingPost.getMood());
			feedPost.setWeather(existingPost.getWeather());
			feedPost.setPreviousStoryAchievements(existingPost.getPreviousStoryAchievements());
			feedPost.setReflection(existingPost.getReflection());
			
			ArrayList<MediaPost> allPostMedias = (ArrayList<MediaPost>) mediaPostsRepository.findByPostId(existingPost.getId());

			ArrayList<FeedPostMediaDTO.MediaDTO> mediaDTOList = new ArrayList<>();
			if(allPostMedias!=null) {
				for (MediaPost postMedia : allPostMedias) {
					FeedPostMediaDTO.MediaDTO mediaDTO = new FeedPostMediaDTO.MediaDTO();
					mediaDTO.setFileName(postMedia.getFileName());
					mediaDTO.setMediaData(postMedia.getMediaData());
					mediaDTO.setBase64MediaData("data:" + postMedia.getMediaType() + ";base64," +
				            Base64.getEncoder().encodeToString(postMedia.getMediaData()));
					mediaDTO.setMediaType(postMedia.getMediaType());
					mediaDTO.setId(postMedia.getId());
					mediaDTOList.add(mediaDTO);
				}
				feedPost.setMediaList(mediaDTOList);
			}
			System.out.println("feedPost toString: "+feedPost.toString());
		}
		return feedPost;
	}


	public void addComment(CommentPost commentPost) {
		// TODO Auto-generated method stub
		commentPostRepository.save(commentPost);
	}


	public ArrayList<FeedPostMediaDTO> getAllPosts(Long userId) {
		// TODO Auto-generated method stub
				ArrayList<FeedPostMediaDTO> feedPosts = new ArrayList<>();
				
				ArrayList<Posts> allPosts = (ArrayList<Posts>) postRepository.findAll().stream()
					    .sorted(Comparator.comparing(Posts::getCreatedDate).reversed()) // Descending order by createdDate
					    .collect(Collectors.toCollection(ArrayList::new));
				
				if(allPosts!=null) {
					for (Posts post : allPosts) {
						if(post.getUserId() == userId) {
							Optional<Users> user = userRepository.findById(post.getUserId());
							FeedPostMediaDTO feedPost = new FeedPostMediaDTO();
							feedPost.setPostId(post.getId());
							if(user.isPresent()) {
							    // Get the actual user object
							    Users existingUser = user.get();
								feedPost.setUsername(existingUser.getUsername());
							}
							feedPost.setPostTitle(post.getPostTitle());
							feedPost.setPostDescription(post.getPostDescription());
							feedPost.setCareRoutine(post.getCareRoutine());
							feedPost.setCreatedDate(post.getCreatedDate());
							feedPost.setMood(post.getMood());
							feedPost.setWeather(post.getWeather());
							feedPost.setPreviousStoryAchievements(post.getPreviousStoryAchievements());
							feedPost.setReflection(post.getReflection());
							
							ArrayList<MediaPost> allPostMedias = (ArrayList<MediaPost>) mediaPostsRepository.findByPostId(post.getId());

							ArrayList<FeedPostMediaDTO.MediaDTO> mediaDTOList = new ArrayList<>();
							if(allPostMedias!=null) {
								for (MediaPost postMedia : allPostMedias) {
									FeedPostMediaDTO.MediaDTO mediaDTO = new FeedPostMediaDTO.MediaDTO();
									mediaDTO.setFileName(postMedia.getFileName());
									mediaDTO.setMediaData(postMedia.getMediaData());
									mediaDTO.setBase64MediaData("data:" + postMedia.getMediaType() + ";base64," +
								            Base64.getEncoder().encodeToString(postMedia.getMediaData()));
									mediaDTO.setMediaType(postMedia.getMediaType());
									mediaDTO.setId(postMedia.getId());
									mediaDTOList.add(mediaDTO);
								}
								feedPost.setMediaList(mediaDTOList);
							}
							
							ArrayList<CommentPost> allpostComments = (ArrayList<CommentPost>) commentPostRepository.findAllByPostId(post.getId());

							ArrayList<FeedPostMediaDTO.CommentPostDTO> commentPostDTOList = new ArrayList<>();
							if(allpostComments!=null) {
								for (CommentPost allpostComment : allpostComments) {
									FeedPostMediaDTO.CommentPostDTO CommentPostDTO = new FeedPostMediaDTO.CommentPostDTO();
									CommentPostDTO.setCommentText(allpostComment.getCommentText());
									CommentPostDTO.setUsername(allpostComment.getUsername());
									CommentPostDTO.setSelectedReactions(allpostComment.getSelectedReactions());
									CommentPostDTO.setCreatedDate(allpostComment.getCreatedDate());
									commentPostDTOList.add(CommentPostDTO);
								}
								feedPost.setCommentList(commentPostDTOList);
							}
							System.out.println("feedPost toString: "+feedPost.toString());
							feedPosts.add(feedPost);
						}
					}
				}
				return feedPosts;
	}


	public void deletePost(Long postId, ArrayList<Long> mediaIdList, ArrayList<Long> commentIdList) {
		// TODO Auto-generated method stub
		postRepository.deleteById(postId);
		for (Long mediaId : mediaIdList) {
			mediaPostsRepository.deleteById(mediaId);
		}
		for (Long commentId : commentIdList) {
			commentPostRepository.deleteById(commentId);
		}
	}


	public FeedPostMediaDTO getPost(Long postId) {
		FeedPostMediaDTO feedPost = new FeedPostMediaDTO();
		
		Optional<Posts> post = postRepository.findById(postId);
		if(post.isPresent()) {
		    // Get the actual user object
		    Posts existingPost = post.get();
		if(existingPost!=null) {
				Optional<Users> user = userRepository.findById(existingPost.getUserId());
				feedPost.setPostId(existingPost.getId());
				if(user.isPresent()) {
				    // Get the actual user object
				    Users existingUser = user.get();
					feedPost.setUsername(existingUser.getUsername());
				}
				feedPost.setPostTitle(existingPost.getPostTitle());
				feedPost.setPostDescription(existingPost.getPostDescription());
				feedPost.setCareRoutine(existingPost.getCareRoutine());
				feedPost.setCreatedDate(existingPost.getCreatedDate());
				feedPost.setMood(existingPost.getMood());
				feedPost.setWeather(existingPost.getWeather());
				feedPost.setPreviousStoryAchievements(existingPost.getPreviousStoryAchievements());
				feedPost.setReflection(existingPost.getReflection());
				
				ArrayList<MediaPost> allPostMedias = (ArrayList<MediaPost>) mediaPostsRepository.findByPostId(existingPost.getId());

				ArrayList<FeedPostMediaDTO.MediaDTO> mediaDTOList = new ArrayList<>();
				if(allPostMedias!=null) {
					for (MediaPost postMedia : allPostMedias) {
						FeedPostMediaDTO.MediaDTO mediaDTO = new FeedPostMediaDTO.MediaDTO();
						mediaDTO.setFileName(postMedia.getFileName());
						mediaDTO.setMediaData(postMedia.getMediaData());
						mediaDTO.setBase64MediaData("data:" + postMedia.getMediaType() + ";base64," +
					            Base64.getEncoder().encodeToString(postMedia.getMediaData()));
						mediaDTO.setMediaType(postMedia.getMediaType());
						mediaDTO.setId(postMedia.getId());
						mediaDTOList.add(mediaDTO);
					}
					feedPost.setMediaList(mediaDTOList);
				}
				
				ArrayList<CommentPost> allpostComments = (ArrayList<CommentPost>) commentPostRepository.findAllByPostId(existingPost.getId());

				ArrayList<FeedPostMediaDTO.CommentPostDTO> commentPostDTOList = new ArrayList<>();
				if(allpostComments!=null) {
					for (CommentPost allpostComment : allpostComments) {
						FeedPostMediaDTO.CommentPostDTO CommentPostDTO = new FeedPostMediaDTO.CommentPostDTO();
						CommentPostDTO.setId(allpostComment.getId());
						CommentPostDTO.setCommentText(allpostComment.getCommentText());
						CommentPostDTO.setUsername(allpostComment.getUsername());
						CommentPostDTO.setSelectedReactions(allpostComment.getSelectedReactions());
						CommentPostDTO.setCreatedDate(allpostComment.getCreatedDate());
						commentPostDTOList.add(CommentPostDTO);
					}
					feedPost.setCommentList(commentPostDTOList);
				}
		}
	}
		return feedPost;
	}



}
