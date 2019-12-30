package com.yangqihang.springbootmvc.dao;

import com.yangqihang.springbootmvc.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainDao extends JpaRepository<City, Integer> {
}
