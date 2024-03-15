### factoryPattern 工厂模式
---
1.定义Function函数接口，定义函数公共方法： getCode，calculate

2.实现AddFunction加法函数类，继承Function接口，做calculate方法的具体实现

3.**FunctionFactory工厂类**，类初始化的时候，将所有实现Function接口的函数赋到Map<String, Function>里，
再创建根据code获取Function方法

4.使用：注入工厂对象，工厂对象根据code得到对应的函数子类
