package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.pizza;
import com.repository.pizzaRepository;

@Service
public class pizzaService {

	@Autowired
	private pizzaRepository pizzarepository;
	
	
	public pizza addPizzaData(pizza data) {
		return this.pizzarepository.save(data);
	}
	
	public List<pizza> getPizzaData(){
		return this.pizzarepository.findAll();
	}

	public pizza getSinglePizzaData(String data) {
		// TODO Auto-generated method stub
		return this.pizzarepository.findByType(data);
		
	}
}
