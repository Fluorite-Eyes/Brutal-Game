package entity;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import main.GamePanel;

/**
 * The class BrutalGame uses singleton design pattern, it is the class in which
 * the game state changes from title state to set state to play state.
 * 
 * @author CHEN Jiashun
 * @version 1.0
 */
public class BrutalGame extends Thread {

	public static final Scanner sc = new Scanner(System.in);

	// gameStatues =1, 0 ,-1: on/ rest/ stop
	public static int gameStatus = 1;
	public static int flag = 1;
	private int notify = 0;
	private int notifyNbr = -1;

	private int team1TookenNum = 0;
	private int team2TookenNum = 0;

	protected static int totalEtcs1 = 400;
	protected static int totalEtcs2 = 400;

	protected static int reservistNum1 = 5;
	protected static int reservistNum2 = 5;

	// the number of the rest protagonist
	protected static int restStudentNum1 = 15;
	protected static int restStudentNum2 = 15;
	protected static int restElite1 = 4;
	protected static int restElite2 = 4;
	protected static int restGobi1 = 1;
	protected static int restGobi2 = 1;

	protected static ArrayList<Protagonist> troop1 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop2 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop3 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop4 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop5 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop6 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop7 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop8 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop9 = new ArrayList<>();
	protected static ArrayList<Protagonist> troop10 = new ArrayList<>();

	protected static ArrayList<Protagonist> reservistList1 = new ArrayList<>();
	protected static ArrayList<Protagonist> reservistList2 = new ArrayList<>();

	/******************************
	 * Singleton Pattern
	 *************************************/

	private static BrutalGame brutalGame = null;

	/**
	 * singleton design pattern.
	 */
	public static BrutalGame getInstance() {
		if (brutalGame == null) {
			brutalGame = new BrutalGame();
		}
		return brutalGame;
	}

	private BrutalGame() {

	}

	public int inputNbr() {
		return this.notifyNbr;
	}

	/**
	 * a listener to get the input.
	 * 
	 * @param input
	 */
	public void click(int input) {

		switch (input) {
		case 0: {
			this.notifyNbr = 0;
			break;
		}
		case 1: {
			this.notifyNbr = 1;
			break;
		}
		case 2: {
			this.notifyNbr = 1;
			break;
		}
		case 3: {
			this.notifyNbr = 2;
			break;
		}
		case 4: {
			this.notifyNbr = 3;
			break;
		}
		case 5: {
			this.notifyNbr = 4;
			break;
		}
		case 6: {
			this.notifyNbr = 5;
			break;
		}
		}
		this.notify = 1;

	}

	/**
	 * a method to sennd team1's reservists to the field.
	 */
	public void sendUntroopedReservist1() {

		int flag = 1;

		while (true && flag == 1) {

			Iterator<Protagonist> itr = reservistList1.iterator();

			while (itr.hasNext()) {

				if (reservistList1.size() > 0) {

					Iterator<Protagonist> it = reservistList1.iterator();

					while (it.hasNext()) {

						it.next().showArguments();
						System.out.println("\n" + "****************************************");
					}

					System.out.println("Which reservist do you want to troop?" + "\n");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int nbr = this.inputNbr();

					System.out.println("Which zone do you want your reservist to fight in?" + "\n");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int zone = this.inputNbr();

					switch (zone) {
					case 1: {
						if (troop1.size() == 0) {
							System.out.println("The library is taken by team 2!");
						} else {

							troop1.add(reservistList1.get(nbr - 1));
							reservistList1.remove(reservistList1.get(nbr - 1));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 2: {
						if (troop3.size() == 0) {
							System.out.println("The student office is taken by team 2!");
						} else {
							troop3.add(reservistList1.get(nbr));
							reservistList1.remove(reservistList1.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 3: {
						if (troop5.size() == 0) {
							System.out.println("The administritive sector is taken by team 2!");
						} else {

							troop5.add(reservistList1.get(nbr));
							reservistList1.remove(reservistList1.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 4: {
						if (troop7.size() == 0) {
							System.out.println("The industry is taken by team 2!");
						} else {

							troop7.add(reservistList1.get(nbr));
							reservistList1.remove(reservistList1.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 5: {
						if (troop9.size() == 0) {
							System.out.println("The sportif is taken by team 2!");
						} else {

							troop9.add(reservistList1.get(nbr));
							reservistList1.remove(reservistList1.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					}

					System.out.println("ARE YOU FININSHED");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int r = this.inputNbr();
					if (r == 1) {
						flag = 0;
						break;
					}

				} else {
					System.out.println("There is no reservist!");
					flag = 0;
					break;
				}
			}
		}
	}

	/**
	 * a method to send team2's reservists to the field.
	 */
	public void sendUntroopedReservist2() {

		int flag = 1;

		while (true && flag == 1) {
			Iterator<Protagonist> itr = reservistList1.iterator();

			while (itr.hasNext()) {

				if (reservistList1.size() > 0) {

					Iterator<Protagonist> it = reservistList1.iterator();

					while (it.hasNext()) {

						it.next().showArguments();
						System.out.println("\n" + "****************************************");
					}

					System.out.println("Which reservist do you want to troop?");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int nbr = this.inputNbr();
					System.out.println("Which zone do you want your reservist to troop in?");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int zone = this.inputNbr();
					switch (zone) {
					case 1: {
						if (troop2.size() == 0) {
							System.out.println("The library is taken by team 1!");
						} else {

							troop2.add(reservistList2.get(nbr));
							reservistList2.remove(reservistList2.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 2: {
						if (troop4.size() == 0) {
							System.out.println("The student office is taken by team 1!");
						} else {

							troop4.add(reservistList2.get(nbr));
							reservistList2.remove(reservistList2.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 3: {
						if (troop6.size() == 0) {
							System.out.println("The administritive sector is taken by team 1!");
						} else {

							troop5.add(reservistList2.get(nbr));
							reservistList2.remove(reservistList2.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 4: {
						if (troop8.size() == 0) {
							System.out.println("The industry is taken by team 1!");
						} else {

							troop8.add(reservistList2.get(nbr));
							reservistList2.remove(reservistList2.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					case 5: {
						if (troop10.size() == 0) {
							System.out.println("The sportif is taken by team 1！");
						} else {

							troop10.add(reservistList2.get(nbr));
							reservistList2.remove(reservistList2.get(nbr));
							System.out.println("ADD DONE!");
						}
						break;
					}
					}

					System.out.println("ARE YOU FININSHED");

					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int r = this.inputNbr();

					if (r == 1) {
						flag = 0;
						break;
					}
				} else {

					System.out.println("There is no reservist!");
					flag = 0;
					break;
				}
			}
		}

	}

	/**
	 * a method to change fighter's zone for team1.
	 */
	public void reArrange1() {

		while (true) {
			System.out.println("In which zone you want to rearrange? like 1 2 3 4 5");
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int tr = this.inputNbr();
			switch (tr) {
			case 1: {
				if (troop1.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t1 = troop1.iterator();
					while (t1.hasNext()) {
						t1.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop1.get(origi);
					this.addProtagonistOfRearange(temp);
					break;
				}
			}
			case 2: {
				if (troop3.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t3 = troop3.iterator();
					while (t3.hasNext()) {
						t3.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop3.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 3: {
				if (troop5.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t5 = troop5.iterator();
					while (t5.hasNext()) {
						t5.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop5.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 4: {
				if (troop7.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t7 = troop7.iterator();
					while (t7.hasNext()) {
						t7.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop7.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 5: {
				if (troop9.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t9 = troop9.iterator();
					while (t9.hasNext()) {
						t9.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop9.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			default:
				System.out.println("WRONG INPUT!");
			}
			System.out.println("ARE YOU FININSHED?");
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int r = this.inputNbr();

			if (r == 1) {
				break;
			}

		}

	}

	/**
	 * a method to change fighter's zone for team1.
	 */
	public void reArrange2() {

		while (true) {
			System.out.println("In which zone you want to rearrange?");
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int tr = this.inputNbr();
			switch (tr) {
			case 1: {
				if (troop2.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t2 = troop2.iterator();
					while (t2.hasNext()) {
						t2.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop2.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 2: {
				if (troop4.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t4 = troop4.iterator();
					while (t4.hasNext()) {
						t4.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop4.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 3: {
				if (troop6.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t6 = troop6.iterator();
					while (t6.hasNext()) {
						t6.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop6.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 4: {
				if (troop8.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t8 = troop8.iterator();
					while (t8.hasNext()) {
						t8.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop8.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			case 5: {
				if (troop10.size() <= 1) {
					System.out.println("You can't rearrange");
					break;
				} else {
					Iterator<Protagonist> t10 = troop10.iterator();
					while (t10.hasNext()) {
						t10.next().showArguments();
					}
					System.out.println("Input a number of protagonist you want to change");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int origi = this.inputNbr();
					Protagonist temp = troop10.get(origi);
					this.addProtagonistOfRearange(temp);
					break;

				}
			}
			default:
				System.out.println("WRONG INPUT!");
			}

			System.out.println("ARE YOU FININSHED?");
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int r2 = this.inputNbr();
			if (r2 == 1) {
				break;
			}

		}

	}

	/**
	 * add the student to the zone you want.
	 * 
	 * @param p the student
	 */
	public void addProtagonistOfRearange(Protagonist p) {

		if (p.getTeam() == true) {

			System.out.println("Where do you want to add your protagonist?");

			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int aimTroop1 = this.inputNbr();

			switch (aimTroop1) {
			case 1: {
				if (troop1.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop1.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 2: {
				if (troop3.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop3.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 3: {
				if (troop5.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop5.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 4: {
				if (troop7.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop7.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 5: {
				if (troop9.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop9.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			default:
				System.out.println("WRONG INPUT");
				break;
			}

		} else {

			System.out.println("Where do you want to add your protagonist?");

			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int aimTroop1 = this.inputNbr();

			switch (aimTroop1) {
			case 1: {
				if (troop2.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop2.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 2: {
				if (troop4.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop4.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 3: {
				if (troop6.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop6.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 4: {
				if (troop8.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop8.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			case 5: {
				if (troop10.size() == 0) {
					System.out.println(" The zone is tooken!");
				} else {
					troop10.add(p);
					System.out.println("ADD DONE");
				}
				break;
			}
			default:
				System.out.println("WRONG INPUT");
				break;
			}

		}

	}

	/**
	 * start the thread.
	 */
	public void fight() {

		this.start();

	}

	/**
	 * Thread run, the batal starts, it also contains stop war, and win.
	 */
	public void run() {

		gameStatus = 1;

		while (gameStatus == 1) {

			while (gameStatus == 1) {

				ArrayList<Protagonist> tempList = new ArrayList<>();

				switch (flag) {
				case 1: {
					// rank the list
					tempList.addAll(troop1);
					tempList.addAll(troop2);
					break;
				}
				case 2: {
					tempList.addAll(troop3);
					tempList.addAll(troop4);
					break;
				}
				case 3: {
					tempList.addAll(troop5);
					tempList.addAll(troop6);
					break;
				}
				case 4: {
					tempList.addAll(troop7);
					tempList.addAll(troop8);
					break;
				}
				case 5: {
					tempList.addAll(troop9);
					tempList.addAll(troop10);
					break;
				}
				}

				Collections.sort(tempList);
				Iterator<Protagonist> it = tempList.iterator();

				switch (flag) {
				case 1: {
					while (it.hasNext()) {
						Protagonist p = it.next();
						System.out.println(
								"initiative" + p.getInitiative() + " attemp to execute strategy! in zone 1" + "\n");
						System.out.println("the size of zone 1 = " + this.getTroop1Size() + "\n");
						System.out.println("the size of zone 2 = " + this.getTroop2Size() + "\n");
						p.executeStrategy(p.getStrategy());

						if (troop2.size() == 0) {
							gameStatus = 0;
							team1TookenNum++;
							System.out.println("Troop1 takes the zone let's take a rest!");
							flag++;
							System.out.println("flag" + flag);
							tempList = null;
							break;
						}

						if (troop1.size() == 0) {
							gameStatus = 0;
							team2TookenNum++;
							System.out.println("Troop2 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						tempList = null;
					}
					break;
				}
				case 2: {
					while (it.hasNext()) {
						Protagonist p = it.next();
						System.out.println(
								"initiative" + p.getInitiative() + " attemp to execute strategy! in zone 1" + "\n");
						System.out.println("the size of zone 3 = " + this.getTroop3Size() + "\n");
						System.out.println("the size of zone 4 = " + this.getTroop4Size() + "\n");
						p.executeStrategy(p.getStrategy());

						if (troop4.size() == 0) {
							gameStatus = 0;
							team1TookenNum++;
							System.out.println("Troop3 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						if (troop3.size() == 0) {
							gameStatus = 0;
							team2TookenNum++;
							System.out.println("Troop4 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						tempList = null;
					}
					break;
				}
				case 3: {
					while (it.hasNext()) {
						Protagonist p = it.next();
						System.out.println(
								"initiative" + p.getInitiative() + " attemp to execute strategy! in zone 1" + "\n");
						System.out.println("the size of zone 5 = " + this.getTroop5Size() + "\n");
						System.out.println("the size of zone 6 = " + this.getTroop6Size() + "\n");
						p.executeStrategy(p.getStrategy());

						if (troop6.size() == 0) {
							gameStatus = 0;
							team1TookenNum++;
							System.out.println("Troop5 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						if (troop5.size() == 0) {
							gameStatus = 0;
							team2TookenNum++;
							System.out.println("Troop6 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						tempList = null;

					}
					break;
				}
				case 4: {
					while (it.hasNext()) {
						Protagonist p = it.next();
						System.out.println(
								"initiative" + p.getInitiative() + " attemp to execute strategy! in zone 1" + "\n");
						System.out.println("the size of zone 7 = " + this.getTroop7Size() + "\n");
						System.out.println("the size of zone 8 = " + this.getTroop8Size() + "\n");
						p.executeStrategy(p.getStrategy());

						if (troop8.size() == 0) {
							gameStatus = 0;
							team1TookenNum++;
							System.out.println("Troop7 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						if (troop7.size() == 0) {
							gameStatus = 0;
							team2TookenNum++;
							System.out.println("Troop8 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						tempList = null;

					}
					break;
				}
				case 5: {
					while (it.hasNext()) {
						Protagonist p = it.next();
						System.out.println(
								"initiative" + p.getInitiative() + " attemp to execute strategy! in zone 1" + "\n");
						System.out.println("the size of zone 9 = " + this.getTroop9Size() + "\n");
						System.out.println("the size of zone 10 = " + this.getTroop10Size() + "\n");
						p.executeStrategy(p.getStrategy());

						if (troop10.size() == 0) {
							gameStatus = 0;
							team1TookenNum++;
							System.out.println("Troop9 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						if (troop9.size() == 0) {
							gameStatus = 0;
							team2TookenNum++;
							System.out.println("Troop10 takes the zone let's take a rest!");
							flag++;
							tempList = null;
							break;
						}

						tempList = null;

					}
					break;
				}
				}

			}

			if (gameStatus == 0) {
				System.out.println("TEAM1 Do you want to rearrange the troop?");
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int r = this.inputNbr();
				System.out.println(r);
				if (r == 1) {
					this.reArrange1();
				}
				System.out.println("TEAM2 Do you want to rearrange the troop?");
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int r2 = this.inputNbr();
				if (r2 == 1) {
					this.reArrange2();
				}
				System.out.println("TEAM1 Do you want to send your reservist to zone?");
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int r3 = this.inputNbr();
				if (r3 == 1) {
					this.sendUntroopedReservist1();
				}
				System.out.println("TEAM2 Do you want to send your reservist to zone?");
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int r4 = this.inputNbr();
				if (r4 == 1) {
					this.sendUntroopedReservist2();
				}

			}

			if ((team1TookenNum + team2TookenNum) < 5) {
				gameStatus = 1;
			} else {
				if (team1TookenNum < team2TookenNum) {
					System.out.println("TEAM 2 WON");
					GamePanel.gameState = 4;
					gameStatus = -1;
				} else {
					System.out.println("TEAM 1 WON");
					GamePanel.gameState = 4;
					gameStatus = -1;
				}
			}
		}
	}

	public int getTroop1Size() {
		return troop1.size();
	}

	public int getTroop2Size() {
		return troop2.size();
	}

	public int getTroop3Size() {
		return troop3.size();
	}

	public int getTroop4Size() {
		return troop4.size();
	}

	public int getTroop5Size() {
		return troop5.size();
	}

	public int getTroop6Size() {
		return troop6.size();
	}

	public int getTroop7Size() {
		return troop7.size();
	}

	public int getTroop8Size() {
		return troop8.size();
	}

	public int getTroop9Size() {
		return troop9.size();
	}

	public int getTroop10Size() {
		return troop10.size();
	}

}
