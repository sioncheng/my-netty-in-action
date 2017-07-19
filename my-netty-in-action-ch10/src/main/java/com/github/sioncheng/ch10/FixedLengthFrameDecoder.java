package com.github.sioncheng.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by cyq on 19/07/2017.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    public FixedLengthFrameDecoder(int frameLength) {
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >= frameLength) {
            out.add(in.readBytes(frameLength));
        }
    }

    private int frameLength;
}
