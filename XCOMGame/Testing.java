package XCOMGame;

/**
 * Created by User on 11/15/2014.
 */
public class Testing {

    public static void main(String[] args) {
        DateContainer date = new DateContainer(2004, 365, 13, 40);
        for (int i = 0; i < 100; i++) {
            System.out.println(date.toString());
            date = date.hourAdvance(2.25);
        }
    }

}
