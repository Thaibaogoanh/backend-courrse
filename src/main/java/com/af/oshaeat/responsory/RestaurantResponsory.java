package com.af.oshaeat.responsory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RestaurantResponsory extends JpaRepository<Restaurant, Integer> {

}
