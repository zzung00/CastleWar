package castlewar.network;

public class PacketCreator {

    public static byte[] startGame() {
        PacketWriter writer = new PacketWriter();
        writer.writeShort(0);
        return writer.getPacket();
    }
}
