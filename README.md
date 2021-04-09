# easy-cloud

## 快速开始

### 1 开发环境

- Linux、Mac、Windows 7/8/10 均可
- git 环境
- JDK 1.8
- Maven

- MySQL 5.7+

### 2 搭建步骤

1）克隆 develop 分支代码。

````shell
git clone -b develop git@github.com:DragonV96/easy-cloud.git
````

2）将本地依赖打包至本地 maven 仓库

````shell
cd easy-cloud
mvn clean && mvn install
````

3）更改 config 文件夹下对应服务 yaml 配置文件数据库连接配置，如 `url`、`username` 和 `password` 等。

4）运行 `easy-cloud\component-server\nacos\src` 下的 Nacos 启动脚本 nacos_startup.cmd，启动 naocs。

5）将更改完毕的配置添加至本地 Nacos 配置中心，文件夹名即为服务对应的 `group` ，添加完毕后修改本地 `bootstrap.yml` 文件的 `namespace` 值为自己 nacos 中的命名空间。

6）依次执行下列数据库脚本初始化数据库。

- easy-cloud\system-server\file-system\script\file_system.sql
- easy-cloud\system-server\oauth2\oauth2-server\script\oauth.sql
- easy-cloud\component-server\distributed-job\script\xxl-job.sql

### 3 启动顺序

按下述顺序依次启动服务：

1）先启动 Nacos。

2）待 Nacos 启动完毕后，启动 oauth2-server、distributed-job、gateway 等服务。

3）启动 file-system、业务服务等其他服务。

4）最后启动 swagger 聚合文档服务。

## 项目说明

### 1 目录结构

```
└─easy-cloud 项目根目录
  ├─business-server 业务服务
  ├─common 公共依赖模块
  │  └─core 核心模块
  ├─component-server 组件服务
  │  ├─distributed-job 分布式任务调度
  │  ├─gateway 网关
  │  ├─nacos 注册中心/配置中心
  │  └─swagger 聚合文档
  ├─config 配置文件夹
  ├─system-server 系统服务
  │  ├─file-system 分布式文件服务
  │  └─oauth2 授权认证模块
  │      ├─oauth2-client 授权认证客户端依赖
  │      └─oauth2-server 授权认证服务
  └─tool-server
      ├─code-generator 代码生成器
      └─data-generator 数据生成器
```

### 2 技术栈
