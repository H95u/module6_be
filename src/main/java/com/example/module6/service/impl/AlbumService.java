package com.example.module6.service.impl;

import com.example.module6.model.Album;
import com.example.module6.model.DTO.UploadAlbumDTO;
import com.example.module6.repository.IAlbumRepository;
import com.example.module6.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private IAlbumRepository iAlbumRepository;

    @Override
    public List<Album> findAll() {
        return iAlbumRepository.findAll();
    }

    @Override
    public Optional<Album> findOne(Long aLong) {
        return iAlbumRepository.findById(aLong);
    }

    @Override
    public void save(Album album) {
        iAlbumRepository.save(album);
    }

    @Override
    public void delete(Long aLong) {
        iAlbumRepository.deleteById(aLong);
    }

    public List<Album> finAllByUserId(Long userId) {
        return iAlbumRepository.findAllByUserId(userId);
    }

    @Transactional
    public void uploadAlbumImg(UploadAlbumDTO uploadAlbumDTO, Long userId) {
        List<String> imgList = uploadAlbumDTO.getAlbumImg();
        for (String s : imgList) {
            iAlbumRepository.uploadAlbumImg(s, userId);
        }
    }


}
