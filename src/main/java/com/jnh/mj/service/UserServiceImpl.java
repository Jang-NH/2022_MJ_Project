package com.jnh.mj.service;

import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.entity.UserEntity;
import com.jnh.mj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository ur;

    @Override
    public long save(UserSaveDTO userSaveDTO) {
        UserEntity userEntity = UserEntity.toSaveUser(userSaveDTO);
        return ur.save(userEntity).getId();
    }
}
