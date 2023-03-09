### AopResearch
#### 
```
概要：
1.继承，组合，jdk代理，cglib代理，原生aop，aspectj 实现业务功能扩展
```
---
```
流程：
1.spring starter project
2.SearchServiceInterface 接口
DefaultSearchService 基础业务类

3.手动功能扩展：
继承方式
用LogExtendsSearchServiceImpl 继承方式
组合方式
如果DefaultSearchService上有final修饰，不允许被继承
LogCombinationSearchServiceImpl 组合方式，进行功能扩展

如果有很多类似DefaultSearchService的基础业务类，需要被扩展
4.自动功能扩展：
JdkProxyObjectFactory jdk代理工厂
LogJdkSearchServiceImplTests 测试

CglibProxyObjectFactory cglib代理工厂
LogCglibSearchServiceImplTests 测试

5.spring框架的aop实现
LogAdvice,LogAdvisor
LogSpringAopServiceImplTests 测试
在启动类加上DefaultAdvisorAutoProxyCreator方法

6.com.qxn.services.aspectj 第三方aop框架，更加简化
```
