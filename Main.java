package application;
	
import java.io.IOException;

import application.Play;
import engine.Game;
import javafx.application.Application;
//import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import javafx.scene.control.ChoiceBox;

public class Main extends Application {
	static Hero selected = null;
	@Override
	public void init() throws IOException {
		Game.loadHeroes("Heroes.csv");
	}
	public void start(Stage primaryStage) {
		VBox root = new VBox(); //layout of first page
		VBox play = new VBox(); //layout of game itself page
		
		root.setBackground(new Background(new BackgroundImage( new Image("bg.jpg"),
				BackgroundRepeat.REPEAT, 
				BackgroundRepeat.NO_REPEAT, 
				new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                )));
		root.setAlignment(Pos.CENTER);
		
		ImageView heading = new ImageView(new Image("title1.jpg"));
		heading.setTranslateY(-20);
		/*
		Label heading = new Label("The Last Of Us - Legacy"); //Title of Game
		heading.setFont(Font.font(70));
		heading.setOpacity(1);
		heading.setTextAlignment(TextAlignment.CENTER);
		heading.setTextFill(Color.WHITE);
		heading.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		heading.setPadding(new Insets(20,20,20,20));*/
		
		HBox selectHeroes = new HBox(); //The horizontal list of heroes
		selectHeroes.setPadding(new Insets(20,20,20,20));
		selectHeroes.setSpacing(20);
		selectHeroes.setMinHeight(250);
		selectHeroes.setAlignment(Pos.BASELINE_CENTER);
		selectHeroes.setBackground(new Background(new BackgroundImage( new Image("woodbg.png"),
				BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.REPEAT, 
				new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                )));
		
		Label noSelectMsg = new Label("");;
		for(int i = 0; i < Game.availableHeroes.size(); i++) {
			int index = i;
			VBox eachHero = new VBox();
			eachHero.setSpacing(10);
			eachHero.setBackground(new Background(new BackgroundImage( new Image("phone.jpg"),
					BackgroundRepeat.NO_REPEAT, 
					BackgroundRepeat.NO_REPEAT, 
					new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
					new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
	                )));
			eachHero.setBorder(Border.stroke(Color.BLACK));
			eachHero.setAlignment(Pos.CENTER);
			eachHero.setPadding(new Insets(10,10,10,10));
			Label eachDetail = new Label("");
			noSelectMsg = new Label("");
			eachDetail.setFont(Font.font(20));
			Button b1 = new Button(Game.availableHeroes.get(index).getName());
			b1.setWrapText(true);
			b1.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
			b1.setAlignment(Pos.BASELINE_CENTER);
			b1.setOnMouseClicked(new EventHandler <Event>(){
				@Override
				public void handle(Event event) {
					selected = Game.availableHeroes.get(index);
				}
				});
			String details = "Damage: " + Game.availableHeroes.get(index).getAttackDmg() + "\n";
			details += "HP: " + Game.availableHeroes.get(index).getMaxHp() + "\n" 
					+ "Max. Actions: " + Game.availableHeroes.get(index).getMaxActions() + "\n";
			if(Game.availableHeroes.get(index) instanceof Fighter) {
				details += "Fighter";
			} else if(Game.availableHeroes.get(index) instanceof Medic) {
				details += "Medic";
			} else {
				details += "Explorer";
			}
			Image remainImg;
			if(Game.availableHeroes.get(i) instanceof Fighter)
				remainImg = new Image("fighter.jpg");
			else if(Game.availableHeroes.get(i) instanceof Medic)
				remainImg = new Image("medic.jpg");
			else 
				remainImg = new Image("explorer.png");
			ImageView remainImgView = new ImageView(remainImg);
			remainImgView.setFitHeight(70);
			remainImgView.setFitWidth(70);
			eachDetail = new Label(details);
			eachDetail.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
			eachDetail.setWrapText(true);
			eachDetail.setTextAlignment(TextAlignment.CENTER);
			eachHero.getChildren().addAll(b1, remainImgView, eachDetail);
			selectHeroes.getChildren().addAll(eachHero);
		}
		
		Label finalNoSelectMsg = noSelectMsg;
		ImageView startImgView = new ImageView(new Image("start.png"));
		startImgView.setFitHeight(100);
		startImgView.setFitWidth(120);
		Button startGame = new Button("",startImgView);
		startGame.setBackground(null);
		startGame.setOnMouseClicked(new EventHandler <Event>(){
			@Override
			public void handle(Event event) {
				if(selected != null) {
					Game.startGame(selected);
					Play.start();
					primaryStage.setScene(Play.play);
				}
				else 
					finalNoSelectMsg.setText("No Hero Selected");
			}
		});
		
		root.getChildren().addAll(heading,selectHeroes,startGame,noSelectMsg);
		
		Scene s = new Scene(root,1000,600);
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("The Last Of Us - Legacy");
		primaryStage.getIcons().add(new Image("1.png"));
		primaryStage.setScene(s);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void stop() {
		
	}
	
}

