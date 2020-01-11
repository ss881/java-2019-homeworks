package GUI;

import Field.ImageViewManager;
import Field.TwoDSpace;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class LayoutControl {
    private TwoDSpace tds;

    @FXML
    private GridPane mainpane;

    public void setTDS(TwoDSpace tds) {
        this.tds = tds;
    }

    public void paintAllView() {
        for (int i = 0; i < tds.getAllView().size(); i++) {
            ImageViewManager tmp = tds.getAllView().get(i);
            mainpane.add(tmp.getIV(), tmp.getY(), tmp.getX());
        }
    }

    public void paintSomeView(int i) {
        ImageViewManager tmp = tds.getAllView().get(i);
        // 先删除后加入的原因
        mainpane.getChildren().remove(tmp.getIV());
        mainpane.add(tmp.getIV(), tmp.getY(), tmp.getX());
    }
}
