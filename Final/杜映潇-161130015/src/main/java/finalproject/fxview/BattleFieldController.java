package finalproject.fxview;

import finalproject.creatures.Creature;
import finalproject.Main;
import finalproject.worldmap.World;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BattleFieldController {
    private static final int MAPSIZE = 10;
    private static final String IMAGEPATH = "/Images/";
    private static final int HEIGHT = 80;
    private static final int WIDTH = 80;
    private static final int CHARACTERHEIGHT = 40;
    private static final int CHARACTERWIDTH = 40;
    private static final int GRAVEHEIGHT = 20;
    private static final int GRAVEWIDTH = 20;
    @FXML
    private GridPane backgroundGridPane;
    @FXML
    private GridPane characterGridPane;
    @FXML
    private GridPane graveGridPane;


    private ImageView[][] backgroundImageViews;
    private ImageView[][] characterImageViews;
    private ImageView[][] graveImageViews;

    public BattleFieldController() {
        backgroundImageViews = new ImageView[MAPSIZE][MAPSIZE];
        characterImageViews = new ImageView[MAPSIZE][MAPSIZE];
        graveImageViews = new ImageView[MAPSIZE][MAPSIZE];

        for (int i = 0; i < MAPSIZE; i++) {
            for (int j = 0; j < MAPSIZE; j++) {
                backgroundImageViews[i][j] =
                        new ImageView(new Image(Main.class.getResourceAsStream(IMAGEPATH + "Grass.jpeg")));
                backgroundImageViews[i][j].setFitWidth(WIDTH);
                backgroundImageViews[i][j].setFitHeight(HEIGHT);

                characterImageViews[i][j] = new ImageView(new Image(Main.class.getResourceAsStream(IMAGEPATH +
                        "Grass.jpeg")));
                characterImageViews[i][j].setFitHeight(CHARACTERHEIGHT);
                characterImageViews[i][j].setFitWidth(CHARACTERWIDTH);
                characterImageViews[i][j].setOpacity(0);

                graveImageViews[i][j] = new ImageView(new Image(Main.class.getResourceAsStream(IMAGEPATH + "Grave" +
                        ".jpeg")));
                graveImageViews[i][j].setFitWidth(GRAVEWIDTH);
                graveImageViews[i][j].setFitHeight(GRAVEHEIGHT);
                graveImageViews[i][j].setOpacity(0);
            }
        }
    }

    private void initialDisplay() {
        for (int i = 0; i < MAPSIZE; i++) {
            for (int j = 0; j < MAPSIZE; j++) {
                backgroundGridPane.add(backgroundImageViews[i][j], j, i);

                characterGridPane.add(characterImageViews[i][j], j, i);
                GridPane.setHalignment(characterImageViews[i][j], HPos.CENTER);
                GridPane.setValignment(characterImageViews[i][j], VPos.CENTER);

                graveGridPane.add(graveImageViews[i][j], j, i);
                GridPane.setHalignment(graveImageViews[i][j], HPos.LEFT);
                GridPane.setValignment(graveImageViews[i][j], VPos.TOP);
            }
        }
    }

    private void setCharacterImageView(String path, int i, int j) {
        if (path.compareTo(IMAGEPATH + "Grass.jpeg") == 0) {
            characterImageViews[i][j].setOpacity(0);
        } else {
            characterImageViews[i][j].setImage(new Image(Main.class.getResourceAsStream(path)));
            characterImageViews[i][j].setFitWidth(CHARACTERWIDTH);
            characterImageViews[i][j].setFitHeight(CHARACTERHEIGHT);
            characterImageViews[i][j].setOpacity(1);
        }
    }

    private void setGraveImageView(boolean hasGrave, int i, int j) {
        if (hasGrave) {
            graveImageViews[i][j].setOpacity(1);
        } else {
            graveImageViews[i][j].setOpacity(0);
        }
    }

    public void showBattleField(World world) {
        for (int i = 0; i < MAPSIZE; i++) {
            for (int j = 0; j < MAPSIZE; j++) {
                Creature creature = world.getCreature(i, j);
                if (creature == null) {
                    setCharacterImageView(IMAGEPATH + "Grass.jpeg", i, j);
                } else {
                    setCharacterImageView(IMAGEPATH + creature.getName() + ".jpeg", i, j);
                }
                setGraveImageView(world.hasGrave(i, j), i, j);
            }
        }
    }

    @FXML
    private void initialize() {
        initialDisplay();
    }
}
