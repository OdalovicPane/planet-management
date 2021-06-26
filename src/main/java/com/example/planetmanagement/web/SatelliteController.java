package com.example.planetmanagement.web;

import com.example.planetmanagement.entity.SatelliteEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {


    private SatelliteDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setSubmissionDate(post.getSubmissionDate(),
                userService.getCurrentUser().getPreference().getTimezone());
        return postDto;
    }

    private Post convertToEntity(PostDto postDto) throws ParseException {
        Post post = modelMapper.map(postDto, Post.class);
        post.setSubmissionDate(postDto.getSubmissionDateConverted(
                userService.getCurrentUser().getPreference().getTimezone()));

        if (postDto.getId() != null) {
            Post oldPost = postService.getPostById(postDto.getId());
            post.setRedditID(oldPost.getRedditID());
            post.setSent(oldPost.isSent());
        }
        return post;
    }

}
