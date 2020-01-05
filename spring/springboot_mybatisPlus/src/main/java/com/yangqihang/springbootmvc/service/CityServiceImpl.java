package com.yangqihang.springbootmvc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangqihang.springbootmvc.entity.City;
import com.yangqihang.springbootmvc.mapper.CityMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
}
