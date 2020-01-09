package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Enum.FormationType;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface Formation {

  static void lineUp(
      FormationType formationType, Vector<? extends FightCreature> soldiers, Range range) {
    switch (formationType) {
      case CS:
        ChangShe.lineUp(soldiers, range);
        break;
      case FS:
        FengShi.lineUp(soldiers, range);
        break;
      case FY:
        FangYuan.lineUp(soldiers, range);
        break;
      case HE:
        HengE.lineUp(soldiers, range);
        break;
      case HY:
        HeYi.lineUp(soldiers, range);
        break;
      case YL:
        YuLin.lineUp(soldiers, range);
        break;
      case YX:
        YanXing.lineUp(soldiers, range);
        break;
      case YY:
        YanYue.lineUp(soldiers, range);
        break;
    }
  }
}
