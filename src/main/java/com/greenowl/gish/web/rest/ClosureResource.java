package com.greenowl.gish.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClosureResource {
	//TODO change url later
	private final static String CURRENT_CLOSURE_URL="https://webservices.greenowlmobile.com/trafficAppCloud_ab/get_incidents";
	private final static String NEXT_CLOSURE_URL="https://webservices.greenowlmobile.com/trafficAppCloud_ab/get_incidents";
    private final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    @RequestMapping(value = "/closure",
            method = RequestMethod.GET, // <-- The HTTP Verb
            produces = MediaType.APPLICATION_JSON_VALUE) // <-- The return type
 
    public String currentClosure(@RequestParam(value="closure_type",required=false) String closureType,@RequestParam("city") String city,@RequestParam("zone") String zone) throws IOException{
        BufferedReader reader = null;
        try {
        	//Parse JSON? or not?
        	if (city.equals("toronto")&&zone.equals("eglinton-yonge_to_kennedy")){
        		LOG.info("Correct parameters");
        		URL url = new URL(CURRENT_CLOSURE_URL);
        		if (closureType!=null){
        			url = new URL(CURRENT_CLOSURE_URL+"?closure_type="+closureType);}
        		reader = new BufferedReader(new InputStreamReader(url.openStream()));
        		StringBuffer buffer = new StringBuffer();
        		int read;
        		char[] chars = new char[1024];
        		while ((read = reader.read(chars)) != -1)
        			buffer.append(chars, 0, read); 

        		return buffer.toString();}
        	else{
        		LOG.info("Wrong parameters "+city+" "+zone);
        		return null;
        	}
        }   
        finally {
            if (reader != null)
                reader.close();
        }
    }
    @RequestMapping(value = "/nextclosure",
            method = RequestMethod.GET, // <-- The HTTP Verb
            produces = MediaType.APPLICATION_JSON_VALUE) // <-- The return type

    public String nextdayClosure(@RequestParam(value="closure_type",required=false) String closureType,@RequestParam("city") String city,@RequestParam("zone") String zone) throws IOException {
        BufferedReader reader = null;
        try {
        	//Parse JSON? or not?
        	if (city.equals("toronto")&&zone.equals("eglinton-yonge_to_kennedy")){
        		LOG.info("Correct parameters");
        		URL url = new URL(NEXT_CLOSURE_URL);
        		if (closureType!=null){
        		 url = new URL(NEXT_CLOSURE_URL+"?closure_type="+closureType);}
        		reader = new BufferedReader(new InputStreamReader(url.openStream()));
        		StringBuffer buffer = new StringBuffer();
        		int read;
        		char[] chars = new char[1024];
        		while ((read = reader.read(chars)) != -1)
        			buffer.append(chars, 0, read); 
        		return buffer.toString();}
        	else{
        		return null;
        	}
        }finally {
    		LOG.info("Wrong parameters "+city+" "+zone);
            if (reader != null)
                reader.close();
        }
    }

}
