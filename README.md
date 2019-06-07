DEMO
===========================

###########说明
1.本项目纯属入门级测试项目

2.在打包jar 过程中要注意清单文件 第一次由于位置错误导致双击jar包运行不起来


3.打包过程注意点

3.1 intelliJ IDEA  双击file  -->ProjectStructure  
  ，里面找到 artifacts 配置
  Main class    我这个项目里面ui下面那个有main方法的
  指定 META-INF/MANIFEST.MF  
  文件路径为 项目目录第一层  
  
3.2 当执行完3.1 并不会生成out目录 需要在ide工具栏中操作build  build Artifacts