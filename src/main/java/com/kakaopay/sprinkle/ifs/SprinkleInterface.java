package com.kakaopay.sprinkle.ifs;


import com.kakaopay.sprinkle.model.network.Header;

public interface SprinkleInterface<Req,Res> {

    Header<Res> create(Header<Req> request, String xRoomId, long xUserId);
    Header<Res> read(String token, String xRoomId, long xUserId);
}
