# engineering-task-system

## 项目结构

此仓库为前后端分离工程模板，前端已初始化 Vue 3 + TypeScript + Vite 项目，后端已初始化 Spring Boot + Maven 项目。

- `frontend/`：前端代码目录
  - `src/views/`：页面视图
  - `src/types/`：前端 TypeScript 类型定义
  - `src/mock/`：前端静态 mock 数据，例如任务管理页演示数据
- `backend/`：后端代码目录
  - `src/main/java/`：Spring Boot 应用源码
  - `src/main/resources/`：后端配置文件
  - `src/test/java/`：后端测试代码
- `docs/`：项目文档或设计说明
- `scripts/`：自动化脚本和开发辅助脚本

## 后端启动

后端使用 Java 21、Maven 和 Spring Boot。Maven 加入 PATH 后可直接执行：

```powershell
cd backend
mvn spring-boot:run
```

如果 Maven 暂未加入 PATH，可以使用当前本机 Maven 完整路径：

```powershell
cd backend
D:\mine\tool\Apache\apache-maven-3.9.11\apache-maven-3.9.16-bin\apache-maven-3.9.16\bin\mvn.cmd spring-boot:run
```

健康检查接口：

```text
GET http://localhost:8080/api/health
```

当前后端仅提供基础健康检查接口，暂未连接数据库，也暂未实现任务管理业务接口。

## 后续计划

- 进一步补充项目 README、目录说明和构建脚本
