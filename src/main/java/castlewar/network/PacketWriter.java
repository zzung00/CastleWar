package castlewar.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketWriter {
    private ByteArrayOutputStream output;

    public PacketWriter() {
        this(32);
    }

    public PacketWriter(int size) {
        output = new ByteArrayOutputStream(size);
    }

    public void write(byte[] b) {
        try {
            output.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeByte(int i) {
        output.write(i);
    }

    public void writeShort(int i) {
        writeByte((byte) ((i >>> 8) & 0xFF));
        writeByte((byte) (i & 0xFF));
    }

    public void writeInt(int i) {
        writeByte((byte) ((i >>> 24) & 0xFF));
        writeByte((byte) ((i >>> 16) & 0xFF));
        writeByte((byte) ((i >>> 8) & 0xFF));
        writeByte((byte) (i & 0xFF));
    }

    public void writeLong(long i) {
        writeByte((byte) ((i >>> 56) & 0xFF));
        writeByte((byte) ((i >>> 48) & 0xFF));
        writeByte((byte) ((i >>> 40) & 0xFF));
        writeByte((byte) ((i >>> 32) & 0xFF));
        writeByte((byte) ((i >>> 24) & 0xFF));
        writeByte((byte) ((i >>> 16) & 0xFF));
        writeByte((byte) ((i >>> 8) & 0xFF));
        writeByte((byte) (i & 0xFF));
    }

    public void writeString(String str) {
        writeShort((short) str.length());
        write(str.getBytes());
    }

    public byte[] getPacket() {
        return output.toByteArray();
    }
}
