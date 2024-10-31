package net.artren.cobble.events

import net.artren.cobble.utils.MessageManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerQuit : Listener {

    @EventHandler
    fun onQuit(e: PlayerQuitEvent){
        val player: Player = e.player
        val targetUUID = player.uniqueId

        if (MessageManager.isJoinQuitMessageDisabled(targetUUID)) {
            return
        }

        e.quitMessage = "&7${player.name} oyundan ayrıldı."
    }
}
