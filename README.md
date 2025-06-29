# TicketSystem 票务系统

## 项目简介
TicketSystem 是一个基于 Spring Boot 的简易票务管理系统，支持用户注册、登录、购票、订单管理等功能，适合学习和演示使用。

## 主要功能
- 用户注册与登录
- 门票信息浏览
- 在线购票与订单管理
- 管理员后台管理
- 支持二维码生成
- WebSocket 实时通信

## 技术栈
- Spring Boot
- MyBatis
- WebSocket
- Thymeleaf
- 前端：HTML/CSS/JavaScript

## 启动方式
1. 克隆项目到本地：
   ```bash
   git clone https://github.com/fbd-creat/TicketSystem.git
   ```
2. 导入到 IDEA 或 Eclipse 等 IDE。
3. 配置数据库连接（修改 `src/main/resources/application.yml`）。
4. 执行数据库脚本，初始化表结构。
5. 启动 `Application.java` 主类。
6. 访问 `http://localhost:8080`。

## 目录结构
```
TicketSystem/
├── src/main/java/com/study/
│   ├── Application.java           # 启动类
│   ├── bean/                     # 实体类
│   ├── component/                # 组件（如 WebSocket）
│   ├── config/                   # 配置类
│   ├── controller/               # 控制器
│   ├── mapper/                   # MyBatis 映射接口
│   ├── service/                  # 业务逻辑
│   └── utils/                    # 工具类
├── src/main/resources/
│   ├── application.yml           # 配置文件
│   ├── mapper/                   # MyBatis 映射 XML
│   ├── public/                   # 静态资源
│   ├── static/                   # 静态资源
│   └── templates/                # Thymeleaf 模板
├── pom.xml                       # Maven 配置
└── README.md                     # 项目说明
```

## 联系方式
如有问题或建议，欢迎提 issue 或联系作者。
