package com.jnh.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapBoardSaveDTO {
    private long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String placeName;
    private long markerLatitude;
    private long markerLongitude;
    private String boardHits;
    private String saveTime;

}
