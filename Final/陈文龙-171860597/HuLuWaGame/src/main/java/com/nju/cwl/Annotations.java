package com.nju.cwl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 游戏名： 葫芦娃大战妖精 版本：3.0
 * 
 */


/* 下面的注解是为了检测葫芦娃等角色属性是否符合我们的预定的要求 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotations
{
	String name();
	int HP值();
	int MP值();
	int 物理伤害();
	int 法术伤害();
	int 每局可执行次数();
	int 移动距离();
	int 法术攻击距离();
	int 物理攻击距离();
	
}
