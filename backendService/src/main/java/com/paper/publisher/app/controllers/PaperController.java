package com.paper.publisher.app.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.services.PaperService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
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
    public ResponseEntity<List<Paper>> getPapersByTitle(@PathVariable String title) {
        return ResponseEntity.ok(paperService.getPapersByTitle(title));
    }

    @GetMapping(value="papers/user/{id}")
    public ResponseEntity<List<Paper>> getPapersByUser(@PathVariable String id) {
        return ResponseEntity.ok(paperService.getPapersByUser(id));
    }


    @PostMapping(value="paper")
    public ResponseEntity<Paper> createPaper(@RequestBody Paper newPaper, HttpServletRequest request) throws ServerException {
        
        Paper paper = paperService.createPaper(newPaper);

        URI location = ServletUriComponentsBuilder.fromPath("paper")
                .path("/{id}")
                .buildAndExpand(paper.getId())
                .toUri();
        return ResponseEntity.created(location).body(paper);
    }
    
    @DeleteMapping(value="/paper/{id}")
    public ResponseEntity<?> deletePaper(@PathVariable String id) {
        paperService.deletePaper(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
