package com.jnh.mj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "photo_table")
public class PhotoBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    // UserEntity 와의 연관관계 (게시글 : 회원 = n:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    // CommentEntity 와의 연관관계 (게시글 : 댓글 = n:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;

    // UserEntity 와의 연관관계 (게시글 : 답글 = n:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private CommentReplyEntity commentReplyEntity;

    @Column(length = 50)
    private String photoWriter;

    @Column(length = 200)
    private String photoFilename;

    @Column(length = 2500)
    private String photoContents;

    @Column(columnDefinition = "integer default 0")
    private int photoHits;

    @Column(columnDefinition = "integer default 0")
    private int photoHeart;

    // HeartEntity 와의 연관관계 (게시글 : 좋아요 = 1:n)
    @OneToMany(mappedBy = "photoBoardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HeartEntity> heartEntityList = new ArrayList<>();

}
