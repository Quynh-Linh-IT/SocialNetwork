package com.socialnetwork.SocialNetWork.controller;

import com.socialnetwork.SocialNetWork.entity.Media;
import com.socialnetwork.SocialNetWork.entity.Post;
import com.socialnetwork.SocialNetWork.model.IMPL.PostById;
import com.socialnetwork.SocialNetWork.model.Response.ApiResponse;
import com.socialnetwork.SocialNetWork.model.dto.UserDTO;
import com.socialnetwork.SocialNetWork.service.*;
import com.socialnetwork.SocialNetWork.util.ConvertJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/api/v1/post")
@RestController
public class PostController {
    @Autowired
    public PostService postService;
    @Autowired
    public MediaService mediaService;
    @Autowired
    public PostMediaService postMediaService;
    @Autowired
    public CommentsService commentsService;
    @Autowired
    public LikesService likesService;

    @GetMapping("/getListPost/{id}")
    public ResponseEntity<?> getListPost(@PathVariable String id) {
        try {
            if (id.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("userId is required"));
            }
            List<PostById> result = postService.getListPost(id);
            return result == null ? ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("No data")) : ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error occurred"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody String body){
        try{
            // CONVERT JSON TO STRING
            Timestamp createdAt = Timestamp.valueOf(ConvertJSON.converJsonToString(body,"createdAt"));
            String content = ConvertJSON.converJsonToString(body,"content");
            String userId = ConvertJSON.converJsonToString(body,"userId");
            int privacyId = Integer.parseInt(ConvertJSON.converJsonToString(body,"privacyId"));
            System.err.println(createdAt + content + userId + privacyId);
            // INIT Media
            if(!content.isEmpty() && !userId.isEmpty() && privacyId > 0){
                Post media = new Post(userId,content,createdAt,null,privacyId);
                return ResponseEntity.status(HttpStatus.OK).body(postService.addPost(media));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Data cannot be left blank"));
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    };

    // delete post
    @GetMapping("/deletePost")
    public ResponseEntity<?> deletePost(@RequestBody String body){
        try {
            int postId =Integer.parseInt(ConvertJSON.converJsonToString(body,"postId"));
            String userId = ConvertJSON.converJsonToString(body,"userId");
            if(postId < 0){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("PostId not exits!");
            }
            if(userId.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserId not exits");
            }
            // delete media and postMedia in post
            List<String> mediaId = postMediaService.getListMediaIdByPost(postId);
            if(!mediaId.isEmpty()){
                postMediaService.deletePostMediaByPost(postId);
                mediaService.deleteMediaOfPost(mediaId);
            }
            // delete comment in post
            int checkComment = commentsService.checkCommentExitsInPost(postId);
            System.err.println(checkComment);
            if (checkComment > 0){
                List<String> commentId = commentsService.getListIdCommentByPost(postId);
               if(!commentId.isEmpty()){
                   commentsService.deleteAllCommentChildInPost(commentId);
               }
                commentsService.deleteAllCommentParentInPost(postId);
            }
            // delete likes exits in post
            int checkLikes = likesService.checkLikesExitInPost(postId);
            if (checkLikes > 0){
                likesService.deleteLikesInPost(postId);
            }
            // delete post by user
            postService.deletePostByUser(postId,userId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete success ?"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }

    // update post
    @PostMapping("/updatePost")
    public ResponseEntity<?> updatePost(@RequestBody String body){
        try {
            String content = (ConvertJSON.converJsonToString(body, "content").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "content");
            Timestamp createdAtPost = (ConvertJSON.converJsonToString(body, "createAtPost").isEmpty()) ? null : Timestamp.valueOf(ConvertJSON.converJsonToString(body, "createAtPost"));
            String privacyId = (ConvertJSON.converJsonToString(body,"privacyId").isEmpty()) ? null : ConvertJSON.converJsonToString(body,"privacyId");
            String postId = (ConvertJSON.converJsonToString(body, "postId").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "postId");
            String userId = (ConvertJSON.converJsonToString(body, "userId").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "userId");
            String idMedia = (ConvertJSON.converJsonToString(body, "idMedia").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "idMedia");
            String mediaUrl = (ConvertJSON.converJsonToString(body, "mediaUrl").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "mediaUrl");
            String mediaType = (ConvertJSON.converJsonToString(body, "mediaType").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "mediaType");
            Timestamp createdAtMedia = (ConvertJSON.converJsonToString(body, "createAtMedia").isEmpty()) ? null : Timestamp.valueOf(ConvertJSON.converJsonToString(body, "createAtMedia"));
            String title = (ConvertJSON.converJsonToString(body, "title").isEmpty()) ? null : ConvertJSON.converJsonToString(body, "title");

            if(postId != null && userId != null){
                // parseInt privacyId, idPostInt convert Integer
                int privacyIdPost =  Integer.parseInt(privacyId);
                int idPostInt = Integer.parseInt(postId);
                if(content != null || createdAtPost != null){
                    postService.updatePost(content,createdAtPost,privacyIdPost,idPostInt,userId);
                }
                if (idMedia != null) {
                    // parseInt idMediaInt convert Integer
                    int idMediaInt = Integer.parseInt(idMedia);
                    if (mediaType != null || mediaUrl != null || title != null){
                        mediaService.updateMedia(idMediaInt,mediaUrl,mediaType,createdAtMedia,title);
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Update Success ?"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Data cannot be left blank"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }
}
