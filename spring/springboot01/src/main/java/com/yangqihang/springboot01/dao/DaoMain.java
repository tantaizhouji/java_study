package com.yangqihang.springboot01.dao;

import com.yangqihang.springboot01.entity.City;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DaoMain {

    private static List<City> cityList = Collections.synchronizedList(new ArrayList<>());

    public List<City> findList() {
        System.out.println("找到列表");
        return cityList;
    }

    public String add(City city) {
        String str = null;

        for (City c : cityList) {
            if (city.getId() == c.getId()) {
                str = "已存在该Id，无法添加相同Id的城市";
                System.out.println(str);
                return str;
            }
        }

        cityList.add(city);
        str = "城市添加成功";
        System.out.println(str);
        return str;
    }

    public void deleteById(Integer id) {
        Iterator<City> iterator = cityList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                System.out.println("删除成功");
                iterator.remove();
                break;
            }
        }
    }
}
