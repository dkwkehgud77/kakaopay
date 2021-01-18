package com.kakaopay.sprinkle.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.sprinkle.config.ObjectMapperConfig;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.SprinkleApiRequest;
import com.kakaopay.sprinkle.model.network.response.ReceiveApiResponse;
import com.kakaopay.sprinkle.model.network.response.SprinkleApiResponse;
import com.kakaopay.sprinkle.service.ReceiveApiLogicService;
import com.kakaopay.sprinkle.service.SprinkleApiLogicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SprinkleApiController.class)        // WebMvc Test Annotation ( UserApiController 를 테스트)
@AutoConfigureMockMvc                       // MockMvc 자동 설정 Annotation
@Import(ObjectMapperConfig.class)
public class SprinkleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;            // AutoConfigureMockMvc 으로 자동 주입된 mockMvc

    @MockBean
    private SprinkleApiLogicService sprinkleApiLogicService;

    @MockBean
    private ReceiveApiLogicService receiveApiLogicService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("뿌리기 API 테스트")
    public void postTest() throws Exception {
        // given
        URI uri = UriComponentsBuilder.newInstance()
                .path("/money/sprinkle")
                .build()
                .toUri();

        var roomId = "AAA";
        long userId = 3;

        var mockReq = new Header<SprinkleApiRequest>();
        var data = new SprinkleApiRequest();
        data.setSprinkleMoney(50000);
        data.setReceiveCount(3);
        mockReq.setData(data);

        var mockRes = new Header<SprinkleApiResponse>();
        mockRes.setResultCode("OK");
        mockRes.setDescription("OK");
        mockRes.setTransactionTime(LocalDateTime.now());

        // userApiLogicService mocking
        given(sprinkleApiLogicService.create(mockReq,roomId,userId)).willReturn(mockRes);

        // when
        mockMvc.perform(post(uri)  // post 로 테스트
                .content(objectMapper.writeValueAsString(mockReq))
                .header("X-ROOM-ID", roomId)
                .header("X-USER-ID", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result_code").value("OK")) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.description").value("OK")) // $.을 통하여 json 에 접근 가능
                .andDo(print());
    }





    @Test
    @DisplayName("조회 API 테스트")
    public void getTest() throws Exception {
        // given

        String token = "RU9";
        URI uri = UriComponentsBuilder.newInstance()
                .path("/money/sprinkle/"+token)
                .build()
                .toUri();

        var roomId = "AAA";
        long userId = 3;
        List<String> tmpList = new ArrayList<String>();
        var mockRes = new Header<SprinkleApiResponse>();
        var resData = new SprinkleApiResponse();
        resData.setSprinkleMoney(50000);
        resData.setReceiveMoney(0);
        resData.setReceiveList(tmpList);
        mockRes.setData(resData);
        mockRes.setResultCode("OK");
        mockRes.setDescription("OK");
        mockRes.setTransactionTime(LocalDateTime.now());

        // userApiLogicService mocking
        given(sprinkleApiLogicService.read(token,roomId,userId)).willReturn(mockRes);

        // when
        mockMvc.perform(get(uri)  // post 로 테스트
                .header("X-ROOM-ID", roomId)
                .header("X-USER-ID", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result_code").value("OK")) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.description").value("OK")) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.data.sprinkle_money").value(50000)) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.data.receive_money").value(0)) // $.을 통하여 json 에 접근 가능
                .andDo(print());
    }



}
