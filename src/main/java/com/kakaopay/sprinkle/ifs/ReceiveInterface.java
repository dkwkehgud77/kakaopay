package com.kakaopay.sprinkle.ifs;


import com.kakaopay.sprinkle.model.network.Header;

import javax.servlet.http.HttpSession;

public interface ReceiveInterface<Req,Res> {

    Header<Res> update(String token, String xRoomId, long xUserId );
}
