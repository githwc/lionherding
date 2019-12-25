

五、@Configuration和 @Component
    @Component指这个类需要被组件扫描器扫描到并实例化对象到IOC容器
    @Configuration是指这个类是一个类似XML文件的配置类,里面用bean标签标记的方法需要被实例化到IOC容器中。在SpringBoot中取消了XML文件并大量
    使用@Configuration注解的类来实现配置。
    