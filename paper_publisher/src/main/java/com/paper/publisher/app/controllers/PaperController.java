package com.paper.publisher.app.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.services.PaperService;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class PaperController {
    
    @Autowired
    PaperService paperService;

    @GetMapping(value="papers")
    public ResponseEntity<List<Paper>> getPapers() {
        return ResponseEntity.ok(paperService.getAllPapers());
    }

    @GetMapping(value="papers/{id}")
    public ResponseEntity<Paper> getPaperById(@PathVariable String id) {
        Paper paper = paperService.getPaperById(id);
        if (paper != null) {
            return ResponseEntity.ok(paperService.getPaperById(id));
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }

    @GetMapping(value="papers/title/{title}")
    public ResponseEntity<List<Paper>> getPaperByTitle(@PathVariable String title) {
        return ResponseEntity.ok(paperService.getPaperByTitle(title));
    }

    @GetMapping(value="papers/user/{id}")
    public ResponseEntity<List<Paper>> getPaperByUser(@PathVariable String id) {
        return ResponseEntity.ok(paperService.getPaperByUser(id));
    }

    @PostMapping(value="papers")
    public ResponseEntity<Paper> createPost(@RequestBody Paper newPaper, HttpServletRequest request) throws ServerException {
        
        Paper paper = paperService.createPost(newPaper);
        if (paper != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(paper.getId())
                    .toUri();
            return ResponseEntity.created(location).body(paper);
        } else {
            throw new ServerException("Error in creating the Post resourse. Try again.");
        }
    }
    
    
    
    
}
