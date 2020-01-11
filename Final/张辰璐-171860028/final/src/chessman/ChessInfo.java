package chessman;

import chessboard.BattleField;

import javafx.scene.image.Image;

public abstract class ChessInfo {
    //dead things
    public Image image;

    private String ImageName;

    protected String name;

    protected static BattleField field;

    public static void setField(BattleField field) {
        ChessInfo.field = field;
    }

    public Image getImage() { return image; }
    public void setImage(Image t){image= t;}

    public String getName() {
        return name;
    }

    protected boolean isKilled = false;

    public boolean isKilled() { return isKilled; }

    public void setName(String name){name = name;}
    public void setImageName(String name){ImageName = name;}
}