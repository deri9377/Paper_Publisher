package com.paper.publisher.app.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaperFactory {

    private void insertPaper(String filename, String id) {
        JDBCTemplate jdbcTemplate = new JDBCTemplate();
        Reader reader;
        try {
            reader = new FileReader(filename);
            try {
                Connection conn = jdbcTemplate.getConnection();
				String sql = "UPDATE paper " +
                            "SET file=? " +
                            "WHERE id = '" + id + "';";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setClob(1, reader);
				stmt.executeUpdate();
            } catch (SQLException ex) {
				ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createPapers() {
        insertPaper("D:\\Raytheon Training\\Paper_Publisher\\backendService\\src\\main\\java\\com\\paper\\publisher\\app\\db\\PineapplePizza.txt", "b7825d87-568f-46f8-ac70-8c4bce5feb59");
        insertPaper("D:\\Raytheon Training\\Paper_Publisher\\backendService\\src\\main\\java\\com\\paper\\publisher\\app\\db\\TheUniverse.txt", "a0ee4ae1-8895-4581-9a07-3dcc64698bf9");
        insertPaper("D:\\Raytheon Training\\Paper_Publisher\\backendService\\src\\main\\java\\com\\paper\\publisher\\app\\db\\TheOdyssey.txt", "111c36e2-f750-4bfa-b206-f8ff0944444c");
    }
}
