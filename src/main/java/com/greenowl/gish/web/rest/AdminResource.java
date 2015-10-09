package com.greenowl.gish.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {

    private static final Logger LOG = LoggerFactory.getLogger(AdminResource.class);

    @RequestMapping(value = "/test",
            method = RequestMethod.GET, // <-- The HTTP Verb
            produces = MediaType.APPLICATION_JSON_VALUE) // <-- The return type
    public ResponseEntity<?> test() {
        // This is a test method to be by devs as an administration end point.
        return new ResponseEntity<>("THIS IS A TEST", HttpStatus.OK);
    }

}
