package application;

import java.util.ArrayList;
import java.util.Collections;

public class SpielModelTTT {

	private char[][] gitter = null;
	private boolean playable = false;
	
	
	public boolean isNotPlayable() {
		return playable;
	}
	
	
	
	
	

	public SpielModelTTT() {
		gitter = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gitter[i][j] = ' ';
			}
		}
	}

	public void refreshSpielfeld() {
		gitter = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gitter[i][j] = ' ';
			}
		}
	}

	public void drankommen(int i, int j, String isUser) {
		if (isUser.equals("Benutzer")) {
			gitter[i][j] = 'X';
		} else {
			gitter[i][j] = 'O';
		}
	}

	public char getZelle(int i, int j) {
		return gitter[i][j];
	}

	public String Zufall() {
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gitter[i][j] == ' ') {
					liste.add(i + "," + j);
				}
			}
		}
		Collections.shuffle(liste);
		String s = liste.get(0);
		liste.remove(0);
		return s;
	}

	public boolean gewinnErmitteln(char a) {
		boolean gewonnen = false;
		int horizontal = 0;
		int vertical = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gitter[i][j] == a) {
					horizontal++;
				}
				if (gitter[j][i] == a) {
					vertical++;
				}
			}
			if (horizontal == 3 || vertical == 3) {
				gewonnen = true;
			}
			horizontal = 0;
			vertical = 0;
		}
		if ((gitter[0][0] == 'X' && gitter[1][1] == 'X' && gitter[2][2] == 'X')
				|| (gitter[0][2] == 'X' && gitter[1][1] == 'X' && gitter[2][0] == 'X')
				|| (gitter[0][0] == 'O' && gitter[1][1] == 'O' && gitter[2][2] == 'O')
				|| (gitter[0][2] == 'O' && gitter[1][1] == 'O' && gitter[2][0] == 'O')) {
			return true;
		}
		return gewonnen;
	}

	public void visualisieren() {
		System.out.println("-------");
		System.out.println("|" + gitter[0][0] + "|" + gitter[0][1] + "|" + gitter[0][2] + "|");
		System.out.println("-------");
		System.out.println("|" + gitter[1][0] + "|" + gitter[1][1] + "|" + gitter[1][2] + "|");
		System.out.println("-------");
		System.out.println("|" + gitter[2][0] + "|" + gitter[2][1] + "|" + gitter[2][2] + "|");
		System.out.println("-------");
	}
}