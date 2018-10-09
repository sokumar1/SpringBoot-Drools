package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.springboot.model.Person;
import com.springboot.model.Result;
import com.springboot.service.PersonLanguageService;

@RestController
public class PersonLanguageController {
	
	private final PersonLanguageService personLanguageService;
	@Autowired
	public PersonLanguageController(PersonLanguageService personLanguageService) {
		this.personLanguageService = personLanguageService;
	}

	@RequestMapping(value = "/getPersonLanguage", method = RequestMethod.GET, produces = "application/json")
	public Result getQuestions(@RequestParam(required = true) JSONArray userAttributes) {

		
		Person person = new Person();	
		Result result = new Result();
		
		for (int i = 0, size = userAttributes.length(); i < size; i++)
	    {
	      JSONObject objectInArray = userAttributes.getJSONObject(i);	    
	      String[] elementNames = JSONObject.getNames(objectInArray);
	     
	      
	      for (String elementName : elementNames)
	      {
	    	  String value = objectInArray.getString(elementName); 
	    	  
	    	  if(value.equals("Locale") ){	    		 
	    		  person.setLocale(objectInArray.getString(elementNames[1]));
	    		  break;
	    	  }
	    	  
	    	  if(value.equals("Custom12") ){	    		 
	    		  person.setCustom12(objectInArray.getString(elementNames[1]));
	    		  break;
	    	  }
	    	  
	    	 
	      } 
	       
	    } 
		personLanguageService.getPersonLanguage(person , result);
		return result;
	}
}