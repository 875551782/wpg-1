package com.wpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.HardwareDao;

@Service
public class HardwareService {

	@Autowired
	private HardwareDao hardwareDao;
}
