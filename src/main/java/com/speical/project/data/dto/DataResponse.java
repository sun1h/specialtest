package com.speical.project.data.dto;

import com.speical.project.data.entity.DataEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DataResponse {

    private Long dataId;
    private int status_1;
    private int status_2;
    private int status_3;
    private int money;
    private int winCount;
    private int loseCount;


    public static DataResponse toDto(DataEntity data){

        return DataResponse.builder()
                .dataId(data.getId())
                .status_1(data.getStatus_1())
                .status_2(data.getStatus_2())
                .status_3(data.getStatus_3())
                .money(data.getMoney())
                .winCount(data.getWinCount())
                .loseCount(data.getLoseCount())
                .build();

    }
}
