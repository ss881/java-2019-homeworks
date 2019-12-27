## 葫芦娃大作业
### 系统使用说明
1.按下**Enter键**开启一场新游戏(排列好阵型)，按下**空格键**线程执行start,游戏开始。
2.按下**L键**，选择文件，文件命名方式为月_日_年_时_分_秒.txt，即可重放一场游戏。
### 设计思想
#### 一、类设计及其相互关系
##### 类图
![类图](http://www.plantuml.com/plantuml/png/fLNDRjim3BxxANJqWEOLXc95Kmpepz0Ks8PX1zPY7Aqo5KYoCo_hky-qPSUARM1eBuh8x-D7YMJaI-V1-hBGIQR1EN5bYW987XCXZWNwdP6lOczL1Hv5PPGKLnR1fnyQFFRV6iet12yOOo462BygocUmpwJy5-CMa3Lak00zxIo2363vh5LsmgRUZGSRkv-DqGWabBkcM6QjK4kHe-SDGw-XE7UWw7Ykx5hbHZz7FcG1jcwDaI9pVCylG553juho7p_1vYxMlmAgm76MaVc_jxIurr2dQwLHR9jbGY0yt421I_MPLbfQE8Gop6AV87l9hIbfYT3EIsanxJSY2vk8hyYB9mlaT6Dz-vwo_RiUIjFrv7ZUfEazGChbZ1UYbUr-wgX1DMKxQqZzGHdQP2fyjz8GbwCVn3X4TP6_9kTtwEzBhGCfS48H1hdZMULO_X58ccAc2LkLx_nOBwACEDU4HJqZuD7vErFXaaPqoti4s_3JcIJbVkDnFpScva0BaCQjNu0idtcA1KbbCVFAq6D9SxXjgpK1Xywdsb3U_RMVL87JFnf73dsvhfGUpoRR8_Z6c6XqsOxWqZ8UPr3XNUpcZDVwedkOjhnEJdHhpClQs0DOcRQA8kUrcpDZ9ThHEzE86t9npkYTtcJ6xXkES64pg_EWtt3kFhXFyGu5_joy9PmR85Qhln-7qKsuPMSkhcAWSK3oeCTMg67duUYalz3OnISSsU7hAingrRKzyZpes0uf8b-V97PociZJNZWvVK1QPo14xaLkNmNsjvuJ8MaZOjebaYmB_Gy0)
##### 生物系统
Creature类实现Runnable。
所有生物均继承于Creature类。Good类继承Creature类，设置Principal为true表示正义一方；Evil设置Principal为false表示邪恶一方。葫芦娃和爷爷继承GOOD类，蝎子精、蛇精和小喽啰继承Evil类。
Creature类包含生物基本属性(地图位置，生命属性等)及基本方法(移动，判断生死等)。
##### UI系统
MainCanvas类实现UI刷新，使用AnimationTimer实现每秒刷新十次地图。
##### 地图系统
Field类实现地图系统，申请空间存放生物，并且包含增加生物，移动生物，获得生物方法。
##### 多线程系统
Command类中，为每个生物申请一个线程，并将生物装载进入线程。
##### 键盘监听系统
Main类中scene.setOnKeyPressed实现监听键盘。若监听到空格键，调用MainCanvas中Start函数；若监听到Enter键，调用MainCanvas中newGame函数；若监听到L键，调用MainCanvas中replay函数。
##### 文件系统
每次UI刷新时，记录生物及其所在位置信息。
##### 战斗系统
Creature中的Run函数中，只要生物未死，则循环执行先扫描四周，若有敌人则战斗，然后依据不同的时间间隔随机游走或朝最近生物行走。期间每次只允许一个生物进入战斗状态，并且游走过程中，一旦发现某个位置为空立刻走过去，即发现位置和行走置于一个加锁函数中。
#### 二、设计原则的应用
SRP原则：Field类只负责有关地图系统操作。
LSP原则：Creature基类实现生物基本功能，派生类只能拓展功能而不能改写功能。基类出现的地方都可以使用派生类替换。
在此之上，也实现了封装、继承和多态等面向对象编程方法。
#### 三、不足
1.战斗系统的单一导致游戏时间较短。
2.UI刷新没有实现动画效果。
3.程序的封装还不够，将键盘系统独立出Main类或许更好。
4.多线程的并发度不够，单纯的只让一个生物进入战斗效率低下。
### 一点感悟
1.学会了面向对象的JAVA开发
2.学习Maven构建