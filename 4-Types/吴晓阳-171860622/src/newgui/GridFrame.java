package newgui;

import figure.feature.Emittable;
import figure.feature.Lifable;
import gui.BasicFrame;
import imgs.ImageUtils;
import item.*;
import itemEventProcessor.ItemEventProcessor;
import javafx.util.Pair;
import location.LocationUtils;
import myUtils.MyUtils;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/*
* 更新版本的地图
* 地图具有地址属性，其精细度由参数控制
* 所有的人物都按照item看待
* 所有item被放置在地图的不同位置，存储在不同的地址位置处
* 每次更新图像的时候自动显示item，并更新行为
* 每个item的图像由矩形决定，会跨越矩形的格子
 */
public class GridFrame extends BasicFrame implements Console {
    /*
    * 格坐标数量
     */
    private int gridRowNum;

    private int gridColNum;

    /*
    * 每一个格子的实际像素框大小，用于 像素坐标 与 格坐标 的转化
     */
    private Dimension gridSize = new Dimension(0, 0);

    /*
    * 格子集合，负责提供每个格子包含的item的信息
    * 与itemMap配合使用
     */
    private final Map<Point, Collection<DisplayableItem>> gridMap;

    /*
    * item集合，负责提供每个item的信息和位置
    * 与gridMap配合使用
     */
    private final Map<DisplayableItem, Rectangle> itemMap;

    /*
    * 外部装填的事件处理器，在每次paint中，处理完基础事件后，会处理这其中的事件
     */
    private final List<ItemEventProcessor> processors = new CopyOnWriteArrayList<>();

    /*
    * 默认构造器，每个格子对应1个像素点，推荐使用，因为不会出现小数问题
     */
    public GridFrame(){
        this.gridRowNum = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.gridColNum = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.gridMap = Collections.synchronizedMap(new HashMap<>());
        this.itemMap = Collections.synchronizedMap((new HashMap<>()));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        makeGridSize(screenSize);
        setSize(screenSize);
    }

    public GridFrame(int gridRowNum, int gridColNum){
        this.gridRowNum = gridRowNum;
        this.gridColNum = gridColNum;
        this.gridMap = Collections.synchronizedMap(new HashMap<>());
        this.itemMap = Collections.synchronizedMap(new HashMap<>());
        /* Caution： 为了防止出现rowNum，colNum的小数省略造成的可显示范围比实际显示范围小的问题，
        * 必须将frame范围根据二者重新设置
         */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        makeGridSize(screenSize);
        setSize(screenSize);
    }

    public GridFrame(int gridRowNum, int gridColNum, Rectangle bounds){
        this.gridRowNum = gridRowNum;
        this.gridColNum = gridColNum;
        this.gridMap = Collections.synchronizedMap(new HashMap<>());
        this.itemMap = Collections.synchronizedMap(new HashMap<>());

        Dimension screenSize = new Dimension(bounds.width, bounds.height);
        makeGridSize(screenSize);
        setBounds(new Rectangle(bounds.x, bounds.y, screenSize.width, screenSize.height));
    }

    public GridFrame(int gridRowNum,
                     int gridColNum,
                     Map<Point, Collection<DisplayableItem>> gridMap,
                     Map<DisplayableItem, Rectangle> itemMap){
        this.gridRowNum = gridRowNum;
        this.gridColNum = gridColNum;
        this.gridMap = gridMap;
        this.itemMap = itemMap;
        /* Caution： 为了防止出现rowNum，colNum的小数省略造成的可显示范围比实际显示范围小的问题，
         * 必须将frame范围根据二者重新设置
         */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        makeGridSize(screenSize);
        setSize(screenSize);
    }

    /*
    * 根据rowNum, colNum, screenSize决定gridSize
    * 由gridSize的情况调整screenSize，是前者的倍数，一般比原screenSize小
     */
    private void makeGridSize(Dimension screenSize){
        gridSize.width = screenSize.width / gridRowNum;
        gridSize.height = screenSize.height / gridColNum;
        screenSize.width = gridSize.width * gridRowNum;
        screenSize.height = gridSize.height * gridColNum;
    }

    @Override
    public synchronized int getGridRowNum() {
        return gridRowNum;
    }

    @Override
    public synchronized int getGridColNum() {
        return gridColNum;
    }

    @Override
    public synchronized Dimension getGridSize() {
        return new Dimension(gridSize);
    }

    /*
    * 改变显示大小时，需要重新更改gridSize，并检查合法性
     */
    private synchronized void resetGridSize(){
        this.gridSize = new Dimension(getSize().width / gridRowNum, getSize().height / gridColNum);
        assert(gridSize.width > 0 && gridSize.height > 0);
    }

    /*
    * 开始显示
     */

    @Override
    public synchronized void display() {
        this.loadFrame();
    }

    /*
     * 添加item
     * 将item添加到每个它属于的格子中去
     * item的大小由 其可显示的尺寸换算成的格尺寸 决定
     * 添加了对SinglePointed的item的考虑，此类item只占据1个格像素点,且位于显示图像的最中部
     * Caution: 在确定item的画幅位置时设置了偏移值，以解决边框占图像区域的问题
     */
    @Override
    public synchronized void addItem(DisplayableItem item, int xItemPos, int yItemPos){
        if((item instanceof Drawable == false) &&
                (item instanceof Imagable == false) &&
                (item instanceof Componented == false)
        ){
            throw new IllegalArgumentException();
        }

        Dimension displayDim = item.getDisplaySize();
        // 对不同类型的displayable的item进行处理
        if(item instanceof Componented){ // 添加component至frame
            JComponent comp = ((Componented) item).getComponent();
            Point pixelPos = LocationUtils.gridPosToPixelPosTopLeft(new Point(xItemPos, yItemPos), gridSize);
            pixelPos.x -= 10; pixelPos.y -= 40; // 偏移值调整
            comp.setLocation(pixelPos);
            add(comp);
        }


        Rectangle itemRect = new Rectangle();
        if(item instanceof SinglePointed){
            itemRect.setBounds(xItemPos, yItemPos, 1, 1);
        }
        else{
            Dimension itemDim = LocationUtils.pixelDimToGridDimUpperBound(displayDim, gridSize);
            itemRect.setBounds(xItemPos, yItemPos, itemDim.width, itemDim.height);
        }

        itemMap.put(item, itemRect);
        addItemInGridMap(item, itemRect);
    }

    /*
    * 添加item
    * caution: 坐标使用的是 格坐标， 不是像素坐标
    * @param rect : (x格坐标， y格坐标， width跨越横格数， height跨越纵格数）
    * 在绘画时，参照点是输入的 格坐标对应像素坐标
    * 将item添加到每个它属于的格子中去
     */
    @Override
    public synchronized void addItem(DisplayableItem item, int xItemPos, int yItemPos, int itemWidth, int itemHeight){
        if((item instanceof Drawable == false) &&
                        (item instanceof Imagable == false) &&
                        (item instanceof Componented == false)
        ){
            throw new IllegalArgumentException();
        }

        if(item instanceof Imagable){ // 调整图片尺寸
            Dimension pixelDim = LocationUtils.gridDimToPixelDim(new Dimension(itemWidth, itemHeight), gridSize);
            item.setDisplaySize(pixelDim);
        }
        if(item instanceof Componented){ // 调整component位置和尺寸，添加至frame
            JComponent comp = ((Componented) item).getComponent();
            Point pixelPos = LocationUtils.gridPosToPixelPosTopLeft(new Point(xItemPos, yItemPos), gridSize);
            Dimension pixelDim = LocationUtils.gridDimToPixelDim(new Dimension(itemWidth, itemHeight), gridSize);
            item.setDisplaySize(pixelDim);
            comp.setLocation(pixelPos.x, pixelPos.y);
            add(comp);
        }

        // 添加 格存储
        Rectangle itemRect = new Rectangle();
        if(item instanceof SinglePointed){
            itemRect.setBounds(xItemPos, yItemPos, 1, 1);
        }
        else{
            Dimension displayDim = item.getDisplaySize();
            Dimension itemDim = LocationUtils.pixelDimToGridDimUpperBound(displayDim, gridSize);
            itemRect.setBounds(xItemPos, yItemPos, itemDim.width, itemDim.height);
        }

        itemMap.put(item, itemRect);
        addItemInGridMap(item, itemRect);
    }



    /*
     * 添加事件处理器
     */
    @Override
    public synchronized int addItemEventProcessor(ItemEventProcessor processor) {
        processors.add(processor);
        return processors.size() - 1;
    }

    @Override
    public void removeItemEventProcessor(int index) {
        processors.remove(index);
    }

    /*
    * 获取某个格子中的item集
    * 不可被外界改变
     */
    @Override
    public synchronized Collection<DisplayableItem> getGridItems(int xPos, int yPos){
        Collection c = gridMap.get(new Point(xPos, yPos));
        if(c == null) return null;
        else return Collections.unmodifiableCollection(c);
    }

    /*
     * 获取item在地图中的对应位置
     * @return item的 格位置
     * 通过itemMap查找
     * 找不到时返回null
     */
    @Override
    public synchronized Rectangle getItemPos(Item item){
        Rectangle rect = itemMap.get(item);
        return rect == null ? rect : new Rectangle(rect);
    }

    @Override
    public Dimension getScreenSize() {
        return getSize();
    }

    @Override
    public void loadFrame() {
        super.loadFrame();
    }

    /*
    * 调整 视窗尺寸
    * 需要提前设置 gridRowNum 和 gridColNum
    * 会根据参数 设置 格大小（gridSize），并调整参数使得其成为格大小的整数倍
    * 然后调用原始方法改变尺寸
    */
    @Override
    public synchronized void setSize(int width, int height) {
        // 做一次重调，保证长宽是gridSize的长宽的整数倍
        gridSize.width = width / gridRowNum;
        gridSize.height = height / gridColNum;
        if(gridSize.width == 0){ // 横格数大于视窗宽度，调整到一一对应
            gridRowNum = width;
            gridSize.width = 1;
        }
        if(gridSize.height == 0){ // 纵格数大于视窗宽度， 调整到一一对应
            gridColNum = height;
            gridSize.height = 1;
        }

        width = gridSize.width * gridRowNum;
        height = gridSize.height * gridColNum;
        super.setSize(width, height);
    }

    /*
    * 调整 格数
    * 会自动调整 格大小（gridSize）
    * 会同时调整视窗尺寸，使之为格大小的整数倍
    * 应保证格数不大于 视窗尺寸
     */
    public synchronized void setGridNum(int gridRowNum, int gridColNum){
        if(gridRowNum > getWidth() || gridColNum > getHeight()){
            throw new RuntimeException();
        }
        this.gridRowNum = gridRowNum;
        this.gridColNum = gridColNum;

        setSize(getSize());
    }

    @Override
    public void clear() {
        gridMap.clear();
        itemMap.clear();
    }

    /*
    * 绘图
    * 给每个item一个相应大小的绘板，让它们在这上面绘图
    * 之后将该绘板重绘到对应位置
     */

    MyUtils.ClockChecker checker = new MyUtils.ClockChecker();
    @Override
    public synchronized void paint(Graphics g){
        checker.start();

        processEvents();
        super.paint(g);
        paintItems(g);
        repaint();
        long time = checker.check();
        time = time >= 30 ? time : 30;
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
    * 绘制item的图
    * Caution：绘图时必须用item自身的画幅决定自身的尺寸，不能用占据的 格像素数，因为一些item（如bullet）只占据1个格像素点
    * 因为一些item（特别是SinglePointed）所占据的格像素点实际上是其图像中心点/区域，而不是整个图像
    * item的绘图顺序是： Component > Image > Drawable
     */
    private synchronized void paintItems(Graphics g){
        itemMap.forEach((item, rect) -> {
            // if(item instanceof Componented){} // Do nothing，会自动显示component
            if(item instanceof Imagable){
                Image image = ((Imagable) item).getImage();
                g.drawImage(image, rect.x, rect.y, null);
            }
            if(item instanceof Drawable){
                Dimension drawDim = item.getDisplaySize();
                Graphics graphics = g.create(rect.x, rect.y, drawDim.width, drawDim.height);
                ((Drawable) item).draw(graphics);
            }
            });
    }



    /*
    * 处理所有item的事件
     */
    private synchronized void processEvents(){

        Collection<Pair<DisplayableItem, Point>> addSet = new ArrayList<>();
        Collection<Pair<DisplayableItem, Rectangle>> removeSet = new ArrayList<>();

        // 循环体中不允许出现对 itemMap中元素的添加删除操作， 允许对其的修改，对gridMap的操作
        itemMap.forEach((item, rect)->{
            processEmit(item, rect, addSet);
            processMovable(item, rect);
            processInteraction(item, rect);
            processLifable(item, rect);
            processors.forEach(processor-> processor.process(
                    item, new Rectangle(rect), addSet,
                    Consoles.UnmodifiableConsole(this)));

            checkRange(item, rect);  // 检查是否超出范围
            checkRemovable(item, rect, removeSet); // 检查 移除标记
        });


        // 实际的清除
        removeSet.forEach(itemPair-> removeItem(itemPair.getKey(), itemPair.getValue()));

        // 实际的添加
        addSet.forEach(pair -> {
            DisplayableItem item = pair.getKey();
            Point pos = pair.getValue();
            addItem(item, pos.x, pos.y);
        });

    }

    /*
    * 检查item是否处在显示界面内，若不在，则设置删除标记
     */
    private synchronized void checkRange(DisplayableItem item, Rectangle itemRect){
        Point midPos = LocationUtils.getMidPoint(itemRect);
        if(midPos.x < 0 || midPos.x >= gridRowNum || midPos.y < 0 || midPos.y >= gridColNum){
            item.setRemovable(true);
        }
    }


    /*
    * 处理item的发射事件
    * 发射的item应当为DisplayableItem,否则不处理
    * 发射的item默认初始化于发射者的中间
    * 若发射的item是Drawable的，则它的大小由它的画布大小决定
    * Caution: 添加时需要先添加到addSet上，再在之后统一加入，防止cocurrent Modification
     */
    private synchronized void processEmit(DisplayableItem item, Rectangle itemRect, Collection<Pair<DisplayableItem, Point>> addSet){
        if(item instanceof Emittable){
            if(((Emittable) item).isReadyToEmit()){
                Item emission = ((Emittable) item).emit();
                if(emission instanceof DisplayableItem){ // 可画的item
                    Point midPos = LocationUtils.getMidPoint(itemRect);
                    Dimension pixelDim = ((DisplayableItem) emission).getDisplaySize();
                    Dimension gridDim = LocationUtils.pixelDimToGridDimUpperBound(pixelDim, gridSize);
                    Point pos = new Point(midPos.x - gridDim.width / 2, midPos.y - gridDim.height / 2);
                    if(emission instanceof SinglePointed){ // 单点形式的item
                        addSet.add(new Pair<>(((DisplayableItem) emission), new Point(pos.x, pos.y)));
                    }
                    else {
                        addSet.add(new Pair<>(((DisplayableItem) emission), new Point(pos.x, pos.y)));
                    }
                }
            }
        }
    }

    /*
    * 处理item的移动事件
    * itemRect 是 itemMap中的数据
    * Caution: 必须处理剩余动量！否则会造成无法移动或移动方向错误等问题！
    * TODO： 函数体中对gridMap和itemMap都进行了修改操作，但尚未出现concurrent modification问题，有待更改
    * TODO：目前的移动速度是基于像素点的，有待适配窗口大小
     */
    private synchronized void processMovable(DisplayableItem item, Rectangle itemRect){
        if(item instanceof Movable){
            // 获取并添加初始动量
            Dimension momentum = ((Movable) item).getMomentum();
            Dimension primalMomentum = ((Movable) item).getPrimalMomentum();
            momentum.width += primalMomentum.width;
            momentum.height += primalMomentum.height;
            if(momentum.width == 0 && momentum.height == 0){ // 没有移动，存储动能
                ((Movable) item).setMomentum(momentum);
                return;
            }
            // 移动，并处理剩余动量
            // 实际移动的格子范围
            Dimension movedGridDim = LocationUtils.pixelDimToGridDimLowerBound(momentum, gridSize);
            if(movedGridDim.width == 0 && movedGridDim.height == 0){ // 没有移动，存储动能
                ((Movable) item).setMomentum(momentum);
                return;
            }
            Dimension remainMomentum = new Dimension(
                    momentum.width - movedGridDim.width * gridSize.width,
                    momentum.height - movedGridDim.height * gridSize.height);

            ((Movable) item).setMomentum(remainMomentum);

            /*
            // 现在的动量是全屏范围，需要将其调整到目前的frame尺寸下
            // Caution: 必须扩大范围转换，并且保证至少有1个像素移动，否则会造成小数点省略问题，无法移动
            movedGridDim.width *= 100; movedGridDim.height *= 100;
            movedGridDim = LocationUtils.dimTransfer(movedGridDim, Toolkit.getDefaultToolkit().getScreenSize(), getSize());
            if(movedGridDim.width > 0) movedGridDim.width += 99;
            else if(movedGridDim.width < 0) movedGridDim.width -= 99;
            if(movedGridDim.height > 0) movedGridDim.height += 99;
            else if(movedGridDim.height < 0) movedGridDim.height -= 99;
            movedGridDim.width /= 100;
            movedGridDim.height /= 100;
             */

            // 移动, 是格坐标
            int dstX = itemRect.x + movedGridDim.width;
            int dstY = itemRect.y + movedGridDim.height;

            // 设置gridMap中的元素的位置
            moveItemInGridMap(item, itemRect, dstX, dstY);
            // 设置itemMap中的元素的位置
            itemRect.x = dstX;
            itemRect.y = dstY;
            // 若item具有component，还需要修改component的位置
            if(item instanceof Componented){
                ((Componented) item).getComponent().setLocation(LocationUtils.gridPosToPixelPosTopLeft(new Point(dstX, dstY), gridSize));
            }
        }
    }

    /*
    * 处理item间的交互
    * 目前只考虑item最中间格子中相遇的item的交互，未来可能有范围交互
     */
    private synchronized void processInteraction(DisplayableItem item, Rectangle itemRect){
        if(item instanceof Interactable){
            Point midPoint = LocationUtils.getMidPoint(itemRect);
            Collection<DisplayableItem> c = gridMap.get(midPoint);
            if(c == null){ // 应该至少包含item自己
                throw new RuntimeException();
            }
            if(c.size() == 1){ // 只有自己，不存在交互
                return;
            }
            c.forEach(interactItem->{
                if(interactItem != item){
                    ((Interactable) item).interact(interactItem);
                }
            });
        }
    }

    /*
    * 处理item的生命事件
    * 若生命值下降到0，则设置删除标记
     */
    private synchronized void processLifable(DisplayableItem item, Rectangle itemRect){
        if(item instanceof Lifable){
            int lifeValue = ((Lifable) item).getLife();
            if(lifeValue <= 0){
                item.setRemovable(true);
            }
        }
    }

    /*
    * 处理item的清除事件
    * 为了防止 concurrent modification问题，先将要删除的item加入 删除item集，之后再统一进行删除
     */
    private synchronized void checkRemovable(DisplayableItem item, Rectangle itemRect, Collection<Pair<DisplayableItem, Rectangle>> removeSet){
        if(item.isRemovable()){
            removeSet.add(new Pair<>(item, itemRect));
        }
    }

    /*
    * 实际删除item，需要分别清理gridMap和itemMap
     */
    private synchronized void removeItem(DisplayableItem item, Rectangle itemRect){
        Rectangle cmpRect = itemMap.remove(item);
        assert(cmpRect.equals(itemRect)); // 两个 item范围 应当相同

        removeItemInGridMap(item, itemRect);

        if(item instanceof Componented){ // 对于component要另行删除
            remove(((Componented) item).getComponent());
        }
    }

    /*
    * 在gridMap中删除item，还需要自己处理itemMap
     */
    private synchronized void removeItemInGridMap(DisplayableItem item, Rectangle itemRect){
        if(itemRect.width == 0 || itemRect.height == 0){
            return;
        }
        Point pos = new Point(0, 0);
        for(int row = itemRect.x; row < itemRect.x + itemRect.width; row++){
            for(int col = itemRect.y; col < itemRect.y + itemRect.height; col++){
                pos.x = row; pos.y = col;
                Collection c = gridMap.get(pos);
                if(c == null){
                    throw new NullPointerException(row + "," + col);
                }
                if(c.size() == 1){ // 清除掉空的集合，减少空间消耗和查找时间消耗
                    gridMap.remove(pos);
                }
                else{
                    c.remove(item);
                }
            }
        }
    }

    /*
    * 在gridMap中删除item，还需要自己处理itemMap
    * 对于相交矩阵中的部分不处理
     */
    private synchronized void removeItemInGridMap(DisplayableItem item, Rectangle itemRect, Rectangle intersectRect){
        if(itemRect.width == 0 || itemRect.height == 0){
            return;
        }
        int x1 = intersectRect.x, x2 = intersectRect.x + intersectRect.width;
        int y1 = intersectRect.y, y2 = intersectRect.y + intersectRect.height;
        Point pos = new Point(0, 0);
        for(int row = itemRect.x; row < itemRect.x + itemRect.width; row++){
            for(int col = itemRect.y; col < itemRect.y + itemRect.height; col++){
                if(intersectRect.contains(row, col)){
                    col = y2 - 1;
                    continue;
                }
                pos.x = row; pos.y = col;
                Collection c = gridMap.get(pos);
                if(c == null){
                    throw new NullPointerException(row + "," + col);
                }
                if(c.size() == 1){ // 清除掉空的集合，减少空间消耗和查找时间消耗
                    gridMap.remove(pos);
                }
                else{
                    c.remove(item);
                }
            }
        }
    }

    /*
    * 在gridMap中添加item，还需要自己处理itemMap
     */
    private synchronized void addItemInGridMap(DisplayableItem item, Rectangle itemRect){
        if(itemRect.width == 0 || itemRect.height == 0){
            return;
        }
        for(int row = itemRect.x; row < itemRect.x + itemRect.width; row++){
            for(int col = itemRect.y; col < itemRect.y + itemRect.height; col++){
                Point pos = new Point(row, col);
                Collection c = gridMap.get(pos);
                if(c == null){
                    gridMap.put(pos, new ArrayList<>());
                    c = gridMap.get(pos);
                }
                c.add(item);
            }
        }
    }

    /*
     * 在gridMap中添加item，还需要自己处理itemMap
     * 对于相交矩阵中的部分不处理
     */
    private synchronized void addItemInGridMap(DisplayableItem item, Rectangle itemRect, Rectangle intersectRect){
        if(itemRect.width == 0 || itemRect.height == 0){
            return;
        }
        int x1 = intersectRect.x, x2 = intersectRect.x + intersectRect.width;
        int y1 = intersectRect.y, y2 = intersectRect.y + intersectRect.height;
        for(int row = itemRect.x; row < itemRect.x + itemRect.width; row++){
            for(int col = itemRect.y; col < itemRect.y + itemRect.height; col++){
                if(intersectRect.contains(row, col)){
                    col = y2 - 1;
                    continue;
                }
                Point pos = new Point(row, col);
                Collection c = gridMap.get(pos);
                if(c == null){
                    gridMap.put(pos, new ArrayList<>());
                    c = gridMap.get(pos);
                }
                c.add(item);
            }
        }
    }

    /*
    * 移动Item
    * 私有方法，被对应的公开方法所调用
    * Caution：只修改了gridMap的数据，itemMap的数据需要另外修改
    * 只允许平行移动，不能改变item尺寸
    * TODO: 目前只简单地删除添加，有待优化
     */
    private synchronized void moveItemInGridMap(DisplayableItem item, Rectangle srcRect, int dstX, int dstY){
        moveItemInGridMapImp1(item, srcRect, dstX, dstY);
    }

    /*
    * 移动Item
    * Caution：只修改了gridMap的数据，itemMap的数据需要另外修改
    * 基础移动方式： 先删除后添加
     */
    private synchronized void moveItemInGridMapImp1(DisplayableItem item, Rectangle srcRect, int dstX, int dstY){
        removeItemInGridMap(item, srcRect);
        addItemInGridMap(item, new Rectangle(dstX, dstY, srcRect.width, srcRect.height));
    }

    /*
     * 移动Item
     * Caution：只修改了gridMap的数据，itemMap的数据需要另外修改
     * 优化移动方式： 对 移动前后都存在的格点不进行修改
     * TODO: 存在问题
     */
    @Deprecated
    private synchronized void moveItemInGridMapImp2(DisplayableItem item, Rectangle srcRect, int dstX, int dstY){
        Rectangle dstRect = new Rectangle(dstX, dstY, srcRect.width, srcRect.height);
        Rectangle intersectRect = srcRect.intersection(dstRect);

        if(intersectRect.width == 0 && intersectRect.height == 0){ // 没有相交点则直接用基本移动法
            moveItemInGridMapImp1(item, srcRect, dstX, dstY);
        }
        else{
            // 删除 相交区域外 的点， 从上往下以行为单位处理
            int x1 = srcRect.x, x2 = srcRect.x + srcRect.width, y1 = srcRect.y, y2 = srcRect.y + srcRect.height;
            int x1i = intersectRect.x, x2i = intersectRect.x + intersectRect.width, y1i = intersectRect.y, y2i = intersectRect.y + intersectRect.height;
            Rectangle rect = new Rectangle();

            rect.x = x1; rect.y = y1; rect.width = x2 - x1; rect.height = y1i- y1;
            removeItemInGridMap(item, rect);

            rect.x = x1; rect.y = y1i; rect.width = x1i - x1; rect.height = y2i - y1i;
            removeItemInGridMap(item, rect);

            rect.x = x2i; rect.y = y1i; rect.width = x2 - x2i; rect.height = y2i - y1i;
            removeItemInGridMap(item, rect);

            rect.x = x1; rect.y = y2i; rect.width = x2 - x1; rect.height = y1i - y1;
            removeItemInGridMap(item, rect);

            // 添加 相交区域外 的点
            x1 = dstRect.x; x2 = dstRect.x + dstRect.width; y1 = dstRect.y; y2 = dstRect.y + dstRect.height;
            x1i = intersectRect.x; x2i = intersectRect.x + intersectRect.width; y1i = intersectRect.y; y2i = intersectRect.y + intersectRect.height;

            rect.x = x1; rect.y = y1; rect.width = x2 - x1; rect.height = y1i- y1;
            addItemInGridMap(item, rect);

            rect.x = x1; rect.y = y1i; rect.width = x1i - x1; rect.height = y2i - y1i;
            addItemInGridMap(item, rect);

            rect.x = x2i; rect.y = y1i; rect.width = x2 - x2i; rect.height = y2i - y1i;
            addItemInGridMap(item, rect);

            rect.x = x1; rect.y = y2i; rect.width = x2 - x1; rect.height = y1i - y1;
            addItemInGridMap(item, rect);
        }
    }

    /*
     * 移动Item
     * Caution：只修改了gridMap的数据，itemMap的数据需要另外修改
     * 优化移动方式： 遍历源rect和目的rect，对重复区域跳过不修改
     * TODO: 存在问题
     */
    private synchronized void moveItemInGridMapImp3(DisplayableItem item, Rectangle srcRect, int dstX, int dstY){
        Rectangle dstRect = new Rectangle(dstX, dstY, srcRect.width, srcRect.height);
        Rectangle intersectRect = srcRect.intersection(dstRect);

        if(intersectRect == null || (intersectRect.width == 0 && intersectRect.height == 0)){ // 没有相交点则直接用基本移动法
            moveItemInGridMapImp1(item, srcRect, dstX, dstY);
        }

        assert(intersectRect.width > 0 && intersectRect.height > 0);

        removeItemInGridMap(item, srcRect, intersectRect);
        addItemInGridMap(item, dstRect, intersectRect);
    }

    /*
    * 检查rect 是否在当前的dimension中
     */
    private synchronized boolean check(Rectangle rect, Dimension dimension){
        return new Rectangle(0, 0, dimension.width, dimension.height).contains(rect);
    }
}
