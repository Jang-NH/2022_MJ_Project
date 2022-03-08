package com.jnh.mj.service;

import com.jnh.mj.dto.UserDetailDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.dto.UserUpdateDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    long save(UserSaveDTO userSaveDTO);

    String emailDuplicate(String userEmail);

    String nickDuplicate(String userNick);

    UserDetailDTO findById(Long userId);

    boolean findByUserEmail(UserLoginDTO userLoginDTO);

    Long findByUserId(String userEmail);

    String getKaKaoAccessToken(String code);

    HashMap<String, Object> getUserInfo(String access_token);

    void update(UserUpdateDTO userUpdateDTO) throws IllegalStateException, IOException;

    void delete(Long userId);

    List<UserDetailDTO> findAll();
}
