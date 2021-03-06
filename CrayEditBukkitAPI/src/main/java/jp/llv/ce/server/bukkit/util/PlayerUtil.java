/*
 * Copyright (c) 2015 Toyblocks
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jp.llv.ce.server.bukkit.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

/**
 *
 * @author Toyblocks
 */
public class PlayerUtil {

    private static byte toChatTypeCode(ChatMessageType type) {
        switch (type) {
            case CHAT:
                return (byte) 0;
            case SYSTEM:
                return (byte) 1;
            case ACTION_BAR:
                return (byte) 2;
            default:
                return (byte) 0;
        }
    }
    
    public static void sendMessage(ChatMessageType type, Player player, String message) {
        sendMessage(type, player, new TextComponent(message));
    }
    
    public static void sendMessage(ChatMessageType type, Player player, BaseComponent ... message) {
        if (type == ChatMessageType.ACTION_BAR) {
            sendMessage(type, player, BaseComponent.toLegacyText(message));
            return;
        }
        String packname = player.getClass().getPackage().getName();
        String[] hier = packname.split("\\.");
        String nmsv = hier[hier.length - 2];
        ReflUtil.wrap(player).m("getHandle").f("playerConnection").m("sendPacket",
                (Object) ReflUtil.wrappedInstance("net.minecraft.server." + nmsv + ".PacketPlayOutChat")
                .s("components", message)
                .s("b", toChatTypeCode(type)).unwrap()
        );
    }

}
