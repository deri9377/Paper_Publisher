package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.User;

@Service
public class PaperService {
    
    private ArrayList<Paper> papers;

    public PaperService() {
        papers = new ArrayList<>();
        generatePapers();
    }

    private void generatePapers() {
        papers.add(new Paper(new User("Bobby Flay"), "The_Universe", "the_universe.txt"));
        papers.add(new Paper(new User("Miley Cyrus"), "On_Tour", "On_Tour.txt"));
    
    }
    
    public ArrayList<Paper> getAllPapers() {
        return papers;
    }

    public Paper getPaperById(String id) {
        for (Paper paper: papers) {
            if (paper.getId().equals(id)) {
                return paper;
            }
        }
        return null;
    }

    public List<Paper> getPaperByTitle(String title) {
        List<Paper> papersWithTitle = new ArrayList<>();
        for (Paper paper: papers) {
            if (paper.getTitle().equals(title)) {
                papersWithTitle.add(paper);
            }
        }
        return papersWithTitle;
    }

    public List<Paper> getPaperByUser(String id) {
        List<Paper> papersByUser = new ArrayList<>();
        for (Paper paper: papers) {
            if (paper.getAuthor().getId().equals(id)) {
                papersByUser.add(paper);
            }
        }
        return papersByUser;
    }

    public Paper createPost(Paper newPaper) {
        Paper paper = new Paper(newPaper.getAuthor(), newPaper.getTitle() , newPaper.getFilename());
        papers.add(paper);
        return paper;
    }
}
