package com.kakaopay.sprinkle.sampledata;

import com.kakaopay.sprinkle.model.entity.MoneySprinkle;
import com.kakaopay.sprinkle.model.entity.Room;
import com.kakaopay.sprinkle.model.entity.RoomUser;
import com.kakaopay.sprinkle.model.entity.User;
import com.kakaopay.sprinkle.model.network.Header;
import com.kakaopay.sprinkle.model.network.request.SprinkleApiRequest;
import com.kakaopay.sprinkle.repository.MoneySprinkleRepository;
import com.kakaopay.sprinkle.repository.RoomRepository;
import com.kakaopay.sprinkle.repository.RoomUserRepository;
import com.kakaopay.sprinkle.repository.UserRepository;
import com.kakaopay.sprinkle.utils.CommonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("Entity sample 생성")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EntitySampleDataTest {

    private static Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomUserRepository roomUserRepository;


    @Test
    @DisplayName("User 데이터 생성")
    public void userCreate(){
        for(int i = 31 ; i <= 40; i++){
            String password = generateToken()+random.nextInt(10)+1;
            User user = User.builder()
                    .name("TestUser"+i)
                    .password(password)
                    .createdAt(getNowDate())
                    .build();

            userRepository.save(user);
        }
    }


    @Test
    @DisplayName("Room 데이터 생성")
    public void roomCreate(){
        for(int i = 75 ; i < 80; i++){
            char tmp = (char)i;
            String ch =String.valueOf(tmp);
            String roomId = ch+ch+ch;
            Room room = Room.builder()
                    .id(roomId)
                    .name("TestRoom"+(i-64))
                    .createdAt(getNowDate())
                    .build();

            roomRepository.save(room);
        }
    }


    @Test
    @DisplayName("Room에 입장한 User 생성")
    public void roomUserCreate(){
        List<User> userList = userRepository.findAll();
        List<Room> roomList = roomRepository.findAll();
        int joinCnt = random.nextInt(10)+5;

        for(Room room : roomList){
            String roomId = room.getId();
            Set<Long> dblChkSet = new HashSet<Long>();
            for (int i = 0; i < joinCnt; i++) {
                int userIdx = random.nextInt(userList.size()-1);
                User user = userList.get(userIdx);
                long userId =user.getId();
                if(!dblChkSet.contains(userId)){
                    dblChkSet.add(userId);

                    String roomUserId = roomId+userId;
                    RoomUser roomUser = RoomUser.builder()
                            .id(roomUserId)
                            .room(room)
                            .user(user)
                            .joinAt(getNowDate())
                            .build();

                    roomUserRepository.save(roomUser);
                }
            }
        }
    }


    public Long getNowDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.parseLong(sdf.format(date));

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
