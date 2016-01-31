package game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.util.Duration;

import java.util.Random;

import java.io.BufferedWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class coolGame extends Application {

	// private static final DateTimeFormatter SHORT_TIME_FORMATTER = null;
	private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		CarPane01 car = new CarPane01();
		talib talibek = new talib();
		banan bananek = new banan();
		
		int sceneX = 800, sceneY = 400;
		double maxSpeedValue = 60.0;
		int curHitsValue = 0;
		int curScore = 0;
		String ver = "v.0.0.7 - ... to be continued.";

		Text curSpeed;
		Text curHits;
		Text score;

		Scene scene = new Scene(car, sceneX, sceneY);

		curSpeed = new Text();
		curSpeed.setLayoutX(15);
		curSpeed.setLayoutY(25);

		curHits = new Text();
		curHits.setLayoutX(15);
		curHits.setLayoutY(45);

		score = new Text();
		score.setLayoutX(15);
		score.setLayoutY(65);

		curHits.setText("Hits: " + curHitsValue);
		score.setText("Score: " + curScore);

		// Text Area

		/*
		 * HTMLEditor htmlEditor = new HTMLEditor();
		 * htmlEditor.setPrefHeight(100); car.getChildren().add(htmlEditor);
		 */
		/*
		 * GridPane gridpane = new GridPane(); gridpane.setPadding(new
		 * Insets(5)); gridpane.setHgap(10); gridpane.setVgap(10);
		 * 
		 * final TextArea cssEditorFld = new TextArea();
		 * cssEditorFld.setPrefRowCount(5);
		 * cssEditorFld.setPrefColumnCount(100); cssEditorFld.setWrapText(true);
		 * cssEditorFld.setPrefWidth(150);
		 * cssEditorFld.getStylesheets().add(coolGame.class.getResource(
		 * "/game/Resources/style/style.css").toString());
		 * cssEditorFld.getStyleClass().add("custom");
		 * 
		 * GridPane.setHalignment(cssEditorFld, HPos.CENTER);
		 * gridpane.add(cssEditorFld, 0, 1);
		 * 
		 * String cssDefault = "line1;\nline2;\n";
		 * cssEditorFld.setText(cssDefault); //cssEditorFld.settext
		 * car.getChildren().add(gridpane);
		 */

		// Background Image
		BackgroundImage myBG = new BackgroundImage(
				new Image(coolGame.class.getResource("/game/Resources/images/desert.png").toString(), sceneX + 30,
						sceneY + 30, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		Image sun = new Image(coolGame.class.getResource("/game/Resources/images/sun3.gif").toString());
		ImageView sunView = new ImageView(sun);
		sunView.setFitHeight(100);
		sunView.setFitWidth(120);
		sunView.setX(680);
		sunView.setY(30);
		car.getChildren().add(sunView);

		Image palm1 = new Image(coolGame.class.getResource("/game/Resources/images/palm1.gif").toString());
		ImageView palm1View = new ImageView(palm1);
		// palm1View.setFitHeight(135);
		// palm1View.setFitWidth(135);
		palm1View.setX(100);
		palm1View.setY(200);
		car.getChildren().add(palm1View);

		Image palm2 = new Image(coolGame.class.getResource("/game/Resources/images/palm2.gif").toString());
		ImageView palm2View = new ImageView(palm2);
		// palm1View.setFitHeight(135);
		// palm1View.setFitWidth(135);
		palm2View.setX(480);
		palm2View.setY(220);
		car.getChildren().add(palm2View);

		Image palm5 = new Image(coolGame.class.getResource("/game/Resources/images/palm5.gif").toString());
		ImageView palm5View = new ImageView(palm5);
		palm5View.setFitHeight(80);
		palm5View.setFitWidth(75);
		palm5View.setX(310);
		palm5View.setY(195);
		car.getChildren().add(palm5View);

		Image palm6 = new Image(coolGame.class.getResource("/game/Resources/images/palm6.gif").toString());
		ImageView palm6View = new ImageView(palm6);
		palm6View.setFitHeight(95);
		palm6View.setFitWidth(95);
		palm6View.setX(670);
		palm6View.setY(175);
		car.getChildren().add(palm6View);

		talibek.rd();
		bananek.rd();

		car.getChildren().add(talibek);
		car.getChildren().add(curSpeed);
		car.getChildren().add(curHits);
		car.getChildren().add(score);

		primaryStage.setTitle("Cool Game " + ver); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> car.move()));
		
		Timeline animShot = new Timeline(
				new KeyFrame(Duration.millis(100), e -> talibek.shot(talibek.getX1(), talibek.getY1(), talibek.getX2(),
						talibek.getY2(), car.getX1(), car.getY1(), car.getX2(), car.getY2())));
		
		Timeline animBanana = new Timeline(
				new KeyFrame(Duration.millis(100), e -> bananek.shot(bananek.getX1(), bananek.getY1(), bananek.getX2(),
						bananek.getY2(), car.getX1(), car.getY1(), car.getX2(), car.getY2())));

		animShot.setCycleCount(Timeline.INDEFINITE);
		animation.setCycleCount(Timeline.INDEFINITE);
//		animBanana.setCycleCount(Timeline.INDEFINITE);

		// speed.setCycleCount(Timeline.INDEFINITE);

		animShot.play();
		animation.play(); // Start animation
//		animBanana.play();
		
		bindToTime();

		// speed.play();

		scene.widthProperty().addListener(e -> car.setW(car.getWidth()));
		scene.heightProperty().addListener(e -> car.setH(car.getHeight()));

		car.setBackground(new Background(myBG));

		car.setOnMousePressed(e -> animation.pause());
		car.setOnMouseReleased(e -> animation.play());

		car.requestFocus();

		curSpeed.setText("Speed: " + String.valueOf(animation.getRate()) + " / " + maxSpeedValue);

		car.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {

				if (animation.getRate() == 0) {
					car.setDirection(0);
					car.setStop(1);
				} else
					car.setStop(0);

				if (car.getDirection() == 0 && animation.getRate() < maxSpeedValue)
					animation.setRate(animation.getRate() + 1);
				else if (car.getDirection() == 1)
					animation.setRate(animation.getRate() - 1);

				curSpeed.setText("Speed: " + String.valueOf(animation.getRate()) + " / " + maxSpeedValue);
				curHits.setText("Hits: " + talibek.getHits());

			} else if (e.getCode() == KeyCode.DOWN) {

				if (animation.getRate() == 0) {
					car.setDirection(1);
					car.setStop(1);
				} else
					car.setStop(0);

				if (car.getDirection() == 0)
					animation.setRate(animation.getRate() - 1);
				else if (car.getDirection() == 1 && animation.getRate() < maxSpeedValue)
					animation.setRate(animation.getRate() + 1);

				curSpeed.setText("Speed: " + String.valueOf(animation.getRate() + " / " + maxSpeedValue));
				curHits.setText("Hits: " + talibek.getHits());
			}
		});
	}

	private void bindToTime() {
		Text t = new Text();

		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0), e -> t.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class CarPane01 extends Pane {

	private double w = 800;
	private double h = 400;
	private double baseX;
	private double baseY = h;
	private int direction = 0;
	private int stop = 0;

	private Circle c1 = new Circle(baseX + 10 + 5, baseY - 10 + 5, 5);
	private Circle c2 = new Circle(baseX + 30 + 5, baseY - 10 + 5, 5);

	private Rectangle carBody = new Rectangle(baseX, baseY - 20, 50, 10);
	private Polygon carTop = new Polygon(baseX + 10, baseY - 20, baseX + 20, baseY - 30, baseX + 30, baseY - 30,
			baseX + 40, baseY - 20);

	public CarPane01() {
		carBody.setFill(Color.GREEN);
		carTop.setFill(Color.RED);
		this.getChildren().addAll(c1, c2, carBody, carTop);
	}

	public void move() {

		if (baseX > w) {
			this.baseX = -20;
		} else if (baseX == -21) {
			this.baseX = w;
		} else {
			if (direction == 0 && stop == 0)
				this.baseX += 1;
			else if (direction == 1 && stop == 0)
				this.baseX -= 1;
		}
		setValues();
	}

	public void setValues() {

		carBody.setX(baseX);
		carBody.setY(baseY - 20);

		carTop.getPoints().clear();

		carTop.getPoints().addAll(baseX + 10, baseY - 20, baseX + 20, baseY - 30, baseX + 30, baseY - 30, baseX + 40,
				baseY - 20);

		c1.setCenterX(baseX + 10 + 5);
		c1.setCenterY(baseY - 10 + 5);
		c2.setCenterX(baseX + 30 + 5);
		c2.setCenterY(baseY - 10 + 5);
	}

	public void setW(double w) {
		this.w = w;
		setValues();
	}

	public void setH(double h) {
		this.h = h;
		this.baseY = h;
		setValues();
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int d) {
		this.direction = d;
	}

	public int getStop() {
		return this.stop;
	}

	public double getX1() {
		return this.carBody.getX();
	}

	public double getY1() {
		return this.carBody.getY();
	}

	public double getX2() {
		return this.carBody.getX() + carBody.getWidth();
	}

	public double getY2() {
		return this.carBody.getY() - carBody.getHeight();
	}

	public void setStop(int s) {
		this.stop = s;
	}
}

class talib extends Pane {

	private int w = 800;
	private int h = 400;

	private int baseY = 260;
	private int baseSpeed = 3;

	private int desertLineX = 0;
	private int desertLineY = baseY;

	private Rectangle talibBody = new Rectangle(desertLineX, desertLineY - 20, 5, 5);
	BufferedWriter writer = null;

	protected int hits = 0;

	public talib() {
		talibBody.setFill(Color.RED);
		this.getChildren().addAll(talibBody);
	}

	public void shot(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {

		if (desertLineY > h) {
			desertLineY = baseY;
			rd();
			terrorist(desertLineX - 5, baseY - 40);
		} else {
			desertLineY += baseSpeed;
		}

		setValues();

		/*
		 * try (PrintWriter out = new PrintWriter(new BufferedWriter(new
		 * FileWriter("d://talibek.log", true)))) {
		 * out.println(String.valueOf(ax) + " " + String.valueOf(ay) + " " +
		 * String.valueOf(bx) + " " + String.valueOf(by) + " " +
		 * String.valueOf(cx) + " " + String.valueOf(cy) + " " +
		 * String.valueOf(dx) + " " + String.valueOf(dy));
		 * 
		 * } catch (IOException e) { // exception handling left as an exercise
		 * for the reader }
		 */

		if (collision(ax, ay, bx, by, cx, cy, dx, dy)) {

			this.start();

			Image explosion1 = new Image(
					coolGame.class.getResource("/game/Resources/images/explosion1.gif").toString());
			ImageView ex1 = new ImageView();

			ex1.setFitHeight(50);
			ex1.setFitWidth(50);
			ex1.setX(dx - 50);
			ex1.setY(dy - 50);

			Timeline wybuszek = new Timeline(
					new KeyFrame(Duration.millis(1), new KeyValue(ex1.imageProperty(), explosion1)),
					new KeyFrame(Duration.millis(600), new KeyValue(ex1.imageProperty(), null)));
			this.getChildren().addAll(ex1);

			wybuszek.play();

			hits++;
		}
	}

	public void rd() {
		Random r = new Random();
		this.desertLineX = r.nextInt(w - 1) + 1;
		this.talibBody.setTranslateX(this.desertLineX);
	}

	public void setValues() {

		talibBody.setY(desertLineY - 20);
	}

	public void setW(int w) {
		this.w = w;
		setValues();
	}

	public void setH(int h) {
		this.h = h;
		baseY = h;
		setValues();
	}

	public double getX1() {
		// talibBody.
		return talibBody.getTranslateX();
	}

	public double getY1() {
		return talibBody.getY();
	}

	public double getX2() {
		return talibBody.getTranslateX() + talibBody.getWidth();
	}

	public int getHits() {
		return hits;
	}

	public double getY2() {
		return talibBody.getY() + talibBody.getHeight();
	}

	public boolean collision(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {

		double w1 = bx - ax;
		double w2 = dx - cx;
		double h1 = by - ay;
		double h2 = dy - cy;

		double x1 = ax;
		double x2 = cx;
		double y1 = ay;
		double y2 = cy;

		if (((x1 <= x2 && x1 + w1 >= x2) || (x2 <= x1 && x2 + w2 >= x1))
				&& ((y1 <= y2 && y1 + h1 >= y2) || (y2 <= y1 && y2 + h2 >= y1)))

			return true;

		else
			return false;
	}

	// public void start(Stage primaryStage) {

	public void start() {
		Media media = new Media(coolGame.class.getResource("/game/Resources/audio/wybuch.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

	}

	public void terrorist(double dx, double dy) {

		Image t = new Image(coolGame.class.getResource("/game/Resources/images/t/t2.gif").toString());
		ImageView ivt = new ImageView();

		ivt.setFitHeight(32);
		ivt.setFitWidth(12);
		ivt.setX(dx);
		ivt.setY(dy);

		Timeline terrorist = new Timeline(new KeyFrame(Duration.millis(1), new KeyValue(ivt.imageProperty(), t)),
				new KeyFrame(Duration.millis(1500), new KeyValue(ivt.imageProperty(), null)));
		this.getChildren().addAll(ivt);

		terrorist.play();
	}
}

class banan extends Pane {

	private int w = 800;
	private int h = 400;

	private int baseY = 260;
	private int baseSpeed = 3;

	private int desertLineX = 0;
	private int desertLineY = baseY;

	private Rectangle bananBody = new Rectangle(desertLineX, desertLineY - 20, 5, 5);
	BufferedWriter writer = null;

	protected int hits = 0;

	public banan() {
		bananBody.setFill(Color.RED);
		this.getChildren().addAll(bananBody);
	}

	public void shot(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {

		if (desertLineY > h) {
			desertLineY = baseY;
			rd();
			terrorist(desertLineX - 5, baseY - 40);
		} else {
			desertLineY += baseSpeed;
		}

		setValues();

		/*
		 * try (PrintWriter out = new PrintWriter(new BufferedWriter(new
		 * FileWriter("d://talibek.log", true)))) {
		 * out.println(String.valueOf(ax) + " " + String.valueOf(ay) + " " +
		 * String.valueOf(bx) + " " + String.valueOf(by) + " " +
		 * String.valueOf(cx) + " " + String.valueOf(cy) + " " +
		 * String.valueOf(dx) + " " + String.valueOf(dy));
		 * 
		 * } catch (IOException e) { // exception handling left as an exercise
		 * for the reader }
		 */

		if (collision(ax, ay, bx, by, cx, cy, dx, dy)) {

			this.start();

			Image explosion1 = new Image(
					coolGame.class.getResource("/game/Resources/images/explosion1.gif").toString());
			ImageView ex1 = new ImageView();

			ex1.setFitHeight(50);
			ex1.setFitWidth(50);
			ex1.setX(dx - 50);
			ex1.setY(dy - 50);

			Timeline wybuszek = new Timeline(
					new KeyFrame(Duration.millis(1), new KeyValue(ex1.imageProperty(), explosion1)),
					new KeyFrame(Duration.millis(600), new KeyValue(ex1.imageProperty(), null)));
			this.getChildren().addAll(ex1);

			wybuszek.play();

			hits++;
		}
	}

	public void rd() {
		Random r = new Random();
		this.desertLineX = r.nextInt(w - 1) + 1;
		this.bananBody.setTranslateX(this.desertLineX);
	}

	public void setValues() {

		bananBody.setY(desertLineY - 20);
	}

	public void setW(int w) {
		this.w = w;
		setValues();
	}

	public void setH(int h) {
		this.h = h;
		baseY = h;
		setValues();
	}

	public double getX1() {
		// talibBody.
		return bananBody.getTranslateX();
	}

	public double getY1() {
		return bananBody.getY();
	}

	public double getX2() {
		return bananBody.getTranslateX() + bananBody.getWidth();
	}

	public int getHits() {
		return hits;
	}

	public double getY2() {
		return bananBody.getY() + bananBody.getHeight();
	}

	public boolean collision(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {

		double w1 = bx - ax;
		double w2 = dx - cx;
		double h1 = by - ay;
		double h2 = dy - cy;

		double x1 = ax;
		double x2 = cx;
		double y1 = ay;
		double y2 = cy;

		if (((x1 <= x2 && x1 + w1 >= x2) || (x2 <= x1 && x2 + w2 >= x1))
				&& ((y1 <= y2 && y1 + h1 >= y2) || (y2 <= y1 && y2 + h2 >= y1)))

			return true;

		else
			return false;
	}

	// public void start(Stage primaryStage) {

	public void start() {
		Media media = new Media(coolGame.class.getResource("/game/Resources/audio/wybuch.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

	}

	public void terrorist(double dx, double dy) {

		Image t = new Image(coolGame.class.getResource("/game/Resources/images/t/t2.gif").toString());
		ImageView ivt = new ImageView();

		ivt.setFitHeight(32);
		ivt.setFitWidth(12);
		ivt.setX(dx);
		ivt.setY(dy);

		Timeline terrorist = new Timeline(new KeyFrame(Duration.millis(1), new KeyValue(ivt.imageProperty(), t)),
				new KeyFrame(Duration.millis(1500), new KeyValue(ivt.imageProperty(), null)));
		this.getChildren().addAll(ivt);

		terrorist.play();
	}
}