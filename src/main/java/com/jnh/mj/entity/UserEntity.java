package com.jnh.mj.entity;

import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.dto.UserUpdateDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(length = 50, unique = true)
    private String userEmail;

    @NotNull
    @Column(length = 50)
    private String userPassword;

    @Column(length = 200)
    private String userProfilename;

    @NotNull
    @Column(length = 50, unique = true)
    private String userNickname;

    @Column(length = 20)
    private String userMbti;

    public static UserEntity toSaveUser(UserSaveDTO userSaveDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserEmail(userSaveDTO.getUserEmail());
        userEntity.setUserPassword(userSaveDTO.getUserPassword());
        userEntity.setUserNickname(userSaveDTO.getUserNickname());

        return userEntity;
    }


    public static UserEntity toUpdateUser(UserUpdateDTO userUpdateDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userUpdateDTO.getUserId());
        userEntity.setUserEmail(userUpdateDTO.getUserEmail());
        userEntity.setUserPassword(userUpdateDTO.getUserPassword());
        userEntity.setUserNickname(userUpdateDTO.getUserNickname());
        userEntity.setUserProfilename(userUpdateDTO.getUserProfilename());

        return userEntity;
    }
}
