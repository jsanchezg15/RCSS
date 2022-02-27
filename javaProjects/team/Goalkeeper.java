package team;

public class Goalkeeper extends Player {

    public Goalkeeper(String team) {
        super(team);
        super.init_cmd = "(init " + super.TEAM + "(goalie))";
    }

}
