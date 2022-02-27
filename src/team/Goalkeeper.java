package src.team;

public class Goalkeeper extends Player {

    public Goalkeeper(String team_name) {
        super(team_name);
        super.init_cmd = "(init " + team_name + "(goalie))";
    }

}
