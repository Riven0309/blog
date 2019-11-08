# 个人博客管理系统 blog

采用SpringBoot框架搭建。前端展示采用Semantic UI框架，thymeleaf模板引擎进行页面渲染。数据库采用MySQL，后台数据访问采用Spring Data JPA，日志信息采用SpringAOP进行处理，页面访问url采用RESTful风格。

## 运用到的技术：

前端框架[Semantic UI](https://onebugman.cn/semantic-ui/)

thymeleaf模板引擎

后台数据访问采用Spring Data JPA

SpringAOP进行日志处理

SpringBoot DevTools 热部署

MD5加密

Markdown转HTML：[commonmark-java](https://github.com/atlassian/commonmark-java) 

## 页面插件集成：

[编辑器 MarkDown](https://pandao.github.io/editor.md/)

[内容排版 Typo.css](https://github.com/sofish/typo.css)

[动画 animate.css](https://daneden.github.io/animate.css/)

[代码高亮 prism](https://github.com/PrismJS/prism)

[目录生成 Tocbot](https://tscanlin.github.io/tocbot/)

[滚动侦测 waypoints](http://imakewebthings.com/waypoints/)

[平滑滚动 jquery.scrollTo](https://github.com/flesler/jquery.scrollTo)

[二维码生成 qrcode.js](https://davidshimjs.github.io/qrcodejs/)

## 仍然存在的一点小问题

对Markdown格式的文章转化成HTML格式时，采用的[commonmark-java](https://github.com/atlassian/commonmark-java)只对一级标题会生成相应的id，所以在利用Tocbot生成目录时，只能生成大标题，而不能生成小标题，生成的目录不够完善。

## 还可以继续增加完善的功能：

一般网页文章会有一个分享按钮，可以把网页内容分享到QQ、微信等，本项目并未实现该功能。该功能待增加。

## 相关学习链接：

bilibili：[av62555970](https://www.bilibili.com/video/av62555970)
