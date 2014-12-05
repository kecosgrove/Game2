package XCOMGame;

/**
 * Created by User on 12/1/2014.
 */
public class GameEvent {

    int ID;
    int value;
    String message;

    public GameEvent(int ID) {
        this.ID = ID;
    }

    public GameEvent(int ID, int value) {
        this.ID = ID;
        this.value = value;
    }

    public GameEvent(int ID, String message) {
        this.ID= ID;
        this.message = message;
    }

    public GameEvent(int ID, int value, String message) {
        this.ID = ID;
        this.value = value;
        this.message = message;
    }

}
