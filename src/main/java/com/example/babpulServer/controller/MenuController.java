package com.example.babpulServer.controller;

import com.example.babpulServer.Entity.MenuEntity;
import com.example.babpulServer.Entity.RestaurantEntity;
import com.example.babpulServer.repository.RestaurantReository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class MenuController {
    final private RestaurantReository restaurantReository;




}
