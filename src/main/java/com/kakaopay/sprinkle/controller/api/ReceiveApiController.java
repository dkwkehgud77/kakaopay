package com.kakaopay.sprinkle.controller.api;

import com.kakaopay.sprinkle.ifs.ReceiveInterface;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.ReceiveApiRequest;
import com.kakaopay.sprinkle.model.network.response.ReceiveApiResponse;
import com.kakaopay.sprinkle.service.ReceiveApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/money/receive")
@RequiredArgsConstructor
public class ReceiveApiController implements ReceiveInterface<ReceiveApiRequest, ReceiveApiResponse> {

    private final ReceiveApiLogicService receiveApiLogicService;

    @Override
    @PutMapping("{token}")
    public Header<ReceiveApiResponse> update(@PathVariable(name = "token") String token, @RequestHeader(required = true,value="X-ROOM-ID") String xRoomId, @RequestHeader(required = true,value="X-USER-ID") long xUserId) {
        log.info("read id : {}",token);


        return receiveApiLogicService.update(token,xRoomId,xUserId);
    }
}

