# 系统使用说明

## 开发环境

  JAVA JDK： 1.8
  MAVEN
  Intellij IDEA
  
##  系统结构

  --.idea
  --src
  ----main
  ------java
  --------brush
  ----------brush.java              # 笔刷类
  --------frame
  ----------ColorToolBar.java       # 颜色选择
  ----------FilePopMenu.java        # 顶部菜单
  ----------ShapePanel.java         # 绘制选择
  ----------ContentPanel.java       # 画布、交互框架入口
  ----------MainFrame.java          # 程序主入口
  --------shape
  ----------ShapeType.java          # 图元父类
  ----------MyBSplineCurve.java     # BSpline曲线
  ----------MyCurve.java            # 三次Bezier曲线
  ----------MyCutWindows.java       # 裁剪、缩放、平移
  ----------MyLine.java             # 直线
  ----------MyOval.java             # 圆
  ----------MyPolygon.java          # 多边形
  ------resources
  ------1.bmp                       # 画布保存文件
  --pom.xml
  --test.txt
  --output.bmp                      # 文件输入接口保存文件
