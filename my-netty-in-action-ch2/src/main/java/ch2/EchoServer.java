package ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        int port = 9090;
        if (args.length > 1) {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer(port).start();
    }

    private final int port;
    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            EchoServerHandler handler =  new EchoServerHandler();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture future = serverBootstrap.group(group)
                    .localAddress(port)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    })
                    .bind().sync();
            System.out.println("port " + port);
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
