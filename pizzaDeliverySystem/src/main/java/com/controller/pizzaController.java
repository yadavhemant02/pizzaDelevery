package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.pizza;
import com.service.pizzaService;

@RestController
public class pizzaController {

	@Autowired
	private pizzaService pizzaservice;

	@PostMapping("/addpizza")
	public pizza addpiza(@RequestBody pizza data) {
		return this.pizzaservice.addPizzaData(data);
	}

	@GetMapping("/pizza")
	public ResponseEntity<?> getAllPizza() {
		try {
			List<pizza> allPizza = this.pizzaservice.getPizzaData();
			return new ResponseEntity<List<pizza>>(allPizza, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Bad Request" + e, HttpStatus.BAD_REQUEST);
		}
	}
}
