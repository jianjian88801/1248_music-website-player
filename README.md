# 音乐播放网站

#### 介绍
基于SpringBoot+Vue实现的音乐播放网站，分前后两端，不同的用户权限，后端主要是歌曲信息的管理，前台是歌曲播放。


#### 软件架构
前端：Vue | Element-ui  
后端：SpringBoot | Mybatis | mysql  
环境：JDK1.8 | Mysql | Maven   


#### 功能介绍
##### 【代码结构与数据库截图】
![输入图片说明](images/image.png)
![输入图片说明](images/image1.png)

##### 【功能详述】 
1. 后端管理-登录页  
![输入图片说明](images/image2.png)

2.后端管理-首页  
![输入图片说明](images/image3.png)

3.后端管理-用户管理  
![输入图片说明](images/image4.png)

4.后端管理-歌手管理  
![输入图片说明](images/image5.png)

5.后端管理-歌单管理  
![输入图片说明](images/image6.png)

6.应用端-登录  
![输入图片说明](images/image7.png)

7.应用端-首页  
![输入图片说明](images/image8.png)

8.应用端-歌单页  
![输入图片说明](images/image9.png)

9.应用端-歌手页  
![输入图片说明](images/image10.png)

10.应用端-我的音乐  
![输入图片说明](images/image11.png)



#### 使用说明
1. 创建数据库，执行数据库脚本
2. 修改jdbc数据库连接参数
3. 下载安装maven依赖jar
4. 启动SpringBoot启动类

后端管理： 
    请求地址： http://localhost:8080  
    用户名：admin    
    密码：123456    
应用端：  
    请求地址： http://localhost:8081  
    用户名：test  
    密码：123456  
