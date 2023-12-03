package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.orderInfo;

public interface orderInfoRepository extends JpaRepository<orderInfo, Integer>{

	orderInfo findByOrderid(int id);

}
