package Java_Review;

import java.util.ArrayList;
import java.util.List;

public class Generic_Review {
    static class Baseball_Player {
        String name;
        public void Catch() {
            System.out.println("Catch the ball");
        }
        public void Throw() {
            System.out.println("Throw the ball");
        }
    }
    static class Hitter extends Baseball_Player {
        public void Hit() {
            System.out.println("Hit the ball");
        }
    }

    static class Player_List<T> {
        List<T> Players =  new ArrayList<>();
        public void add(T player) {
            Players.add(player);
        }
    }
    public static void main(String[] args) {
        Player_List<Baseball_Player> Players = new Player_List<>();
        Players.add(new Baseball_Player());
        Players.add(new Hitter());
    }
}

