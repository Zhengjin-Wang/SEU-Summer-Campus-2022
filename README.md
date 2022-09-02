# SEU-Summer-Campus-2022
石化安全管理平台，有网页端和移动端两个部分，网页端实现了巡检任务的安排，危险源监控，移动端实现了巡检人员的巡检上报

项目地址: https://github.com/qq1054129023/SEU-Summer-Campus-2022

### 网页端后端

​	使用redis和mysql，redis默认需要本地环境，mysql默认连接远程数据库，在application.properties中修改连接url

### 移动端后端

​	使用redis和mysql，redis默认需要本地环境，mysql默认连接远程数据库，在application.yml中修改连接url

### 网页端前端

​	使用yarn作为包管理工具

安装依赖

```powershell
yarn install 
```

启动服务

```powershell
yarn serve 
```

默认连接是云服务器地址，在main.js可修改

### 移动端前端

使用HBuilderX开发工具调试运行

