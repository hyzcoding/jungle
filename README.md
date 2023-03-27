# jungle
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/hyz951226/jungle.svg?style=social&label=Stars)](https://github.com/hyz951226/jungle)[![GitHub forks](https://img.shields.io/github/forks/hyz951226/jungle.svg?style=social&label=Fork)](https://github.com/hyz951226/jungle)

🐶 a bbs project/完整的BBS论坛

## 技术框架:

### 后台
* 整体模块
  * 
* 整体框架
    * 使用`Spring Boot` 构建整个项目 去除 XML 配置
    * `Maven`构建项目 
    * 采用`SpringCloud`作为RPC框架
    * `Jenkins`作为持续集成
* 权限验证
    * 权限验证使用`Shiro+JWT`
* 数据存储
    * `mybatis` 数据库持久化 
    * 七牛云存储作为`OSS`存储地址 
* 接口文档
     * 使用`swagger2` 作为接口文档
	
### 论坛网页
* 采用react+MaterialUI架构
	* [jungle-react](https://github.com/hyz951226/my-project)
### 管理界面
* 采用vue+ElementUI架构 
	 * [jungle_vue](https://github.com/hyz951226/my-project)
## 目录说明

## 端口地址
 * `jungle-eureka : 8761`
 * `jungle-gateway: 80`
 * `jungle-user : 9001`
 * `jungle-article : 9003`
 * `jungle-web : 9002`
