package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CardTypeServiceImpl implements CardTypeService {
	
	CardTypeRepository cardTypeRepo;
	
	@Override
	@Transactional
	public void save(CardTypeEntity cardType) {
		log.trace("Start of CardTypeService.save(" + cardType + ")");
		cardTypeRepo.save(cardType);
		log.trace("End of CardTypeService.save(" + cardType + ")");
		}
	
	@Override
	@Transactional
	public List<CardTypeEntity> getAll(){
		log.trace("Start of CardTypeService.getAll()");
		List<CardTypeEntity> cardTypes = cardTypeRepo.findAll();
		log.trace("End of CardTypeService.getAll()");
		return cardTypes;
	}

}
