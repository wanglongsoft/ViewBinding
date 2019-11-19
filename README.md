# ViewBinding
MVVM架构和DataBinding的基本使用
#### MVVM简介
MVVM(Model-View-ViewModel)是一种用于把数据和UI分离的设计模式，本质上就是MVC的改进版。  
Model用于处理应用程序数据逻辑的部分，它主要负责网络请求，数据库处理，I/O等的操作。  
View应用程序中处理数据显示的部分。在Android开发中，它一般对应着xml布局文件，Activity，Fragment。  
ViewModel充当数据转换器，将Model信息转换为View 的信息，将事件从View传递到Model。
#### MVVM优点
* 数据驱动  
在MVVM中，数据和业务逻辑处于一个独立的ViewModel中，ViewModel只要关注数据和业务逻辑，不需要和UI或者控件打交道。
由数据自动去驱动UI去自动更新UI，UI的改变又同时自动反馈到数据，数据成为主导因素，这样使得在业务逻辑处理只要关心数
据，方便而且简单很多。
* 低耦合度  
MVVM模式中，数据是独立于UI的，ViewModel只负责处理和提供数据，UI想怎么处理数据都由UI自己决定，ViewModel不涉及
任何和UI相关的事也不持有UI控件的引用
* 团队协作  
MVVM的分工是非常明显的，由于View和ViewModel之间是松散耦合的。一个是处理业务和数据，一个是专门的UI处理，所以完全
可以由两个人分工来做，一个做UI（xml 和 Activity）一个写ViewModel，效率更高。
* 可复用性  
一个ViewModel可以复用到多个View中。同样的一份数据，用不同的UI去做展示，对于版本迭代频繁的UI改动，只要更换View层
就行
#### MVVM缺点
* 数据绑定使得调试变得困难。当你看到界面异常，有可能是你View的代码有Bug，也可能是Model的代码有问题。数据绑定使得一个位置的Bug被快速传递到别的位置，要定位原始出问题的地方就变得不那么容易了。
* 数据双向绑定不利于代码重用。客户端开发最常用的重用是View，但是数据双向绑定技术让你的一个View都绑定了一个model，不同模块的model都不同
* 更加大的学习成本。目前这种架构的实现方式比较不完善，常见的就是通过DataBinding来完成
#### 工程配置
在工程app的build.gradle配置如下
```java
apply plugin: 'com.android.application'

android {
    ......//无关配置
    dataBinding {
        enabled true
    }
    ......//无关配置
}

dependencies {
　　......//无关配置
    implementation "android.arch.lifecycle:extensions:1.1.1"
    ......//无关配置
}
```
#### 工程代码介绍
###### View层简介
布局文件，引用ViewModel中的数据
```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable name="vm" type="soft.wl.viewmodel.MyViewModel"/>
    </data>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <Button
            android:id="@+id/button"
            android:layout_width="match_parent"
            android:layout_height="150px"
            android:text="@{vm.ButtonText}" />

        <TextView
            android:id="@+id/textView"
            android:layout_width="match_parent"
            android:layout_height="70px"
            android:text="@{vm.ViewText}" />

        <soft.wl.control.ImageBase
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="500px"
            app:bitmap="@{vm.BitmapData}"/>

        <Button
            android:id="@+id/button_switch"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Switch Image"
            android:onClick="switchImage"/>
    </LinearLayout>
</layout>
```
主Activity中，获取ViewModel的实例
```java
mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
mMyViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

if (null != mActivityMainBinding && null != mMyViewModel) {
    mActivityMainBinding.setVm(mMyViewModel);
    mActivityMainBinding.setLifecycleOwner(this);
}
```
###### ViewModel层简介
```java
    public MutableLiveData<String> ButtonText = new MutableLiveData<>();
    //首字母大写，默认绑定函数，小写默认绑定变量
    public MutableLiveData<String> ViewText = new MutableLiveData<>();
    public MutableLiveData<Bitmap> BitmapData = new MutableLiveData<>();
    
    public MutableLiveData<String> getButtonText() {
        return modelDataAPI.getTestButtonText();
    }

    public MutableLiveData<String> getViewText() {
        return modelDataAPI.getTestViewText();
    }

    public MutableLiveData<Bitmap> getBitmapData() {//调用model接口获取数据
        return modelDataAPI.getTestBitmap();
    }
```
###### Model层简介
```java
    //ModelDataAPI的实现，可调用底层接口
    public MutableLiveData<String> getTestButtonText() {
        return mButtonText;
    }

    public MutableLiveData<String> getTestViewText() {
        return mViewText;
    }

    public MutableLiveData<Bitmap> getTestBitmap() {
        return mBitmap;
    }
```
