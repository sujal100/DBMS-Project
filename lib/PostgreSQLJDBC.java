import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class PostgreSQLJDBC {
  public static void main(String args[]) {

    // File read karke print kane ke liye
    Iterator<String> itr = readFileInList("source.txt").iterator();
    String Title = null, Venue = null, abst = null;
    String[] Author = null;
    int year = 0, id = 0;
    List<Integer> ref = new ArrayList<>();
    while (itr.hasNext()) {
      String a = itr.next();
      if (!a.isEmpty()) {
        if (a.charAt(1) == '@') {
          Author = a.substring(2).split(",");
        } else if (a.charAt(1) == '*') {
          Title = a.substring(2);
        } else if (a.charAt(1) == 't') {
          year = Integer.parseInt(a.substring(2));
        } else if (a.charAt(1) == 'c') {
          Venue = a.substring(2);
        } else if (a.charAt(1) == 'i') {
          id = Integer.parseInt(a.substring(6));
        } else if (a.charAt(1) == '%') {
          ref.add(Integer.parseInt(a.substring(2)));
        }
      } else {
        System.out.println("Title - " + Title);
        System.out.println("Main Author - " + Author[0]);
        for (int j = 1; j < Author.length; j++) {
          System.out.println("Co-Author - " + Author[j] + "id =" + id);
        }
        System.out.println("Year - " + year);
        System.out.println("Venue - " + Venue);
        System.out.println("Id of this paper - " + id);
        System.out.println("id(s) of paper(s) referenced - " + ref);
        ref.clear();
        System.out.println("Abstrac - t" + abst);
        System.out.println("\n new paper \n");
      }
    }
    System.out.println("Opened txt successfully");
  }

  // file raed karne ke liye
  public static List<String> readFileInList(String fileName) {
    List<String> lines = Collections.emptyList();
    try {
      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }
}
