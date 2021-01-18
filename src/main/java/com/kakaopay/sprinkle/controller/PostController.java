package com.kakaopay.sprinkle.controller;

import com.kakaopay.sprinkle.model.entity.RoomUser;
import com.kakaopay.sprinkle.model.entity.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PostController {

    // HTML <Form>
    // ajax 검색
    // http post body -> data
    //  json, xml, multipart-form / text-plain

    @PostMapping(value = "/postMethod")
    @ResponseBody
    public RoomUser postMethod(@RequestBody RoomUser roomUser){
        return roomUser;
    }


}
