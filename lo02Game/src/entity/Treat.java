package entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Class Treat is the strategy that allows students to treat.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class Treat implements Strategy {

	private Protagonist p;
	private int zone;

	public Treat(Protagonist p, int zone) {
		this.p = p;
		this.zone = zone;
	}

	@Override
	/**
	 * execute the treat strategy.
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

			int least = 0;
			for (int i = 0; i < temp1.size() - 1; i++) {
				if (temp1.get(least).getEcts() > temp1.get(i + 1).getEcts()) {
					least = i + 1;
				}
			}

			System.out.println("attempt to treat " + temp1.get(least).getCode());

			Random r = new Random();
			float f1 = r.nextFloat();
			f1 *= 100;
			if (f1 <= (20 + 6 * temp1.get(least).getDexterity())) {

				// treatment happens
				float y = r.nextFloat();
				y *= 0.6;
				int amountOfTreat = (int) (y * (10 + p.getConstitution()));
				int temp = temp1.get(least).getEcts();
				if (amountOfTreat < 30 + p.getConstitution()) {
					switch (zone) {
					case 0: {
						BrutalGame.troop1.get(least).setEcts(30 + p.getConstitution());
						System.out.println(
								"TREAT1  + " + amountOfTreat + "Ects = " + BrutalGame.troop1.get(least).getEcts());
						break;
					}
					case 1: {
						BrutalGame.troop3.get(least).setEcts(30 + p.getConstitution());
						System.out.println(
								"TREAT1  + " + amountOfTreat + "Ects = " + BrutalGame.troop3.get(least).getEcts());
						break;
					}
					case 2: {
						BrutalGame.troop5.get(least).setEcts(30 + p.getConstitution());
						System.out.println(
								"TREAT1  + " + amountOfTreat + "Ects = " + BrutalGame.troop5.get(least).getEcts());
						break;
					}
					case 3: {
						BrutalGame.troop7.get(least).setEcts(30 + p.getConstitution());
						System.out.println(
								"TREAT1  + " + amountOfTreat + "Ects = " + BrutalGame.troop7.get(least).getEcts());
						break;
					}
					case 4: {
						BrutalGame.troop9.get(least).setEcts(30 + p.getConstitution());
						System.out.println(
								"TREAT1  + " + amountOfTreat + "Ects = " + BrutalGame.troop9.get(least).getEcts());
						break;
					}
					}

				} else {
					BrutalGame.troop1.get(least).setEcts(temp + amountOfTreat);
					System.out.println("TREAT1 to full " + "Ects = " + BrutalGame.troop1.get(least).getEcts());
				}

			} else {
				System.out.println("TREAT FAILED!");
			}

		} else if (p.getTeam() == false) {

			int least2 = 0;

			for (int i = 0; i < BrutalGame.troop2.size() - 1; i++) {
				System.out.println(
						"BrutalGame.troop2.size" + BrutalGame.troop2.size() + "least2" + least2 + "i+1" + (i + 1));

				if (BrutalGame.troop2.get(least2).getEcts() > BrutalGame.troop2.get(i + 1).getEcts()) {
					least2 = i + 1;

					System.out.println("ok");
					System.out.println("attempt to treat " + BrutalGame.troop2.get(least2).getCode());
					Random r2 = new Random();
					float f2 = r2.nextFloat();
					f2 *= 100;

					if (f2 <= (40 + 3 * BrutalGame.troop2.get(least2).getDexterity())) {

						// treatment happens
						float y = r2.nextFloat();
						y *= 0.6;
						int amountOfTreat2 = (int) (y * (10 + p.getConstitution()));
						int temp = BrutalGame.troop2.get(least2).getEcts();
						if (amountOfTreat2 < 30 + p.getConstitution()) {

							switch (zone) {
							case 0: {
								BrutalGame.troop2.get(least2).setEcts(30 + p.getConstitution());
								System.out.println("TREAT2  + " + amountOfTreat2 + "Ects = "
										+ BrutalGame.troop2.get(least2).getEcts());
								break;
							}
							case 1: {
								BrutalGame.troop4.get(least2).setEcts(30 + p.getConstitution());
								System.out.println("TREAT2  + " + amountOfTreat2 + "Ects = "
										+ BrutalGame.troop4.get(least2).getEcts());
								break;
							}
							case 2: {
								BrutalGame.troop6.get(least2).setEcts(30 + p.getConstitution());
								System.out.println("TREAT2  + " + amountOfTreat2 + "Ects = "
										+ BrutalGame.troop6.get(least2).getEcts());
								break;
							}
							case 3: {
								BrutalGame.troop8.get(least2).setEcts(30 + p.getConstitution());
								System.out.println("TREAT2  + " + amountOfTreat2 + "Ects = "
										+ BrutalGame.troop8.get(least2).getEcts());
								break;
							}
							case 4: {
								BrutalGame.troop10.get(least2).setEcts(30 + p.getConstitution());
								System.out.println("TREAT2  + " + amountOfTreat2 + "Ects = "
										+ BrutalGame.troop10.get(least2).getEcts());
								break;
							}
							}
						} else {
							switch (zone) {
							case 0: {
								BrutalGame.troop2.get(least2).setEcts(temp + amountOfTreat2);
								System.out.println(
										"TREAT2 to full " + "Ects = " + BrutalGame.troop2.get(least2).getEcts());
							}
							case 1: {
								BrutalGame.troop4.get(least2).setEcts(temp + amountOfTreat2);
								System.out.println(
										"TREAT2 to full " + "Ects = " + BrutalGame.troop4.get(least2).getEcts());
							}
							case 2: {
								BrutalGame.troop6.get(least2).setEcts(temp + amountOfTreat2);
								System.out.println(
										"TREAT2 to full " + "Ects = " + BrutalGame.troop6.get(least2).getEcts());
							}
							case 3: {
								BrutalGame.troop8.get(least2).setEcts(temp + amountOfTreat2);
								System.out.println(
										"TREAT2 to full " + "Ects = " + BrutalGame.troop8.get(least2).getEcts());
							}
							case 4: {
								BrutalGame.troop10.get(least2).setEcts(temp + amountOfTreat2);
								System.out.println(
										"TREAT2 to full " + "Ects = " + BrutalGame.troop10.get(least2).getEcts());
							}
							}
						}

					} else {
						System.out.println("TREAT FAILED!");
					}
				}
			}
		}

	}

}
