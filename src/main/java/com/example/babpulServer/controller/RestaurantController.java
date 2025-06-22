package com.example.babpulServer.controller;

import com.example.babpulServer.DTO.RestaurantDTO;
import com.example.babpulServer.DTO.RestaurantInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class RestaurantController {

    @PostMapping("/restaurant/list")
    public ResponseEntity<List<RestaurantDTO>> listRestaurant(){
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();


    }
}
