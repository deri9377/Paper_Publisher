package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.repository.PaperRepository;

@Service
public class PaperService {
    

    @Autowired
    private PaperRepository paperRepository;
    
    public List<Paper> getAllPapers() {
        List<Paper> papers = new ArrayList<>();
        paperRepository.findAll().forEach(papers::add);
        return papers;
    }

    public Paper getPaperById(String id) {

        Optional<Paper> paper = paperRepository.findById(id);
        if (paper.isEmpty()) {
            return null;
        }
        return paper.get();
    }

    public List<Paper> getPapersByTitle(String title) {
        List<Paper> papersWithTitle = new ArrayList<>();
        for (Paper paper: getAllPapers()) {
            if (paper.getTitle().equals(title)) {
                papersWithTitle.add(paper);
            }
        }
        return papersWithTitle;
    }

    public List<Paper> getPapersByUser(String id) {
        List<Paper> papersByUser = new ArrayList<>();
        for (Paper paper: getAllPapers()) {
            if (paper.getUser().getId().equals(id)) {
                papersByUser.add(paper);
            }
        }
        return papersByUser;
    }

    public Paper createPaper(Paper paper) {
        return paperRepository.save(paper);
    }

    public void deletePaper(String id) {
        paperRepository.deleteById(id);
    }
    
}
