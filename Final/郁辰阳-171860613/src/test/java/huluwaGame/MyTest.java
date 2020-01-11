package huluwaGame;

import static org.junit.Assert.*;

import org.junit.Test;

import Creature.*;

import java.util.ArrayList;

import BattleField.*;
import Formation.Formation;
import Position.Position;

import junit.framework.TestCase;

public class MyTest {
	
	@Test
	public void test() {
		BattleField battlefield = new BattleField();
		battlefield.initBattleField("鹤翼");

		assertFalse(battlefield.posQualified(new Position(-1,-1)));
		assertFalse(battlefield.posQualified(new Position(11,16)));
		assertTrue(battlefield.emptyplace(battlefield.getHuluwa().get(0).getPosition().left()));
		assertFalse(battlefield.emptyplace(battlefield.getHuluwa().get(0).getPosition().down()));
		assertTrue(battlefield.isOpposite(battlefield.getHuluwa().get(0).getPosition(), battlefield.getXiezijing().getPosition()));
		assertFalse(battlefield.isOpposite(battlefield.getHuluwa().get(0).getPosition(), battlefield.getHuluwa().get(1).getPosition()));
		System.out.println("Test Pass");
	}

}
