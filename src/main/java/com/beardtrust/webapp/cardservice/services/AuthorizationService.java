package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.UserDTO;

public interface AuthorizationService {
	
	UserDTO getUserByUserId(String id);
}
