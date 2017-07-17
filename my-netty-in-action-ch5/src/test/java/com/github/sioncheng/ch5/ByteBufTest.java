package com.github.sioncheng.ch5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * Created by cyq on 17/07/2017.
 */
public class ByteBufTest {

    @Test
    public void testGetSetOp() {

        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!", Charset.forName("UTF-8"));

        Assert.assertEquals('N',(char)byteBuf.getByte(0));

        int readIndex = byteBuf.readerIndex();
        int writeIndex = byteBuf.writerIndex();
        int capacity = byteBuf.capacity();
        System.out.println(String.format("read index %d", readIndex));
        System.out.println(String.format("write index %d", writeIndex));
        System.out.println(String.format("capacity %d", capacity));

        byteBuf.setByte(0, (byte)'n');
        Assert.assertEquals('n', byteBuf.getByte(0));

        Assert.assertEquals(readIndex, byteBuf.readerIndex());
        Assert.assertEquals(writeIndex, byteBuf.writerIndex());
    }

    @Test
    public void testReadWriteOp() throws UnsupportedEncodingException {

        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!", Charset.forName("UTF-8"));

        Assert.assertEquals('N',(char)byteBuf.getByte(0));

        int readIndex = byteBuf.readerIndex();
        int writeIndex = byteBuf.writerIndex();
        int capacity = byteBuf.capacity();
        System.out.println(String.format("read index %d", readIndex));
        System.out.println(String.format("write index %d", writeIndex));
        System.out.println(String.format("capacity %d", capacity));

        Assert.assertEquals('N', byteBuf.readByte());

        Assert.assertEquals(readIndex + 1, byteBuf.readerIndex());
        Assert.assertEquals(writeIndex, byteBuf.writerIndex());

        byte[] bytes = " Isn't is ?".getBytes("UTF-8");
        byteBuf.writeBytes(bytes);

        Assert.assertEquals(readIndex + 1, byteBuf.readerIndex());
        Assert.assertEquals(writeIndex + bytes.length, byteBuf.writerIndex());

        System.out.println(String.format("read index %d", readIndex));
        System.out.println(String.format("write index %d", writeIndex));
        System.out.println(String.format("capacity %d", capacity));
    }
}
