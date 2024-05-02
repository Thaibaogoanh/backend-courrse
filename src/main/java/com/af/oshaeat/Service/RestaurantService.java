package com.af.oshaeat.Service;

import com.af.oshaeat.Service.imp.FileServiceImp;
import com.af.oshaeat.Service.imp.RestaurantServiceImp;
import com.af.oshaeat.dto.RestaurantDTO;
import com.af.oshaeat.responsory.RestaurantResponsory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantResponsory restaurantResponsory;

    @Autowired
    FileServiceImp fileServiceImp;
    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean is_freeship, String address, String open_date) {
        boolean isInserted = false;
        try {
            boolean isSuccess = fileServiceImp.saveFile(file);
            if (isSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm");
                Date opendate = simpleDateFormat.parse(open_date);
                restaurant.setCreateDate(opendate);

                restaurantResponsory.save(restaurant);

                isInserted = true;
            }
        }catch (Exception e){
            System.out.println("Error in insertRestaurant" + e.getMessage());
        }

        return isInserted;
    }

    @Override
    public List<RestaurantDTO> getRestaurants() {
        List<RestaurantDTO> ListRestaurantDTO = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listPage = restaurantResponsory.findAll(pageRequest);

        for (Restaurant restaurant : listPage) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(restaurant.getImage());
            restaurantDTO.setTitle(restaurant.getTitle());
            restaurantDTO.setSubtitle(restaurant.getSubtitle());
            restaurantDTO.setFreeShip(restaurant.isFreeship());
            restaurantDTO.setRating(caculationRating(restaurant.getListRatingRestaurant()));
            ListRestaurantDTO.add(restaurantDTO);

        }
        return ListRestaurantDTO;
    }
    private double caculationRating(Set<RatingRestaurant> listRating) {
        double totalRating = 0;
        for (RatingRestaurant data : listRating){
            totalRating = data.getRatePoint();
        }
        return totalRating/listRating.size();
    }
}
