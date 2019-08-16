package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TTTtest {

	@Test
	void test() {
		// ----------------------------
		// Arrange
		SpielModelTTT dasSpiel = new SpielModelTTT();

		System.out.println("----- Am Anfang -----");
		System.out.println();
		dasSpiel.visualisieren();
		System.out.println();

		// ----------------------------
		// Act
		dasSpiel.drankommen(1, 0, "Benutzer");
		dasSpiel.drankommen(0, 2, "PC");
		dasSpiel.drankommen(1, 1, "Benutzer");
		dasSpiel.drankommen(2, 0, "PC");

		System.out.println("----- Noch nicht beendet -----");
		System.out.println();
		dasSpiel.visualisieren();
		System.out.println();

		// ----------------------------
		// Assert
		assertFalse(dasSpiel.gewinnErmitteln('X'));

		// ----------------------------
		// Act
		dasSpiel.drankommen(1, 2, "Benutzer");

		System.out.println("----- Am Ende -----");
		System.out.println();
		dasSpiel.visualisieren();
		System.out.println();

		// ----------------------------
		// Assert
		assertTrue(dasSpiel.gewinnErmitteln('X'));

	}
}