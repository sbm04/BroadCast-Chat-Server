package com.sample1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample1.Repository.RegistrationRepository;
import com.sample1.model.RegistrationModel;

@Service
public class RegistrationService {

	private final RegistrationRepository repository;
	
	public RegistrationService(RegistrationRepository repository) {
		super();
		this.repository = repository;
	}

	public void register(RegistrationModel  registrationmodel) {
		repository.save(registrationmodel);
	}
	public List<String> validate(RegistrationModel registrationmodel){
		List<String> list=new ArrayList<String>();
		RegistrationModel model=repository.findbymobilenumber(registrationmodel.getMobilenumber(), registrationmodel.getPassword());
		if(model!=null) {
			list.add(model.getName());
			list.add(model.getMobilenumber());
		}
		else {
			return null;
		}
		return list;
	}
}
