package com.github.sioncheng.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cyq on 19/07/2017.
 */
public class TestNumberMirrorEncoderWithEmbeddedChannel {

    @Test
    public void test() {

        NumberMirrorEncoder numberMirrorEncoder = new NumberMirrorEncoder();
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(numberMirrorEncoder);

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(1);
        byteBuf.writeInt(2);
        byteBuf.writeInt(0);
        byteBuf.writeInt(-2);
        byteBuf.writeInt(-1);

        Assert.assertTrue(embeddedChannel.writeOutbound(byteBuf));

        Assert.assertTrue(embeddedChannel.finish());

        assertEqualsInt(-1, embeddedChannel.readOutbound());
        assertEqualsInt(-2, embeddedChannel.readOutbound());
        assertEqualsInt(0, embeddedChannel.readOutbound());
        assertEqualsInt(2, embeddedChannel.readOutbound());
        assertEqualsInt(1, embeddedChannel.readOutbound());
        Assert.assertNull(embeddedChannel.readOutbound());

    }

    private void assertEqualsInt(Integer a, Integer b) {
        Assert.assertEquals(a, b);
    }
}
