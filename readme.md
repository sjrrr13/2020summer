# Project设计文档
19302010035 佘家瑞
## 项目完成情况
### 首页

1. 实现了最热图片轮播功能（3张）；
2. 实现了最新图片输出功能（6张）；
3. 实现了点击图片跳转到详情页的功能，不是每张图片一个详情页面。

----------

### 搜索页

1. 实现了根据选择的内容进行模糊搜索以及对结果排序的功能；
2. 实现了分页功能，每一页6张图片。

----------

### 上传页

1. 实现了上传图片到数据库的功能，若信息填写不全会拒绝上传并提示用户填写完全；
2. 实现了修改图片的功能，会主动加载要修改图片的信息。

----------

### 我的照片

1. 能正确显示用户上传的图片；
2. 修改按钮与删除按钮功能正常，删除按钮将直接从数据库中删除该图片的所有数据；
3. 实现了分页功能。

----------

### 我的收藏

1. 能正确显示用户收藏的图片；
2. 删除按钮功能正常，仅删除`travelimagefavor`数据表中该图片的数据；
3. 实现了足迹功能，显示最近浏览的图片（最多不超过十张）；

----------

### 登录页

1. 实现了用户通过用户名或邮箱登录；
2. 若登录错误有提示，返回登录页面后用户名不删除；
3. 若输入为空不会提交表单；

----------

### 注册页

1. 实现了注册功能，有效注册后可以立即登录；
2. 检验了用户名是否重复，邮箱地址是否有效；
3. 对`password`有醒目的UI强度检验，显示为不同颜色字符；
4. 对`confirm your password`检验了与`password`是否相同；
5. 实现了不合理注册或失败注册的提示。

----------

### 详情页

1. 能根据图片正确显示信息；
2. 实现了收藏按钮的功能，若未登录或已收藏过则无法点击收藏按钮。

----------

### 好友列表页、好友收藏页

1. 能根据用户正确显示好友；
2. 点击好友用户名或邮箱能显示好友的收藏图片。

----------

## 项目结构
根据MVC模式构建整个PJ项目，具体细节如下：

1. 利用`Jsp`页面进行展示，其中为了减少`Jsp`页面代码量，将输出HTML代码的函数封装在一个类中，作为静态方法进行调用来实现HTML代码的输出，使得`Jsp`页面易读性增加；
2. 利用`Servlet`文件进行后台数据处理，合理利用请求的转发与重定向实现与前端的数据交换;
3. 利用`DAO`实现数据库连接与访问、操作。其中，构造了两个实体类`Image`和`User`，并分别写了两个类的`DAO`接口`ImageDAOImpl`和`UserDAOImpl`来实现对数据库中数据的对象化操作。

## Bouns完成情况
文档完成如上。