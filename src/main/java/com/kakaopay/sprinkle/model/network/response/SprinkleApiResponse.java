package com.kakaopay.sprinkle.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprinkleApiResponse {

    private long sprinkleMoney;
    private long sprinkleAt;

    private String token;

    private List<String> receiveList;
    private long receiveMoney;

}
