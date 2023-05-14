package it.polimi.ingsw.view.GUI.Scenes;


import it.polimi.ingsw.modello.Board;
import it.polimi.ingsw.modello.Card;
import it.polimi.ingsw.modello.Library;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * this class represent the main interface of the game which handle changes of scenes after user or server requests
 */
public class LivingRoomController implements Controller {
    @FXML
    private static StackPane stackPane;
    @FXML
    private static StackPane stackPanelibrary;
    private static Scene activeScene;
    private static Controller activeController;
    private static Image image;
    private static Map<String, Image[]> tiles;
    @FXML
    private ImageView background;
    @FXML
    private GridPane gameBoard;
    @FXML
    private ImageView backgroundlibrary;
    @FXML
    private GridPane gameBoardlibrary;

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static Controller getActiveController() {
        return activeController;
    }

    public void setTiles() {
        InputStream is;
        tiles = Collections.synchronizedMap(new HashMap<>());
        Image[] imageY = new Image[3];
        is = this.getClass().getResourceAsStream("/images/item_tiles/yellow/yellow1.png");
        imageY[0] = new Image(is);
        imageY[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/yellow/yellow2.png"));
        imageY[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/yellow/yellow3.png"));
        tiles.put("yellow", imageY);
        Image[] imageP = new Image[3];
        imageP[0] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/pink/pink1.png"));
        imageP[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/pink/pink2.png"));
        imageP[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/pink/pink3.png"));
        tiles.put("pink", imageP);
        Image[] imageW = new Image[3];
        imageW[0] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/white/white1.png"));
        imageW[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/white/white2.png"));
        imageW[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/white/white3.png"));
        tiles.put("white", imageW);
        Image[] imageG = new Image[3];
        imageG[0] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/green/green1.png"));
        imageG[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/green/green2.png"));
        imageG[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/green/green3.png"));
        tiles.put("green", imageG);
        Image[] imageL = new Image[3];
        imageL[0] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/light_blue/light_blue1.png"));
        imageL[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/light_blue/light_blue2.png"));
        imageL[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/light_blue/light_blue3.png"));
        tiles.put("lightBlue", imageL);
        Image[] imageB = new Image[3];
        imageB[0] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/blue/blue1.png"));
        imageB[1] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/blue/blue2.png"));
        imageB[2] = new Image((InputStream) LivingRoomController.class.getResourceAsStream("/images/item_tiles/blue/blue3.png"));
        tiles.put("blue", imageB);
    }

    public void initialize() {
        setTiles();
        stackPanelibrary = new StackPane();
        //  backgroundlibrary.toBack();
        stackPane = new StackPane();
        background.toBack();
        //   Library l=new Library();
        Board board = new Board(2);
        board.fill(0);
        setTiles();
        createBoard(board);
        //  createLibrary(l);
        gameBoard.toFront();
    }

    public void createBoard(Board b) {
        Card[][] cards = b.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cards[i][j] != null) {
                    String color = cards[i][j].getColour();
                    if (color != "") {
                        int x = (int) Math.floor(Math.random() * (3));
                        ImageView image = new ImageView(this.tiles.get(color)[x]);
                        image.setFitWidth(35);
                        image.setFitHeight(39);
                        gameBoard.add(image, i, j);
                        image.setOnMouseClicked(event -> {
                            gameBoard.getChildren().remove(image);
                        });
                    }
                }
            }
        }
    }

    public void createLibrary(Library l) {
        Card[][] cards = l.getLibrary();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (cards[i][j] != null) {
                    String color = cards[i][j].getColour();
                    if (color != "") {
                        int x = (int) Math.floor(Math.random() * (3));
                        ImageView image = new ImageView(this.tiles.get(color)[x]);
                        image.setFitWidth(35);
                        image.setFitHeight(39);
                        gameBoardlibrary.add(image, i, j);
                    }
                }
            }
        }
    }
}