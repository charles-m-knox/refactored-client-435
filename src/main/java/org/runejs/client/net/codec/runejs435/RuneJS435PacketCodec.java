package org.runejs.client.net.codec.runejs435;

import org.runejs.client.net.PacketType;
import org.runejs.client.net.codec.MessagePacketCodec;
import org.runejs.client.message.outbound.chat.*;
import org.runejs.client.message.outbound.console.*;
import org.runejs.client.message.outbound.interactions.*;
import org.runejs.client.message.outbound.magic.*;
import org.runejs.client.message.outbound.useitem.*;
import org.runejs.client.net.codec.runejs435.decoder.audio.*;
import org.runejs.client.net.codec.runejs435.decoder.chat.*;
import org.runejs.client.net.codec.runejs435.decoder.console.ReceiveConsoleCommandMessageDecoder;
import org.runejs.client.net.codec.runejs435.decoder.console.ReceiveConsoleLogMessageDecoder;
import org.runejs.client.net.codec.runejs435.decoder.misc.*;
import org.runejs.client.net.codec.runejs435.decoder.region.*;
import org.runejs.client.net.codec.runejs435.encoder.chat.*;
import org.runejs.client.net.codec.runejs435.encoder.console.*;
import org.runejs.client.net.codec.runejs435.encoder.interactions.*;
import org.runejs.client.net.codec.runejs435.encoder.magic.*;
import org.runejs.client.net.codec.runejs435.encoder.useitem.*;
import org.runejs.client.net.codec.runejs435.decoder.updating.UpdatePlayersMessageDecoder;

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
        register(AcceptRequestOutboundMessage.class, new AcceptRequestMessageEncoder());
        register(SendChatMessageOutboundMessage.class, new SendChatMessageMessageEncoder());
        register(SendPrivateMessageOutboundMessage.class, new SendPrivateMessageMessageEncoder());
        register(ChatCommandOutboundMessage.class, new ChatCommandMessageEncoder());
        
        register(PlayerInteractionOutboundMessage.class, new PlayerInteractionMessageEncoder());
        register(NPCInteractionOutboundMessage.class, new NPCInteractionMessageEncoder());
        register(ObjectInteractionOutboundMessage.class, new ObjectInteractionMessageEncoder());
        register(WorldItemInteractionOutboundMessage.class, new WorldItemInteractionMessageEncoder());
        register(WidgetV1ItemInteractionOutboundMessage.class, new WidgetV1ItemInteractionMessageEncoder());
        register(WidgetV2ItemInteractionOutboundMessage.class, new WidgetV2ItemInteractionMessageEncoder());
        
        register(ChatCommandOutboundMessage.class, new ChatCommandMessageEncoder());

        // console
        register(ConsoleCommandOutboundMessage.class, new ConsoleCommandMessageEncoder());

        // magic
        register(CastMagicOnPlayerOutboundMessage.class, new CastMagicOnPlayerMessageEncoder());
        register(CastMagicOnObjectOutboundMessage.class, new CastMagicOnObjectMessageEncoder());
        register(CastMagicOnWidgetItemOutboundMessage.class, new CastMagicOnWidgetItemMessageEncoder());
        register(CastMagicOnWorldItemOutboundMessage.class, new CastMagicOnWorldItemMessageEncoder());
        register(CastMagicOnNPCOutboundMessage.class, new CastMagicOnNPCMessageEncoder());

        // use item on
        register(UseItemOnPlayerOutboundMessage.class, new UseItemOnPlayerMessageEncoder());
        register(UseItemOnNPCOutboundMessage.class, new UseItemOnNPCMessageEncoder());
        register(UseItemOnObjectOutboundMessage.class, new UseItemOnObjectMessageEncoder());
        register(UseItemOnWorldItemOutboundMessage.class, new UseItemOnWorldItemMessageEncoder());
        register(UseItemOnWidgetItemOutboundMessage.class, new UseItemOnWidgetItemMessageEncoder());
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

        // misc
        register(PacketType.LOGOUT.getOpcode(), new LogoutMessageDecoder());
      
        register(PacketType.UPDATE_CARRY_WEIGHT.getOpcode(), new UpdateCarryWeightMessageDecoder());
        register(PacketType.UPDATE_RUN_ENERGY.getOpcode(), new UpdateRunEnergyMessageDecoder());
        register(PacketType.UPDATE_SKILL.getOpcode(), new UpdateSkillMessageDecoder());
        register(PacketType.UPDATE_PLAYERS.getOpcode(), new UpdatePlayersMessageDecoder());

        // region
        register(PacketType.UPDATE_REFERENCE_POSITION.getOpcode(), new UpdateReferencePositionMessageDecoder());
    }
}
