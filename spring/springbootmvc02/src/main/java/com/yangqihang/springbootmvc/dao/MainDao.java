package com.yangqihang.springbootmvc.dao;

import com.yangqihang.springbootmvc.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainDao extends JpaRepository<Account,Integer> {
}
