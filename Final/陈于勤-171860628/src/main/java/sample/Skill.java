package sample;

import javafx.util.Pair;

import java.util.Random;
import java.util.Vector;

public class Skill {
    private final static int SCOPE_DEFAULT = 3, EFFECT_SELF_DEFAULT = 15, EFFECT_GROUP_DEFAULT = 5,
                             TIME_DEFAULT = 10, HEALTH_DEFAULT = 5;
    int _type;
    int _cooltime;
    int _rest_time1;
    int _rest_time2;
    public Skill(int type) {
        if (type < 0 || type >= Creation.TOTAL_NUMBER) throw new RuntimeException("" + type);
        _type = type;
        _cooltime = TIME_DEFAULT;
        _rest_time1 = 0;
        _rest_time2 = TIME_DEFAULT;
    }
    public void refresh(Attribute a, Random r) {
        _rest_time1--;
        _rest_time1 -= Judge.judge(a.getAttribute(Attribute.INTELLECT), r);
        _rest_time1 = (_rest_time1 < 0) ? 0 : _rest_time1;
        _rest_time2--;
        _rest_time2 -= Judge.judge(a.getAttribute(Attribute.INTELLECT), r);
        _rest_time2 = (_rest_time2 < 0) ? 0 : _rest_time2;
    }

    private void addBuffSelfTemplate(int type, int effect, int time, Creation order) {
        Random rand = order.getRandom();
        Attribute att = order.getAttributesInfluence();
        int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
        if (success <= 0) return;
        Influence ifl = new Influence(type, effect * success, time);
        order.addInfluence(ifl);
    }
    private void addBuffGroupTemplate(int type, int scope, int effect, int time, boolean group, Creation order, GroundMap map) {
        Random rand = order.getRandom();
        Attribute att = order.getAttributesInfluence();
        int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
        if (success <= 0) return;
        Vector<Creation> targets = map.selectCreations(order, scope, group, true);
        for (int i = 0; i < targets.size(); i++) {
            Creation target = targets.get(i);
            Influence ifl = new Influence(type, effect * success, time);
            target.addInfluence(ifl);
        }
    }

    private boolean skill00Grandpa(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffGroupTemplate(Attribute.HEALTH, SCOPE_DEFAULT,
                    HEALTH_DEFAULT, TIME_DEFAULT / 2, order.getGroup(), order, map);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            Vector<Creation> targets = map.selectCreations(order, SCOPE_DEFAULT, order.getGroup(), false);
            if (targets.isEmpty() == true) return false;
            _rest_time2 = TIME_DEFAULT;
            Random rand = order.getRandom();
            Attribute att = order.getAttributesInfluence();
            int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
            if (success <= 0) return true;
            for (int i = 0; i < success && i < targets.size(); i++) {
                Creation target = (Creation) targets.get(i);
                int health = target.getAttribute(Attribute.CONSTITUTION);
                target.setAttribute(Attribute.HEALTH, health);
                target.setAlive(true);
                try {
                    target.notify();
                } catch (Exception e) {
                    ;
                }
            }
            return true;
        }
        return false;
    }
    private boolean skill01DollOne(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffSelfTemplate(Attribute.ATTACK, EFFECT_SELF_DEFAULT, TIME_DEFAULT, order);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.ATTACK, SCOPE_DEFAULT,
                    EFFECT_GROUP_DEFAULT, TIME_DEFAULT, order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill02DollTwo(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffGroupTemplate(Attribute.SPEED, (GroundMap.SIZE_X + GroundMap.SIZE_Y) / 8,
                    EFFECT_SELF_DEFAULT, TIME_DEFAULT, order.getGroup(), order, map);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.SPEED, (GroundMap.SIZE_X + GroundMap.SIZE_Y) / 8,
                    0-EFFECT_GROUP_DEFAULT, TIME_DEFAULT, !order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill03DollThree(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffSelfTemplate(Attribute.CONSTITUTION, EFFECT_SELF_DEFAULT, TIME_DEFAULT, order);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.HEALTH, 1,
                    0-HEALTH_DEFAULT, TIME_DEFAULT / 2, !order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill04DollFour(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffGroupTemplate(Attribute.ATTACK, SCOPE_DEFAULT,
                    0-EFFECT_GROUP_DEFAULT, TIME_DEFAULT, !order.getGroup(), order, map);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.CONSTITUTION, SCOPE_DEFAULT,
                    0-EFFECT_GROUP_DEFAULT, TIME_DEFAULT, !order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill05DollFive(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffGroupTemplate(Attribute.SPEED, SCOPE_DEFAULT,
                    0-EFFECT_GROUP_DEFAULT, TIME_DEFAULT, !order.getGroup(), order, map);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.INTELLECT, SCOPE_DEFAULT,
                    0-EFFECT_GROUP_DEFAULT, TIME_DEFAULT, !order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill06DollSix(Creation order, GroundMap map) {
        if (_rest_time1 == 0) {
            addBuffSelfTemplate(Attribute.SPEED, EFFECT_SELF_DEFAULT, TIME_DEFAULT, order);
            _rest_time1 = TIME_DEFAULT;
        }
        if (_rest_time2 == 0) {
            addBuffGroupTemplate(Attribute.INTELLECT, SCOPE_DEFAULT,
                    EFFECT_GROUP_DEFAULT, TIME_DEFAULT, order.getGroup(), order, map);
            _rest_time2 = TIME_DEFAULT;
        }
        return false;
    }
    private boolean skill07DollSeven(Creation order, GroundMap map) {
        if (_rest_time1 == 0 && _rest_time2 == 0) {
            Vector<Creation> targets = map.selectCreations(order, SCOPE_DEFAULT, !order.getGroup(), true);
            if (targets.isEmpty() == true) return false;
            _rest_time1 = _rest_time2 = TIME_DEFAULT;
            Random rand = order.getRandom();
            Attribute att = order.getAttributesInfluence();
            int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
            if (success <= 0) return true;
            for (int i = 0; i < targets.size(); i++) {
                Creation target = (Creation) targets.get(i);

                Attribute order_attr = order.getAttributesInfluence();
                int damage = rand.nextInt(order_attr.getAttribute(Attribute.INTELLECT));
                damage = target.attacked(success, damage);
                order.changeAttribute(Attribute.HEALTH, damage * EFFECT_SELF_DEFAULT * success / 100);
            }
        }
        return false;
    }
    private boolean skill08Scorp(Creation order, GroundMap map) {
        if (_rest_time1 == 0 && _rest_time2 == 0) {
            _rest_time1 = _rest_time2 = TIME_DEFAULT;
            Random rand = order.getRandom();
            Attribute att = order.getAttributesInfluence();
            int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
            if (success > GroundMap.MAX_CREATION_SIZE - map.getCreationSize())
                success = GroundMap.MAX_CREATION_SIZE - map.getCreationSize();
            for (int i = 0; i < success; i++) {
                Pair<Integer, Integer> pos = map.getRandomPosition();
                int pos_x = pos.getKey(), pos_y = pos.getValue();
                int number = map.getNextNumber();
                map.addCreaiont(new Creation(number, Creation.SOLDIER, pos_x, pos_y, map, order.getFileOperator()));
            }
            return true;
        }
        return false;
    }
    private boolean skill09Snake(Creation order, GroundMap map) {
        if (_rest_time1 == 0 && _rest_time2 == 0) {
            _rest_time1 = _rest_time2 = TIME_DEFAULT;
            Random rand = order.getRandom();
            Attribute att = order.getAttributesInfluence();
            int success = Judge.judge(att.getAttribute(Attribute.INTELLECT), rand);
            if (success <= 0) return true;
            int type = 0;
            while (true) {
                type = order.getRandom().nextInt(ALL_SKILL);
                if (type != 9)
                    break;
            }
            Skill skill = new Skill(type);
            return skill.executeSkill(order, map);
        }
        return false;
    }
    private boolean skill10Soldier(Creation order, GroundMap map) {
        if (_rest_time1 == 0 && _rest_time2 == 0) {
            Vector<Creation> targets = map.selectCreations(order, 1, !order.getGroup(), true);
            if (targets.isEmpty() == true) return false;
            _rest_time1 = _rest_time2 = TIME_DEFAULT;
            addBuffGroupTemplate(Attribute.HEALTH, 1, 0-HEALTH_DEFAULT,
                    TIME_DEFAULT / 2, !order.getGroup(), order, map);
        }
        return false;
    }

    public final static int ALL_SKILL = 11;

    public boolean executeSkill(Creation order, GroundMap map) {
        switch (_type) {
            case 0: return skill00Grandpa(order, map);
            case 1: return skill01DollOne(order, map);
            case 2: return skill02DollTwo(order, map);
            case 3: return skill03DollThree(order, map);
            case 4: return skill04DollFour(order, map);
            case 5: return skill05DollFive(order, map);
            case 6: return skill06DollSix(order, map);
            case 7: return skill07DollSeven(order, map);
            case 8: return skill08Scorp(order, map);
            case 9: return skill09Snake(order, map);
            case 10: return skill10Soldier(order, map);
            default: return false;
        }
    }
}
