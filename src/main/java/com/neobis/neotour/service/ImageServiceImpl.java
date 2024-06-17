package com.neobis.neotour.service;

import com.neobis.neotour.dto.ImageDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Image;
import com.neobis.neotour.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public ImageDto getImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new ResourceNotFoundException("Image with id " + id + " does not exist.");
        }
        return modelMapper.map(image.get(), ImageDto.class);
    }

    @Override
    public List<ImageDto> getImages() {
        return imageRepository.findAll()
                .stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto createImage(ImageDto imageDto) {
        Image image = modelMapper.map(imageDto, Image.class);
        return modelMapper.map(imageRepository.save(image), ImageDto.class);
    }

    @Override
    public void deleteImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new ResourceNotFoundException("Image with id " + id + " does not exist.");
        }
        imageRepository.deleteById(id);
    }
}
