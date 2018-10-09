package com.springboot.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Person;
import com.springboot.model.Result;

@Service
public class PersonLanguageService {
	private final KieContainer kieContainer;

	@Autowired
	public PersonLanguageService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		
	}

	public Result getPersonLanguage(Person person, Result result) {
		//get the stateful session
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(person);
		kieSession.insert(result);
		kieSession.fireAllRules();
		kieSession.dispose();
		return result;
	}
}
