# EBookShop

**项目链接：**https://github.com/zzzzffff6666/ebookshop.git

## 1. 功能需求

- 登陆
- 注册
- 修改用户信息
- 增加书本
- 删除书本
- 修改书本信息
- 查询书本信息
- 搜索（书本）
- 下载书本

## 2. 数据表设计

- User表

|   id    |    name     |  password   |    phone    |    authority    |
| :-----: | :---------: | :---------: | :---------: | :-------------: |
| primary |   unique    |             |             | 0增,1删,2改,3查 |
|   int   | varchar(30) | varchar(16) | varchar(30) |   varchar(20)   |

- Book表

|   id    |     name     | price |     tag     |   author    |    file     |
| :-----: | :----------: | :---: | :---------: | :---------: | :---------: |
| primary |    unique    |       |             |             |             |
|   int   | varchar(100) | float | varchar(30) | varchar(30) | varchar(40) |

## 3. 数据库建表语句（Mysql）

```sql
** Log in mysql as root, then execute the sql statement follows: **

create database shop;

use shop;

create table user (
	id int auto_increment,
    name varchar(30) unique,
    password varchar(16),
    phone varchar(30),
    authority varchar(20),
    primary key pk_user(id)
);

create table book (
	id int auto_increment,
    name varchar(100) unique,
    price float,
    tag varchar(30),
    author varchar(30),
    file varchar(40),
    primary key pk_book(id)
);

** Next is the sql statement to delete the tables and database **

drop table book;
drop table user;

use mysql;
drop database shop;
```

## 4. 前端页面

- login.html
- all_book.html
- modify.html

## 5. 访问控制器

|     请求     |      URL       |     页面      |
| :----------: | :------------: | :-----------: |
|     登陆     |     /login     |  login.html   |
|     列表     |  /getAllBook   | all_book.html |
|     查看     |   /showFile    |               |
|     删除     |  /delete/{id}  | all_book.html |
| 修改信息页面 | /modify/{name} |  modify.html  |
|     修改     |    /modify     |  modify.html  |
|     增加     |    /addBook    | all_book.html |

