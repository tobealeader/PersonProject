# Weixin
#### 这是一个用java实现微信公众号开发的demo
## 开发要点
#### 微信公众号开发文档，数据解析，数据封装
## 知识点
#### 微信的数据格式是xml，所以用到了xtream对xml格式的解析，以及将对象转为xml格式
#### 微信中想要发送单图片消息，需要获取到图片的mediaId，而图片的mediaId需要通过上传临时素材获得，上传临时素材需要获得先access_token，所以在上传图片和获得access_token里，我们需要用到urlhttpconnection对一些接口进行请求来获得相关数据。
 #### 在这其中，遇到很多关于java中流的知识，inpustream,outputstream,outstream,ByteArrayOutputStream以及byte等等。
## 功能
#### 在微信公众号中实现了一些简单的功能
#### 回复图文消息
#### 翻译的功能：这里用到了百度翻译api接口
#### 查询天气的功能：这里用到了一个百度关于查询天气的接口
#### 发送位置来获得天气的功能：用户发送位置，我们需要根据得到的经纬度，通过百度地图api接口获得所在的城市，再去找到当地的天气
#### 人脸识别的功能：用户发送图片，通过阿里的人脸识别api接口，来得到这个头像的年龄和性别
## demo中的重点处理类
#### Weixin\src\java\cn\com\weixin\util\WeixinUtil.java
## 框架
#### springmvc+hibernate
## 开发环境
#### idea，jdk1.8
## 总结
####  这是个很兴奋的开始，很有意思的过程，很糟糕的结果（ps：用点工夫谁都能搞得定，技术含量的东西不多，因为有很多前人写好的api，写好的demo，工程。）
 
#### 这是我的微信公众号（个人订阅号）：hdjtime
 
