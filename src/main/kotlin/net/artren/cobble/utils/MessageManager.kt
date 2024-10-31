package net.artren.cobble.utils

import net.artren.cobble.Cobble
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.UUID

object MessageManager {
    private val file = File(Cobble.instance.dataFolder, "connection_messages.yml")
    private val config = YamlConfiguration.loadConfiguration(file)
    private val disabledJoinQuitMessages = mutableSetOf<UUID>()

    init {
        if (!file.exists()) {
            try {
                config.save(file)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        loadDisabledJoinQuitMessages()
    }

    private fun loadDisabledJoinQuitMessages() {
        val uuids = config.getStringList("disabled_join_quit_messages").map { UUID.fromString(it) }
        disabledJoinQuitMessages.addAll(uuids)
    }

    fun saveDisabledJoinQuitMessages() {
        config.set("disabled_join_quit_messages", disabledJoinQuitMessages.map { it.toString() })
        try {
            config.save(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disableJoinQuitMessage(uuid: UUID) {
        disabledJoinQuitMessages.add(uuid)
        saveDisabledJoinQuitMessages()
    }

    fun enableJoinQuitMessage(uuid: UUID) {
        disabledJoinQuitMessages.remove(uuid)
        saveDisabledJoinQuitMessages()
    }

    fun isJoinQuitMessageDisabled(uuid: UUID): Boolean {
        return disabledJoinQuitMessages.contains(uuid)
    }
}
