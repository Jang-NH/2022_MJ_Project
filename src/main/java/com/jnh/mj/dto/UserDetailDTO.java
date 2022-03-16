package com.jnh.mj.dto;

import com.jnh.mj.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private MultipartFile userProfile;
    private String userProfilename;
    private String userMbti;

    public static UserDetailDTO toUserDetailDTO(UserEntity userEntity) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setUserId(userEntity.getId());
        userDetailDTO.setUserEmail(userEntity.getUserEmail());
        userDetailDTO.setUserPassword(userEntity.getUserPassword());
        userDetailDTO.setUserProfilename(userEntity.getUserProfilename());
        userDetailDTO.setUserNickname(userEntity.getUserNickname());
        userDetailDTO.setUserMbti(userEntity.getUserMbti());
        return userDetailDTO;
    }
}
