package com.jnh.mj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "marker_table")
public class MarkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marker_id")
    public Long id;

    // MapBoardEntity 와의 연관관계 (마커 : 게시글 = n:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private MapBoardEntity mapBoardEntity;

    // 위도
    @Column
    private Long markerLatitude;

    // 경도
    @Column
    private Long markerLongitude;

    // 장소 이름
    @Column
    private String placeName;

}
