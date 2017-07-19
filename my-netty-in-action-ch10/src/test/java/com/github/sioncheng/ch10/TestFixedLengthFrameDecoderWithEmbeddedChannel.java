package com.github.sioncheng.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Created by cyq on 19/07/2017.
 */
public class TestFixedLengthFrameDecoderWithEmbeddedChannel {

    @Test
    public void test() {

        final int frameLength = 3;

        FixedLengthFrameDecoder fixedLengthFrameDecoder = new FixedLengthFrameDecoder(frameLength);
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(fixedLengthFrameDecoder);

        ByteBuf input = Unpooled.copiedBuffer("hello, netty.", Charset.forName("UTF-8"));

        Assert.assertTrue(embeddedChannel.writeInbound(input.duplicate()));
        Assert.assertTrue(embeddedChannel.finish());


        Assert.assertEquals(input.readBytes(frameLength), embeddedChannel.readInbound());
        Assert.assertEquals(input.readBytes(frameLength), embeddedChannel.readInbound());
        Assert.assertEquals(input.readBytes(frameLength), embeddedChannel.readInbound());

    }
}
