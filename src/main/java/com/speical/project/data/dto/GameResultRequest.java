package com.speical.project.data.dto;


import com.speical.project.data.entity.DataEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class GameResultRequest {

    private Long userId;
    private boolean win;


    public DataEntity toDataEntity(DataEntity data){

        return DataEntity.builder()
                .id(data.getId())
                .user(data.getUser())
                .winCount(win ? data.getWinCount() + 1 : data.getWinCount())
                .loseCount(win ? data.getLoseCount() : data.getLoseCount() + 1)
                .build();

    }

}
