package org.geysermc.addons.util;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class NetworkUtils {

    public static String readString(ByteBuf buf) {
        return new String(readByteArray(buf), StandardCharsets.UTF_8);
    }

    public static void writeString(ByteBuf buf, String string) {
        writeByteArray(buf, string.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] readByteArray(ByteBuf buf) {
        byte[] bytes = new byte[readUnsignedVarInt(buf)];
        buf.readBytes(bytes);
        return bytes;
    }

    public static void writeByteArray(ByteBuf buf, byte[] bytes) {
        writeUnsignedVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    public static int readVarInt(ByteBuf buf) {
        int i = 0, j = 0;
        while (true) {
            int k = buf.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt was too big!");
            }
            if ((k & 0x80) != 128) {
                break;
            }
        }
        return i;
    }

    public static void writeVarInt(ByteBuf buf, int value) {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                buf.writeByte(value);
                return;
            }

            buf.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public static int readUnsignedVarInt(ByteBuf buf) {
        int i = 0;
        for (int j = 0; j < 64; j += 7) {
            final byte b = buf.readByte();
            i |= (long) (b & 0x7F) << j;
            if ((b & 0x80) == 0) {
                return i;
            }
        }
        throw new RuntimeException("Varint was too big!");
    }

    public static void writeUnsignedVarInt(ByteBuf buf, int value) {
        long i = value & 0xFFFFFFFFL;
        while (true) {
            if ((i & ~0x7FL) == 0) {
                buf.writeByte((int) i);
                return;
            } else {
                buf.writeByte((byte) ((i & 0x7F) | 0x80));
                i >>>= 7;
            }
        }
    }
}
