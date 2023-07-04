package org.runejs.client.net.codec.runejs435;

import org.runejs.client.net.PacketType;
import org.runejs.client.net.codec.MessagePacketCodec;
import org.runejs.client.message.outbound.chat.*;
import org.runejs.client.message.outbound.console.*;
import org.runejs.client.message.outbound.interactions.*;
import org.runejs.client.net.codec.runejs435.decoder.audio.*;
import org.runejs.client.net.codec.runejs435.decoder.chat.*;
import org.runejs.client.net.codec.runejs435.decoder.console.ReceiveConsoleCommandMessageDecoder;
import org.runejs.client.net.codec.runejs435.decoder.console.ReceiveConsoleLogMessageDecoder;
import org.runejs.client.net.codec.runejs435.encoder.chat.*;
import org.runejs.client.net.codec.runejs435.encoder.console.*;
import org.runejs.client.net.codec.runejs435.encoder.interactions.*;

/**
 * A {@link MessagePacketCodec} for the RuneJS customised 435 protocol.
 */
public class RuneJS435PacketCodec extends MessagePacketCodec {
    public RuneJS435PacketCodec() {
        super(PacketType.incomingPacketSizes);

        this.registerEncoders();
        this.registerDecoders();
    }

    private void registerEncoders() {
        register(SendChatMessageOutboundMessage.class, new SendChatMessageMessageEncoder());
        register(SendPrivateMessageOutboundMessage.class, new SendPrivateMessageMessageEncoder());
        register(PlayerInteractionOutboundMessage.class, new PlayerInteractionMessageEncoder());
        register(NPCInteractionOutboundMessage.class, new NPCInteractionMessageEncoder());
        register(ObjectInteractionOutboundMessage.class, new ObjectInteractionMessageEncoder());

        // console
        register(ConsoleCommandOutboundMessage.class, new ConsoleCommandMessageEncoder());
    }

    private void registerDecoders() {
        register(PacketType.PRIVATE_MESSAGE_RECEIVED.getOpcode(), new ReceivePrivateMessageDecoder());
        register(PacketType.CHATBOX_MESSAGE_RECEIVED.getOpcode(), new ReceiveChatboxMessageDecoder());
        register(PacketType.FORCE_PRIVATE_MESSAGE.getOpcode(), new ForcedPrivateMessageDecoder());
        register(PacketType.FRIEND_LOGGED_IN.getOpcode(), new FriendChangedWorldMessageDecoder());
        register(PacketType.SET_CHAT_MODES.getOpcode(), new SetChatModesMessageDecoder());
        register(PacketType.SET_FRIEND_LIST_STATUS.getOpcode(), new SetFriendsListStatusMessageDecoder());
        register(PacketType.PLAY_SONG.getOpcode(), new PlaySongMessageDecoder());
        register(PacketType.PLAY_QUICK_SONG.getOpcode(), new PlayQuickSongMessageDecoder());
        register(PacketType.PLAY_SOUND.getOpcode(), new PlaySoundMessageDecoder());

        // console
        register(PacketType.RECEIVE_CONSOLE_COMMAND.getOpcode(), new ReceiveConsoleCommandMessageDecoder());
        register(PacketType.RECEIVE_CONSOLE_LOG.getOpcode(), new ReceiveConsoleLogMessageDecoder());
    }
}
