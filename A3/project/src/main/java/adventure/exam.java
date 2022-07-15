package adventure;
import java.util.ArrayList;
public class Monster{
	private Room myRoom;
	private ArrayList<Monster> theMonsters;
	private long id;
	private String name;
	private int hp;
	private double maxDamage;
	private double damage;

	/*
	 * Constructor initializes variables
	 */
	public Monster(){
		myRoom = new Room();
	}
	/*
	 * sets the name of the monster
	 * @param temp
	 */
	public void setName(String temp){

	}
	/*
	 * gets the name of the monster
	 * @return the name of the monster
	 */
	public String getName(){

	}
	/*
	 * sets the ID of the monster
	 * @param temp
	 */
	public void setId(long temp){

	}
	/*
	 * gets the ID of the monster
	 * @return the ID of the monster
	 */
	public long getId(){

	}
	/*
	 * Sets the room where the monster exists
	 * @param tempRoom
	 */
	public void setRoom(Room tempRoom){

	}
	/*
	 * gets the room where the monster exists
	 * @return the room where the monster exists
	 */
	public Room getRoom(){

	}
	/*
	 * adds a monster to the list of monsters
	 * @param tempRoom
	 */
	public void setMonsterList(Monster m){

	}
	/*
	 * gets the list of all the monsters in the game
	 * @return the list of monsters
	 */
	public ArrayList<Monster> getMonsterList(){

	}
	/*
	 * sets the HP of the monster
	 * @param temp
	 */
	public void setHP(int temp){

	}
	/*
	 * gets the HP of the monster
	 * @return the HP of the monster
	 */
	public int getHP(){

	}
	/*
	 * sets the Max damage the monster can deal
	 * @param temp
	 */
	public void setMaxDamage(double temp){

	}
	/*
	 * gets the max damage the monster can deal
	 * @return the max damage the monster can deal
	 */
	public double getMaxDamage(){

	}
	/*
	 * gets a random number from 1-maxDamage
	 * @return the damage the monster is going to deal
	 */
	public double getDamage(){

	}


}
