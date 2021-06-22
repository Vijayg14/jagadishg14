package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	private static final Logger logger = LoggerFactory.getLogger(AlienController.class);

	@RequestMapping("/")
	public String Home() {
		return "Show";
	}
	
	@RequestMapping("/addAlien")
	@ResponseBody
	public String addAlien(Alien alien) {
		System.out.println(alien);
		logger.info("1st log message");
		repo.save(alien);
		return "Alien Successfully added";
	}
	
	@RequestMapping("/getAlien")
	@ResponseBody
	public Alien getAlien(@RequestParam int aid) {
		//ModelAndView mv = new ModelAndView("showData");
		Alien alien = repo.findById(aid).orElse(new Alien());
		//mv.addObject(alien);
		return alien;
	}
	
	@RequestMapping("/getByTech")
	@ResponseBody
	public List<Alien> getByTech(@RequestParam String tech) {
		System.out.println(repo.getByTech(tech));
		return repo.getByTech(tech);
	}
	
	@RequestMapping("/findByTech")
	@ResponseBody
	public List<Alien> Test(@RequestParam String tech) {
		System.out.println(repo.findByTech(tech));
		return repo.findByTech(tech);
	}

}
