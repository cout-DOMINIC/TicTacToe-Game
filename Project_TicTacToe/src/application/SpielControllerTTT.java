package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpielControllerTTT implements Initializable {

	@FXML
	GridPane gridPaneGitter;

	@FXML
	Pane paneZelle00;

	@FXML
	Pane paneZelle01;

	@FXML
	Pane paneZelle02;

	@FXML
	Pane paneZelle10;

	@FXML
	Pane paneZelle11;

	@FXML
	Pane paneZelle12;

	@FXML
	Pane paneZelle20;

	@FXML
	Pane paneZelle21;

	@FXML
	Pane paneZelle22;

	@FXML
	Text textGewonnen;

	@FXML
	Button musicButton;

	@FXML
	Button musicStopButton;

	@FXML
	Button resetButton;

	private SpielModelTTT dasSpielTTT = null;
	private Media media = null;
	private MediaPlayer mediaPlayer = null;

	private void gitterAktualisieren() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Pane paneZelle = (Pane) gridPaneGitter.lookup("#paneZelle" + i + j);
				char c = dasSpielTTT.getZelle(i, j);
				if (paneZelle.getChildren().isEmpty() && c != ' ') {
					Text text = new Text();
					text.setText("" + c);
					Font font = new Font(80);
					text.setFont(font);
					text.setFill(Color.BLACK);
					text.setX(48);
					text.setY(90);
					paneZelle.getChildren().add(text);
				}
			}
		}

		if (dasSpielTTT.gewinnErmitteln('X')) {
			textGewonnen.setText("DISMEMBERED");
			dasSpielTTT.isNotPlayable();
		}

		else if (dasSpielTTT.gewinnErmitteln('O')) {
			textGewonnen.setText("DOOMED");
			dasSpielTTT.isNotPlayable();
		}
	}

	public void handlePaneZelle(MouseEvent event) {
		Node zelle = (Node) event.getSource();
		String fxId = zelle.getId();
		int length = fxId.length();
		int i = Integer.parseInt(fxId.substring(length - 2, length - 1));
		int j = Integer.parseInt(fxId.substring(length - 1, length));
		dasSpielTTT.drankommen(i, j, "Benutzer");

		gitterAktualisieren();

		if (dasSpielTTT.gewinnErmitteln('X') == false) {
			String info = dasSpielTTT.Zufall();
			String[] array = info.split(",");
			int pci = Integer.parseInt(array[0]);
			int pcj = Integer.parseInt(array[1]);
			dasSpielTTT.drankommen(pci, pcj, "PC");
			gitterAktualisieren();
		}
	}

	public void handleButtonPlay(ActionEvent event) {
		mediaPlayer.play();
	}

	public void handleButtonStop(ActionEvent event) {
		mediaPlayer.stop();
	}

	public void handleButtonReset(ActionEvent event) {
		dasSpielTTT.refreshSpielfeld();
		gitterAktualisieren();
		resetView();
	}

	public void resetView() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Pane paneZelle = (Pane) gridPaneGitter.lookup("#paneZelle" + i + j);
				paneZelle.getChildren().clear();
			}
		}
		textGewonnen.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dasSpielTTT = new SpielModelTTT();

		media = new Media(getClass().getResource("RipTear.m4a").toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(false);
		musicButton.setOnAction(this::handleButtonPlay);
		musicStopButton.setOnAction(this::handleButtonStop);

		resetButton.setOnAction(this::handleButtonReset);

		paneZelle00.setOnMouseClicked(this::handlePaneZelle);
		paneZelle01.setOnMouseClicked(this::handlePaneZelle);
		paneZelle02.setOnMouseClicked(this::handlePaneZelle);
		paneZelle10.setOnMouseClicked(this::handlePaneZelle);
		paneZelle11.setOnMouseClicked(this::handlePaneZelle);
		paneZelle12.setOnMouseClicked(this::handlePaneZelle);
		paneZelle20.setOnMouseClicked(this::handlePaneZelle);
		paneZelle21.setOnMouseClicked(this::handlePaneZelle);
		paneZelle22.setOnMouseClicked(this::handlePaneZelle);
	}
}