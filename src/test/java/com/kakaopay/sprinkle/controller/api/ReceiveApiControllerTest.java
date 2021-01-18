package com.kakaopay.sprinkle.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.sprinkle.config.ObjectMapperConfig;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.response.ReceiveApiResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceiveApiController.class)        // WebMvc Test Annotation ( UserApiController 를 테스트)
@AutoConfigureMockMvc                       // MockMvc 자동 설정 Annotation
@Import(ObjectMapperConfig.class)
public class ReceiveApiControllerTest {

    @Autowired
    private MockMvc mockMvc;            // AutoConfigureMockMvc 으로 자동 주입된 mockMvc

    @MockBean
    private ReceiveApiLogicService receiveApiLogicService;


    @Test
    @DisplayName("받기 API 테스트")
    public void putTest() throws Exception {
        // given

        String token = "RU9";
        URI uri = UriComponentsBuilder.newInstance()
                .path("/money/receive/"+token)
                .build()
                .toUri();

        var roomId = "AAA";
        long userId = 4;
        List<String> tmpList = new ArrayList<String>();
        var mockRes = new Header<ReceiveApiResponse>();
        var resData = new ReceiveApiResponse();
        resData.setReceiveMoney(16666);
        mockRes.setData(resData);
        mockRes.setResultCode("OK");
        mockRes.setDescription("OK");
        mockRes.setTransactionTime(LocalDateTime.now());

        // userApiLogicService mocking
        given(receiveApiLogicService.update(token,roomId,userId)).willReturn(mockRes);

        // when
        mockMvc.perform(put(uri)  // put 로 테스트
                .header("X-ROOM-ID", roomId)
                .header("X-USER-ID", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result_code").value("OK")) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.description").value("OK")) // $.을 통하여 json 에 접근 가능
                .andExpect(jsonPath("$.data.receive_money").value(16666)) // $.을 통하여 json 에 접근 가능
                .andDo(print());
    }



}
