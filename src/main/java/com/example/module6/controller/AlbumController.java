package com.example.module6.controller;

import com.example.module6.model.Album;
import com.example.module6.model.DTO.UploadAlbumDTO;
import com.example.module6.service.impl.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(albumService.finAllByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbumImg(@PathVariable Long id) {
        Optional<Album> album = albumService.findOne(id);
        if (album.isPresent()) {
            albumService.delete(id);
            return new ResponseEntity<>(albumService.finAllByUserId(album.get().getUser().getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
