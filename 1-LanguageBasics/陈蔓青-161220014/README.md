# 系统使用说明


  具体代码对应位置请见系统报告部分。



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
  
  -------------brush.java              # 笔刷类
  
  --------frame
  
  -------------ColorToolBar.java       # 颜色选择
  
  -------------FilePopMenu.java        # 顶部菜单
  
  -------------ShapePanel.java         # 绘制选择
  
  -------------ContentPanel.java       # 画布、交互框架入口
  
  -------------MainFrame.java          # 程序主入口
  
  --------shape
  
  -------------ShapeType.java          # 图元父类
  
  -------------MyBSplineCurve.java     # BSpline曲线
  
  -------------MyCurve.java            # 三次Bezier曲线
  
  -------------MyCutWindows.java       # 裁剪、缩放、平移、旋转
  
  -------------MyLine.java             # 直线
  
  -------------MyOval.java             # 圆
  
  -------------MyPolygon.java          # 多边形
  
  ------resources
  
  ------1.bmp                          # 画布保存文件
  
  --pom.xml
  
  --input.txt
  
  --output.bmp                         # 文件输入接口保存文件
  
  
##  编译说明

   请按照以上代码文件结构进行编译
   
   部分非本次作业要求的功能代码未列出（如填色等）
   
   此外，需要使用MAVEN加载所需文件
   
   
##  程序运行


### 程序入口
  
  frame 下的 MainFrame.java 文件。
  
  
### 菜单栏可用部分

  文件菜单
  
  > 输入： 文件输入接口
  
  > 重置： 重置画布
  
  > 保存： 保存当前画布内容至/src/1.bmp
  
  
### 图元绘制可用部分  

  选中左侧对应绘制图标即可进行绘制，此外重绘函数需要缓冲，若不显示可耐心等待或点击画布。
  
  > 直线
  
  > 矩形
  
  > 椭圆
  
  > 圆
  
  > 多边形
  
  > 曲线
  
  > 填充
  
  > 套索（裁剪指定区域，并可对其进行平移、缩放、旋转等操作）
  
  
### 颜色选择、画笔设置：  可用


### 用户鼠标交互接口：  可用

  此时若保存画布，文件为/main/src/1.bmp
  
  对窗口进行缩放后，若画布未显示，可单击画布等待重绘


### 文件输入接口： 部分可用

  文件输入位置为根目录下 input.txt
  
  画布图像输出位置为根目录下 name.bmp (name 为文件输入内自定义文件保存名)
  
  
  
  
  

