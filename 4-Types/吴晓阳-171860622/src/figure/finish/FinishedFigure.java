package figure.finish;

import figure.DisplayableFigure;
import figure.feature.Emittable;
import figure.feature.Lifable;
import imgs.ImageRepository;
import imgs.ImageUtils;
import item.*;
import location.LocationUtils;

import javax.swing.*;
import java.awt.*;

/*
* 基础的可装载的，可放射的，可移动的，有生命的figure
* 具有一个label，装载了其图片，可以实现gif动图
 */
public class FinishedFigure implements DisplayableFigure, Emittable, Componented, Movable, Lifable {
    private String name = "FinishedFigure";

    protected static Dimension defaultPicDim;{
        defaultPicDim = LocationUtils.virtualDimToPixelDim(new Dimension(50, 50), Toolkit.getDefaultToolkit().getScreenSize());
    }

    public FinishedFigure(){}

    public FinishedFigure(Image image){
        ((ImageIcon)((JLabel)this.getComponent()).getIcon()).setImage(image);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isReadyToEmit(){
        return false;
    }

    @Override
    public Item emit(){
        return null;
    }

    /*
    * 生命模块
    * maxLife没有与其它对象的交互，设置为protected
     */
    protected int maxLife = 100;

    public int getMaxLife(){
        return maxLife;
    }

    private int life = maxLife;

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int addLife(int life) {
        return this.life += life;
    }

    @Override
    public int diminishLife(int life) {
        this.life -= life;
        if(this.life <= 0) this.life = 0;
        return this.life;
    }

    /*
    * Componented接口, 需要自己装载组件
     */
    JComponent component;{
        JLabel label = new JLabel(new ImageIcon(ImageRepository.getImage("Dawa", defaultPicDim))) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Dimension dim = getDisplaySize();
                ImageUtils.drawLifeRect(g, new Rectangle(0, 0,
                                dim.width, dim.height / 10),
                        getLife(), getMaxLife(),
                        Color.RED, Color.GREEN);
            }
        };
        label.setSize(defaultPicDim);
        this.setComponent(label);
    }

    @Override
    public JComponent getComponent(){
        return component;
    }

    /*
    * Caution: 为了防止在组件已经进入frame后被修改，必须将访问设置控制为protected
     */
    protected void setComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public Dimension getDisplaySize(){
        return component.getSize();
    }

    @Override
    public void setDisplaySize(Dimension size){
        component.setSize(size);
    }

    /*
    * Item接口
     */

    private boolean removal = false;

    @Override
    public boolean isRemovable() {
        return removal;
    }

    @Override
    public void setRemovable(boolean flag) {
        removal = flag;
    }

    private Dimension primalMomentum = new Dimension(0, 0);

    private Dimension momentum = new Dimension(0, 0);

    @Override
    public Dimension getMomentum() {
        return new Dimension(momentum);
    }

    @Override
    public void setMomentum(Dimension momentum) {
        this.momentum.width = momentum.width;
        this.momentum.height = momentum.height;
    }

    @Override
    public Dimension getPrimalMomentum() {
        return new Dimension(primalMomentum);
    }

    @Override
    public void setPrimalMomentum(Dimension momentum) {
        this.primalMomentum.width = momentum.width;
        this.primalMomentum.height = momentum.height;
    }

}
