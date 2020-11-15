package com.example.demo.controller;

import com.example.demo.model.datasource1.User;
import com.example.demo.model.datasource2.Image;
import com.example.demo.services.images.IImagesService;
import com.example.demo.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IImagesService iImagesService;

	@Autowired
	private IUserService iUserService;

	private class HomeData {
		private Iterable<Image> images;
		private Iterable<User> users;

		public Iterable<Image> getImages() {
			return images;
		}

		public void setImages(Iterable<Image> images) {
			this.images = images;
		}

		public Iterable<User> getUsers() {
			return users;
		}

		public void setUsers(Iterable<User> users) {
			this.users = users;
		}
	}

	@GetMapping
	public ResponseEntity<HomeData> goGetIndex(){
		HomeData homeData = new HomeData();

		homeData.setImages(iImagesService.findAll());
		homeData.setUsers(iUserService.findAll());

		return new ResponseEntity<>(homeData, HttpStatus.OK);
	}
}
