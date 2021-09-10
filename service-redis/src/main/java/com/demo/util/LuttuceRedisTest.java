package com.demo.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.data.redis.core.RedisCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LuttuceRedisTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        openSingle();
     //   openCluster();
    }

    public static void openSingle() throws ExecutionException, InterruptedException {
        // redis地址
        RedisURI redisURI = RedisURI.Builder.redis("localhost",6379).build();
        // 获取连接
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String,String> connection= client.connect();

        // 同步调用
        RedisCommands<String,String> commands = connection.sync();
        commands.set("hello","hello world");
        String str = commands.get("hello");
        System.out.println(str);

        // 异步调用
        RedisAsyncCommands<String,String> asyncCommands = connection.async();
        RedisFuture<String> future = asyncCommands.get("hello");
        System.out.println(future.get());

        connection.close();
        client.shutdown();
    }

    /**
     * 集群模式
     */
    public static void openCluster() throws ExecutionException, InterruptedException {
        List<RedisURI> list = new ArrayList<>();
        list.add(RedisURI.Builder.redis("localhost",6379).build());
        list.add(RedisURI.Builder.redis("localhost",6380).build());
        list.add(RedisURI.Builder.redis("localhost",6381).build());
        list.add(RedisURI.Builder.redis("localhost",6382).build());

        RedisClusterClient client = RedisClusterClient.create(list);
        StatefulRedisClusterConnection<String,String> connection = client.connect();

        // 同步执行命令
        RedisAdvancedClusterCommands<String,String > commands = connection.sync();
        commands.set("redis","hello redis");
        System.out.println(commands.get("redis"));

        // 异步执行
        RedisAdvancedClusterAsyncCommands asyncCommands = connection.async();
        RedisFuture<String > future = asyncCommands.get("reids");
        System.out.println(future.get());

        connection.close();
        client.shutdown();
    }
}
