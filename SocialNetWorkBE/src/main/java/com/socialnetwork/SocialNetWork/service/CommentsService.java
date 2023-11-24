package com.socialnetwork.SocialNetWork.service;

import com.socialnetwork.SocialNetWork.entity.Comments;
import com.socialnetwork.SocialNetWork.model.IMPL.CommentById;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentsService {
    public Comments addComments(Comments comments);

    public List<CommentById> getListCommentByPost(int postId, int limit);

    public List<CommentById> getListParentCommentByPost(int postId,int commentId , int limit);
}
