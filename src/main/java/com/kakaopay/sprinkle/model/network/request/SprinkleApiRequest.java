package com.kakaopay.sprinkle.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprinkleApiRequest {

    private long sprinkleMoney;
    private int receiveCount;



}
