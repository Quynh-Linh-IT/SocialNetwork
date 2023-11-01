package com.socialnetwork.SocialNetWork.service.IMPL;

import com.socialnetwork.SocialNetWork.entity.User;
import com.socialnetwork.SocialNetWork.model.IMPL.RequestUserFriends;
import com.socialnetwork.SocialNetWork.model.Response.AuthResponse;
import com.socialnetwork.SocialNetWork.model.dto.UserDTO;
import com.socialnetwork.SocialNetWork.model.IMPL.UserFriendshipStatus;
import com.socialnetwork.SocialNetWork.model.mapper.UserMapper;
import com.socialnetwork.SocialNetWork.repository.UserRepository;
import com.socialnetwork.SocialNetWork.service.JwtTokenProvider;
import com.socialnetwork.SocialNetWork.service.UserService;
import com.socialnetwork.SocialNetWork.util.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final  UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<UserDTO> getListUser() {
        ArrayList<User> result = (ArrayList<User>) userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<UserDTO>();
        // Convert users -> result
        for (User user : result) {
            userDTOS.add(UserMapper.toUserDto(user));
        }
        return userDTOS;
    }

    @Override
    public List<String> getListIdUser() {
        ArrayList<User> result = (ArrayList<User>) userRepository.findAll();
        ArrayList<String> listID = new ArrayList<>();
        for (User user : result) {
            listID.add(user.getId());
        }
        return listID;
    }

    @Override
<<<<<<< HEAD
    public UserDTO getUserById(String id){
        for (User user : users) {
            if(user.getId().equals(id)){
                return UserMapper.toUserDto(user);
            }
=======
    public List<UserFriendshipStatus> getListSuggestedFriends(String id) {
        ArrayList<UserFriendshipStatus> listUser = (ArrayList<UserFriendshipStatus>) userRepository.getListSuggestedFriends(id);
        if(!listUser.isEmpty()){
            return listUser;
>>>>>>> 411619b7a2a97a1978f481422002a3bb164342c3
        }
        return null;
    }

    @Override
    public List<RequestUserFriends> getUserRequestFriends(String id,String limit) {
        int convertLimit = Integer.parseInt(limit);
        if(!id.isEmpty() && convertLimit > 0){
            ArrayList<RequestUserFriends> result = (ArrayList<RequestUserFriends>) userRepository.getUserRequestFriends(id,convertLimit);
            if(!result.isEmpty()){
                return result;
            }
        }
        return null;
    }

    @Override
    public String addUser(User user) {
        try{
            String uniqueId = IdGenerator.generateUniqueId();
            ArrayList<User> listUsers = (ArrayList<User>) userRepository.findAll();
            for (User item :listUsers) {
                if(item.getId().equals(uniqueId)){
                    uniqueId = IdGenerator.generateUniqueId();
                }
            }
            user.setId(uniqueId);
            userRepository.save(user);
            return "success";
        } catch(DataAccessException ex){
            ex.printStackTrace();
            return "error";
        }
    }

    @Override
    public UserDTO getUserByToken(String token){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        boolean checkToken = jwtTokenProvider.validateToken(token);
        if(checkToken){
            String emailUser = jwtTokenProvider.getUserIdFromJWT(token);
            User user = userRepository.findUserByEmail(emailUser);
            return UserMapper.toUserDto(user);
        }
        return null;
    }

    @Override
    public AuthResponse login(String email, String password) {
        User user = userRepository.findUserByLogin(email, password);
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        AuthResponse authResponse = null;
        if(user != null){
            String token = jwtTokenProvider.generateToken(user.getEmail());
            authResponse = new AuthResponse(token,"success",user.getId());
        } else {
            authResponse = new AuthResponse("","error" , "");
        }
        return authResponse;
    }

    @Override
    public String updateUser(User user) {
        try{
            int userDB = userRepository.updateUser(user.getEmail(), user.getFirstName(),user.getLastName(),
                    user.getDateOfBirth(),user.getAddress(),user.getId());
            if(userDB > 0){
                return "success update";
            }
        } catch(DataAccessException e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @Override
    public String updateImageUser(User user) {
        try{
            int userDB = userRepository.updateImageUser(user.getImage(),user.getId());
            if(userDB > 0){
                return "success update image";
            }
        } catch(DataAccessException e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @Override
    public String updateStatusFriend(String receiverId, String senderId, String title) {
        try{
            if(!receiverId.isEmpty() && !senderId.isEmpty()){
                int updateStatusByFriends;
                if(title.equals("confirm")){
                    updateStatusByFriends = userRepository.updateStatusByFriends(2, senderId, receiverId);
                } else {
                    updateStatusByFriends = userRepository.updateStatusByFriends(3, senderId, receiverId);
                }
                if(updateStatusByFriends > 0){
                    return "success update";
                }
            }
        } catch(DataAccessException e){
            e.printStackTrace();
        }
        return "error";
    }

    @Override
    public String checkTokenUser(String token) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        boolean checkToken = jwtTokenProvider.validateToken(token);
        if(checkToken){
            return "exits";
        } else {
            return "not exits";
        }
    }
}
