package com.socialnetwork.SocialNetWork.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String dateOfBirth;

    private String image;

}
