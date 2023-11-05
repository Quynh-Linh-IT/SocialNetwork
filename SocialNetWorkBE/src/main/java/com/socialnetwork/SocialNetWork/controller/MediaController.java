package com.socialnetwork.SocialNetWork.controller;

import com.socialnetwork.SocialNetWork.entity.Frindship;
import com.socialnetwork.SocialNetWork.entity.Media;
import com.socialnetwork.SocialNetWork.model.Response.ApiResponse;
import com.socialnetwork.SocialNetWork.model.dto.UserDTO;
import com.socialnetwork.SocialNetWork.service.MediaService;
import com.socialnetwork.SocialNetWork.util.ConvertJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/api/v1/media")
@RestController
public class MediaController {
    @Autowired
    public MediaService mediaService;

    @PostMapping("")
    public ResponseEntity<?> getListUser(@RequestBody String body){
        String userId = ConvertJSON.converJsonToString(body,"id");
        String limit = ConvertJSON.converJsonToString(body,"limit");
        List<Media> result = mediaService.getListMedia(userId,limit);
        if(!result.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("error"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody String body){
        try{
            // CONVERT JSON TO STRING
            Timestamp createdAt = Timestamp.valueOf(ConvertJSON.converJsonToString(body,"createdAt"));
            String userId = ConvertJSON.converJsonToString(body,"userId");
            String mediaUrl = ConvertJSON.converJsonToString(body,"mediaUrl");
            String mediaType = ConvertJSON.converJsonToString(body,"mediaType");
            String title = ConvertJSON.converJsonToString(body,"title");
            // INIT Media
            Media media = new Media(userId,mediaUrl,mediaType,createdAt,title,null);
            String result = mediaService.addMedia(media);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(result));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    };
}