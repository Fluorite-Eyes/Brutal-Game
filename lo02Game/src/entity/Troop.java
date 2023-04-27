package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * This class is created to generate random arguments for each students, and to
 * get the arguments set in UI.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class Troop {

	public boolean team;
	public int ects;
	public int dexterity;
	public int force;
	public int resistance;
	public int constitution;
	public int initiative;
	public int str;
	public int code;
	public boolean isReservist;
	public int type;
	public int zone;

	private String nom;
	private String programme;
	private HashMap<String, Protagonist> listProtagonists;
	private HashMap<String, Protagonist> listProtagonists2;
	private int restNbr1;
	private int restNbr2;

	public Troop() {
		listProtagonists = new HashMap<String, Protagonist>();
		listProtagonists2 = new HashMap<String, Protagonist>();
		this.restNbr1 = 15;
		this.restNbr2 = 15;
		this.initListes();
	}

	/**
	 * add randomly generated students to the HashMap.
	 */
	public void initListes() {
		for (int i = 0; i < 15; i++) {
			listProtagonists.put("Etudiant " + i, new Protagonist());
			listProtagonists.get("Etudiant " + i).setTeam(true);
			listProtagonists.get("Etudiant " + i).setType(1);
		}

		for (int i = 0; i < 4; i++) {
			listProtagonists.put("Elite " + i, new Protagonist());
			listProtagonists.get("Elite " + i).setTeam(true);
			listProtagonists.get("Elite " + i).setType(2);
		}

		listProtagonists.put("Capitaine Gobi", new Protagonist());
		listProtagonists.get("Capitaine Gobi").setTeam(true);
		listProtagonists.get("Capitaine Gobi").setType(3);

		for (int i = 0; i < 15; i++) {
			listProtagonists2.put("Etudiant " + i, new Protagonist());
			listProtagonists2.get("Etudiant " + i).setTeam(false);
			listProtagonists2.get("Etudiant " + i).setType(1);
		}

		for (int i = 0; i < 4; i++) {
			listProtagonists2.put("Elite " + i, new Protagonist());
			listProtagonists2.get("Elite " + i).setTeam(false);
			listProtagonists2.get("Elite " + i).setType(2);
		}

		listProtagonists2.put("Capitaine Gobi", new Protagonist());
		listProtagonists2.get("Capitaine Gobi").setTeam(false);
		listProtagonists2.get("Capitaine Gobi").setType(3);
	}

	/**
	 * print the arguments.
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("The fighters of team one are listed as follows  \n");
		Iterator<String> it = listProtagonists.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Protagonist pr = listProtagonists.get(key);
			str.append("++++++++++++++++++ " + key + "+++++++++++++++++ \n");
			str.append(key + "\n");
			str.append("Force " + pr.getForce() + "\n");
			str.append("Dexterite " + pr.getDexterity() + "\n");
			str.append("Resistance" + pr.getResistance() + "\n");
			str.append("Constitution " + pr.getConstitution() + "\n");
			str.append("Initiative " + pr.getInitiative() + "\n");
			str.append("StrategieIndex " + pr.getStrategy() + "\n");
			str.append("Zone Number " + pr.getZone() + "\n");
			str.append("is reservist? " + pr.isReservist() + "\n");
			str.append("++++++++++++++++++++++++++++++++++++++++++++++ \n");
		}
		str.append("\n \n \n The fighters of team two are listed as follows  \n \n \n");
		Iterator<String> it2 = listProtagonists2.keySet().iterator();
		while (it2.hasNext()) {
			String key = it2.next();
			Protagonist pr = listProtagonists2.get(key);
			str.append("++++++++++++++++++ " + key + "+++++++++++++++++ \n");
			str.append(key + "\n");
			str.append("Force " + pr.getForce() + "\n");
			str.append("Dexterite " + pr.getDexterity() + "\n");
			str.append("Resistance" + pr.getResistance() + "\n");
			str.append("Constitution " + pr.getConstitution() + "\n");
			str.append("Initiative " + pr.getInitiative() + "\n");
			str.append("StrategieIndex " + pr.getStrategy() + "\n");
			str.append("Zone Number " + pr.getZone() + "\n");
			str.append("is reservist? " + pr.isReservist() + "\n");
			str.append("++++++++++++++++++++++++++++++++++++++++++++++ \n");
		}
		return str.toString();
	}

	/**
	 * Check limits like you can only set 5 reservists.
	 */
	public void checkLimits() {
		int count = 0;
		Iterator<String> it = listProtagonists.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Protagonist p = listProtagonists.get(key);
			if (count < 5) {
				if (p.isReservist() == true) {
					count++;
				}
			} else {
				if (p.isReservist() == true) {
					p.setReservist(false);
					listProtagonists.put(key, p);
				}
			}
		}
		int count2 = 0;
		Iterator<String> it2 = listProtagonists2.keySet().iterator();
		while (it2.hasNext()) {
			String key = it2.next();
			Protagonist p = listProtagonists2.get(key);
			if (count2 < 5) {
				if (p.isReservist() == true) {
					count2++;
				}
			} else {
				if (p.isReservist() == true) {
					p.setReservist(false);
					listProtagonists2.put(key, p);
				}
			}
		}

	}

	public HashMap<String, Protagonist> getListProtagonists() {
		return listProtagonists;
	}

	public void setListeCapitaine(HashMap<String, Protagonist> listProtagonists) {
		this.listProtagonists = listProtagonists;
	}

	public HashMap<String, Protagonist> getListProtagonists2() {
		return listProtagonists2;
	}

	public void setListeCapitaine2(HashMap<String, Protagonist> listProtagonists2) {
		this.listProtagonists2 = listProtagonists2;
	}

	public int getPointsDistribuer() {
		return BrutalGame.totalEtcs1;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public void setResistance(int resistance) {
		this.ects = resistance;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	/**
	 * send the students to their battalfield.
	 */
	public void troop() {
		Iterator<String> it1 = listProtagonists.keySet().iterator();
		while (it1.hasNext()) {
			String key = it1.next();
			Protagonist p = listProtagonists.get(key);
			if (p.isReservist() == false) {
				int nullZoneNbr = 0;
				if (BrutalGame.troop1.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop3.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop5.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop7.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop9.size() == 0) {
					nullZoneNbr++;
				}
				if (restNbr1 == nullZoneNbr) {
					if (p.getZone() == 0) {
						if (BrutalGame.troop1.size() != 0) {
							int temp = p.getZone();
							p.setZone(temp + 1);
						}
					}
					if (p.getZone() == 1) {
						if (BrutalGame.troop3.size() != 0) {
							int temp = p.getZone();
							p.setZone(temp + 1);
						}
					}
					if (p.getZone() == 2) {
						if (BrutalGame.troop5.size() != 0) {
							int temp = p.getZone();
							p.setZone(temp + 1);
						}
					}
					if (p.getZone() == 3) {
						if (BrutalGame.troop7.size() != 0) {
							int temp = p.getZone();
							p.setZone(temp + 1);
						}
					}
				}
				switch (p.getZone()) {
				case 0: {
					BrutalGame.troop1.add(p);
					break;
				}
				case 1: {
					BrutalGame.troop3.add(p);
					break;
				}
				case 2: {
					BrutalGame.troop5.add(p);
					break;
				}
				case 3: {
					BrutalGame.troop7.add(p);
					break;
				}
				case 4: {
					BrutalGame.troop9.add(p);
					break;
				}
				}
				restNbr1--;
			} else {
				BrutalGame.reservistList1.add(p);
			}
		}
		Iterator<String> it2 = listProtagonists2.keySet().iterator();
		while (it2.hasNext()) {
			String key2 = it2.next();
			Protagonist p2 = listProtagonists2.get(key2);
			if (p2.isReservist() == false) {
				int nullZoneNbr = 0;
				if (BrutalGame.troop2.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop4.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop6.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop8.size() == 0) {
					nullZoneNbr++;
				}
				if (BrutalGame.troop10.size() == 0) {
					nullZoneNbr++;
				}
				if (restNbr2 == nullZoneNbr) {
					if (p2.getZone() == 0) {
						if (BrutalGame.troop2.size() != 0) {
							int temp = p2.getZone();
							p2.setZone(temp + 1);
						}
					}
					if (p2.getZone() == 1) {
						if (BrutalGame.troop4.size() != 0) {
							int temp = p2.getZone();
							p2.setZone(temp + 1);
						}
					}
					if (p2.getZone() == 2) {
						if (BrutalGame.troop6.size() != 0) {
							int temp = p2.getZone();
							p2.setZone(temp + 1);
						}
					}
					if (p2.getZone() == 3) {
						if (BrutalGame.troop8.size() != 0) {
							int temp = p2.getZone();
							p2.setZone(temp + 1);
						}
					}
				}
				switch (p2.getZone()) {
				case 0: {
					BrutalGame.troop2.add(p2);
					break;
				}
				case 1: {
					BrutalGame.troop4.add(p2);
					break;
				}
				case 2: {
					BrutalGame.troop6.add(p2);
					break;
				}
				case 3: {
					BrutalGame.troop8.add(p2);
					break;
				}
				case 4: {
					BrutalGame.troop10.add(p2);
					break;
				}
				}
				restNbr2--;
			} else {
				BrutalGame.reservistList2.add(p2);
			}
		}
	}

}