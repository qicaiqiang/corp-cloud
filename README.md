# corp-cloud


## 简介
  corp-cloud是基于SpringCloud和SpringBoot实现的微服务框架。目标是打造成一套为企业搭建微服务框架的Demo和开发规范。阅读后即可参考此框架为样例和规范搭建企业级的微服务框架。


## 说明
> * 此框架使用的所有组件都为开源组件，后期会加入一些自己想做的微服务组件</br>
> * 以SpringCloud和SpringBoot为基础，目标为打造一套企业级的微服务框架的开发Demo和开发规范</br>
> * 根据框架的构建情况会陆续更新博客，写出自己在搭建框架时遇到的各种坑。博客地址：`http://www.cnblogs.com/Caucasian/`，欢迎持续关注</br>
> * 欢迎大家一起完善项目,提出缺陷，提供架构思路，编码规范和更好的架构项目规范，让此项目完成最终目标</br>
> * 如果此项目有帮助，请点击star支持一下</br>


## 项目架构
> ### 请求流程
>  客户端 -> 网关（集群，可实现客户端负载） -> 应用服务器 -> 数据库 (架构图如下，可以根据现有资源进行横向扩展)</br>
>  ![image](https://images2018.cnblogs.com/blog/853208/201803/853208-20180307003532727-3534740.png)

> ### 项目模块解析
> *    #### corp-boot：业务逻辑目录，主要用于实现项目的业务逻辑代码，提供给开发人员开发</br>
>       1.corp-jwt：Json Web Token的Util包，在模块引入该包时项目需要token验证（该包逻辑后续补充）</br>
>       2.corp-util：corp-cloud的工具类，在引入util包后，会对controller的返回值进行统一处理，后续会更新一些工具类</br>
>       3.corp-data-util：corp-cloud的数据访问层工具类，用于深度封装和优化持久层框架。引入该包后，项目必须连接封装好的数据库，noSql,mq等（该逻辑后续补充，计划支持spring-data,mybatis）</br>
>       4.corp-boot-user：示例工程，用于演示框架，集成swagger可以直接访问工程中接口。swagger地址：http://localhost:7071/doc.html （7071为服务端口）</br>

> *    #### corp-cloud：服务目录，主要用于启动服务例如：注册中心，网关，和corp-boot中写好的服务</br>
>       1.corp-cloud-discovery：注册中心，架构核心，所有服务都需要注册在discovery中，进行统一调度和管理</br>
>       2.corp-cloud-gateway：网关，从外部来的请求都需要经过该服务进行统一分发，并可实现客户端负载（该包逻辑后续补充）</br>
>       3.corp-cloud-user：corp-boot-user的服务，用于启动user服务，让corp-boot中只关心业务逻辑代码</br>


## 项目开发规范（持续完善）
> *   1.基础开发规范这边不做详情介绍，例如驼峰命名，类名首字母大写等等，可参考阿里开发规范手册</br>
> *   2.业务逻辑写在corp-boot目录下，服务写在corp-cloud下</br>
> *   3.微服用公用方法写在corp-util中，依赖单个服务业务逻辑公用方法可在单个服务中建立util路径</br>
> *   4.关于微服务的持久层封装和公用方法可写在corp-dtae-util中，如果服务规模不大可和util包合并，或者所有关于需要连接客户端的都写在data中，如果服务规模庞大建议拆分（实际项目中util和data放在了一起，存在了引入util后必需连接数据库，所以建议分开）</br>
> *   5.在考虑是否要加入专门存放服务的entity，dto等公用包，因为在实际开发中存在服务之间调用，造成太多重复的实体(如果有好的建议可联系本人)</br>
##  鸣谢
> 超级感谢曹老板鼓励我做开源（工作后第一任老大）</br>
> 感谢swagger-bootstrap-ui的开源者xiaoyming,swagger具体使用方法可访问：https://github.com/xiaoymin/Swagger-Bootstrap-UI/blob/master/README_zh.md</br>
