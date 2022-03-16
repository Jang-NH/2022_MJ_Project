package com.jnh.mj.entity;

import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.dto.UserUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    @Column(length = 20)
    private String userPassword;

    @NotNull
    @Column(length = 50, unique = true)
    private String userNickname;

    @Column(length = 200)
    private String userProfilename;

    @Column(length = 20)
    private String userMbti;

    // MapBoardEntity 와의 연관관계 (회원 : 게시글 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MapBoardEntity> mapBoardEntityList = new ArrayList<>();

    // BookmarkEntityList 와의 연관관계 (회원 : 찜 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookmarkEntity> bookmarkEntityList = new ArrayList<>();

    // PhotoBoardEntity 와의 연관관계 (회원 : 게시글 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhotoBoardEntity> photoBoardEntityList = new ArrayList<>();

    // HeartEntity 와의 연관관계 (회원 : 좋아요 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HeartEntity> heartEntityList = new ArrayList<>();

    // CommentEntity 와의 연관관계 (회원 : 댓글 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    // CommentReplyEntity 와의 연관관계 (회원 : 답글 = 1:n)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentReplyEntity> commentReplyEntityList = new ArrayList<>();


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
