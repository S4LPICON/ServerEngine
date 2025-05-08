package com.zofrenic.serverengine.inputs.model;

import com.zofrenic.serverengine.inputs.api.InputActionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InputListener implements Listener {

    // Mapa de jugadores a sus manejadores de acción
    private final Map<UUID, InputActionHandler> handlers = new ConcurrentHashMap<>();

    public void registerHandler(UUID playerId, InputActionHandler handler) {
        handlers.put(playerId, handler);
    }

    public void unregisterHandler(UUID playerId) {
        handlers.remove(playerId);
    }

    @EventHandler
    public void onPlayerInput(PlayerInputEvent event) {
        InputActionHandler handler = handlers.get(event.getPlayer().getUniqueId());
        if (handler == null)
            return;

        var input = event.getInput();

        // Inicia o detiene el movimiento según la entrada
        handler.setMovingForward(input.isForward());
        handler.setMovingBackward(input.isBackward());
        handler.setMovingLeft(input.isLeft());
        handler.setMovingRight(input.isRight());

        // Otras acciones como salto, sigilo, etc.
        if (input.isJump())
            handler.jump();
        if (input.isSneak())
            handler.sneak();
        if (input.isSprint())
            handler.sprint();
    }
}
