package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public interface Generator<T> {
    T  produceFormation(String formationName, ArrayList<? extends Creature> camp, int symobol);
}
