package com.af.oshaeat.Service.imp;

import com.af.oshaeat.dto.RestaurantDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean insertRestaurant(MultipartFile file, String title ,
                              String subtitle ,
                              String description ,
                              boolean is_freeship ,
                              String address ,
                              String open_date);

    List<RestaurantDTO> getRestaurants();
}
