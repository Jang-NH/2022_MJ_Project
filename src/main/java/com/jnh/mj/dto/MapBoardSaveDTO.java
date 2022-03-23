package com.jnh.mj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapBoardSaveDTO {
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private long markerLatitude;
    private long markerLongitude;
    private String placeName;
    private String boardHits;
    private String saveTime;

}
