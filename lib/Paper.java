import java.util.Vector;

public class Paper {

    int Id;
    int year;
    String Title;
    String Abstract;
    Vector<Integer> A_id;
    int V_id;
    Vector<Integer> R_id;

    Paper(int id_, int year_, String title_, String abst,
            Vector<Integer> a_id, int v_id, Vector<Integer> r_id) {
        Id = id_;
        year = year_;
        Title = title_;
        Abstract = abst;
        A_id = a_id;
        V_id = v_id;
        R_id = r_id;
    }
}
