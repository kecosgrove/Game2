package XCOMGame;

import javalib.worldimages.Posn;

/**
 * Created by User on 12/6/2014.
 */
public class PosnList {

    Posn first;
    PosnList rest;

    public PosnList(Posn first, PosnList rest) {
        this.first = first;
        this.rest = rest;
    }

    public Posn[] toArray() {
        Posn[] array = new Posn[this.length()];
        PosnList list = this;
        for (int i = 0; i < array.length; i++) {
            array[i] = list.first;
            list = list.rest;
        }
        return array;
    }

    public int length() {
        if (rest == null) {
            return 1;
        } else {
            return 1 + rest.length();
        }
    }

    public boolean member(Posn posn) {
        if (first.x == posn.x && first.y == posn.y) return true;
        else if (rest == null) return false;
        else return rest.member(posn);
    }

    public PosnList append(PosnList list) {
        if (rest == null) return new PosnList(first, list);
        else return new PosnList(first, rest.append(list));
    }

}
