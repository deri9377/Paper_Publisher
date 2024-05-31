package com.paper.publisher.app.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class PaperFactory {

    private void insertPaper(String filename, String id) {
        JDBCTemplate jdbcTemplate = new JDBCTemplate();
        File file;
        try {
            file = new File(filename);
            String encodedFile = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
            try {
                Connection conn = jdbcTemplate.getConnection();
				String sql = "UPDATE paper " +
                            "SET file=? " +
                            "WHERE id = '" + id + "';";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, encodedFile);
				stmt.executeUpdate();
            } catch (SQLException ex) {
				ex.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block

        }
    }

    public void createPapers() {
        insertPaper("src"+ File.separator + "main" + File.separator +"java" + File.separator +"com" + File.separator +"paper"+ File.separator + "publisher"+ File.separator + "app"+ File.separator + "db"+ File.separator + "PineapplePizza.txt", "b7825d87-568f-46f8-ac70-8c4bce5feb59");
        insertPaper("src"+ File.separator + "main" + File.separator +"java" + File.separator +"com" + File.separator +"paper"+ File.separator + "publisher"+ File.separator + "app"+ File.separator + "db"+ File.separator + "TheUniverse.txt", "a0ee4ae1-8895-4581-9a07-3dcc64698bf9");
        insertPaper("src"+ File.separator + "main" + File.separator +"java" + File.separator +"com" + File.separator +"paper"+ File.separator + "publisher"+ File.separator + "app"+ File.separator + "db"+ File.separator + "TheOdyssey.txt", "111c36e2-f750-4bfa-b206-f8ff0944444c");
    }
}
