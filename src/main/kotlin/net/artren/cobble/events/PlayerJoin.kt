package net.artren.cobble.events

import net.artren.cobble.utils.MessageManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoin : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        val player: Player = e.player
        val targetUUID = player.uniqueId

        if (MessageManager.isJoinQuitMessageDisabled(targetUUID)) {
            return
        }

        e.joinMessage = "&7${player.name} oyuna katıldı."
    }
}
