package com.github.sioncheng.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by cyq on 19/07/2017.
 */
public class NumberMirrorEncoder extends MessageToMessageEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List out) throws Exception {
        while (msg.readableBytes() >= 4) {
            int v = msg.readInt();
            out.add(v * -1);
        }
    }
}
