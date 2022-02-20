package com.example.springbootnettyserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/11
 * @time: 11:02 下午
 * @description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //接受客户端端响应时触发该事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //转换为 ByteBuf 缓冲区（底层是 byte[] 数组）
        ByteBuf buffer=(ByteBuf)msg;
        //定义一个 byte[] 数组
        byte[] bytes=new byte[buffer.readableBytes()];
        //缓冲区把数据写到 byte[] 数组
        buffer.readBytes(bytes);
        //把 byte[] 转换字符串
        String req=new String(bytes,"UTF-8");
        System.out.println("客户端请求："+req);

        //给客户端响应信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        String res="Hello World>>>>Client";
        //把字符串转换 ByteBuf
        ByteBuf buf=getByteBuf(ctx,res);

        //把 ByteBuf 写到通道并且刷新
        ctx.writeAndFlush(buf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String str) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. 准备数据，指定字符串的字符集为 utf-8
        byte[] bytes = str.getBytes(Charset.forName("utf-8"));
        // 3. 填充数据到 ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }
}

