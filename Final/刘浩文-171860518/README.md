#   葫芦娃大战妖精 
刘浩文 171860518
##   游戏运行
-   开始战斗：空格键
-   重放战斗：L键弹窗，选择.gamelog文件进行重放
##   游戏代码细节
UML类图：

-   Creature类：存放参与战斗的葫芦娃等战斗人员【生物都是近战】实现Runnable接口
    *  x y：坐标
    * id：每个生物唯一的id，在寻找敌人以及记录血量，攻击力等时候会用到
    * icon：游戏内部地图上记录的方法，与id对应
    *  goodPerson：正邪，判断周围敌人时用到
    *  Creature类static成员记录一些成员其他需要被别的线程读取的信息，如位置，血量，攻击力等
    *  BUFF：在老爷爷生存的时候，为葫芦娃加buff，老爷爷死后葫芦娃的攻防降低。蛇精也有类似功能，BUFF元素记录BUFF是否有效。
    *  rn，bn：通知UI刷新
    *  lock：实现线程同步的锁
    *  whoturn：为公平期间每个生物轮流行动，这个成员判断是否轮到某个生物，没有轮到的话这个生物阻塞自己并且notifyAll
    *   角色每走一步，判断周围是否有活着的敌人，有的话进行攻击，敌人扣血。
        如果HP小于0，判断此角色死亡，之后的线程轮替轮到它会立刻notifyAll。

-   Controller类：管理游戏运行的类
    *  各个Thread：创建Creature的生物线程并且进行配置
    * replayer：在重放时会用到的线程；由于直接在Main UI类里没办法做到走一步暂停一段时间的效果（界面卡死），需要另开一个线程进行重放

-   Main类：javafx的类
    *  gameLog：游戏文件输入输出时用到的变量
    *  start：设置各个角色的图像，位置，注册观察者
    *  setOnKeyPressed：lambda表达式，检测输入的键盘按键，根据不同的按键执行Controller的不同方法

-   Notifier类：小型类，通知UI刷新以及记录游戏内容时会用到

-   MyObserver/MyObservable：自己写的观察者模式接口
    *  registerObserver:注册观察者
    *  Notify：通知观察者信息
    *  Update：UI刷新
    *  Print：输出gameLog

-   Replayer类：靠读取文件通知UI进行刷新，具体方法和Creature类似，实现Runnable接口

##  多线程
-    每个生物体一个线程，读取文件重放有一个线程，UI有一个线程。
    线程之间数据访问使用static数组，类之间共同读写
    通知UI刷新的办法使用观察者模式
-   文件I/O格式为 
    *   MOV id 目标横坐标 目标纵坐标
    *   KIL 攻击者id 被攻击者id


##  面向对象
-   封装：将游戏中的人物数据封装在一起，Controller类配置具体人物的能力值，UI不参与游戏本身的操作，只注册观察者。
-   继承：考虑到生物体之间都有id，位置信息，攻击力等等，行为也基本一样，如果再设计一个好人/坏人类，那么子类并没有什么新的方法或成员变量，而显得多此一举。所以，在这个游戏中涉及到继承的有我设计的观察者模式的接口，以及javafx UI继承的Application等。
-   多态：javafx中图片图层设置，文件读写等。

##  异常处理try-catch
-    很多方法都会抛出异常。如果是线程内部异常，考虑到多线程比较复杂，一般都是就地解决；
    文件读取异常则会判断文件是否为null进行处理。

    gameLog=new File("1.gamelog");
                int fileNum=1;
                while(gameLog.exists())
                {
                    fileNum++;
                    gameLog=new File((fileNum+"") +".gamelog");
                }
                try {
                    gameLog.createNewFile();
                } catch (IOException ex) {
                    System.out.println("File not exist!");
                    ex.printStackTrace();
                }

##  泛型
-    在注册观察者模式的时候，使用ArrayList进行注册。
    处理UI角色图层的时候也用ArrayList进行管理。

##  注解与测试
-    测试类huluTest测试我设计的方向决定函数是否正确。
    方向决定函数根据当前生物的xy坐标判断它现在是否太靠近边缘，
    如果是的话，那么在“抽奖池“directPool里多放一些代表反方向的数字，否则均匀放置。
    这样做可以让生物尽量集中在中心进行混战，在人少的时候抑制出现满图乱跑但是不打人的现象。
    在测试方法中用了注解before test after和suppressWarning。
    由于决定函数是要返回一个数字的，如果靠返回数字来判断是否生成了正确的抽奖池，那么需要重复很多遍获得大量结果，并且也不一定准确，所以我在设计的时候靠传入函数的一个bool变量判断是否在测试，如果是的话，把抽奖池输出到控制台。
    在huluTest里，使用
        private PrintStream ps = null;
        private ByteArrayOutputStream bs = null;
    这两个工具获得控制台的输出，
    
    再使用断言：
    assertEquals(s0,bs.toString().trim().replace("\r",""));//windows to mac
    来判断是不是正确。（考虑到不同系统的换行符不一样，稍微改了一点保持在windows不会出错）
    *   before：
        配置PrintStream与ByteArrayOutputStream
    *   Test：
        执行测试
    *   After：
        解除相关绑定

##  设计原则以及设计模式
-   SRP 单一职责原则：
        UI类不妨碍游戏的进行，只接受游戏传来的更新UI信号
-   ISP 接口隔离原则
        Controller类不参与UI刷新的过程，因此没有implement观察者模式的接口
-   使用了观察者模式来通知UI刷新
-   UI中各个组件体现了组合模式