package com.example.babpulServer.controller;


import com.example.babpulServer.DTO.MenuDTO;
import com.example.babpulServer.DTO.RestaurantDTO;
import com.example.babpulServer.repository.MenuRepository;
import com.example.babpulServer.repository.RestaurantReository;
import com.example.babpulServer.service.MenuService;
import com.example.babpulServer.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@CrossOrigin
public class RestaurantController {
    final private RestaurantService restaurantService;
    final private MenuService menuService;
    private final RestaurantReository restaurantReository;


    @GetMapping("/restaurant/list")
    public List<RestaurantDTO> getRestaurantList(){
        List<RestaurantDTO> restaurantDTOList = restaurantService.getAllRestaurantInfo();
        return restaurantDTOList;
    }

    @GetMapping("restaurant/menu")
    public List<MenuDTO> getMenuList(Long restaurantKey){
        List<MenuDTO> menuDTOList = menuService.getMenus(restaurantKey);
        return menuDTOList;
    }
}
