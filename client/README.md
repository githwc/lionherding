# travel
vue前端历程

快捷键：
Ctrl + Shift + K 删除行
Ctrl + Enter 在当前行下插入新的一行
Ctrl + Shift + Enter 在当前行上插入新的一行
Shift+Alt+F     代码格式化
Ctrl + W        关闭当前页
Ctrl +  /       注释
Alt + B         打开默认浏览器
Shift + Alt + B 打开其他浏览器

《项目加载介绍》
一、main.js:整个项目的入口文件
    //==START===
    // The Vue build version to load with the `import` command
    // (runtime-only or standalone) has been set in webpack.base.conf with an alias.
    import Vue from 'vue'
    import App from './App'
    import router from './router'
    
    Vue.config.productionTip = false
    
    /* eslint-disable no-new */
    new Vue({
      el: '#app',
      router,
      components: { App },
      template: '<App/>'
    })
    //==END===
(1)#app：是挂载到id为app的元素上，在index.html文件中可以看到
(2)components:{App}:es6写法，类似于{App:App}
(3)template:<App/>渲染app这个组件
(4)App指的是当前目录下的App.vue加载而来（加载顺序：App.vue,App.js,App.json）

二、App.vue:以vue结尾的文件，称为单文件组件
    //===START===    
    <template>
      <div id="app">
        <img src="./assets/logo.png">
        <router-view/>
      </div>
    </template>
    
    <script>
    export default {
      name: 'App'
    }
    </script>
    
    <style>
    #app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }
    </style>
    //===EDND===
(1)组成：
     <template></template>：组件的模板
     <script></script>        ：组件的逻辑
     <style></style>    ：组件的css样式
(2)name:'App':向外抛出名为App
(3)路由：根据url的不同，返回给页面的内容不同
(4)<router-view>：显示的是当前路由地址所对应的内容
    也就是main.js里边所对应的router内容
引出：router下的index.js导出的是一个路由配置项；路由内容引入components/HelloWorld里的内容


《多页面应用VS单页面应用》
一、
多页面应用：
	页面跳转返回HTML：
			优点：首屏时间快，SEO效果好
			缺点：页面切换慢
二、单页面应用：
	页面跳转通过JS渲染：
			优点：页面切换快
			缺点：首屏时间稍慢，SEO差

《VUEX》

  状态管理简介：vuex是专为vue.js应用程序开发的状态管理模式;集中存储管理应用的所有组件的状态
  状态管理核心：
              1.state:单一状态树，在state中定义需要管理的数组，对象，字符串等；以便在vue.js组件中获取
              2.getter:类似vue.js的计算属性，当需要从store的state中派生出一些状态，可以用getter，getter会接收state作为第一个参数，
                        而且getter的返回值会根据它的依赖被缓存起来，只有getter中的依赖值（state中的某个需要派生状态的值）发生改变的时候才会被重新计算。
              3.mutation：通过提交mutation改变store中state的状态。每个mutation都有一个字符串类型的事件类型和一个回调函数，
                          我们需要改变state的值就要在回调函数中改变。执行一个相应的调用方法：store.commit来执行这个回调函数；
              4.action:action可以提交mutation，在action中可以执行store.commit，而且action中可以有任何的异步操作。
                        在页面中如果我们要调用这个action，则需要执行store.dispatch；
              5.module:module用来解决当state中很复杂臃肿的时候，module可以将store分割成模块，每个模块中拥有自己的state、mutation、action和getter