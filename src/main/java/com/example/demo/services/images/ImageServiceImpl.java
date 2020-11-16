package com.example.demo.services.images;

import com.example.demo.model.datasource2.Image;
import com.example.demo.repositories.datasource2.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements IImagesService {
	@Autowired
	ImageRepository imageRepository;

	@Override
	public Iterable<Image> findAll() {
		return imageRepository.findAll();
	}

	@Override
	public Optional<Image> findById(Long id) {
		return imageRepository.findById(id);
	}

	@Override
	public void save(Image entity) {
		imageRepository.save(entity);
	}

	@Override
	public void delete(Image entity) {
		imageRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);
	}
}
