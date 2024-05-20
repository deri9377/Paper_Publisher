package com.paper.publisher.app.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class PaperServiceTest {
    
    @Autowired
    PaperService paperService;

    @Autowired
    UserService userService;


    @Test
    void createPaper() {
        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));

        assertTrue(paper.equals(paperService.getPaperById(paper.getId())));
    }

    @Test
    void newPaperAddedToList() {
        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));

        assertTrue(paper.equals(paperService.getAllPapers().get(0)));
    }

    @Test
    void getPapersByUser() {
        User user = userService.createUser(new User("bobby flay"));
        paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.createPaper(new Paper(user, "Baha Blaset" , "baha_blast.txt"));

        assertTrue(paperService.getPapersByUser(user.getId()).size() == 2);
    }

    @Test
    void getPapersByTitle() {
        User user = userService.createUser(new User("bobby flay"));
        paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.createPaper(new Paper(user, "The Universe" , "baha_blast.txt"));

        assertTrue(paperService.getPapersByTitle("Flavor Town").size() == 1);
    }

    @Test
    void removePaperFromList() {
        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        paperService.deletePaper(paper.getId());

        assertNull(paperService.getPaperById(paper.getId()));
    }
}
