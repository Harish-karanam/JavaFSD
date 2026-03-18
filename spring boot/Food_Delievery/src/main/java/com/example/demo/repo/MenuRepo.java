package com.example.demo.repo;

import com.example.demo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer> {
	//jpa has methods like save find all but does no how to filter 

    @Query("SELECT m FROM Menu m WHERE m.restaurant.resId = :restaurantId")
    List<Menu> findByRestaurantId(@Param("restaurantId") int restaurantId);
}

