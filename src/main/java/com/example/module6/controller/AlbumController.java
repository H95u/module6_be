package com.example.module6.controller;

import com.example.module6.model.Album;
import com.example.module6.model.DTO.UploadAlbumDTO;
import com.example.module6.service.impl.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Album>> findAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(albumService.finAllByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<?> uploadAlbumImg(@RequestBody UploadAlbumDTO uploadAlbumDTO,
                                            @PathVariable Long id) {
        albumService.uploadAlbumImg(uploadAlbumDTO, id);
        return new ResponseEntity<>(albumService.finAllByUserId(id),HttpStatus.OK);
    }

}
