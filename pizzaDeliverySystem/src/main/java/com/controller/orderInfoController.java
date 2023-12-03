package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helper.Helper;
import com.model.orderInfo;
import com.model.pizza;
import com.model.user;
import com.service.orderInfoService;
import com.service.pizzaService;
import com.service.userService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class orderInfoController {

	@Autowired
	private orderInfoService orderinfoservice;

	@Autowired
	private Helper jwtHelper;

	@Autowired
	private userService userservice;

	@Autowired
	private pizzaService pizzaservice;

	@PostMapping("order")
	public ResponseEntity<?> addorderInfo(@RequestBody orderInfo data, HttpServletRequest request) {

		String requestHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			// looking good
			token = requestHeader.substring(7);
			try {
				username = this.jwtHelper.getUsernameFromToken(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		user userdata = this.userservice.getUserData(username);

		data.setUserid(userdata.getId());
		if(data.getAddress()==null) {	
			data.setAddress(userdata.getAddress());
		}

		pizza pizzadata = this.pizzaservice.getSinglePizzaData(data.getPizzatype());

		if (pizzadata == null) {
			return new ResponseEntity<String>("invalid pizaa type", HttpStatus.BAD_REQUEST);
		}

		data.setTotalprice((data.getQuntity() * pizzadata.getPrice()));
		data.setStatus("successfuly");

		orderInfo ff = this.orderinfoservice.addorderInfoData(data);
		return new ResponseEntity<orderInfo>(ff, HttpStatus.OK);
	}

	@GetMapping("/order")
	public ResponseEntity<?> getorderInfo() {

		try {
			List<orderInfo> orderInfodata = this.orderinfoservice.getorderInfoData();
			return new ResponseEntity<List<orderInfo>>(orderInfodata, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("bad request", HttpStatus.BAD_REQUEST);
		}
		// return this.orderinfoservice.getorderInfoData();
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<?> getsingledata(@PathVariable("id") int id) {

		try {
			orderInfo orderdata = this.orderinfoservice.getSingleorderInfodata(id);
			return new ResponseEntity<orderInfo>(orderdata, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("order/{id}")
	public ResponseEntity<?> updateOrderInfoData(@PathVariable("id") int id, @RequestBody orderInfo data) {

		pizza pizzadata = this.pizzaservice.getSinglePizzaData(data.getPizzatype());
		if (pizzadata == null) {
			return new ResponseEntity<String>("invalid pizza type", HttpStatus.BAD_REQUEST);
		}

		try {

			orderInfo orderUpdateData = this.orderinfoservice.getSingleorderInfodata(id);
			orderUpdateData.setAddress(data.getAddress());
			orderUpdateData.setPizzatype(data.getPizzatype());
			orderUpdateData.setTotalprice((data.getQuntity() * pizzadata.getPrice()));
			orderUpdateData.setQuntity(data.getQuntity());
			orderUpdateData.setStatus("succuessfully");
			orderInfo orderdata = this.orderinfoservice.addorderInfoData(orderUpdateData);
			return new ResponseEntity<orderInfo>(orderUpdateData, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception

			return new ResponseEntity<String>("bad Request", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deletedataOforderInfo(@PathVariable("id") int id) {
		try {
			String data = this.orderinfoservice.deleteorderInfo(id);
			return new ResponseEntity<String>(data, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("bad request"+e,HttpStatus.OK);
		} 
	}

}
