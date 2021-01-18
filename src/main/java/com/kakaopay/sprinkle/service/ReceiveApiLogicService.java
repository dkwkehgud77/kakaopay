package com.kakaopay.sprinkle.service;

import com.kakaopay.sprinkle.ifs.ReceiveInterface;
import com.kakaopay.sprinkle.model.entity.MoneyReceive;
import com.kakaopay.sprinkle.model.entity.MoneySprinkle;
import com.kakaopay.sprinkle.model.entity.RoomUser;
import com.kakaopay.sprinkle.model.entity.User;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.ReceiveApiRequest;
import com.kakaopay.sprinkle.model.network.request.SprinkleApiRequest;
import com.kakaopay.sprinkle.model.network.response.ReceiveApiResponse;
import com.kakaopay.sprinkle.model.network.response.SprinkleApiResponse;
import com.kakaopay.sprinkle.repository.MoneyReceiveRepository;
import com.kakaopay.sprinkle.repository.MoneySprinkleRepository;
import com.kakaopay.sprinkle.repository.RoomUserRepository;
import com.kakaopay.sprinkle.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiveApiLogicService implements ReceiveInterface<ReceiveApiRequest, ReceiveApiResponse> {

    private final MoneySprinkleRepository moneySprinkleRepository;

    private final MoneyReceiveRepository moneyReceiveRepository;

    private final RoomUserRepository roomUserRepository;


    private final CommonUtils comUtils;

    @Override
    public Header<ReceiveApiResponse> update(String token, String xRoomId, long xUserId) {
        Optional<RoomUser> roomUser = roomUserRepository.findAllByRoomIdAndUserId(xRoomId,xUserId);
        if(roomUser.isEmpty()){
            return Header.ERROR("동일한 대화방에 속한 사용자가 아닙니다.");
        }

        Optional<MoneySprinkle> moneySprinkle = moneySprinkleRepository.findByTokenAndSprinkleUserIdStartsWith(token,xRoomId);
        if (moneySprinkle.isPresent()) {

                String xReceiveUser = xRoomId+xUserId;
                String sprinkleUserId = moneySprinkle.get().getSprinkleUser().getId();
                if(sprinkleUserId.equals(xReceiveUser)) {
                    return Header.ERROR("자신이 뿌리기 한 건은 자신이 받을 수 없습니다.");
                }

                long moneySprinkleSeq = moneySprinkle.get().getSeq();
                long nowDate = comUtils.getNowDate();
                List<MoneyReceive> yList = moneyReceiveRepository.findByMoneySprinkleSeqAndReceiveStatus(moneySprinkleSeq,"Y");
                List<String> receiveList = new ArrayList<String>();
                for(MoneyReceive m : yList) {
                    receiveList.add(m.getReceiveUser());
                }
                if(receiveList.contains(xReceiveUser)) {
                    return Header.ERROR("뿌리기 당 한 사용자는 한번만 받을 수 있습니다.");
                }

                List<MoneyReceive> nlist = moneyReceiveRepository.findByMoneySprinkleSeqAndReceiveStatus(moneySprinkleSeq,"N");
                if(nlist.size() > 0) {
                    MoneyReceive moneyReceive = nlist.get(0);
                    if(moneyReceive.getExpireAt() < nowDate){
                        return Header.ERROR("뿌린지 10분이 지난 요청입니다.");
                    }else {
                        // 2. MoneyReceive 생성  - 받기
                        moneyReceive.setReceiveStatus("Y");
                        moneyReceive.setReceiveUser(xReceiveUser);
                        moneyReceive.setReceiveAt(comUtils.getNowDate());
                        MoneyReceive newMoneyReceive = moneyReceiveRepository.save(moneyReceive);

                        return response(newMoneyReceive);
                    }
                }else{
                    return Header.ERROR("뿌리기한 금액이 모두 받아졌습니다.");
                }

            }else {
              return Header.ERROR("token값에 해당하는 데이터가 없습니다.");
          }
    }

    private Header<ReceiveApiResponse> response(MoneyReceive moneySprinkle){

        ReceiveApiResponse receiveApiResponse = ReceiveApiResponse.builder()
                .receiveMoney(moneySprinkle.getReceiveMoney())
                .receiveeAt(moneySprinkle.getReceiveAt())
                .build();

        return Header.OK(receiveApiResponse);
    }

    private boolean isNumber(String str_num) {
        try {
            long str = Long.parseLong(str_num);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}

