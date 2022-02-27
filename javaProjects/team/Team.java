package team;

public class Team {

	String   name;
	Player[] players;

	public Team(String name) {
		
		this.name    = name;
		this.players = new Player[11];

		this.players[0] = new Goalkeeper(name);

		for(int i = 1; i < this.players.length; i++)
			this.players[i] = new Player(name);
	}

	public void start() {
		for(int i = 0; i < this.players.length; i++)
			this.players[i].start();
	}

}
