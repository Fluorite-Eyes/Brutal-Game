package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * The class Protagonist has the arguments of a student, like ects.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class Protagonist implements Comparable<Protagonist> {

	private boolean team;
	private int ects;
	private int dexterity;
	private int force;
	private int resistance;
	private int constitution;
	private int initiative;
	private int str;
	private int code;
	private boolean isReservist;
	private int type;
	private int zone;

	private Strategy strategy = null;

	/**
	 * generate arguments randomly.
	 */
	public Protagonist() {
		Random rn = new Random();
		this.ects = 30;
		this.force = rn.nextInt(11);
		this.dexterity = rn.nextInt(11);
		this.resistance = rn.nextInt(11);
		this.constitution = rn.nextInt(31);
		this.initiative = rn.nextInt(11);
		this.str = rn.nextInt(3);
		this.isReservist = false;
		this.zone = rn.nextInt(5);

	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 
	 * @param i if i == 0, then attack, if i == 1, then treat, else, random.
	 */
	public void executeStrategy(int i) {
		if (this.str == 0) {
			this.setStrategy(new Attack(this, this.getZone()));
			this.strategy.executeStrategy(this, this.getZone());
		} else if (this.str == 1) {
			this.setStrategy(new Treat(this, this.getZone()));
			this.strategy.executeStrategy(this, this.getZone());
		} else {
			Random r = new Random();
			float f1 = r.nextFloat();
			if (f1 < 0.5) {
				this.setStrategy(new Attack(this, this.getZone()));
				this.strategy.executeStrategy(this, this.getZone());
			} else {
				this.setStrategy(new Treat(this, this.getZone()));
				this.strategy.executeStrategy(this, this.getZone());
			}

		}

	}

	/**
	 * print the arguments for the student.
	 */
	public void showArguments() {
		System.out.println("TEAM: " + this.team + "\n");
		System.out.println("ECTS: " + this.ects + "\n");
		System.out.println("DEXTERITY: " + this.dexterity + "\n");
		System.out.println("FORCE: " + this.force + "\n");
		System.out.println("RESISTANCE: " + this.resistance + "\n");
		System.out.println("CONSTITUTION: " + this.constitution + "\n");
		System.out.println("INITIATIVE: " + this.initiative + "\n");
		System.out.println("STRATEGY: " + this.str + "\n");
		System.out.println("ISRESERVIST?: " + this.isReservist + "\n");
		System.out.println("TYPEE:" + this.type);
	}

	// Getters& setters

	public boolean getTeam() {
		return this.team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public void setEcts(int e) {
		this.ects = e;
	}

	public int getEcts() {
		return this.ects;
	}

	public void setInitiative(int i) {
		this.initiative = i;
	}

	public int getInitiative() {
		if (initiative < 0) {
			initiative = 0;
		}
		if (initiative > 10) {
			initiative = 10;
		}
		return this.initiative;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		if (force < 0) {
			force = 0;
		}
		if (force > 10) {
			force = 10;
		}
		this.force = force;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		if (dexterity < 0) {
			dexterity = 0;
		}
		if (dexterity > 10) {
			dexterity = 10;
		}
		this.dexterity = dexterity;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		if (resistance < 0) {
			resistance = 0;
		}
		if (resistance > 10) {
			resistance = 10;
		}
		this.resistance = resistance;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		if (constitution < 0) {
			constitution = 0;
		}
		if (constitution > 30) {
			constitution = 30;
		}
		this.constitution = constitution;
	}

	public int getStrategy() {
		return str;
	}

	public void setStrategy(int str) {
		this.str = str;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int z) {
		this.zone = z;
	}

	public boolean isReservist() {
		return isReservist;
	}

	public void setReservist(boolean reserviste) {
		this.isReservist = reserviste;
	}

	public int getCode() {
		return this.code;
	}

	@Override
	public int compareTo(Protagonist p) {
		return p.getInitiative() - this.getInitiative();
	}

}
