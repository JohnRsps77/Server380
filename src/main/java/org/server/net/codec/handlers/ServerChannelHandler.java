package org.server.net.codec.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.server.ServerLauncher;
import org.server.model.User;
import org.server.net.packet.Packet;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    private User user;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //handle removing user from server here
        ServerLauncher.server.removeUser(user);
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof User) {
            user = (User) msg;
            return;
        }

        if(msg instanceof Packet) {
            user.getSession().read((Packet) msg);
            return;
        }

        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
