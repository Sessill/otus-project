//package ru.homework.otusproject.utils.rest.resttemplate;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//
//@Component
//public class ClaimRestBuilder {
//    private static final String baseUrl = "http://localhost:8765/planner-users/user/";
//
//    public boolean getDamages(){
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<List> request = new HttpEntity();
//
//        ResponseEntity<List> response = null;
//        try {
//
//            response = restTemplate.exchange(baseUrl+"/id", HttpMethod.POST, request, List.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return true;
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return false;
//
//    }
//}
