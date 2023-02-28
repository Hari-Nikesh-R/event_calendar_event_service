package com.dosmart.event_calendar_service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.dosmart.event_calendar_service.utils.Constants.AUTHORIZATION;
import static com.dosmart.event_calendar_service.utils.Urls.AUTHENTICATION_URL;
import static com.dosmart.event_calendar_service.utils.Urls.GET_EMAIL;


public class TokenValidator {

    @Autowired
    RestTemplate restTemplate;
    private TokenValidator(){}
    private static TokenValidator instance;
    public static TokenValidator getInstance()
    {
        if(instance==null)
        {
            instance = new TokenValidator();
        }
        return instance;
    }

//    public AdminDetail getUserById(String token, Integer id) throws Exception {
//        HttpEntity<String> entity = setTokenInHeaders(token);
//        BaseResponse<AdminDetail> baseResponse = restTemplate.exchange(AUTHENTICATION_URL + FETCH_USER + id,HttpMethod.GET,entity,new ParameterizedTypeReference<BaseResponse<AdminDetails>>() {}).getBody();
//        if(Objects.nonNull(baseResponse.getValue())) {
//            return baseResponse.getValue();
//        }
//        throw new Exception("User Not Found");
//    }

    public String validateByToken(String token)
    {
        HttpEntity<String> entity = setTokenInHeaders(token);
        return restTemplate.exchange(AUTHENTICATION_URL + GET_EMAIL, HttpMethod.GET,entity,String.class).getBody();
    }
    private HttpEntity<String> setTokenInHeaders(String token){
        HttpHeaders httpHeaders = getHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        return new HttpEntity<>(httpHeaders);
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
