package com.wpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.OrdersDao;

@Service
public class OrdersService {

	@Autowired
	private OrdersDao ordersDao;
}
