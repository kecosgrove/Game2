package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;

import java.util.Random;

public class Testing {

    //Ensure MapWorld properly decrements recovery time on tick
    public static void mapWorldOnTickTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            MapWorld world = (MapWorld) new MapWorld(MapWorld.start, XCOMGameWrapper.initContinents(),
                                                     MapWorld.startUnits, 1).onTick();
            if (world.wounded != 0) throw new RuntimeException();
        }
        System.out.println("MapWorld decremented recovery time on tick properly " + repeat + " times.");
    }

    //Ensures that MapWorld will never transition to a Mission when no missions are available.
    public static void mouseClickedOnEmptyBoardTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            World world = new MapWorld(MapWorld.start, XCOMGameWrapper.initContinents(),
                    MapWorld.startUnits, 1).onMouseClicked(new Posn(rng.nextInt(MapWorld.screenWidth),
                                                                    rng.nextInt(MapWorld.screenHeight)));
            if (world instanceof TransitionWorld) throw new RuntimeException();
        }
        System.out.println("MapWorld correctly did not start a mission after " + repeat + " random clicks.");
    }

    //Ensures that a continent will eventually generate a mission
    public static void missionGenerationTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Continent continent = new Continent(0, "", XCOMGameWrapper.initAFCities(), MapWorld.AFPos, MapWorld.AFPos,
                                                new MTMSlot(false));
            while(!continent.mission.hasImage()) {
                continent = continent.onTick();
            }
        }
        System.out.println("The continent successfully generated a mission " + repeat + " times.");
    }

    //Ensures that a continent successfully reduces its panic when told to do so
    public static void missionHandledTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Continent continent = new Continent(MapWorld.panicRate, "", XCOMGameWrapper.initAFCities(), MapWorld.AFPos,
                    MapWorld.AFPos, new MTMSlot(false));
            if (continent.missionHandled().panic != 0) throw new RuntimeException();
        }
        System.out.println("The continent successfully executed missionHandled() " + repeat + " times.");
    }

    //Ensures that a continent successfully increase panic when told to do so
    public static void processPanicTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Continent continent = new Continent(0, "", XCOMGameWrapper.initAFCities(), MapWorld.AFPos,
                    MapWorld.AFPos, new Mission(0, new City(new Posn(0,0), ""), new Posn(0,0), false));
            if (!(continent.onTick().panic > 0)) {
                throw new RuntimeException();
            }
        }
        System.out.println("The continent successfully incremented panic " + repeat + " times.");
    }

    //Ensures that Missions correctly expire and become empty through repeated calls to onTick()
    public static void missionExpirationTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            MissionSlot mission = new Mission(1, new City(new Posn(0,0), ""), new Posn(0,0), false);
            if (mission.onTick() instanceof Mission) {}
            else throw new RuntimeException();
            if (mission.onTick().onTick() instanceof MTMSlot) {}
            else throw new RuntimeException();
        }
        System.out.println("The mission successfully decremented and expired " + repeat + " times.");
    }

    //Ensures that onMouseClicked(Posn mouse) successfully processes and accurate click
    public static void missionOnMouseClickedTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            Posn randPosn = new Posn(rng.nextInt(), rng.nextInt());
            MissionSlot mission = new Mission(2, new City(randPosn, ""), new Posn(0,0), false);
            if (mission.onMouseClicked(randPosn).ID == MapWorld.startMID) {}
            else throw new RuntimeException();
        }
        System.out.println("The mission successfully identified an accurate mouse click " + repeat + " times.");
    }

    //Ensures that TransitionWorlds expire properly
    public static void transitionWorldTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            int duration = rng.nextInt(100);
            int counter = -1;
            World world = new TransitionWorld("", duration, new MapWorld(MapWorld.start,
                    XCOMGameWrapper.initContinents(), MapWorld.startUnits, 1));
            while (world instanceof TransitionWorld) {
                counter++;
                world = world.onTick();
            }
            if (duration != counter) throw new RuntimeException();
        }
        System.out.println("TransitionWorld expired properly " + repeat + " times.");
    }

    //Ensures that the MissionWorld successfully changes turns on calling changeTurn()
    public static void changeTurnTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            MissionWorld world = new MissionWorld(null, 1, 1);
            if (world.state == world.yourMove && world.changeTurn().state == world.enemyTurn &&
                world.changeTurn().changeTurn().state == world.yourMove) {}
            else throw new RuntimeException();
        }
        System.out.println("MissionWorld succesfully changed turns " + repeat + " times.");
    }

    //Ensures that the MissionWorld successfully returns a TransitionWorld when told to complete
    public static void completeMissionTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            MissionWorld world = new MissionWorld(new MapWorld(MapWorld.start, XCOMGameWrapper.initContinents(),
                    MapWorld.startUnits, 1), 1, 1);
            if (world.completeMission() instanceof TransitionWorld) {}
            else throw new RuntimeException();
        }
        System.out.println("MissionWorld succesfully returned a TransitionWorld upon completion " + repeat + " times.");
    }

    //Ensures that rotateRight() and rotateLeft appropriately increment and loop around the indexes of the team array
    public static void rotateTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Board board = BoardFactory.makeBoard(3,3);
            if (board.rotateRight().selected != 1 || board.rotateLeft().selected != 2)
                throw new RuntimeException();
        }
        System.out.println("The board successfully rotated in each direction around teamOne[] " + repeat + " times.");
    }

    //Ensures that aimRight() and aimLeft appropriately increment and loop around the indexes of the team array
    public static void aimTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Board board = BoardFactory.makeBoard(3,3);
            if (board.aimRight().target != 1 || board.aimLeft().target != 2)
                throw new RuntimeException();
        }
        System.out.println("The board successfully rotated in each direction around teamTwo[] " + repeat + " times.");
    }

    //Ensures that hasCover() recognizes units in cover
    public static void hasCoverTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            PlayingSquare[][] board = new PlayingSquare[1][4];
            for (int j = 0; j < board[0].length; j++) {
                board[0][j] = new OpenSquare(new Posn(0, j));
            }
            board[0][rng.nextInt(2) + 1] = new Cover(new Posn(0,0));
            if (Board.hasCover(new Posn(0,0), new Posn(0,3), board) || Board.hasCover(new Posn(0,3), new Posn(0,0), board)) {}
            else throw new RuntimeException();
        }
        System.out.println("The hasCover function successfully recognized a unit in cover " + repeat + " times.");
    }

    //Ensures that board's attack() function returns an AttackWorld
    public static void attackTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Board board = BoardFactory.makeBoard(1,1);
            World world = board.attack(null);
            if (world instanceof AttackWorld) {}
            else throw new RuntimeException();
        }
        System.out.println("attack() correctly returned an AttackWorld " + repeat + " times.");
    }

    //Ensures forceEnd returns true when the player has no moves to make
    public static void forceEndTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Board board = BoardFactory.makeBoard(1, 1);
            board.board[board.teamOne[0].x][board.teamOne[0].y] = new Unit(5, new Posn(0, 0), 0, true, 0, false);
            if (!board.forceEnd()) throw new RuntimeException();
        }
        System.out.println("forceEnd() recognized that the player had no moves to make " + repeat + " times.");
    }

    //Ensures that move(Posn posn) moves the unit to the indicated position
    public static void moveTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            Board board = BoardFactory.makeBoard(1,1);
            Posn randReachable = board.reachable[rng.nextInt(board.reachable.length)];
            if (board.move(randReachable).board[board.teamOne[0].x][board.teamOne[0].y].getPosn().x == randReachable.x &&
                board.move(randReachable).board[board.teamOne[0].x][board.teamOne[0].y].getPosn().y == randReachable.y)
            {}
            else throw new RuntimeException();
        }
        System.out.println("The move() function successfully moved the unit " + repeat + " times.");
    }

    //Ensures updateDeath removes units at 0 or less health and shortens the array
    public static void updateDeathTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            Board board = BoardFactory.makeBoard(2,2);
            Posn posn = new Posn(board.teamOne[1].x, board.teamOne[1].y);
            board.board[posn.x][posn.y] = new Unit(0, new Posn(0,0), 0, false, 0, false);
            try {board = board.updateDeath();}
            catch (BoardClearException e) {}
            if (board.board[posn.x][posn.y] instanceof OpenSquare) {}
            else throw new RuntimeException();
        }
        System.out.println("The updateDeath() function successfully removed a unit at 0 health " + repeat + " times.");
    }

    //Ensures giveDamage(int damage) reduces a unit's health by the given amount
    public static void giveDamageTester(int repeat) {
        for (int i = 0; i < repeat; i++) {
            Random rng = new Random();
            PlayingSquare unit = new Unit(5, new Posn(0,0), 0, false, 0, false);
            int damage = rng.nextInt(5);
            unit = unit.giveDamage(damage);
            if (unit.damage() == 5 - damage) {}
            else throw new RuntimeException();
        }
        System.out.println("giveDamage(int) gave the correct amount of damage " + repeat + " times.");
    }

    public static void main(String[] args) {
        mapWorldOnTickTester(10);
        mouseClickedOnEmptyBoardTester(1000);
        missionGenerationTester(10);
        missionHandledTester(10);
        processPanicTester(10);
        missionExpirationTester(10);
        missionOnMouseClickedTester(10);
        transitionWorldTester(10);
        changeTurnTester(10);
        completeMissionTester(10);
        rotateTester(10);
        aimTester(10);
        hasCoverTester(10);
        attackTester(10);
        forceEndTester(10);
        moveTester(10);
        updateDeathTester(10);
        giveDamageTester(10);
    }

}
