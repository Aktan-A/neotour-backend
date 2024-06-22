package com.neobis.neotour.service;

import com.neobis.neotour.dto.ImageDto;

import java.util.List;

public interface ImageService {

    ImageDto getImageById(Long id);

    List<ImageDto> getImages();

    ImageDto createImage(ImageDto imageDto);

    void deleteImageById(Long id);

}
