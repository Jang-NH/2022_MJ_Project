package com.jnh.mj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "board_heart_table")
public class MapHeartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_number")
    public Long id;

    // FK, MapBoard 게시글 Number
    @NotNull
    @Column
    private Long boardNumber;
}
