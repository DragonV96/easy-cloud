# 基于openjdk:8-jdk-slim构建
FROM openjdk:8-jdk-slim

# 维护者信息
# MAINTAINER glw

# 设置环境变量
ENV JAVA_OPTIONS -Xms256m -Xmx256m -Duser.timezone=GMT+08 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dump.hprof

# 设置容器内服务路径
RUN mkdir -p /data/logs/cloud/file-system/

# 添加 jar 包到服务目录
ADD ./target/file-system-0.0.1-SNAPSHOT.jar /data/logs/cloud/file-system/

# 启动 jar 包
CMD java -jar $JAVA_OPTIONS /data/logs/cloud/file-system/file-system-0.0.1-SNAPSHOT.jar

