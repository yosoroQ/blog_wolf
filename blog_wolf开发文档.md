[TOC]



# blog_wolf - 后端

## 项目开发文档

* 如果你看见该项目却不知所措，那么可以参照该开发文档一步步走下去，那样就可以完全复现并理解该项目。

* author：李海威

| 李海威 | 陈子宜 | 黄文健 | 庞伟杰 | 杨雨 |
| :----: | :----: | :----: | :----: | :--: |

  

# 新建springboot项目

# 构建数据库（blog_wolf）

## t_blog（博客表）

![image-20221101151643478](http://qny.expressisland.cn/schoolOpens/image-20221101151643478.png)

## t_category（分类表）

![image-20221101151713903](http://qny.expressisland.cn/schoolOpens/image-20221101151713903.png)

## t_user（用户表）

![image-20221101151727291](http://qny.expressisland.cn/schoolOpens/image-20221101151727291.png)

# 配置application.yml

```yml
# 后台访问端口配置
server:
  port: 8081

# 数据库连接配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3307/blog_wolf?serverTimezone=UTC
    username: root
    password: 1234

# mybatis配置
mybatis:
  mapper-locations: classpath:mapper/*.xml  # 定位mapper文件的位置，当xml文件和mapper接口路径一致时可以不用配置
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 标准日志输出
    map-underscore-to-camel-case: true  # 开启下划线转驼峰
  type-aliases-package: com.li.entity # 实体类起别名
```

# pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.li</groupId>
    <artifactId>blog</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>blog</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--        引入mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!--        引入lombok插件-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--        引入mybatis依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.3.7.RELEASE</version>
                <configuration>
                    <mainClass>com.li.BlogApplication</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>

```

# 新建包

* ![image-20221101151257924](http://qny.expressisland.cn/schoolOpens/image-20221101151257924.png)

# entity包内新建实体类

## 实体类Blog

```java
package com.li.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String id;
    private String title;
    private String description;
    private String content;
    private Integer views;
    private String published;
    private Date createTime;
    private String titleImage;

    // 实体类属性

    private Category category;
    private User user;
}
```

## 实体类Category

```java
package com.li.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    // 基本属性
    private String id;
    private String name;

    // 实体类属性
    private List<Blog> blogs = new ArrayList<>();
}
```

## 实体类User

```java
package com.li.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

public class User {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    private String id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String role;

    private List<Blog> blogs = new ArrayList<>();
}
```

# mapper包下新建mapper类（接口类_intetface）

* `@Mapper注解`的作用相当于在mybatis-config.xml中注册mapper.xml文件。
* `@Param注解`用来标识传递给sql语句的参数名称是什么。

## BlogMapper

```java
package com.li.mapper;

import com.li.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> selectAll();

    List<Blog> selectPage(@Param("currentPage") Integer currentPage,
                          @Param("pageSize") Integer pageSize,
                          @Param("title") String title
    );

    Integer selectCount(@Param("title") String title);

    Blog selectById(String id);

    Integer insertOne(Blog Blog);
    Integer updateOne(Blog Blog);
    Integer deleteById(@Param("id") String id);
}
```

## UserMapper

```java
package com.li.mapper;

import com.li.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    List<User> selectPage(@Param("pageNum") Integer pageNum,
                          @Param("pageSize") Integer pageSize,
                          @Param("username") String username,
                          @Param("role") String role
                          );

    Integer selectCount(@Param("username") String username,
                        @Param("role") String role);

    User selectById(@Param("id") String id);

    Integer insertOne(User user);
    Integer updateOne(User user);
    Integer deleteById(@Param("id") String id);
}
```

## CategoryMapper

```java
package com.li.mapper;

import com.li.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectAll();

    List<Category> selectPage(@Param("currPage") Integer currPage,
                          @Param("pageSize") Integer pageSize,
                          @Param("categoryName") String categoryName
    );

    Integer selectCount(@Param("categoryName") String categoryName);

    Category selectById(@Param("id") String id);

    Integer insertOne(Category Category);
    Integer updateOne(Category Category);
    Integer deleteById(@Param("id") String id);
}
```

# mybatis自动生成SQL（mybatisX-Generator）

* 这些mapper在resource目录的mapper目录下。

## BlogMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.li.mapper.BlogMapper">

    <insert id="insertOne">
        insert into t_blog (id, title, description, content, views, published, create_time, title_image, category_id, user_id)
        values (#{id}, #{title}, #{description}, #{content}, #{views}, #{published}, #{createTime}, #{titleImage}, #{category.id}, #{user.id});
    </insert>
    <update id="updateOne">
        update t_blog
        <set>
            <if test="title != null">
                title = #{title},
            </if>
            <if test="description != null">
                description = #{description},
            </if>
            <if test="content != null">
                content = #{content},
            </if>
            <if test="views != null">
                views = #{views},
            </if>
            <if test="published != null">
                published = #{published},
            </if>
            <if test="createTime != null">
                create_time = #{createTime},
            </if>
            <if test="titleImage != null">
                title_image = #{titleImage},
            </if>
            <if test="category != null">
                category_id = #{category.id},
            </if>
            <if test="user != null">
                user_id = #{user.id}
            </if>
        </set>
        <where>
            id = #{id}
        </where>
    </update>

    <delete id="deleteById">
        delete from t_blog where id = #{id}
    </delete>

    <select id="selectAll" resultMap="BlogsResultMap">
        select tb.id blog_id,
               tb.title blog_title,
               tb.description blog_description,
               tb.content blog_content,
               tb.views blog_views,
               tb.published blog_published,
               tb.create_time blog_create_time,
               tb.title_image blog_title_image,
               tc.id category_id,
               tc.name category_name,
               tu.id user_id,
               tu.username user_username
        from t_blog tb
                 left outer join t_category tc on tb.category_id = tc.id
                 left outer join t_user tu on tb.user_id = tu.id
        order by blog_create_time desc
    </select>

    <resultMap id="BlogsResultMap" type="Blog">
        <id property="id" column="blog_id"></id>
        <result property="title" column="blog_title"></result>
        <result property="description" column="blog_description"></result>
        <result property="content" column="blog_content"></result>
        <result property="views" column="blog_views"></result>
        <result property="published" column="blog_published"></result>
        <result property="createTime" column="blog_create_time"></result>
        <result property="titleImage" column="blog_title_image"></result>
        <association property="category" javaType="Category">
            <id property="id" column="category_id"></id>
            <result property="name" column="category_name"></result>
        </association>
        <association property="user" javaType="com.li.entity.User">
            <id property="id" column="user_id"></id>
            <result property="username" column="user_username"></result>
        </association>
    </resultMap>

    <select id="selectById" resultMap="BlogResultMap">
        select tb.id blog_id,
               tb.title blog_title,
               tb.description blog_description,
               tb.content blog_content,
               tb.views blog_views,
               tb.published blog_published,
               tb.create_time blog_create_time,
               tc.id category_id,
               tc.name category_name,
               tu.id user_id,
               tu.username user_username,
               tu.nickname user_nickname,
               tu.avatar user_avatar,
               tu.role user_role
        from t_blog tb
                 left outer join t_category tc on tb.category_id = tc.id
                 left outer join t_user tu on tb.user_id = tu.id
        where tb.id = #{id}
    </select>

    <select id="selectPage" resultMap="BlogsResultMap">
        select tb.id blog_id,
        tb.title blog_title,
        tb.description blog_description,
        tb.content blog_content,
        tb.views blog_views,
        tb.published blog_published,
        tb.create_time blog_create_time,
        tb.title_image blog_title_image,
        tc.id category_id,
        tc.name category_name,
        tu.id user_id,
        tu.username user_username
        from t_blog tb
        left outer join t_category tc on tb.category_id = tc.id
        left outer join t_user tu on tb.user_id = tu.id
        <where>
            <if test="title != null">
                title like "%"#{title}"%"
            </if>
        </where>
        limit #{currentPage}, #{pageSize}
    </select>

    <select id="selectCount" resultType="int">
        select count(*) from t_blog
        <where>
            <if test="title != null">
                title like "%"#{title}"%"
            </if>
        </where>
    </select>

    <resultMap id="BlogResultMap" type="Blog">
        <id property="id" column="blog_id"/>
        <result property="title" column="blog_title"/>
        <result property="description" column="blog_description"/>
        <result property="content" column="blog_content"/>
        <result property="views" column="blog_views"/>
        <result property="published" column="blog_published"/>
        <result property="createTime" column="blog_create_time"/>
        <association property="category" javaType="Category">
            <id property="id" column="category_id"/>
            <result property="name" column="category_name"/>
        </association>
        <association property="user" javaType="User">
            <id property="id" column="user_id"/>
            <result property="username" column="user_username"/>
            <result property="nickname" column="user_nickname"/>
            <result property="avatar" column="user_avatar"/>
            <result property="role" column="user_role"/>
        </association>
    </resultMap>

</mapper>
```

## UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--绑定UserMapper接口-->
<mapper namespace="com.li.mapper.UserMapper">

    <!--    查询所有用户-->
    <select id="selectAll" resultType="com.li.entity.User">
        select * from t_user
    </select>

    <!--    分页查询-->
    <select id="selectPage" resultType="com.li.entity.User">
        select * from t_user
        <where>
            <if test="username != ''">
                username like "%"#{username}"%"
            </if>
            <if test="role != ''">
                and role like "%"#{role}"%"
            </if>
        </where>
        limit #{pageNum}, #{pageSize}
    </select>

    <!--    根据Id查询用户-->
    <select id="selectById" resultType="com.li.entity.User">
        select * from t_user where id=#{id}
    </select>

    <!--    查询该表总数据条数-->
    <select id="selectCount" resultType="int">
        select count(*) from t_user
        <where>
            <if test="username != ''">
                username like "%"#{username}"%"
            </if>
            <if test="role != ''">
                and role like "%"#{role}"%"
            </if>
        </where>
    </select>

    <!--    插入一条数据-->
    <insert id="insertOne" parameterType="com.li.entity.User">
        insert into t_user(id, username, password, nickname, avatar, role)
        values (#{id}, #{username}, #{password}, #{nickname}, #{avatar}, #{role})
    </insert>

    <!--    根据Id更新数据-->
    <update id="updateOne" parameterType="com.li.entity.User">
        update t_user
        <set>
            <if test="username != null">
                username=#{username},
            </if>
            <if test="password != null">
                password=#{password},
            </if>
            <if test="nickname != null">
                nickname=#{nickname},
            </if>
            <if test="avatar != null">
                avatar=#{avatar},
            </if>
            <if test="role != null">
                role=#{role}
            </if>
        </set>
        <where>
            id=#{id}
        </where>
    </update>

    <!--    根据Id删除数据-->
    <delete id="deleteById">
        delete from t_user where id=#{id}
    </delete>

</mapper>
```

## CategoryMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.li.mapper.CategoryMapper">

    <insert id="insertOne">
        insert into t_category(id, name) values (#{id}, #{name})
    </insert>

    <update id="updateOne">
        update t_category
        <set>
            <if test="name != null">
                name = #{name}
            </if>
        </set>
        <where>
            id = #{id}
        </where>
    </update>

    <delete id="deleteById">
        delete from t_category where id = #{id}
    </delete>

    <select id="selectAll" resultType="Category">
        select * from t_category
    </select>

    <select id="selectById" resultType="Category" parameterType="string">
        select * from t_category where id = #{id}
    </select>

    <select id="selectPage" resultType="Category">
        select * from t_category
        <where>
            <if test="categoryName != null">
                name like "%"#{categoryName}"%"
            </if>
        </where>
        limit #{currPage}, #{pageSize}
    </select>
    <select id="selectCount" resultType="int">
        select count(*) from t_category
        <where>
            <if test="categoryName != null">
                name like "%"#{categoryName}"%"
            </if>
        </where>
    </select>
</mapper>
```

# 在service包下新建service类

* `@Service注解`用于类上，标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中，不需要再在applicationContext.xml文件定义bean了。
* `@Autowired`是一种注解，可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作。
* 查询全部博客 —— selectAll方法
* 进行分页 —— selectPage
* 汇总 —— selectCount
* 根据Id查询博客 —— selectById
* 保存博客 —— save、saveBlog
* 检查博客中是否存在分类属性，进行新增
  * checkBlogWithCategory
* 新增分类并拿到新增之后的category_id，判断是否为空
* 检查博客中是否存在用户属性，进行新增
  * checkBlogWithUser
* 新增用户并拿到新增之后的user_id，判断是否为空
* 删除 —— deleteById

## BlogService

```java
package com.li.service;

import com.li.common.IDUtils;
import com.li.common.Result;
import com.li.entity.Blog;
import com.li.entity.Category;
import com.li.entity.User;
import com.li.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    //查询全部博客
    public Result selectAll(){
        List<Blog> blogs = blogMapper.selectAll();
        if (blogs.size() == 0){
            return new Result().error();
        }else{
            return new Result().success().set("blogs",blogs);
        }
    }

    
    public Result selectPage(Integer currentPage,Integer pageSize, String name){
        int startIndex = (currentPage - 1) * pageSize;
        List<Blog> blogList = blogMapper.selectPage(startIndex,pageSize,name);
        if (blogList != null){
            return new Result().success().set("blogList",blogList);
        }
        return new Result().error();
    }

    public Result selectCount(String title){
        Integer count = blogMapper.selectCount(title);
        if (count != null){
            return new Result().success().set("count",count);
        }
        return new Result().error();
    }

    // 根据Id查询博客
    public Result selectById(String id){
        Blog blog = blogMapper.selectById(id);
        if (blog != null){
            return new Result().success().set("blog",blog);
        }else{
            return new Result().error();
        }
    }

    //保存博客
    public Result save(Blog blog){
        blog.setCreateTime(new Date());
        Category category = (Category) blog.getCategory();
        User user = blog.getUser();

        if (category != null){
            blog = checkBlogWithUser(blog);
        }
        if (user != null){
            blog = checkBlogWithUser(blog);
        }
        return saveBlog(blog);
    }

    public Result saveBlog(Blog blog){
        int affectRow;
        if (blog.getId() == null){
            blog.setId(IDUtils.getUUID());
            affectRow = blogMapper.insertOne(blog);
        }else{
            affectRow = blogMapper.updateOne(blog);
        }
        if (affectRow > 0){
            return new Result().success();
        }else{
            return new Result().error();
        }
    }

    // 检查博客中是否存在分类属性，进行新增
    public Blog checkBlogWithCategory(Blog blog){
        Category category = (Category) blog.getCategory();

        // 新增分类并拿到新增之后的category_id
        if (category.getId() == null){
            String category_id = (String) categoryService.save(category).getData().get("id");
            category.setId(category_id);
        }
        return blog;
    }

    // 检查博客中是否存在用户属性，进行新增
    public Blog checkBlogWithUser(Blog blog){
        User user = blog.getUser();

        // 新增用户并拿到新增之后的user_id
        if (user.getId() == null){
            String user_id = (String) userService.save(user).getData().get("id");
            user.setId(user_id);
        }
        return blog;
    }

    public Result deleteById(String id){
        int affectRow = blogMapper.deleteById(id);
        if (affectRow > 0){
            return new Result().success();
        }else{
            return new Result().error();
        }
    }




}
```

## CategoryService

* 后端的categoryService有误，导致分类加载不出来。
  * categoryList写成首字母大写了，应该是小写。
* 查询全部分类 —— selectAll方法
* 保存分类 —— save
* IDUtils工具类提供方法，生成随机id
* 进行分页 —— selectPage
* 汇总 —— selectCount
* 根据Id查询分类 —— selectById
* 删除 —— deleteById

```java
package com.li.service;

import com.li.common.IDUtils;
import com.li.common.Result;
import com.li.entity.Category;
import com.li.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Result selectAll(){
        List<Category> categoryList = categoryMapper.selectAll();
        return new Result().success().set("categoryList",categoryList);
    }

    public Result save(Category category){
        int affectRow;
        String id = IDUtils.getUUID();
        if (category.getId() == null){
            category.setId(id);
            affectRow = categoryMapper.insertOne(category);
        }else{
            affectRow = categoryMapper.updateOne(category);
            id = category.getId();
        }
        if (affectRow > 0){
            return new Result().success().set("id",id);
        }else {
            return new Result().error();
        }
    }

    public Result selectPage(Integer currPage,Integer pageSize, String categoryName){
        int startIndex = (currPage - 1) * pageSize;
        List<Category> categoryList = categoryMapper.selectPage(startIndex,pageSize,categoryName);
        if (categoryList != null){
            return new Result().success().set("categoryList",categoryList);
        }
        return new Result().error();
    }

    public Result selectCount(String name){
        Integer count = categoryMapper.selectCount(name);
        if (count != null){
            return new Result().success().set("count",count);
        }
        return new Result().error();
    }

    public Result selectById(String id){
        Category category = categoryMapper.selectById(id);
        if (category != null){
            return new Result().success().set("category",category);
        }else{
            return new Result().error();
        }
    }

    public Result deleteById(String id){
        int affectRow = categoryMapper.deleteById(id);
        if (affectRow > 0){
            return new Result().success();
        }else{
            return new Result().error();
        }
    }

}
```

## UserService

* 保存用户信息 —— save
* IDUtils工具类提供方法，生成随机id
* 查询全部用户 —— selectAll方法
* 汇总 —— selectCount
* 根据Id查询用户 —— selectById
* 进行分页 —— selectPage
* 删除 —— deleteById

```java
package com.li.service;

import com.li.common.IDUtils;
import com.li.common.Result;
import com.li.entity.User;
import com.li.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result save(User user){
        int affectRow;
        String id = IDUtils.getUUID();
        if (user.getId() == null){
            user.setId(id);
            affectRow = userMapper.insertOne(user);
        }else{
            affectRow = userMapper.updateOne(user);
            id = user.getId();
        }
        if (affectRow > 0){
            return new Result().success().set("id",id);
        }else {
            return new Result().error();
        }
    }

    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    public Integer selectCount(String username, String role){
        return userMapper.selectCount(username, role);
    }

    public List<User> selectPage(Integer pageNum, Integer pageSize, String username, String role){
        return userMapper.selectPage(pageNum,pageSize,username, role);
    }

    public Result deleteById(String id){
        int affectRow = userMapper.deleteById(id);
        if (affectRow == 0){
            return new Result().success();
        }else{
            return new Result().error();
        }
    }

    public User selectById(String id){
        return userMapper.selectById(id);
    }
}
```

# 在controller包下新建controller类

* `@PathVariable`注解：用于接收请求路径中占位符的值。
* `@RequestBody`注解：将前台传过来的json对象转换为后台的Java对象
* `@RestController`等于这两个注解`@Controller、@ResponseBody`
* `RequestMapping`是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
* 查询全部博客 —— selectAll方法
* 进行分页 —— selectPage
* 根据Id查询博客 —— selectById

* 保存博客信息 —— save
* 删除 —— deleteById

## BlogController

```java
package com.li.controller;

import com.li.common.Result;
import com.li.entity.Blog;
import com.li.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public Result selectAll(){
        return blogService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
           @RequestParam(value = "currentPage",defaultValue = "1",required = true) Integer currentPage,
           @RequestParam(value = "pageSize",defaultValue = "10",required = true) Integer pageSize,
           @RequestParam(value = "title",defaultValue = "",required = false) String title)
    {
        Result pageResult = blogService.selectPage(currentPage,pageSize,title);
        Result countResult = blogService.selectCount(title);
        if (pageResult.getState().equals("200") && countResult.getState().equals("200")){
            return new Result().success().set("blogList",pageResult.getData().get("blogList"))
                    .set("count",countResult.getData().get("count"));
        }
        return new Result().error();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") String id){
        return blogService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody Blog blog){
        return blogService.save(blog);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return blogService.deleteById(id);
    }
}
```

## CategoryController

* 查询全部分类 —— selectAll方法
* 进行分页 —— selectPage
* 根据Id查询分类 —— selectById

* 保存分类信息 —— save
* 删除 —— deleteById

```java
package com.li.controller;

import com.li.common.Result;
import com.li.entity.Blog;
import com.li.entity.Category;
import com.li.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping
    public Result selectAll(){
        return categoryService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(value = "currPage",defaultValue = "1",required = true) Integer currPage,
            @RequestParam(value = "pageSize",defaultValue = "2",required = true) Integer pageSize,
            @RequestParam(value = "categoryName",defaultValue = "",required = false) String categoryName)
        {
            Result pageResult = categoryService.selectPage(currPage,pageSize,categoryName);
            Result countResult = categoryService.selectCount(categoryName);
            if (pageResult.getState().equals("200") && countResult.getState().equals("200")){
                return new Result().success().set("categoryList",pageResult.getData().get("categoryList"))
                        .set("count",countResult.getData().get("count"));
            }
            return new Result().error();
        }


    @GetMapping("/{id}")
    public Result selectById(@PathVariable String id){
        return categoryService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        return categoryService.deleteById(id);
    }

}
```

## UserController

* 查询全部用户 —— selectAll方法
* 进行分页 —— selectPage
* 根据Id查询用户 —— selectById

* 保存用户信息 —— save
* 删除 —— deleteById

```java
package com.li.controller;

import com.li.common.Result;
import com.li.entity.User;
import com.li.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> selectAll(){
        return userService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(value = "pageNum",defaultValue = "1",required = true) Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10",required = true) Integer pageSize,
            @RequestParam(value = "username",defaultValue = "",required = false) String username,
            @RequestParam(value = "role",defaultValue = "",required = false) String role)
    {
        int startIndex = (pageNum - 1) * pageSize;
        int total = userService.selectCount(username,role);
        List<User> userList = userService.selectPage(startIndex,pageSize,username,role);
        Result result = new Result().success();
        result.set("total",total).set("userList",userList);
        return result;
    }

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") String id){
        return userService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return userService.deleteById(id);
    }

}
```

# 在common包下新建Result类

* 通用*Result类*模板代码
* 返回状态码信息

```java
package com.li.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    // 返回状态码，200表示ok，400表示错误
    private String state;
    // 返回操作信息
    private String message;
    // 返回的数据
    private Map<String, Object> data = new HashMap<>();

    public Result success() {
        return new Result("200", "操作成功", this.data);
    }

    public Result set(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public Result error() {
        return new Result("400", "操作失败", this.data);
    }


}
```

# 在common包下新建IDUtils类

* IDUtils工具类提供方法，生成随机id

```java
package com.li.common;

import java.util.UUID;

public class IDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
```

# 进行跨域配置

## 在config包下新建CorsConfig类 —— 跨域配置

```java
package com.li.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//进行跨域配置
public class CorsConfig {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }

}
```

<hr>
# blog_wolf - 前端（管理端）


* axios
* core-js
* element-ui
* mavon-editor
* pubsub-js —— 订阅消息和发布消息（控制台）
* vue-router —— Vue Router 是 Vue.js 的官方路由。它与 Vue.js 核心深度集成，让用 Vue.js 构建单页应用变得轻而易举。
  * 跳转页面
* vue2

## 配置package.json

```json
{
  "name": "admin",
  "version": "0.1.0",
  "private": true,
  "scripts": {
    "serve": "vue-cli-service serve --open",
    "build": "vue-cli-service build"
  },
  "dependencies": {
    "axios": "^0.26.0",
    "core-js": "^3.6.5",
    "element-ui": "^2.15.6",
    "mavon-editor": "^2.10.4",
    "pubsub-js": "^1.9.4",
    "vue": "^2.6.11",
    "vue-router": "^3.2.0"
  },
  "devDependencies": {
    "@vue/cli-plugin-babel": "~4.5.0",
    "@vue/cli-plugin-router": "~4.5.0",
    "@vue/cli-service": "~4.5.0",
    "vue-template-compiler": "^2.6.11"
  },
  "browserslist": [
    "> 1%",
    "last 2 versions",
    "not dead"
  ]
}
```

## 在assets文件夹中设置全局样式

### all.css

```css
/* 去掉body的边框，让body充满整个屏幕 */
*{
    margin: 0;
    padding: 0;
}

/* 设置高度100%，撑满整个屏幕*/
html, body {
    height: 100%;
}

/* 设置统一class */
.ml-5 {
    margin-left: 5px;
}

.mr-5 {
    margin-right: 5px;
}

.pd-10 {
    padding: 10px;
}
```

## 配置main.js

```js
import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 引入mavon-editor
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';


// use _ element ui
//并设置size属性的组件的默认尺寸均为small
Vue.use(ElementUI,{size:small});

// use _ mavon-editor
Vue.use(mavonEditor);

// 全局样式
import './assets/all.css'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

```

## 配置axios

### 新建utils文件夹和request.js

```js
import axios from "axios";

const request = axios.create({
    baseURL:'http://localhost:8081',
    timeout:5000
})

export default request
```

## 新建router文件夹和index.js

* vue-router是Vue.js官方的路由插件，它和vue.js是深度集成的，适合用于构建单页面应用。vue的单页面应用是基于路由和组件的，路由用于设定访问路径，并将路径和组件映射起来。传统的页面应用，是用一些超链接来实现页面切换和跳转的。在vue-router单页面应用中，则是路径之间的切换，也就是组件的切换。路由模块的本质 就是建立起url和页面之间的映射关系。

  （vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。路由实际上就是可以理解为指向，就是**我在页面上点击一个按钮需要跳转到对应的页面，这就是路由跳转**）


### index.js

```js
import Vue from 'vue'
import Manager from '../views/Manager.vue'
import VueRouter from "vue-router"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        component: Manager,
        redirect: '/index',
        children:[
            {
                path: 'index',
                name: 'Index',
                component: () => import('../views/Index')
            },
            {
                path: 'Manager/user',
                name: 'User',
                component: () => import('../views/User')
            },
            {
                path: 'Manager/blog',
                name: 'Blog',
                component: () => import('../views/Blog')
            },
            {
                path: 'Manager/category',
                name: 'Category',
                component: () => import('../views/Category')
            },
            {
                // 定义:id占位符，根据博客id来指定动态路由
                path: 'detail/:id',
                name: 'BlogDetail',
                component: () => import('../views/BlogDetail')
            }
        ]
    },
]

//history 模式不仅可以在url里放参数，还可以将数据存放在一个特定的对象中。
const router = new VueRouter({
    mode:"history",
    base: process.env.BASE_URL,
    routes
})

export default router

```

## 修改app.vue

```vue
<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>
#app {
  height: 100%;
}
</style>
```

## 新建views文件夹

* 包含管理端的各个页面。
* ![image-20221103193640126](http://qny.expressisland.cn/schoolOpens/image-20221103193640126.png)

## 在components文件夹下新建通用组件

![image-20221103193858168](C:\Users\one\AppData\Roaming\Typora\typora-user-images\image-20221103193858168.png)

## 编写通用组件

### Aside.vue

* 采用了element ui的NavMenu组件
* el-menu标签加上collapse属性，绑定变量isCollapse(属性值为boolean类型)，通过变量控制收缩展开

```vue
<template>
  <el-menu :default-openeds="['1', '2']" style="height: 100%; overflow-x: hidden;"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
            router>

      <!--  logo-->
  <div style="height: 60px; line-height: 60px; text-align: center">
    <img src="../assets/logo.png"  style="width: 20px; position: relative; top: 5px; margin-right: 5px">
    <b style="color: white" v-show="logoTextShow">叩丁狼博客后台管理</b>
  </div>

  <el-menu-item index="/">
    <template slot="title">
      <i class="el-icon-house"></i>
      <span slot="title">首页</span>
    </template>
  </el-menu-item>

  <el-submenu index="2">
    <template slot="title">
      <i class="el-icon-message"></i>
      <span slot="title">系统管理</span>
    </template>

    <el-menu-item-group>

      <el-menu-item index="/manager/user">
        <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">用户管理</span>
        </template>
      </el-menu-item>

      <el-menu-item index="/manager/blog">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">博客管理</span>
        </template>
      </el-menu-item>

      <el-menu-item index="/manager/category">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">分类管理</span>
        </template>
      </el-menu-item>

    </el-menu-item-group>

  </el-submenu>

    </el-menu>

</template>

<script>
export default {
  name:"Aside",
  props:{
    isCollapse: Boolean,   //控制收缩展开
    logoTextShow: Boolean,
    asideWidth: Number
  }
}
</script>

<style scoped></style>

```

### Header.vue

```vue
<template>
  <div style="font-size: 12px;line-height: 60px;display: flex">
    <div style="flex:1;font-size: 18px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse">
      </span>
    </div>

  <el-dropdown style="cursor: pointer;width: 80px">
    <span>管理员</span>
    <i class="el-icon-arrow-down" style="margin-right: 15px;margin-left: 5px">
    </i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item>个人信息</el-dropdown-item>
      <el-dropdown-item>退出系统</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
    collapse: Function
  }
}
</script>

<style scoped>

</style>
```

## 编写单页面组件

### Index.vue

```vue
<template>
  <div>
    欢迎来到叩丁狼博客后台管理系统
  </div>
</template>

<script>
export default {
  name: "Index"
}
</script>

<style scoped>

</style>
```

### Manager.vue

```vue
<template>
  <div style="height: 100%">
    <el-container style="height: 100%">
      <el-aside :width="asideWidth + `px`" style="background-color: rgb(238, 241, 246); height: 100%">
        <Aside :width="asideWidth" :isCollapse="isCollapse" :logoTextShow="logoTextShow">
        </Aside>
      </el-aside>

      <el-container>
<!--        flex布局-->
        <el-header style="border-bottom: 1px solid #ccc">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"></Header>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>

  </div>
</template>

<script>

import Aside from '@/components/Aside';
import Header from '@/components/Header';

export default {
  name: "Manager",
  components:{Header,Aside},
  data(){
    return{
      // Header 相关属性
      collapseBtnClass: 'el-icon-s-fold',

      // Aside 相关属性
      isCollapse: false,
      asideWith: 200,
      logoTextShow:true,
    }
  },

  methods:{
    collapse(){
      // 控制Aside隐藏、展开
      this.isCollapse = !this.isCollapse
      if (this.isCollapse){
        this.asideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else{
        this.asideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }

  }
}
</script>
```

## 初步成果

![image-20221102114015390](http://qny.expressisland.cn/schoolOpens/image-20221102114015390.png)

![image-20221102113956376](http://qny.expressisland.cn/schoolOpens/image-20221102113956376.png)

## 编写单页面组件（2）

* `：`是指令 `v-bind` 的缩写，是为了动态绑定数据，用于响应式地更新 HTML 特性。
* `v-model`在表单输入元素或组件上创建双向绑定。
* `@` 是指令 `v-on` 的缩写，用来监听DOM事件，比如点击、拖拽、键盘事件等等。
* v-on的修饰符：修饰符是以半角句号 `.` 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。
* Vue提供了修饰符来帮助我们方便的处理一些事件：
  * `.stop` 调用 event.stopPropagation()。
  *  `.prevent` 调用 event.preventDefault()
  *   `.once` 只触发一次回调

### User.vue（用户管理）

```vue
<template>
  <div>
    <!--          引入搜索框-->
    <div style="padding: 10px 0">
      <el-input
          v-model="username"
          style="width: 200px"
          suffix-icon="el-icon-search"
          placeholder="请输入用户名"
          class="ml-5">
      </el-input>
      <el-input
          v-model="role"
          style="width: 200px"
          suffix-icon="el-icon-user"
          placeholder="请输入角色"
          class="ml-5">
      </el-input>
      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
    </div>

    <!--          引入增删改查按钮-->
    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline el-icon--right"></i></el-button>
    </div>

    <!--  表格列表-->
    <!--  stripe	是否为斑马纹 table	boolean	—	false-->
    <!--  border	是否带有纵向边框-->
    <el-table :data="tableData" stripe border>
      <el-table-column prop="id" label="ID" width="260"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <!--            <el-table-column prop="avatar" label="头像"></el-table-column>-->
      <el-table-column prop="role" label="角色"></el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="success"
              @click="handleEdit(scope.$index, scope.row)">编辑
            <i class="el-icon-edit el-icon--right"></i>
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            <i class="el-icon-delete el-icon--right"></i>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--          引入分页组件-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="用户名" >
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" >
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" >
          <el-input v-model="form.role" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data() {
    return {
      // 表格数据及分页 属性
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      // 搜索框 相关属性
      username: '',
      role: '',
      // 新增｜编辑 按钮 相关属性
      dialogFormVisible: false,
      form: {
        username: "",
        password: "",
        nickname: "",
        role: ""
      }
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    // 向后端服务器请求数据并加载
    loadData() {
      request.get("/users/", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          role: this.role
        }
      }).then(
          response => {
            this.tableData = response.data.data.userList
            this.total = response.data.data.total
            console.log("loadData()被调用了")
          },
          error => {
            console.log(error)
          }
      )
    },
    // 分页查询（页大小改变）触发事件
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.loadData()
    },
    // 分页查询（当前页改变）触发事件
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.loadData()
    },
    // 搜索按钮触发事件
    handleSearch() {
      this.loadData()
    },
    // 新增按钮触发事件
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    // 对话框取消按钮触发事件
    handleCancel() {
      this.dialogFormVisible = false
      this.form = {}
    },
    // 对话框确认按钮触发事件
    handleConfirm() {
      request.post("/users", this.form).then(
          response => {
            if (response.data.state === "200") {
              this.$message.success("操作成功")
              this.loadData()
            }
          }
      )
      this.dialogFormVisible = false
    },
    // 表格中编辑按钮触发事件
    handleEdit(index, row) {
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    // 表格中删除按钮触发事件
    handleDelete(index, row) {
      let userId = row.id
      request.delete("/users/" + userId).then(
          response => {
            if(response.data.state === "200") {
              this.$message.success("删除成功")
              this.loadData()
            }
          },
          error => {
            console.log(error)
          }
      )
    }
  }
}
</script>

<style scoped>

</style>
```

### Blog.vue（博客管理）

```vue
<template>
  <div>
<!--    搜索框-->
    <div style="margin: 10px">
      <el-input
        style="width: 300px"
        placeholder="博客名称"
        suffix-icon="el-icon-search"
        v-model="searchTitle">
      </el-input>

      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
    </div>

<!--   新增博客 按钮-->
    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增博客<i class="el-icon-circle-plus el-icon--right"></i></el-button>
    </div>

<!--    表格-->
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="user.username" label="作者" width="100px"></el-table-column>
      <el-table-column prop="views" label="浏览次数" width="80px"></el-table-column>
      <el-table-column prop="category.name" label="分类" width="100px"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="160px">

        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime | formatDate}}</span>
        </template>
      </el-table-column>

      <el-table-column prop="published" label="是否发表" width="80px"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit el-icon--right"></i></el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[5, 10, 15, 20]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Blog",
  data(){
    return{
      tableData:[],
      currentPage:1,
      pageSize:10,
      total:0,
      searchTitle:''
    }
  },
  created() {
    this.loadData()
  },
  methods:{
    loadData(){
      request.get("/blogs/", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          title: this.searchTitle
        }
      }).then(
          response => {
            this.tableData = response.data.data.blogList
            this.total = response.data.data.count
          }
      )
    },
    handleAdd() {
      this.$router.push({name: "BlogDetail", params: {id: "-1"}})
    },
    handleEdit(index, row) {
      this.$router.push({name: "BlogDetail", params: {id: row.id}})
      console.log(row)
    },
    handleDelete(index, row) {
      let blogId = row.id
      request.delete("/blogs/" + blogId).then(
          response => {
            if(response.data.state === "200") {
              this.$message.success("删除成功！")
              this.loadData()
            }
          },
          error => {
            this.$message.error("操作失败！")
          }
      )
    },

    handleCurrentChange(val){
      this.currentPage = val
      this.loadData()
    },

    handleSizeChange(val){
      this.pageSize = val
      this.loadData()
    },

    handleSearch(val){
      this.loadData()
    },

    // 过滤器，用于格式化日期时间
    filters:{
      formatDate: function (dateTimeStr) {
        let dateTime = new Date(dateTimeStr)
        let YY = dateTime.getFullYear()
        let MM = dateTime.getMonth().toString().padStart(2, '0')
        let DD = dateTime.getDay().toString().padStart(2, '0')
        let hh = dateTime.getHours().toString().padStart(2, '0')
        let mm = dateTime.getMinutes().toString().padStart(2, '0')
        let ss = dateTime.getSeconds().toString().padStart(2, '0')
        return YY + "-" + MM + "-" + DD + " " + hh + ":" + mm + ":" + ss
      }
    }

  }
}
</script>

<style scoped>

</style>
```

### Category.vue（分类管理）

```vue
<template>
  <div>
    <!--    搜索框-->
    <div style="margin: 10px">
      <el-input
          style="width: 300px"
          placeholder="分类名称"
          suffix-icon="el-icon-search"
          v-model="searchName">
      </el-input>

      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
      </div>

    <!--   新增分类 按钮-->
    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增分类<i class="el-icon-circle-plus el-icon--right"></i></el-button>
    </div>

    <!--    表格-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit el-icon--right"></i></el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination @current-change="handleCurrentChange"
                     :current-page="currPage"
                     :page-size="pageSize"
                     layout="total, prev, pager, next"
                     :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </div>
    </el-dialog>

    </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Category",
  data(){
    return{
      tableData:[],
      form:{
        name:''
      },
      dialogFormVisible:false,
      currPage:1,
      pageSize:5,
      total:0,
      searchName:''
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    // 向后端服务器请求数据并加载
    loadData() {
      request.get("/categories/", {
        params: {
          currPage: this.currPage,
          pageSize: this.pageSize,
          categoryName: this.searchName
        }
      }).then(
          response => {
            this.tableData = response.data.data.categoryList
            this.total = response.data.data.count
            console.log(response)
          }
      )
    },
    // 新增按钮触发事件
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    // 对话框取消按钮触发事件
    handleCancel() {
      this.dialogFormVisible = false
      this.form = {}
    },
    // 对话框确认按钮触发事件
    handleConfirm() {
      this.dialogFormVisible = false
      request.post("/categories", this.form).then(
          response => {
            if (response.data.state === '200') {
              this.$message.success("操作成功")
              this.loadData()
            }
          }
      )
    },
    // 表格中编辑按钮触发事件
    handleEdit(index, row) {
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    // 表格中删除按钮触发事件
    handleDelete(index, row) {
      let categoryId = row.id
      request.delete("/categories/" + categoryId).then(
          response => {
            if (response.data.state === '200') {
              this.$message.success("删除成功")
              this.loadData()
            }
          },
          error => {
            this.$message.error("该分类下还有文章哟，不能删除~")
          }
      )
    },

    // 分页查询（当前页改变）触发事件
    handleCurrentChange(val) {
      this.currPage = val
      this.loadData()
    },
    // 搜索按钮触发事件
    handleSearch() {
      this.loadData()
    }
  }
}

</script>

<style scoped>

</style>


```

### BlogDetail.vue（编写文章）

```vue

<template>
  <div>
    <div class="title-box">
      <el-input placeholder="请输入博客标题" v-model="form.title">
        <template slot="prepend">标题</template>
      </el-input>
    </div>
    <div class="title-box">
      <el-input placeholder="请输入题图链接" v-model="form.titleImage">
        <template slot="prepend">题图<i class="el-icon-link el-icon--right"></i> </template>
      </el-input>
    </div>
    <mavon-editor v-model="form.content" style="min-height: 500px"/>

    <div class="bottom-box">
      <el-select
          class="mr-5"
          v-model="categoryId"
          filterable
          allow-create
          default-first-option
          placeholder="请选择博客分类">
        <el-option
            v-for="item in optionsCategory"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <div style="flex: 1"></div>
      <el-button type="primary" @click="handleSave('否')">保存为草稿</el-button>
      <el-button type="primary" @click="handleSave('是')">发表</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "BlogDetail",
  data() {
    return {
      categoryId: '',
      optionsCategory: [],
      form: {
        title: '',
        description: '',
        content: '',
        views: 0,
        published: '',
        titleImage: '',
        user: {'id': '1', name: '良辰美景'},
        category: {},
      }
    }
  },
  created() {
    this.loadCategory()
    this.loadBlogDataByIdFromPath()
    // console.log(this.form)
    // console.log(this.optionsCategory)
  },
  methods: {
    // 初始化界面，加载分类数据
    loadCategory() {
      request.get("/categories").then(
          response => {
            this.optionsCategory = response.data.data.categoryList
          }
      )
    },
    loadBlogDataByIdFromPath() {
      // 根据参数是否包含blog来判断是新增博客还是编辑博客
      let blog_id = this.$route.params.id
      if (blog_id !== '-1') {
        request.get("/blogs/" + blog_id).then(
            response => {
              let blog = response.data.data.blog
              this.form = blog
              this.categoryId = blog.category.id
            }
        )
      }
    },
    // 保存并发表
    handleSave(published) {
      let newCategoryFlag = !this.isNewCategory()

      if(newCategoryFlag) {
        this.form.category = {"name": this.categoryId}
      } else {
        this.updateCategory()
      }
      this.form.published = published
      this.form.description = this.form.content.slice(0,20) + "..."
      console.log(this.form.description)

      request.post("/blogs", this.form).then(
          response => {
            if(response.data.state === '200') {
              this.$message.success("保存成功！")
              this.$router.push("/manager/blog")
            }
          },
          error => {
            this.$message.error("保存失败！")
          }
      )
    },


    // 判断当前分类是否为一个新的分类
    isNewCategory() {
      for(let i=0; i<this.optionsCategory.length; i++) {
        if(this.categoryId === this.optionsCategory[i].id) {
          return true
        }
      }
      return false
    },
    // 根据绑定在select选择器中的categoryId更新form中的category
    updateCategory() {
      for(let i=0; i<this.optionsCategory.length; i++) {
        if(this.categoryId === this.optionsCategory[i].id) {
          this.form.category = this.optionsCategory[i]
          return
        }
      }
    }
  }
}
</script>

<style scoped>
.title-box {
  width: 500px;
  margin: 10px auto;
}

.bottom-box {
  display: flex;
  margin: 10px 0;
}

</style>
```



# blog_wolf - 前端（用户端）

## 配置package.json

* axios
* core-js
* element-ui
* mavon-editor
* pubsub-js —— 订阅消息和发布消息（控制台）
* vue-router —— Vue Router 是 Vue.js 的官方路由。它与 Vue.js 核心深度集成，让用 Vue.js 构建单页应用变得轻而易举。
  * 跳转页面
* vue2
* echarts-wordcloud —— 词云图
* vue-b2wordcloud —— 词云
* echarts
* markdown-it —— Markdown 解析器
* marked —— Markdown 解析和编译器

```json
{
  "name": "blog",
  "version": "0.1.0",
  "private": true,
  "scripts": {
    "serve": "vue-cli-service serve --open",
    "build": "vue-cli-service build"
  },
  "dependencies": {
    "axios": "^0.26.0",
    "core-js": "^3.6.5",
    "echarts": "^5.3.2",
    "echarts-wordcloud": "^2.0.0",
    "element-ui": "^2.15.6",
    "markdown-it": "^13.0.0",
    "marked": "^4.0.14",
    "vue": "^2.6.11",
    "vue-b2wordcloud": "^1.0.5",
    "vue-router": "^3.2.0",
    "vuex": "^3.4.0"
  },
  "devDependencies": {
    "@vue/cli-plugin-babel": "~4.5.0",
    "@vue/cli-plugin-router": "~4.5.0",
    "@vue/cli-plugin-vuex": "~4.5.0",
    "@vue/cli-service": "~4.5.0",
    "vue-template-compiler": "^2.6.11"
  },
  "browserslist": [
    "> 1%",
    "last 2 versions",
    "not dead"
  ]
}
```

## 在assets文件夹中引入iconfont

![image-20221102233730736](http://qny.expressisland.cn/schoolOpens/image-20221102233730736.png)

* 注意：此处iconfont.css如果为相对路径的，要改为绝对路径。

## 在assets文件夹中新建img文件夹

* 引入author.png
* ![logo2](http://qny.expressisland.cn/schoolOpens/logo2.png)
* 引入cover.jpg
* 如果文章没有指定封面，则使用该图片。
* ![image-20221103201123820](http://qny.expressisland.cn/schoolOpens/image-20221103201123820.png)

## 配置main.js

* 引入element-ui、echarts、iconfont

```js
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/iconfont/iconfont.css'
import './assets/css/iconfont/iconfont'
import echarts from 'echarts';

Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

```

## 配置axios

### 新建utils文件夹和request.js

```js
import axios from "axios";

const request = axios.create({
    baseURL:'http://localhost:8081',
    timeout:5000
})

export default request
```

## 新建router文件夹和index.js

* vue-router是Vue.js官方的路由插件，它和vue.js是深度集成的，适合用于构建单页面应用。vue的单页面应用是基于路由和组件的，路由用于设定访问路径，并将路径和组件映射起来。传统的页面应用，是用一些超链接来实现页面切换和跳转的。在vue-router单页面应用中，则是路径之间的切换，也就是组件的切换。路由模块的本质 就是建立起url和页面之间的映射关系。

  （vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。路由实际上就是可以理解为指向，就是**我在页面上点击一个按钮需要跳转到对应的页面，这就是路由跳转**）


### index.js

```js
import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/home/Home";
import Category from "@/views/category/Category";
import Archive from "@/views/archive/Archive";
import About from "@/views/about/About";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/category',
        name: 'Category',
        component: Category
    },
    {
        path: '/archive',
        name: 'Archive',
        component: Archive
    },
    {
        path: '/about',
        name: 'About',
        component: About
    },
    {
        path: '/blog/:id',
        name: 'Blog',
        component: () => import('../views/blog/Blog')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

```

## 配置Vuex

* Vuex 可以帮助我们管理共享状态，并附带了更多的概念和框架。这需要对短期和长期效益进行权衡。
* 可以`方便的实现组件之间的数据共享`

### 新建store文件夹

* 新建index.js

#### index.js

```js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
    },
    mutations: {
    },
    actions: {
    },
    modules: {
    }
})

```

## 修改app.vue

```vue
<template>
  <div id="app">
    <Header></Header>
    <router-view></router-view>
    <BackToTop></BackToTop>
    <Footer></Footer>
  </div>
</template>

<style>

</style>
<script>

import BackToTop from "@/components/layout/BackToTop";
import Header from "@/components/layout/Header";
import Footer from "@/components/layout/Footer";

export default {
  components: {Footer, Header, BackToTop}
}
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  background-color: #efeded;
}
#app {
  height: 100%;
}
</style>
```

## 新建views文件夹

* 包含用户端的各个页面。
* 
* ![image-20221103201447798](http://qny.expressisland.cn/schoolOpens/image-20221103201447798.png)

## 在components文件夹下layout文件夹

* 新建vue通用组件
* ![image-20221102235532771](http://qny.expressisland.cn/schoolOpens/image-20221102235532771.png)

## 编写通用组件

### BlogItem.vue

* 每个blog，称为`blog item`

```vue
<template>
  <div class="article" @click="view(blog)">
    <div class="article-cover">
      <img v-if="blog.titleImage" :src="blog.titleImage" style="width: 100%; height: 100%; border-radius: 5px">
      <img v-else src="../assets/img/cover.jpg" style="width: 100%; height: 100%; border-radius: 5px">
    </div>

    <div class="article-item-right">
      <div class="article-title">
        {{blog.title}}
      </div>

      <div class="article-info">
        <span>
            <i class="el-icon-date"></i> {{blog.createTime | formatDate}}
            <span class="separator">| </span>
          </span>

        <span>
          <i class="el-icon-document"></i> {{blog.category.name}}
        </span>
      </div>

      <div class="article-description">
        {{blog.description}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BlogItem",
  props:['blog'],
  data(){
    return{}
  },

  methods:{
    view(blog){
      console.log('view方法被调用了')
      this.$router.push({name: 'Blog', params: {id: blog.id, blog: blog}})
    }
  },
  // 过滤器，用于格式化日期时间
  filters: {
    formatDate: function (dateTimeStr) {
      let dateTime = new Date(dateTimeStr)
      let YY = dateTime.getFullYear()
      let MM = dateTime.getMonth().toString().padStart(2, '0')
      let DD = dateTime.getDay().toString().padStart(2, '0')
      let hh = dateTime.getHours().toString().padStart(2, '0')
      let mm = dateTime.getMinutes().toString().padStart(2, '0')
      let ss = dateTime.getSeconds().toString().padStart(2, '0')
      return YY + "-" + MM + "-" + DD + " " + hh + ":" + mm + ":" + ss
    }
  }

}
</script>

<style scoped>
.article {
  display: flex;
  background-color: white;
  width: 730px;
  height: 200px;
  border: 1px solid white;
  margin: 10px 0;
  border-radius: 5px;
}

.article-cover {
  width: 270px;
}
.article-item-right {
  width: 440px;
  margin-left: 20px;
}
.article-title {
  font-size: 20px;
  text-align: center;
  margin: 20px 0;
}
.article-info {
  font-size: 13px;
  color: gray;
}
.article-description {
  margin-top: 10px;
  font-size: 14px;
  color: gray;
}
</style>
```

### card目录

#### AuthorInfo.vue（博主信息）

```vue
<template>
  <div class="author-card">
    <div class="author-img">
      <img src="../../assets/img/author.png" style="margin-left:10%;width: 120%;height: 100%">
    </div>

    <div>
      <div style="font-size: 30px">Wolf</div>
      <div style="margin: 10px 0">千里稻花应秀色，五更桐叶最佳音</div>
    </div>

    <div class="blog-info">
      <div>
        <div class="text-item">文章</div>
        <div>{{blogNum}}</div>
      </div>

      <div>
        <div class="text-item">分类</div>
        <div>{{categoryNum}}</div>
      </div>

      <div>
        <div class="text-item">标签</div>
        <div>10</div>
      </div>
    </div>

    <div class="fellow-me">联系方式</div>
    <div class="contact-me">
      <a class="iconfont iconfont-GitHub"
                style="font-size: 30px"
                href="https://github.com/yosoroQ"
                TARGET="_blank"
      ></a>
      <a class="iconfont iconfont-bilibili"
         style="font-size: 30px"
         href="https://space.bilibili.com/6536171"
         TARGET="_blank"
      ></a>
      <a
          class="iconfont iconfont-QQ"
         style="font-size: 30px"
         href="https://wpa.qq.com/wpa_jump_page?v=3&uin=1249508470&site=qq&menu=yes"
         TARGET="_blank"
      ></a>
    </div>
  </div>
</template>

<script>
export default {
  name: "AuthorInfo",
  props: ['blogNum', 'categoryNum']
}
</script>

<style scoped>
.author-card {
  width: 270px;
  height: 350px;
  background-color: white;
  border-radius: 10px;
  text-align: center;
}
.author-img {
  width: 100px;
  height: 100px;
  margin: 0 auto;
  padding-top: 5px;
}
.blog-info {
  display: flex;
  justify-content: space-between;
  margin: 10px 50px;
}
.text-item {
  margin: 10px 0;
}
.fellow-me {
  height: 30px;
  width: 240px;
  background-color: #49b1f5;
  line-height: 30px;
  color: white;
  margin: 0 auto;
}

.contact-me {
  margin: 10px 0;
}
.contact-me a {
  margin: 0 20px;
  text-decoration: none;
  color: #49b1f5;
}
</style>
```

#### WebsiteInfo.vue（网站运行信息）

```vue
<template>
  <div class="website-card">
    <div class="icon-title">
      <i class="el-icon-sunny" style="font-size: 18px"/>有什么好看的呢
    </div>

    <div class="detail-info">
      <div>本站勉强运行：{{ runTime }}</div>
      <div style="margin-top: 5px">总浏览量： {{ totalViews }} 次</div>
    </div>
  </div>
</template>

<script>
import request  from "@/utils/request";

export default {
  name: "WebsiteInfo",
  data(){
    return{
      runTime:'',
      totalViews:0,
      startDate: '2022-11-01 00:00:00'
    }
  },

  created() {
    this.calcTotalViews()
  },
  mounted() {
    this.timer = setInterval(this.refreshRunTime, 1000)
  },

  beforeDestroy() {
    clearInterval(this.timer)
  },

  methods:{
    refreshRunTime(){
      let time = Date.now() - Date.parse(this.startDate)
      console.log(time)
      let dd = Math.floor(time / (24 * 60 * 60 * 1000))
      let hh = Math.floor((time - dd * (24 * 60 * 60 * 1000)) / (60 * 60 * 1000))
      let mm = Math.floor((time - dd * (24 * 60 * 60 * 1000) - hh * (60 * 60 * 1000)) / (60 * 1000))
      let ss = Math.floor((time - dd * (24 * 60 * 60 * 1000) - hh * (60 * 60 * 1000) - mm * (60 * 1000)) / 1000)
      this.runTime = dd + "天" + hh + "时" + mm + "分" + ss + "秒"
    },

    calcTotalViews(){
      request.get("/blogs").then(
          response => {
            let blogs = response.data.data.blogs
            let total = 0
            blogs.forEach(function (blog) {
              total += blog.views
            })
            this.totalViews = total
          }
      )
    }
  }
}
</script>


<style scoped>
.website-card {
  width: 270px;
  height: 140px;
  margin-top: 20px;
  background-color: white;
  border-radius: 10px;
}

.icon-title {
  font-size: 18px;
  padding: 20px;
}

.detail-info {
  padding-left: 20px;
  font-size: 14px;
  color: #000000;
}

</style>
```

### layout目录

#### BackToTop.vue（回到顶部）

```vue
<template>
  <transition>
    <div @click="toTop" v-show="topShow" class="me-to-top">
      <i class="el-icon-caret-top"></i>
    </div>
  </transition>
</template>

<script>
export default {
  name: "BackToTop",
  data(){
    return{
      topShow:false
    }
  },

  methods:{
    toTop(){
      document.documentElement.scrollTop = 0
      this.topShow = false
    },
    needToTop(){
      //获取滚动条位置
      let currentHeight = document.documentElement.scrollTop
      this.topShow = currentHeight > 400;
    }
  },

  mounted() {
    // 等到页面渲染完成之后给window添加一个监听事件（用于监听滚动条的位置，来判断是否需要触发needToTop函数）
    this.$nextTick(function (){
      window.addEventListener('scroll', this.needToTop)
    })
  }
}
</script>

<style scoped>
.me-to-top {
  background-color: #fff;
  position: fixed;
  right: 100px;
  bottom: 150px;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  /*鼠标放到该位置显示为一个手指的形状*/
  cursor: pointer;
  transition: 0.3s;
  /*给div元素添加阴影*/
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
  z-index: 5;
}
.me-to-top i {
  color: skyblue;
  display: block;
  /*line-height和height相同，控制元素垂直居中*/
  line-height: 40px;
  /*控制元素水平居中*/
  text-align: center;
  font-size: 18px;
}
</style>
```

#### Footer.vue（版权信息部分，底部）

```vue
<template>
  <el-footer style="height: 100px">
    <div class="footer-wrap">
      <div style="margin-bottom: 10px">©2022 - 19信管2班</div>
      <a href="https://beian.miit.gov.cn/" target="_blank" style="color: white; text-decoration: none">
        粤ICP备20047854号
      </a>
    </div>
  </el-footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.el-footer {
  background: #795da3;
}
.footer-wrap {
  text-align: center;
  position: relative;
  font-size: 13px;
  color: white;
  top: 20%;
}
</style>
```

#### Header.vue（头部，导航栏）

```vue
<template>
  <div class="header-container">
    <div class="header-left">
      叩丁狼博客
    </div>
    <div style="flex: 1"></div>
    <div class="header-right">
      <!--      使用vue-router模式，在激活导航时以index作为path进行路由跳转 -->
      <el-menu :default-active="path"
               router
               mode="horizontal"
               background-color="#efeded"
               active-text-color="black"
               style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; color: #ccc"
      >
        <el-menu-item style="height: 50px; line-height: 50px" index="/">首页</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/category">分类</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/archive">归档</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/about">关于</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      path: this.$router.path
    }
  }
}
</script>

<style scoped>
.header-container {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #cccccc;
  display: flex;
}
.header-left {
  width: 200px;
  padding-left: 50px;
  font-weight: bold;
  font-size: 25px;
  color: #49b1f5;
}
.header-right {
  width: 500px;
}
</style>
```

## 编写单页面组件

### About.vue（关于页面）

```vue
<template>
  <div class="container">
    <div class="content-box">
      <div class="subtitle">我是谁</div>
      <hr>
      <div class="text">
        <p>猜猜我是谁？！</p>
        <p>19信管2班 实训第八组 所做作品</p>
        <p>组内成员：</p>
        <ul>
          <li>李海威</li>
          <li>陈子宜</li>
          <li>庞伟杰</li>
          <li>黄文健</li>
          <li>杨雨</li>
        </ul>
      </div>
      <div class="subtitle">关于本站</div>
      <hr/>
      <div class="text">
        技术栈？很简单
        <br>
        <p><b>后端</b></p>
        <ul>
          <li>SpringBoot</li>
          <li>Mybatis</li>
          <li>Mysql</li>
        </ul>
        <b>前端</b>
        <ul>
          <li>Vue</li>
          <li>Vue router</li>
          <li>Axios</li>
          <li>Element Ui</li>
        </ul>
      </div>
      <div class="subtitle">我们在这里等你！</div>
      <hr>
      <p>学瀚楼</p>
<!--      <baidu-map class="bm-view" ak="rK1hIqy1rMCK5AQdID75hoFe8wVEazue">-->
<!--      </baidu-map>-->
<!--      <baidu-map :center="center" :zoom="zoom" @ready="handler"></baidu-map>-->
      <baidu-map id="mapContent" class="bm-view" ak="rK1hIqy1rMCK5AQdID75hoFe8wVEazue" :center="center" :zoom="zoom" @ready="handler">
        <bm-marker :position="{lng: 110.34469, lat: 21.2943}" :dragging="true">
<!--          湛江科技学院坐标-->
<!--          110.34469,21.2943-->
        </bm-marker>
        <bm-map-type :map-types="mapType" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
      </baidu-map>

      <div>

      </div>
    </div>
  </div>

</template>

<script>
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import BmMarker from 'vue-baidu-map/components/overlays/Marker.vue'

export default {
  name: "About",
  data() {
    return {
      center: {lng: 110.34469,lat: 21.2943},
      zoom: 50,
      mapType:['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']
    }
  },
  components: {
    BaiduMap,
    BmMarker
  },
  methods: {
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = 110.34469
      this.center.lat = 21.2943
      this.zoom = 50,
          //这句使得初始为卫星图
          map.setMapType(BMAP_HYBRID_MAP);
    },
  }
}
</script>

<style scoped>

.container {
  min-height: calc(100vh - 120px - 50px - 40px);
  background: white;
  width: 800px;
  margin: 20px auto;
  border-radius: 10px;
}

.content-box {
  padding: 10px 20px;
}

.subtitle {
  font-size: 1.3em;
  margin: 20px 0 10px 0;
}

.logo {
  font-size: 30px;
  margin: 20px;
  text-decoration: none;
}

.text {
  color: dimgray;
  font-size: 15px;
  line-height: 22px;
}

.bm-view {
  width: 100%;
  height: 300px;
}
.map {
  width: 100%;
  height: 400px;
}


</style>
```

### Home.vue（首页）

```vue
<template>
  <el-container>
    <el-main>
      <div style="min-height: 80%">
          <BlogItem
                    v-for="blog in blogs"
                    :key="blog.id"
                    :blog="blog"
          ></BlogItem>
      </div>

      <div>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[2, 5, 10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </el-main>

    <el-aside style="width: 270px">
      <div style="position: fixed">
        <AuthorInfo
              :blog-num="total"
              :category-num="categoryNum"
          ></AuthorInfo>
        <WebsiteInfo/>
      </div>
    </el-aside>
  </el-container>
</template>

<script>
import BlogItem from "@/components/BlogItem";
import AuthorInfo from "@/components/card/AuthorInfo";
import request from "@/utils/request";
import WebsiteInfo from "@/components/card/WebsiteInfo";

export default {
  name: "Home",
  components: {WebsiteInfo, AuthorInfo, BlogItem},
  data(){
    return{
      blogs: [],
      currentPage: 1,
      pageSize: 5,
      total: 0,
      categoryNum: 0,
      totalViews: 0,
    }
  },

  created() {
    this.loadBlogData()
    this.loadCategoryData()
  },

  methods:{
    loadBlogData() {
      request.get("/blogs/", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
      }).then(
          response => {
            this.blogs = response.data.data.blogList
            this.total = response.data.data.count
          }
      )
    },

    loadCategoryData(){
      request.get("/categories").then(
          response => {
            this.categoryNum = response.data.data.categoryList.length
          }
      )
    },

    handleSizeChange(val){
      this.pageSize = val
      this.loadBlogData()
    },

    handleCurrentChange(val) {
      this.currentPage = val
      this.loadBlogData()
    }
  }
}
</script>

<style scoped>
.el-container {
  width: 1020px;
  min-height: 100%;
  margin: 10px auto;
}
.el-main {
  padding: 10px 0;
}
.el-aside {
  margin-left: 10px;
  margin-top: 20px;
}

</style>
```

### Category.vue（分类页面、词云）

```vue
<template>
  <div class="category-container">
    <div class="category-box" ref="chart1"></div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';
import 'echarts-wordcloud';


export default {
  name: "Category",
  data(){
    return{
      categories:[]
    }
  },

  created() {
    this.loadCategoryData()
  },

  watch:{
    // 使用watch属性监听categories的变化，mounted获取不到created中的数据
  categories:function (val){
    this.showEcharts()
  }
  },

  methods:{
    loadCategoryData() {
      request.get("/categories").then(
          response => {
            let tmp_data = []
            let categories = response.data.data.categoryList

            if(categories){
              categories.forEach(function (category) {
                tmp_data.push({'name': category.name, 'value': Math.floor(Math.random() * (50 - 1 + 1) + 1)})
              })
              this.categories = tmp_data
            }

          }
      )
    },

    showEcharts(){
      let chart = echarts.init(this.$refs.chart1)

      let option = {
        series: [{
          type: 'wordCloud',
          shape: 'cardioid',

          left: 'center',
          top: 'center',
          width: '80%',
          height: '80%',
          right: null,
          bottom: null,

          sizeRange: [18, 100],

          rotationRange: [-90, 90],
          rotationStep: 25,

          gridSize: 25,
          drawOutOfBound: false,

          // Global text style
          textStyle: {
            fontFamily: 'sans-serif',
            fontWeight: 'bold',
            // Color can be a callback function or a color string
            color: function () {
              // Random color
              return 'rgb(' + [
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255)
              ].join(',') + ')';
            }
          },

          emphasis: {
            focus: 'self',

            textStyle: {
              textShadowBlur: 10,
              textShadowColor: '#333'
            }
          },

          data: this.categories
        }]
      }

      chart.setOption(option)
    }
  }
}
</script>

<style scoped>
.category-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 120px - 50px);
  text-align: center;
}

.category-box {
  height: 500px;
  width: 800px;
  background-color: white;
  border-radius: 10px;
}
</style>
```

### Archive.vue（归档页面）

```vue
<template>
  <div class="container">
    <div class="archive-card">
      <div
          v-for = "year in years"
      >
        <h3 style="color: black">{{year}}</h3>
      <hr>

        <div
            v-for = "blog in archive[years]"
            style="color: dimgray"
        >
          {{blog.createTime | formatDate}} &nbsp;&nbsp;&nbsp; {{blog.title}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Archive",
  data(){
    return{
      years:[],
      archive:{}
    }
  },

  created() {
    this.loadAllBlog()
  },

  methods:{
    loadAllBlog(){
      request.get("/blogs").then(
          response => {
            let blogs = response.data.data.blogs
            this.archiveByYear(blogs)
          }
      )
    },

    archiveByYear(blogs){
      let archive_dict = {}
      blogs.forEach(function (blog) {
        let year = blog.createTime.slice(0, 4)
        if(archive_dict.hasOwnProperty(year)) {
          archive_dict[year].push(blog)
        } else {
          archive_dict[year] = [blog]
        }
      })
      let years = Object.keys(archive_dict).sort(function (a, b) {
        return a > b ? -1 : a < b ? 1 : 0
      })
      this.years = years
      this.archive = archive_dict
    }
  },

  filters:{
    formatDate:function(dateTimeStr){
      let dateTime = new Date(dateTimeStr)
      let YY = dateTime.getFullYear()
      let MM = dateTime.getMonth().toString().padStart(2, '0')
      let DD = dateTime.getDay().toString().padStart(2, '0')
      return YY + "-" + MM + "-" + DD
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 120px - 50px);
}

.archive-card {
  width: 800px;
  min-height: 400px;
  margin: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 10px;
}

</style>
```

### Blog.vue（博客详情页面）

* 

```vue
<template>
  <div class="blog-box">
    <div class="base-info">
      <h1>{{blog.title}}</h1>

      <div style="font-size: 15px">
        <span>
          <i class="el-icon-time">
            文章发表于：{{blog.createTime | formatDate}}
          </i>
        </span>

        <span>
          <i class="el-icon-document">
            {{blog.category.name}} |
          </i>
        </span>

        <span>
          <i class="el-icon-view">
           阅读量：{{blog.views}}
          </i>
        </span>
      </div>
    </div>

    <div class="content">
      <div
          v-html="markdownToHtml()"
      ></div>
    </div>
  </div>
</template>

<script>
//highlight.js, 代码高亮库
import request from "@/utils/request";
import MarkdownIt from "markdown-it";
import hljs from "highlight.js";
import 'highlight.js/styles/default.css';


export default {
  name: "Blog",
  data(){
    return{
      blog:{}
    }
  },

  created() {
    this.loadDataByIdFromPath()
    this.markdownToHtml()
  },

  methods:{
    // 根据path中的id向后端请求博客数据
    loadDataByIdFromPath(){
      let blog_id = this.$route.path.split("/")[2]
      request.get("/blogs/" + blog_id).then(
          response =>{
            this.blog = response.data.data.blog
            console.log(this.blog)
            this.updateViews()
          }
      )
    },

    // markdown文本转html
    markdownToHtml(){
      let md = new MarkdownIt({
        html:true,
        linkify:true,
        typographer:true,
        highlight:function (str,lang){
          if (lang && hljs.getLanguage(lang)) {
            try {
              return '<pre class="hljs"><code>' +
                  hljs.highlight(str, {language: lang, ignoreIllegals: true}).value +
                  '</code></pre>'
            } catch(_) {}
          }
        }
      })

      return md.render(this.blog.content)
    },

    // 更新浏览量updateViews()
    updateViews(){
      let form = {id:this.blog.id,views:this.blog.views +1}
      request.post("/blogs", form).then(
          response => {
            console.log(response)
          }
      )
    }
  },

  filters: {
    formatDate: function (dateTimeStr) {
      let dateTime = new Date(dateTimeStr)
      let YY = dateTime.getFullYear()
      let MM = dateTime.getMonth().toString().padStart(2, '0')
      let DD = dateTime.getDay().toString().padStart(2, '0')
      let hh = dateTime.getHours().toString().padStart(2, '0')
      let mm = dateTime.getMinutes().toString().padStart(2, '0')
      let ss = dateTime.getSeconds().toString().padStart(2, '0')
      return YY + "-" + MM + "-" + DD + " " + hh + ":" + mm + ":" + ss
    }
  }
}
</script>

<style scoped>
.blog-box {
  min-height: calc(100vh - 120px - 50px - 100px);
  width: 60%;
  margin: 50px auto;
}

.base-info {
  text-align: center;
  margin: 30px 0 60px 0;
  color: palevioletred;
}

.content {
  background-color: white;
  color: dimgray;
  font-family: "Arial", sans-serif;
  border: 1px solid white;
  border-radius: 10px;
  padding: 0 20px;
}

</style>
```









