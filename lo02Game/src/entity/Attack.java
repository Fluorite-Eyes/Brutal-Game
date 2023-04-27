package entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Class Attack is the strategy that allows students to attack.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */

public class Attack implements Strategy {

	private Protagonist p;
	private int zone;

	public Attack(Protagonist p, int zone) {
		this.p = p;
		this.zone = zone;
	}

	@Override
	/**
	 * for each student execute the strategy.
	 */
	public void executeStrategy(Protagonist p, int zone) {

		ArrayList<Protagonist> temp1 = new ArrayList<>();
		ArrayList<Protagonist> temp2 = new ArrayList<>();

		switch (zone) {
		case 0: {
			temp1.addAll(BrutalGame.troop1);
			temp2.addAll(BrutalGame.troop2);
			break;
		}
		case 1: {
			temp1.addAll(BrutalGame.troop3);
			temp2.addAll(BrutalGame.troop4);
			break;
		}
		case 2: {
			temp1.addAll(BrutalGame.troop5);
			temp2.addAll(BrutalGame.troop6);
			break;
		}
		case 3: {
			temp1.addAll(BrutalGame.troop7);
			temp2.addAll(BrutalGame.troop8);
			break;
		}
		case 4: {
			temp1.addAll(BrutalGame.troop9);
			temp2.addAll(BrutalGame.troop10);
			break;
		}
		}

		if (p.getTeam() == true) {

			int leastEtcsNum = 0;
			for (int i = 0; i < temp2.size() - 1; i++) {

				if (temp2.get(leastEtcsNum).getEcts() < temp2.get(i + 1).getEcts()) {
					leastEtcsNum = i + 1;
				}
			}

			// attack happens
			System.out.println("team 1 attempt to attack in zone " + temp2.get(leastEtcsNum).getZone());
			Random r = new Random();
			float f1 = r.nextFloat();
			f1 *= 100;
			if (f1 <= (40 + 3 * p.getDexterity())) {
				int temp = temp2.get(leastEtcsNum).getEcts();
				Random r2 = new Random();
				float y = r2.nextFloat();
				float attackerForce = p.getForce();
				float resistance = temp2.get(leastEtcsNum).getResistance();
				float cd = Math.max(0, Math.min(100, (10 * attackerForce - 5 * resistance))) / 100;
				int damage = (int) (y * (1 + cd) * 10);

				switch (zone) {
				case 0: {
					System.out.println("team1 attack BrutalGame.troop2");
					BrutalGame.troop2.get(leastEtcsNum).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop2.get(leastEtcsNum).getEcts());
					if (BrutalGame.troop2.get(leastEtcsNum).getEcts() <= 0) {
						BrutalGame.troop2.remove(leastEtcsNum);
					}
					break;
				}

				case 1: {
					System.out.println("team1 attack BrutalGame.troop4");
					BrutalGame.troop4.get(leastEtcsNum).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop4.get(leastEtcsNum).getEcts());
					if (BrutalGame.troop4.get(leastEtcsNum).getEcts() <= 0) {
						BrutalGame.troop4.remove(leastEtcsNum);
					}
					break;
				}
				case 2: {
					System.out.println("team1 attack BrutalGame.troop6");
					BrutalGame.troop6.get(leastEtcsNum).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop6.get(leastEtcsNum).getEcts());
					if (BrutalGame.troop6.get(leastEtcsNum).getEcts() <= 0) {
						BrutalGame.troop6.remove(leastEtcsNum);
					}
					break;
				}
				case 3: {
					System.out.println("team1 attack BrutalGame.troop8");
					BrutalGame.troop8.get(leastEtcsNum).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop8.get(leastEtcsNum).getEcts());
					if (BrutalGame.troop8.get(leastEtcsNum).getEcts() <= 0) {
						BrutalGame.troop8.remove(leastEtcsNum);
					}
					break;
				}
				case 4: {
					System.out.println("team1 attack BrutalGame.troop10");
					BrutalGame.troop10.get(leastEtcsNum).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop10.get(leastEtcsNum).getEcts());
					if (BrutalGame.troop10.get(leastEtcsNum).getEcts() <= 0) {
						BrutalGame.troop10.remove(leastEtcsNum);
					}
					break;
				}
				}

			} else {
				System.out.println("ATTACK FAILED!");

			}

		} else if (p.getTeam() == false) {

			int leastEtcsNum2 = 0;
			for (int i = 0; i < temp1.size() - 1; i++) {

				if (temp1.get(leastEtcsNum2).getEcts() < temp1.get(i + 1).getEcts()) {
					leastEtcsNum2 = i + 1;
				}
			}
			// attack happens
			System.out.println(".......");
			System.out.println("team2 attempt to attack in zone " + temp1.get(leastEtcsNum2).getZone());
			Random r = new Random();
			float f1 = r.nextFloat();
			f1 *= 100;
			if (f1 <= (40 + 3 * p.getDexterity())) {

				int temp = temp1.get(leastEtcsNum2).getEcts();
				Random r2 = new Random();
				float y = r2.nextFloat();
				float attackerForce = p.getForce();
				float resistance = temp1.get(leastEtcsNum2).getResistance();
				float cd = Math.max(0, Math.min(100, (10 * attackerForce - 5 * resistance))) / 100;
				int damage = (int) (y * (1 + cd) * 10);

				switch (zone) {
				case 0: {
					System.out.println("team2 attack BrutalGame.troop1");
					BrutalGame.troop1.get(leastEtcsNum2).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop1.get(leastEtcsNum2).getEcts());
					if (BrutalGame.troop1.get(leastEtcsNum2).getEcts() <= 0) {
						BrutalGame.troop1.remove(leastEtcsNum2);
					}
					break;
				}

				case 1: {
					System.out.println("team2 attack BrutalGame.troop3");
					BrutalGame.troop3.get(leastEtcsNum2).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop3.get(leastEtcsNum2).getEcts());
					if (BrutalGame.troop3.get(leastEtcsNum2).getEcts() <= 0) {
						BrutalGame.troop3.remove(leastEtcsNum2);
					}
					break;
				}
				case 2: {
					System.out.println("team2 attack BrutalGame.troop5");
					BrutalGame.troop5.get(leastEtcsNum2).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop5.get(leastEtcsNum2).getEcts());
					if (BrutalGame.troop5.get(leastEtcsNum2).getEcts() <= 0) {
						BrutalGame.troop5.remove(leastEtcsNum2);
					}
					break;
				}
				case 3: {
					System.out.println("team2 attack BrutalGame.troop7");
					BrutalGame.troop7.get(leastEtcsNum2).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop7.get(leastEtcsNum2).getEcts());
					if (BrutalGame.troop7.get(leastEtcsNum2).getEcts() <= 0) {
						BrutalGame.troop7.remove(leastEtcsNum2);
					}
					break;
				}
				case 4: {
					System.out.println("team2 attack BrutalGame.troop9");
					BrutalGame.troop9.get(leastEtcsNum2).setEcts(temp - damage);
					System.out.println("ATTACK HAPPENED! ECTS - " + damage + ". NOW Ects = "
							+ BrutalGame.troop9.get(leastEtcsNum2).getEcts());
					if (BrutalGame.troop9.get(leastEtcsNum2).getEcts() <= 0) {
						BrutalGame.troop9.remove(leastEtcsNum2);
					}
					break;
				}
				}

			} else {
				System.out.println("ATTACK FAILED!");
			}

		}

	}

}
