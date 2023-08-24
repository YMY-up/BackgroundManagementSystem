# 前端处理

在wms-web文件下打开终端，依次输入以下命令

```java
npm install -g @vue/cli 

npm i element-ui -S

npm install axios --save

npm i vue-router@3.5.4

npm i [vuex@3.0.0](mailto:vuex@3.0.0)
```
前端运行命令：npm run serve

# 数据库处理

1. 首先建立数据库命名为wms
2. 新建查询复制wms.sql文件内容，直接在查询中运行即可把数据添加成功

# 后端处理处理

1.wms文件中的pom.xml,mysql修改成自己的驱动版本

2.修改src-main-java-resources-application.yml文件中的数据库名称和密码（改成自己mysql的用户名和密码）

(如果mysql驱动是8版本以下的，把driver-class-name: com.mysql.cj.jdbc.Driver修改为driver-class-name: com.mysql.jdbc.Driver)

3.如果数据库名称不是wms的话,修改src-main-java-resources-application.yml中的mysql-url

​    url: jdbc:mysql://localhost:3306/==wms==?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8

其中wms修改成自己的数据库名称
4.后端直接在idea中运行即可

# 





