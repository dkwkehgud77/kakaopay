package com.kakaopay.sprinkle.controller.api;

import com.kakaopay.sprinkle.ifs.SprinkleInterface;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.SprinkleApiRequest;
import com.kakaopay.sprinkle.model.network.response.SprinkleApiResponse;
import com.kakaopay.sprinkle.service.SprinkleApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/money/sprinkle")
@RequiredArgsConstructor
public class SprinkleApiController implements SprinkleInterface<SprinkleApiRequest, SprinkleApiResponse> {

    private final SprinkleApiLogicService sprinkleApiLogicService;

    @Override
    @PostMapping("")    // /money/sprinkle
    public Header<SprinkleApiResponse> create(@RequestBody Header<SprinkleApiRequest> request,@RequestHeader(value="X-ROOM-ID") String xRoomId,@RequestHeader(value="X-USER-ID") long xUserId) {
        log.info("{}",request);
        return sprinkleApiLogicService.create(request,xRoomId,xUserId);
    }

    @Override
    @GetMapping("{token}") // /money/sprinkle/{token}
    public Header<SprinkleApiResponse> read(@PathVariable(name = "token") String token,@RequestHeader(value="X-ROOM-ID") String xRoomId,@RequestHeader(value="X-USER-ID") long xUserId) {
        log.info("read id : {}",token);
        return sprinkleApiLogicService.read(token,xRoomId,xUserId);
    }

}

