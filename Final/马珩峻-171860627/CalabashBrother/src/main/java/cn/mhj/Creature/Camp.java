package cn.mhj.Creature;

import cn.mhj.Enum.FormationType;

public interface Camp {
  void setFormation(FormationType formationType);

  boolean isLoss();
}
