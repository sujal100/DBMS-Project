public class Author {

    int Id;
    String First_name;
    String Middle_name;
    String Last_name;

    String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    Author(String name,int id_) {
        Id = id_;
        int first_space = name.indexOf(' ');
        int second_space = name.indexOf(' ', first_space + 1);
        System.out.print("firstspaec :" + first_space + "\nsecondspace :" + second_space + "\n");

        if (first_space > 0) {
            First_name = capitalize(name.substring(0, first_space));
            if (second_space > 0) {
                Middle_name = capitalize(name.substring(first_space + 1, second_space));
                Last_name = capitalize(name.substring(second_space + 1));
            } else {
                Middle_name = "\0";
                Last_name = capitalize(name.substring(first_space + 1));
            }
        }
    }

}