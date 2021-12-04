package castlewar.network;

import castlewar.Player;

public class PacketCreator {

    public static byte[] startGame() {
        PacketWriter writer = new PacketWriter();
        writer.writeShort(0);
        return writer.getPacket();
    }

    public static byte[] movePlayer(Player player) {
        PacketWriter writer = new PacketWriter();
        writer.writeShort(1);
        writer.writeByte(player.isHorizontally() ? 1 : 0);
        writer.writeInt((int) player.getX());
        writer.writeInt((int) player.getY());
        return writer.getPacket();
    }

    public static byte[] attack(Player player) {
        PacketWriter writer = new PacketWriter();
        writer.writeShort(2);
        return writer.getPacket();
    }
}
