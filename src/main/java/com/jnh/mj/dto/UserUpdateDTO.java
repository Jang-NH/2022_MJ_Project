package com.jnh.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private Long userId;
    private String userEmail;
    private String userPassword;
    private MultipartFile userProfile;
    private String userProfilename;
    private String userNickname;
}
