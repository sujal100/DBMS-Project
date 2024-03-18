public class Venue {
    int Id;
    String location;

    Venue(String str,int id_) {
        Id = id_;
        location = format_str(str);
    }

    String format_str(String str) {
        return str;
    }
}

/**
 * format_str definition :
 * 
 * 
 */