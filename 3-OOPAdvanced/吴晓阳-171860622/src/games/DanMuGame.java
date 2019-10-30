package games;

import figure.Figure;
import figure.feature.Lifable;
import figure.finish.FinishedBulletEmitter;
import gui.BasicFrame;
import imgs.ImageRepository;
import item.Emissions.EmissionFireball;
import item.Finished.FinishedFireball;
import itemEventProcessor.ItemEventProcessors;
import location.Direction;
import location.LocationUtils;
import myUtils.MyUtils;
import newgui.GridFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
* 弹幕游戏基础
 */


/*
 * 表示是敌人
 * 用于子弹等物品区别敌我
 * 通常用于临时通过继承创建的类
 * 不要用于通用人物的创建
 */
interface Enemy{

}

class EnemyEmitter extends FinishedBulletEmitter implements Enemy {
}

public class DanMuGame {

    public static final int ME_WIDTH = 20;

    public static final int ME_HEIGHT = 20;

    private GridFrame frame = new GridFrame(800, 450, new Rectangle(0, 0, 800, 450));

    FinishedBulletEmitter me;

    private void addMainCharacter(){
        me = new FinishedBulletEmitter(10, new Direction(270), MyUtils.DEFAULT_STD_PACE, 300, TimeUnit.MILLISECONDS);
        me.bulletEmitter.startGenerating();
        Point mePixelPos = LocationUtils.virtualPosToPixelPos(new Point(500, 800), frame.getSize());
        Point meGridPos = LocationUtils.pixelPosToGridPos(mePixelPos, frame.getGridSize());
        frame.addItem(me, meGridPos.x, meGridPos.y);
    }

    private void addKilledCounter(){
        AtomicInteger killed = new AtomicInteger(0);
        final JButton text = new JButton(Integer.toString(killed.get()));
        text.setBounds(0, 0, 100, 100);
        BasicFrame basicFrame = new BasicFrame();
        basicFrame.setBounds(0, 0, 100, 100);
        basicFrame.setLayout(new FlowLayout());
        basicFrame.add(text);
        basicFrame.loadFrame();

        frame.addItemEventProcessor((item, itemRect, addSet, console)-> {
            if(item instanceof Figure && item instanceof Lifable){
                if(((Lifable) item).getLife() <= 0){
                    text.setText(Integer.toString(killed.incrementAndGet()));
                }
            }
        });
    }

    private void addKeyListener(){
        frame.addKeyListener(new KeyAdapter() {
            public static final int PACE = 8;
            @Override
            public void keyPressed(KeyEvent e) {
                Dimension moveDim;
                switch(e.getKeyCode()){
                    case KeyEvent.VK_SPACE:
                        moveDim = new Dimension(0, 0);
                        me.setPrimalMomentum(moveDim);
                        break;
                    case KeyEvent.VK_A:
                        moveDim = LocationUtils.virtualDimToPixelDim(new Dimension(-PACE, 0), frame.getSize());
                        me.setPrimalMomentum(moveDim);
                        break;
                    case KeyEvent.VK_D:
                        moveDim = LocationUtils.virtualDimToPixelDim(new Dimension(PACE, 0), frame.getSize());
                        me.setPrimalMomentum(moveDim);
                        break;
                    case KeyEvent.VK_W:
                        moveDim = LocationUtils.virtualDimToPixelDim(new Dimension(0, -PACE), frame.getSize());
                        me.setPrimalMomentum(moveDim);
                        break;
                    case KeyEvent.VK_S:
                        moveDim = LocationUtils.virtualDimToPixelDim(new Dimension(0, PACE), frame.getSize());
                        me.setPrimalMomentum(moveDim);
                        break;
                    case KeyEvent.VK_E:
                        Rectangle meRect = frame.getItemPos(me);
                        Point midPos = LocationUtils.getMidPoint(meRect);
                        EmissionFireball fireball = new EmissionFireball(me, 50, new Direction(270), 5);
                        frame.addItem(fireball, midPos.x, midPos.y);
                        break;
                    case KeyEvent.VK_1:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Dawa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                        break;
                    case KeyEvent.VK_2:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Erwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));                        break;
                    case KeyEvent.VK_3:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Sanwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                        break;
                    case KeyEvent.VK_4:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Siwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                        break;
                    case KeyEvent.VK_5:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Wuwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                        break;
                    case KeyEvent.VK_6:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Liuwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                        break;
                    case KeyEvent.VK_7:
                        ((JLabel)me.getComponent()).setIcon(new ImageIcon((ImageRepository.getImage("Qiwa", new Dimension(ME_WIDTH, ME_HEIGHT), LocationUtils.VIRTUAL_PIXEL_MODE))));
                    break;
                }
                super.keyTyped(e);
            }
        });
    }

    private ExecutorService executor = Executors.newCachedThreadPool();

    private void addEnemyCreationThread(){
        executor.execute(()->{
            try {
                MyUtils.ClockChecker checker = new MyUtils.ClockChecker();
                checker.start();
                Random r = new Random(System.nanoTime());
                int sleepTime = 2000;
                while(true) {
                    FinishedBulletEmitter enemy = new EnemyEmitter();
                    enemy.setPrimalMomentum(new Dimension(0, 2));
                    if(MyUtils.random.nextInt(10) == 0){
                        enemy.bulletEmitter.setCreator(arg-> new EmissionFireball(
                                enemy,
                                FinishedFireball.DEFAULT_DAMAGE,
                                null,
                                FinishedFireball.DEFAULT_SPEED)
                        );
                    }

                    frame.addItem(enemy, r.nextInt(frame.getGridRowNum()), 5);

                    Thread.sleep(sleepTime);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.exit(-1);
            }
        });
    }

    private void addDeadInfo(){
        frame.addItemEventProcessor(((item, itemRect, addSet, console) -> {
            if(me.isRemovable()){
                JButton b = new JButton("你死了!!");
                b.setBounds(frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 10, frame.getHeight() / 10);
                frame.add(b);
                return;
            }
        }));
    }

    public void startGame(){
        addMainCharacter();

        //addKilledCounter();

        addKeyListener();

        frame.addItemEventProcessor(FinishedFireball.FIREBALL_MEET_CHECK_PROCESSOR);

        frame.addItemEventProcessor(new ItemEventProcessors.FigureBoundPreventProcessor(me));

        addEnemyCreationThread();

        //addDeadInfo();

        frame.display();
    }
}

