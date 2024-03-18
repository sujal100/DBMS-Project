import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Source {
    public static void main(String[] args) {

        Vector<Paper> papers = new Vector<Paper>();
        Vector<Author> authors = new Vector<Author>();
        Vector<Venue> venues = new Vector<Venue>();

        File inputFile = new File("V.txt");
        Scanner myReader;

        try {
            myReader = new Scanner(inputFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        HashMap<String, Integer> authorMap = new HashMap<String, Integer>();
        HashMap<String, Integer> venueMap = new HashMap<String, Integer>();
        int id_generator = 0, vid_generator = 0;

        String Title = null, Venue = null, abst = null;
        Vector<String> author = new Vector<String>();
        int year = 0, id = 0, v_id;
        Vector<Integer> ref = new Vector<>();
        Vector<Integer> a_id = new Vector<>();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!data.isEmpty()) {
                String prefix = data.substring(0, 2);
                switch (prefix) {
                    case "#*":
                        Title = data.substring(2);
                        break;
                    case "#@":
                        author = new Vector<String>(Arrays.asList(data.substring(2).split(",")));
                        break;
                    case "#t":
                        year = Integer.parseInt(data.substring(2));
                        break;
                    case "#c":
                        Venue = data.substring(2);
                        break;
                    case "#%":
                        ref.add(Integer.parseInt(data.substring(2)));
                        break;
                    case "#!":
                        abst = data.substring(2);
                        break;
                    case "#i":
                        id = Integer.parseInt(data.substring(6));
                        break;
                }
            } else {
                for (String itr : author) {
                    if (!authorMap.containsKey(itr.toLowerCase())) {
                        authorMap.put(itr.toLowerCase(), id_generator++);
                    }
                    a_id.add(authorMap.get(itr.toLowerCase()));
                }

                if (!Venue.isBlank()) {
                    if (!venueMap.containsKey(Venue)) {
                        venueMap.put(Venue, vid_generator++);
                    }
                    v_id = venueMap.get(Venue);
                } else {
                    v_id = -1;
                }

                papers.add(new Paper(id, year, Title, abst, a_id, v_id, ref));

                a_id = new Vector<>();
                ref = new Vector<>();
                author = new Vector<>();
                Title = null;
                abst = null;
                Venue = null;
                v_id = -1;
                id = -1;

            }
        }
        myReader.close();

        for (Map.Entry<String, Integer> venueSet : venueMap.entrySet()) {
            venues.add(new Venue(venueSet.getKey(), venueSet.getValue()));
        }

        for (Map.Entry<String, Integer> authorSet : authorMap.entrySet()) {
            authors.add(new Author(authorSet.getKey(), authorSet.getValue()));
        }

        /** Inserting into database */
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1231");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        for (Paper p : papers) {
            try {
                PreparedStatement pstmt = c.prepareStatement("insert into research_paper values(?,?,?,?,?)");

                pstmt.setInt(1, p.Id);
                pstmt.setString(2, p.Title);
                pstmt.setInt(3, p.year);
                pstmt.setString(4, p.Abstract);
                pstmt.setInt(5, p.V_id);

                pstmt.executeQuery();
                pstmt.close();
                int rank = 0;
                for (Integer i : p.A_id) {
                    try {
                        PreparedStatement pstmt_auth = c.prepareStatement("insert into written_by values(?,?,?)");
                        pstmt_auth.setInt(1, p.Id);
                        pstmt_auth.setInt(2, i);
                        pstmt_auth.setInt(2, ++rank);

                        pstmt_auth.executeQuery();
                        pstmt_auth.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                for (Integer r : p.R_id) {
                    try {
                        PreparedStatement pstmt_auth = c.prepareStatement("insert into cited_by values(?,?)");
                        pstmt_auth.setInt(1, p.Id);
                        pstmt_auth.setInt(2, r);

                        pstmt_auth.executeQuery();
                        pstmt_auth.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (Author a : authors) {
            try {
                PreparedStatement pstmt = c.prepareStatement("insert into author values(?,?,?,?)");

                pstmt.setInt(1, a.Id);
                pstmt.setString(2, a.First_name);
                pstmt.setString(3, a.Middle_name);
                pstmt.setString(4, a.Last_name);

                pstmt.executeQuery();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (Venue v : venues) {
            try {
                PreparedStatement pstmt = c.prepareStatement("insert into conference values(?,?)");

                pstmt.setInt(1, v.Id);
                pstmt.setString(2, v.location);

                pstmt.executeQuery();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}