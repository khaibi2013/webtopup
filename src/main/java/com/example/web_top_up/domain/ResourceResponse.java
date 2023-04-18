package com.example.web_top_up.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResourceResponse<T> extends ResponseEntity<T> {
    public ResourceResponse(T body) {
        super(body, HttpStatus.OK);
    }


//    public ResourceResponse(void like) {
//        super(like, HttpStatus.OK);
//    }
}
