package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Game {
	/**The constant specifies the path to save the hall of fame information
	 * */
	public final static String SER_HALL_OF_FAME = "data/hall.got";
	/**The constant specifies the path to save a game
	 * */
	public final static String SER_PATH = "data/game.got";
	/**The constant specifies the path where the information about the level 0  is allowed
	 * */
	public final static String EASY_LEVEL_PATH = "data/level0.txt";
	/**The constant specifies the path where the information about the level 1 is allowed
	 * */
	public final static String MEDIUM_LEVEL_PATH = "data/level1.txt";
	/**The constant specifies the path where the information about the level 2 is allowed
	 * */
	public final static String HARD_LEVEL_PATH = "data/level2.txt";

	/**The list represents all the pacmans that the player has to catch in the corresponding level
	 * */
	private ArrayList<Pacman> pacmans;

	/**The constructor allows to initialize a new game, either from a level file or a pre-saved game
	 * @param archivePath The relative path of the file with which the game will start
	 * */
	public Game(String archivePath) throws ClassNotFoundException, IOException {
		File file = new File(archivePath); 
		if(!archivePath.equals(SER_PATH)) {
			startNewGameFromScratch(file);
		}
		else {
			if(file.exists()) {
				load();
			}
			else {
				pacmans = new ArrayList<>();
			}
		}
	}
	
	/**The method allows to save the current state of the game
	 * */
	public void save() throws IOException {
		File file = new File(SER_PATH);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pacmans);
		oos.close();
		fos.close();
	}

	/**The method allows to start the game in the same state that was saved previously
	 * */
	private void load() throws IOException, ClassNotFoundException {
		File file = new File(SER_PATH);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		pacmans = (ArrayList)ois.readObject();
		ois.close();
		fis.close();
	}
	
	/**The method allows to start a new game starting from a state in which the player has not made any progress
	 * @param level The archive that represents the difficulty level for this game
	 * */
	private void startNewGameFromScratch(File level) throws IOException {
		pacmans = new ArrayList<>();
		FileReader fr = new FileReader(level);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null) {
			if(line.trim().length() > 1 && !line.startsWith("#")) {
				String[] input = line.split("\t");
				int radius = Integer.parseInt(input[0]);
				int posX = Integer.parseInt(input[1]);
				int posY = Integer.parseInt(input[2]);
				int wt = Integer.parseInt(input[3]);
				String direction = input[4];
				int bounces = Integer.parseInt(input[5]);
				boolean caught = Boolean.parseBoolean(input[6]);
				Pacman pac = new Pacman(radius, posX, posY, wt, direction, bounces, caught);
				pacmans.add(pac);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
	
	/**The method allows to obtain the list of pacmans that are beeing displayed at this time
	 * @return An ArrayList of Pacmans
	 * */
	public ArrayList<Pacman> getPacmans() {
		return pacmans;
	}
	
	/**The method allows to know whether or not a hall member fits in the hall of fame
	 * @param score Player's score
	 * @return A boolean that indicates if the score is enough to integrate the player into the hall of fame
	 * */
	public boolean fitForTheHallOfTheFame(int score) throws IOException, ClassNotFoundException {
		boolean fit = true;
		File file = new File(SER_HALL_OF_FAME);
		if(file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ArrayList<HallMember> hallMembers = (ArrayList<HallMember>)ois.readObject();
			ArrayList<HallMember> test = (ArrayList<HallMember>) hallMembers.clone();
			HallMember hallTest = new HallMember("testHallMember", score);
			test.add(hallTest);
			test.sort(new Comparator<HallMember>() {
				@Override
				public int compare(HallMember o1, HallMember o2) {
					if(o1.getScore() > o2.getScore()) {
						return 1;
					}
					else if(o1.getScore() < o2.getScore()){
						return -1;
					}
					else {
						return 0;
					}
				}
			});
			fit = (hallMembers.size() < 10) || (test.size() == 11 && !test.get(test.size()-1).equals(hallTest));
			ois.close();
			fis.close();
		}
		return fit;
	}
	
	/**The method allows to add a new member to the hall of fame
	 * @param hm The new member of the hall of fame
	 * */
	public void addHallOfFameMember(HallMember hm) throws IOException, ClassNotFoundException {
		File file = new File(SER_HALL_OF_FAME);
		ArrayList<HallMember> hallMembers = new ArrayList<>();
		if(file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			hallMembers = (ArrayList)ois.readObject();
			ois.close();
			fis.close();
		}
		
		hallMembers.add(hm);
		hallMembers.sort(new Comparator<HallMember>() {
			@Override
			public int compare(HallMember o1, HallMember o2) {
				if(o1.getScore() > o2.getScore()) {
					return 1;
				}
				else if(o1.getScore() < o2.getScore()){
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		if(hallMembers.size() == 11) {
			hallMembers.remove(10);
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hallMembers);
		oos.close();
		fos.close();
	}
	
	/**The method allows to obtain the hall of fame members
	 * @return an ArrayList with all the members of the hall of fame
	 * */
	public ArrayList<HallMember> getHallOfFame() throws IOException, ClassNotFoundException {
		File file = new File(SER_HALL_OF_FAME);
		ArrayList<HallMember> hallMembers = null;
		if(file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			hallMembers = (ArrayList)ois.readObject();
			ois.close();
			fis.close();
		}
		return hallMembers;
	}
	
	/**The method allows to obtain the player's score
	 * @return An integer with the number of bounces that the pacmans have accumulated
	 * */
	public int getScore() {
		int score = 0;
		for(Pacman pac:pacmans) {
			score += pac.getBounces();
		}
		return score;
	}
}
