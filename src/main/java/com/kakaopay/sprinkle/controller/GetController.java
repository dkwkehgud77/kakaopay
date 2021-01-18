package com.kakaopay.sprinkle.controller;

import com.kakaopay.sprinkle.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")    // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        System.out.println("id : "+id);
        System.out.println("pwd : "+pwd);

        return id+pwd;
    }


    @GetMapping("/header")
    public Header getHeader(){

        // {"resultCode: OK", description :"OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
