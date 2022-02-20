package com.example.springbootnettyclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/11
 * @time: 11:01 下午
 * @description:
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        //自定义业务 Handler
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        // 4.建立连接
        ChannelFuture future=bootstrap.connect("127.0.0.1", 80).sync();
    }
}

