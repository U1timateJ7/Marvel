package dustw.libgui.network;

import io.netty.buffer.Unpooled;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;

/**
 * @author DustW
 */
public class PacketByteBufs {
    public static RegistryFriendlyByteBuf create(RegistryAccess registryAccess) {
        return new RegistryFriendlyByteBuf(Unpooled.buffer(), registryAccess);
    }
}
