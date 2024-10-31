package net.artren.cobble.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import net.artren.cobble.utils.MessageManager

object ToggleConnectionMsgs : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Bu komut sadece bir oyuncu tarafından kullanılabilir!")
            return false
        }

        val player = sender as Player
        val targetUUID = player.uniqueId

        if (MessageManager.isJoinQuitMessageDisabled(targetUUID)) {
            MessageManager.enableJoinQuitMessage(targetUUID)
            player.sendMessage("&bBağlantı mesajları &3aktif.")
        } else {
            MessageManager.disableJoinQuitMessage(targetUUID)
            player.sendMessage("&bBağlantı mesajları &3deaktif.")
        }

        return true
    }
}
