package com.kakaopay.sprinkle.service;

import com.kakaopay.sprinkle.ifs.SprinkleInterface;
import com.kakaopay.sprinkle.model.entity.MoneyReceive;
import com.kakaopay.sprinkle.model.entity.MoneySprinkle;
import com.kakaopay.sprinkle.model.entity.RoomUser;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.SprinkleApiRequest;
import com.kakaopay.sprinkle.model.network.response.SprinkleApiResponse;
import com.kakaopay.sprinkle.repository.MoneyReceiveRepository;
import com.kakaopay.sprinkle.repository.MoneySprinkleRepository;
import com.kakaopay.sprinkle.repository.RoomUserRepository;
import com.kakaopay.sprinkle.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprinkleApiLogicService implements SprinkleInterface<SprinkleApiRequest, SprinkleApiResponse> {

    private final MoneySprinkleRepository moneySprinkleRepository;

    private final MoneyReceiveRepository moneyReceiveRepository;

    private final RoomUserRepository roomUserRepository;

    private final CommonUtils comUtils;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<SprinkleApiResponse> create(Header<SprinkleApiRequest> request,String xRoomId, long xUserId) {

        Optional<RoomUser> sprinkleUser = roomUserRepository.findAllByRoomIdAndUserId(xRoomId,xUserId);

        if (sprinkleUser.isPresent()) {
            // 1. request data
            SprinkleApiRequest sprinkleApiRequest = request.getData();
            String token = generateToken();

            Optional<MoneySprinkle> isToken = moneySprinkleRepository.findByTokenAndSprinkleUserIdStartsWith(token,xRoomId);
            while(isToken.isPresent()){
                token = generateToken();
                isToken = moneySprinkleRepository.findByTokenAndSprinkleUserIdStartsWith(token,xRoomId);
            }

            // 2. MoneySprinkle 생성  - 뿌리기
            MoneySprinkle moneySprinkle = MoneySprinkle.builder()
                    .sprinkleUser(sprinkleUser.get())
                    .token(token)
                    .sprinkleMoney(sprinkleApiRequest.getSprinkleMoney())
                    .receiveCount(sprinkleApiRequest.getReceiveCount())
                    .sprinkleAt(comUtils.getNowDate())
                    .expireAt(comUtils.getExpireDay(7))
                    .build();
            MoneySprinkle newMoneySprinkle = moneySprinkleRepository.save(moneySprinkle);


            // 3. MoneyReceive 생성 - 받기 분배
            long sprinkleMoney = sprinkleApiRequest.getSprinkleMoney();
            int receiveCount = sprinkleApiRequest.getReceiveCount();

            if( (receiveCount >= sprinkleMoney) || receiveCount <= 0){
                receiveCount = 1;
            }

            long receiveMoneyPerOne = Math.round(sprinkleMoney/receiveCount);
            for (int i = 0; i < receiveCount; i++) {
                long receiveMoney = receiveMoneyPerOne;
                if(i == receiveCount-1){
                    receiveMoney = sprinkleMoney;
                }

                MoneyReceive moneyReceive = MoneyReceive.builder()
                        .moneySprinkle(newMoneySprinkle)
                        .receiveMoney(receiveMoney)
                        .receiveStatus("N")
                        .receiveUser(null)
                        .receiveAt(0)
                        .expireAt(comUtils.getExpireMinute(10))
                        .build();
                moneyReceiveRepository.save(moneyReceive);

                sprinkleMoney -= receiveMoneyPerOne;
            }


            return response(newMoneySprinkle);
        } else {
            return Header.ERROR("뿌리기 하는 사용자가 존재하지 않습니다.");
        }
    }



    @Override
    public Header<SprinkleApiResponse> read(String token,String xRoomId, long xUserId) {
        Optional<RoomUser> sprinkleUser = roomUserRepository.findAllByRoomIdAndUserId(xRoomId,xUserId);

        if (sprinkleUser.isPresent()) {
            String sprinkleUserId = sprinkleUser.get().getId();
            Optional<MoneySprinkle>  optional = moneySprinkleRepository.findByTokenAndSprinkleUserId(token,sprinkleUserId);

            if(optional.isPresent()){
                MoneySprinkle moneySprinkle = optional.get();
                long nowDate = comUtils.getNowDate();
                if(moneySprinkle.getExpireAt() < nowDate){
                    return Header.ERROR("뿌린 건에 대한 조회는 7일 동안 할 수 있습니다.");
                }

                long moneySprinkleSeq = moneySprinkle.getSeq();
                List<MoneyReceive> moneyReceiveList = moneyReceiveRepository.findByMoneySprinkleSeqAndReceiveStatus(moneySprinkleSeq,"Y");
                List<String> receiveList = new ArrayList<String>();
                long receiveMoney = 0;
                for(MoneyReceive m : moneyReceiveList) {
                    receiveList.add(m.getReceiveUser().toString());
                    receiveMoney += m.getReceiveMoney();
                }

                return response(moneySprinkle,receiveList,receiveMoney);

            }else{
                return Header.ERROR("token값에 해당하는 데이터가 없습니다.");
            }

        }else{
            return Header.ERROR("뿌린 사용자가 존재하지 않습니다.");
        }

    }

    private Header<SprinkleApiResponse> response(MoneySprinkle moneySprinkle){
        SprinkleApiResponse sprinkleApiResponse = SprinkleApiResponse.builder()
                .sprinkleMoney(moneySprinkle.getSprinkleMoney())
                .sprinkleAt(moneySprinkle.getSprinkleAt())
                .token(moneySprinkle.getToken())
                .build();

        return Header.OK(sprinkleApiResponse);
    }

    private Header<SprinkleApiResponse> response(MoneySprinkle moneySprinkle,List<String> receiveList,long receiveMoney){

        SprinkleApiResponse sprinkleApiResponse = SprinkleApiResponse.builder()
                .sprinkleMoney(moneySprinkle.getSprinkleMoney())
                .sprinkleAt(moneySprinkle.getSprinkleAt())
                .receiveList(receiveList)
                .receiveMoney(receiveMoney)
                .build();

        // Header + data return
        //return Header.OK(SprinkleApiResponse);
        return Header.OK(sprinkleApiResponse);
    }



    private String generateToken() {
        char[] tmp = new char[3];
        for(int i=0; i<tmp.length; i++) {
            int div = (int) Math.floor( Math.random() * 2 );

            if(div == 0) { // 0이면 숫자로
                tmp[i] = (char) (Math.random() * 10 + '0') ;
            }else { //1이면 알파벳
                tmp[i] = (char) (Math.random() * 26 + 'A') ;
            }
        }

        String token = new String(tmp);



        return token;
    }
}
