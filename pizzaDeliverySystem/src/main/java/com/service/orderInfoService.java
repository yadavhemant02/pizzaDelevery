package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.orderInfo;
import com.repository.orderInfoRepository;

@Service
public class orderInfoService {

	@Autowired
    private orderInfoRepository orderInforepository;
	
	
	public orderInfo addorderInfoData(orderInfo data) {
		return this.orderInforepository.save(data);
	}
	
	public List<orderInfo> getorderInfoData(){
		return this.orderInforepository.findAll();
	}
	
	public orderInfo getSingleorderInfodata(int id) {
		return this.orderInforepository.findByOrderid(id);
	}
	
	public String deleteorderInfo(int id) {
		this.orderInforepository.deleteById(id);
		return "delete successfuly";
	}
  
}
