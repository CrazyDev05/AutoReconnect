package autoreconnect.reconnect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.text.Text;

public class SingleplayerReconnectHandler extends ReconnectHandler {
    private final String worldName;

    public SingleplayerReconnectHandler(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public String getName() {
        return worldName;
    }

    /**
     * @see WorldListWidget.WorldEntry#start()
     * @see WorldListWidget.WorldEntry#openReadingWorldScreen()
     */
    @Override
    public void reconnect() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!client.getLevelStorage().levelExists(getName())) return;
        var screen = new MessageScreen(Text.translatable("selectWorld.data_read"));
        client.setScreenAndRender(screen);
        client.createIntegratedServerLoader().start(screen, getName());
    }
}