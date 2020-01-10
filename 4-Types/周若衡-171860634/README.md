# 葫芦兄弟面向对象编程——泛型 反射

## 1.creature包
### 1.1 具体对象
    Elder: 爷爷  
    CalabashBrother: 葫芦娃  
    Goblin：喽啰
    Snake：蛇精   
    Scorpion：蝎子精
### 1.2 好坏阵营
    GoodCreature类
    BadCreature类
### 1.3 抽象Creature类
    将项目要求中的所有角色可向上抽象成Creature类

## 2.factory包
### 2.1 Generator接口
    public interface Generator<T> {
        T generate(String className,int rank);
    }
### 2.2 CreatureFactory类
    工厂设计模式
    通过反射机制来生成泛型对象
    抽象为Creature对象

## 3.chessboard包
### 3.1 Cell类
    cell为棋盘chessboard的基本单元
    cell持有Creature对象

    public class Cell <T extends Creature>{
        private T creature;

        public Cell(){}
        public Cell(T creature){
            this.creature=creature;
        }
        public void setCreature(T creature){
            this.creature=creature;
        }
        public T getCreature(){
            return creature;
        }
    }
### 3.2 ChessBoard类
    利用Cell单元构成二维空间

## 4.gamemaster包
### 4.1 GameDirector类
    逻辑上程序的操作者
    完成
    工厂生产角色，布置棋盘，完成把角色放置再棋盘，移动角色和重整棋盘
    等功能

## 5.MainMethod类
    程序主方法入口
    
