package com.jnh.mj.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "map_table")
public class MapBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    public Long id;

    // UserEntity 와의 연관관계 (게시글 : 회원 = n:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(length = 50)
    private String boardWriter;

    @Column(length = 50)
    private String boardTitle;

    @Column(length = 2500)
    private String boardContents;

    @Column(columnDefinition = "integer default 0")
    private int boardHits;

    @Column(columnDefinition = "integer default 0")
    private int boardBookmark;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateTime;

    // BookmarkEntityList 와의 연관관계 (게시글 : 찜 = 1:n)
    @OneToMany(mappedBy = "mapBoardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookmarkEntity> bookmarkEntityList = new ArrayList<>();

    // MarkerEntity 와의 연관관계 (게시글 : 마커 = 1:n)
    @OneToMany(mappedBy = "mapBoardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MarkerEntity> markerEntityList = new ArrayList<>();
}
