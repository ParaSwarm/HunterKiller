package HunterKiller;

public class Main {
	public static void main(String[] args) {
		try {
			
			HunterKillerMain hunterKiller = new HunterKillerMain();
		
			hunterKiller.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}