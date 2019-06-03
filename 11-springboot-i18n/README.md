# Spring Boot— 国际化支持

国际化是一个可以让应用程序适应不同语言和区域显示而无需修改工程代码的一种软件本地化自适应能力。在本章节中，将使用Spring Boot与Thymeleaf来演示国际化的支持，系统会自动根据当前的语言环境或者Session中的语言来读取对应的语言模板。

## 测试
1.没有给任何的语言参数，Spring Boot应用程序默认使用US资源文件。
浏览器中输入： http://localhost:8080/index 

2. 修改请求地址为：http://localhost:8080/index?lang=zh_CN

将界面从英文状态切换到中文状态进行显示。



## 依赖

需要将Spring Boot Web Starter和Spring Boot Thymeleaf Starter依赖加入到工程中。下面是Maven的build文件内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.3.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.ramostear</groupId>
	<artifactId>i18n</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>i18n</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

## 配置LocaleResolver

需要为应用程序配置一个默认的本地化语言环境的解析器。在Spring Boot应用程序中，可以通过下面的方式配置一个默认的 LocaleResolver:

```java
@Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }
```



## 配置拦截器

需要使用**LocaleChangeInterceptor**拦截语言参数的变化，并设置语言参数的名称。

```java
@Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
```



## 注册拦截器

为了让**LocaleChangeInterceptor**生效，需要将拦截器注册到Spring Boot的拦截器注册表中。

```java
@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
```

参考资料： https://www.ramostear.com/articles/i18n_support.html
