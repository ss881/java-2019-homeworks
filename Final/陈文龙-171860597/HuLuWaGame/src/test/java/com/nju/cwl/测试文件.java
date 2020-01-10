package com.nju.cwl;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;

public class 测试文件
{
	/**
	 * 下面将对项目进行一些单元测试
	 * 比如读取文件资源测试
	 * 相关的逻辑测试等等
	 * 
	 */
	
	@Test
	public void 测试分辨率资源文件()
	{
		try
		{
			File file = new File("src/main/resources/分辨率文件/");
			String[] 文件名  = file.list();
			assertTrue("没有分辨率文件.txt", 文件名[0].equals("分辨率.txt"));
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
		
	}
	
	@Test
	public void 测试战斗就文件()
	{
		try
		{
			File file = new File("src/main/resources/战斗记录文件/");
			String[] 文件名  = file.list();
			assertTrue("没有任何战斗记录文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}
	
	@Test
	public void 测试attack资源文件()
	{
		try
		{
			File file = new File("src/main/resources/attack/");
			String[] 文件名  = file.list();
			assertTrue("没有attack素材文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}
	
	@Test
	public void 测试CSS资源文件()
	{
		try
		{
			File file = new File("src/main/resources/CSS/");
			String[] 文件名  = file.list();
			assertTrue("没有CSS文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}
	
	@Test
	public void 测试images资源文件()
	{
		try
		{
			File file = new File("src/main/resources/images/");
			String[] 文件名  = file.list();
			assertTrue("没有images素材文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}
	
	@Test
	public void 测试JS资源文件()
	{
		try
		{
			File file = new File("src/main/resources/JS/");
			String[] 文件名  = file.list();
			assertTrue("没有JS文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}
	
	@Test
	public void 测试mp3资源文件()

	{
		try
		{
			File file = new File("src/main/resources/mp3/");
			String[] 文件名  = file.list();
			assertTrue("没有mp3文件", 文件名.length > 0 );
		} catch (Exception e)
		{
            assertTrue("分辨率文件夹打开失败，请检查！",false);
		}
	}

	@Test
	public void 测试Formation参数是否正确()
	{
		assertTrue("Formation.M的值不是90", Formation.M == 90);
	}
	
	
	
	/**
	 * public static int M = 90; // 设置坐标单位；
	public static int top = 1 * M;
	public static int left = 1 * M;
	public static int time = 3000; // 设置时间
	public static int right_distance = 2 * M;
	public static double HP宽度 = 0.9 * M;
	public static double HP高度 = 0.05 * Formation.M;
	public static double MP宽度 = 0.9 * M;
	public static double MP高度 = 0.05 * Formation.M;
	public static int scene宽度 = 10 * M + right_distance;
	public static int scene高度 = 10 * M;
	public static int MainScene宽度 = 7 * M;
	public static int MainScene高度 = 4 * M;
	 */
	@Test
	public void 测试Formation的top参数是否正确()
	{
		assertTrue("Formation.top的值不是1*Formation.M", Formation.top == 1 * Formation.M);
	}


	@Test
	public void 测试Formation的left参数是否正确()
	{
		assertTrue("Formation.left的值不是1*Formation.M", Formation.left == 1 * Formation.M);
	}

	
	@Test
	public void 测试Formation的time参数是否正确()
	{
		assertTrue("Formation.time的值不是3000", Formation.time == 3000);
	}

	
	@Test
	public void 测试Formation的HP高度参数是否正确()
	{
		assertTrue("Formation.HP的高度的值不是0.05*Formation.M", Formation.HP高度 == 0.05 * Formation.M);
	}

	
	@Test
	public void 测试Formation的HP宽度参数是否正确()
	{
		assertTrue("Formation.HP的宽度值不是0.9*Formation.M", Formation.HP宽度 == 0.9 * Formation.M);
	}

	
	@Test
	public void 测试Formation的MP高度参数是否正确()
	{
		assertTrue("Formation.P的高度的值不是0.05*Formation.M", Formation.MP高度 == 0.05 * Formation.M);
	}

	
	@Test
	public void 测试Formation的MP宽度参数是否正确()
	{
		assertTrue("Formation.MP的宽度的值不是0.05*Formation.M", Formation.MP高度 == 0.05 * Formation.M);
	}

	
	

	
}
