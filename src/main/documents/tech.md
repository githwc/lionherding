一、@Configuration和 @Component
    @Component指这个类需要被组件扫描器扫描到并实例化对象到IOC容器
    @Configuration是指这个类是一个类似XML文件的配置类,里面用bean标签标记的方法需要被实例化到IOC容器中。
    在SpringBoot中取消了XML文件并大量使用@Configuration注解的类来实现配置。
二、快捷键
	1.Alt+Enter：创建该接口的实现类/实现未实现的方法
	2.【精准搜索】
		ctrl+f	页面内查找
		ctrl+shift+t	查找方法
		ctrl+shift+r	查找文件
		ctrl+alt+s	打开设置
	3.【重构】
		F2：修改名称/重构变量
		Alt+Enter:指定函数修改参数后可使用此快捷键重构关联函数
	4.【其他】
		ctrl + O 重写父类方法    