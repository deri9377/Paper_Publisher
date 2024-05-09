package com.paper.publisher.app.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.User;

@SpringBootTest
public class PaperServiceTest {
    
    @Autowired
    PaperService paperService;

    @BeforeEach
    public void cleanUp() {
        paperService.getAllPapers().clear();
    }

    @Test
    void createPaper() {
        User user = new User("bobby flay");
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));

        assertTrue(paper.equals(paperService.getPaperById(paper.getId())));
    }

    @Test
    void newPaperAddedToList() {
        User user = new User("bobby flay");
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));

        assertTrue(paper.equals(paperService.getAllPapers().get(0)));
    }

    @Test
    void getPapersByUser() {
        User user = new User("bobby flay");
        paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.createPaper(new Paper(user, "Baha Blaset" , "baha_blast.txt"));

        assertTrue(paperService.getPaperByUser(user.getId()).size() == 2);
    }

    @Test
    void getPapersByTitle() {
        User user = new User("bobby flay");
        paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.createPaper(new Paper(user, "Flavor Town" , "baha_blast.txt"));

        assertTrue(paperService.getPaperByTitle("Flavor Town").size() == 2);
    }

    @Test
    void removePaperFromList() {
        User user = new User("bobby flay");
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.removePaper(paper);

        assertNull(paperService.getPaperById(paper.getId()));
    }
}
