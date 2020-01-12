package hulubro.gui;

import hulubro.controller.Controller;
import hulubro.life.Life;
import hulubro.map.Map;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collection;

public class Layout {
    private GridPane root;
    private Map map;
    private ArrayList<ImageView> imageViewsdelete;

    public Layout(GridPane root){
        this.root=root;
        imageViewsdelete=new ArrayList<ImageView>();
    }

    public void setMap(Map map){
        this.map=map;
    }

    public void paint(){
        ArrayList<ImageShow> imageshows=map.getImageShows();
        for (ImageShow imageShow:imageshows){
            ImageView imageView=new ImageView();
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            imageView.setImage(imageShow.image);

            Node result = null;
            ObservableList<Node> childrens = root.getChildren();

            for (Node node : childrens) {
                if (GridPane.getRowIndex(node) == imageShow.x && GridPane.getColumnIndex(node) == imageShow.y) {
                    result = node;
                    break;
                }
            }

            assert result != null;
            ((StackPane) result).getChildren().add(imageView);
            imageViewsdelete.add(imageView);
            //root.add(imageView, imageShow.y, imageShow.x);
        }
        System.out.println("Painting");
    }

    public synchronized void repaint(int x, int y, int x1, int y1, Life life,boolean play) {
        System.out.println("Repainting");

        Node result = null;
        ObservableList<Node> childrens = root.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
                result = node;
                break;
            }
        }
        assert result != null;
        ((StackPane) result).getChildren().clear();

        Node result2 = null;
        ObservableList<Node> childrens2 = root.getChildren();
        for (Node node : childrens2) {
            if (GridPane.getRowIndex(node) == x1 && GridPane.getColumnIndex(node) == y1) {
                result2 = node;
                break;
            }
        }
        Image image =null;
        if ((!map.empty(x1,y1)&&play )||map.empty(x1,y1)&&!play) {
             image= new Image(Map.class.getResourceAsStream("../pic/墓碑.png"));
            if (life.alive) {
                image = life.image;
            }
        }
        ImageView imageView = new ImageView();
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setImage(image);
        assert result2 != null;
        assert image !=null;
        ((StackPane) result2).getChildren().add(imageView);
            //root.add(imageView, imageShow.y, imageShow.x);
    }
}
