package src.team;

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
		try {
			for(int i = 0; i < this.players.length; i++) {
				this.players[i].start();	
				Thread.sleep(100);
			}
		}
		catch(InterruptedException e) {
			System.out.println("Error while initing team " + this.name);
		}
	}

}
