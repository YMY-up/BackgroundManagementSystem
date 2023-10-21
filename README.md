# 技术栈

jdk1.8 、Vue2、Springboot2、mybatis-plus、maven、密码md5加密处理,redis,token+jwt、axios、node.js14、element ui(vue组件库)、navicat

# 前端处理

1.去官网下载node.js14

2.npm install

3.前端运行命令：npm run serve

# 数据库处理

1. 首先建立数据库命名为wms
2. 新建查询复制wms.sql文件内容，直接在查询中运行即可把数据添加成功

# 后端处理处理

1.wms文件中的pom.xml,mysql修改成自己的驱动版本

2.修改src-main-java-resources-application.yml文件中的数据库名称和密码（改成自己mysql的用户名和密码）

(如果mysql驱动是8版本以下的，把driver-class-name: com.mysql.cj.jdbc.Driver修改为driver-class-name: com.mysql.jdbc.Driver)

3.如果数据库名称不是wms的话,修改src-main-java-resources-application.yml中的mysql-url

    url: jdbc:mysql://localhost:3306/==wms==?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8

其中wms修改成自己的数据库名称
4.后端直接在idea中运行即可
