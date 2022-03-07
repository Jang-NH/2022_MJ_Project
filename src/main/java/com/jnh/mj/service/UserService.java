package com.jnh.mj.service;

import com.jnh.mj.dto.UserDetailDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.dto.UserSaveDTO;

public interface UserService {
    long save(UserSaveDTO userSaveDTO);

    String emailDuplicate(String userEmail);

    String nickDuplicate(String userNick);

    UserDetailDTO findById(Long userId);

    boolean findByUserEmail(UserLoginDTO userLoginDTO);

    Long findByUserId(String userEmail);

    String getKaKaoAccessToken(String code);

    String getUserInfo(String access_token);
}
