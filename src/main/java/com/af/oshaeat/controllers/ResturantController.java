package com.af.oshaeat.controllers;

import com.af.oshaeat.Service.FileService;
import com.af.oshaeat.Service.RestaurantService;
import com.af.oshaeat.Service.imp.FileServiceImp;
import com.af.oshaeat.payload.ResponseData;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resturant")
public class ResturantController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<?> createResturant(@RequestParam MultipartFile file, @RequestParam String title ,
    @RequestParam String subtitle ,
    @RequestParam String description ,
    @RequestParam boolean is_freeship ,
    @RequestParam String address ,
    @RequestParam String open_date ) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = restaurantService.insertRestaurant(file, title, subtitle, description, is_freeship, address, open_date);
        responseData.setSuccess(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getResturant() {
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantService.getRestaurants());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileResturant(@PathVariable String filename) {
       Resource resource = fileServiceImp.loadFile(filename);

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
