public class Controller {
    public static void BubbleSort(CalabashBrother[] queue) {
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if (queue[j-1].getSeq() > queue[j].getSeq()) {
                    CalabashBrother tmp = queue[j-1];
                    queue[j-1] = queue[j];
                    queue[j] = tmp;
                }
            }
        }
    }

    public static void HeYi(Battle battle, Camp<?> camp) {
        if (camp.getLeader().getName().equals("爷爷")) {
            battle.setUnit(camp.getLeader(), 0, 14);
            int tmp = -1;
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                battle.setUnit(camp.getSoldiers().get(i), 7 + tmp * ((i + 1) / 2), 10 + ((i + 1) / 2));
                tmp = tmp * (-1);
            }
        }
        else {
            battle.setUnit(camp.getLeader(), 0, 0);
            int tmp = -1;
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                battle.setUnit(camp.getSoldiers().get(i), 7 + tmp * ((i + 1) / 2), 6 - ((i + 1) / 2));
                tmp = tmp * (-1);
            }
        }
    }

    public static void YanXing(Battle battle, Camp<?> camp) {
        if (camp.getLeader().getName().equals("爷爷")) {
            battle.setUnit(camp.getLeader(), 0, 14);
            for (int i = 0; i < camp.getSoldiers().size(); i++)
                battle.setUnit(camp.getSoldiers().get(i), 5 + i, 8 + i);
        }
        else {
            battle.setUnit(camp.getLeader(), 0, 0);
            for (int i = 0; i < camp.getSoldiers().size(); i++)
                battle.setUnit(camp.getSoldiers().get(i), 11 - i, 8 - i);
        }
    }

    public static void HengE(Battle battle, Camp<?> camp) {
        if (camp.getLeader().getName().equals("爷爷")) {
            battle.setUnit(camp.getLeader(), 0, 14);
            for (int i = 0; i < camp.getSoldiers().size(); i++)
                battle.setUnit(camp.getSoldiers().get(i), 4 + i, 11 + (i % 2));
        }
        else {
            battle.setUnit(camp.getLeader(), 0, 0);
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                battle.setUnit(camp.getSoldiers().get(i), 3 + i, 3 - (i % 2));
            }
        }
    }

    public static void ChangShe(Battle battle, Camp<?> camp) {
        if (camp.getLeader().getName().equals("爷爷")) {
            battle.setUnit(camp.getLeader(), 0, 14);
            for (int i = 0; i < camp.getSoldiers().size(); i++)
                battle.setUnit(camp.getSoldiers().get(i), 4 + i, 12);
        }
        else {
            battle.setUnit(camp.getLeader(), 0, 0);
            int tmp = -1;
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                battle.setUnit(camp.getSoldiers().get(i), 7 + tmp * ((i + 1) / 2), 2);
                tmp = tmp * (-1);
            }
        }
    }
    /*
    public static void YuLing(Battle battle, Camp<?> camp) {
    }

    public static void FangYuan(Battle battle, Camp<?> camp) {

    }

    public static void YanYue(Battle battle, Camp<?> camp) {

    }
    */
    public static void FengShi(Battle battle, Camp<?> camp) {
        if (camp.getLeader().getName().equals("爷爷")) {
            battle.setUnit(camp.getLeader(), 0, 14);
            int tmp = -1;
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                if (i < 5) {
                    battle.setUnit(camp.getSoldiers().get(i), 7 + tmp * ((i + 1) / 2), 10 + ((i + 1) / 2));
                    tmp = tmp * (-1);
                } else
                    battle.setUnit(camp.getSoldiers().get(i), 7, 6 + i);
            }
        }
        else {
            battle.setUnit(camp.getLeader(), 0, 0);
            int tmp = -1;
            for (int i = 0; i < camp.getSoldiers().size(); i++) {
                if (i < 5) {
                    battle.setUnit(camp.getSoldiers().get(i), 7 + tmp * ((i + 1) / 2), 6 - ((i + 1) / 2));
                    tmp = tmp * (-1);
                }
                else
                    battle.setUnit(camp.getSoldiers().get(i), 7, 10 - i);
            }
        }
    }
}