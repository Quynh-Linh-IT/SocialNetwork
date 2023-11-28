package com.socialnetwork.SocialNetWork.repository;

import com.socialnetwork.SocialNetWork.entity.Post;
import com.socialnetwork.SocialNetWork.model.IMPL.PostById;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "SELECT DISTINCT " +
            "    p.id, " +
            "    p.user_id AS userID, " +
            "    p.content, " +
            "    p.created_at AS createdAt, " +
            "    p.delete_at AS deleteAt, " +
            "    p.privacy_id AS privacyId, " +
            "    u.first_name AS firstName, " +
            "    u.last_name AS lastName, " +
            "    u.image AS avatarUser " +
            "FROM " +
            "    posts AS p " +
            "LEFT JOIN " +
            "    frindship AS f ON (p.user_id = f.sender_id OR p.user_id = f.receiver_id) " +
            "JOIN " +
            "    user AS u ON p.user_id = u.id " +
            "WHERE " +
            "    ( " +
            "        (f.sender_id = ?1 OR f.receiver_id = ?1 OR f.sender_id IS NULL) " +
            "        AND IFNULL(f.status, 2) = 2 " +
            "    ) " +
            "    OR " +
            "    ( " +
            "        p.user_id = ?1 " +
            "        AND IFNULL(f.status, 2) = 1 " +
            "    ) " +
            "    AND ( " +
            "        p.privacy_id = 1 OR " +
            "        (p.privacy_id = 2 AND p.user_id = ?1) OR " +
            "        (p.privacy_id = 3 AND EXISTS ( " +
            "            SELECT 1 " +
            "            FROM frindship AS f2 " +
            "            WHERE " +
            "                (f2.sender_id = ?1 OR f2.receiver_id = ?1) " +
            "                AND IFNULL(f2.status, 2) = 2 " +
            "                AND (f2.sender_id = p.user_id OR f2.receiver_id = p.user_id) " +
            "        )) " +
            "    ) " +
            "ORDER BY p.created_at DESC ", nativeQuery = true)
    List<PostById> getListPost(String userId);

    // delete post by user
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM posts WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    void deletePostByUser(String postId, String userId);

}
