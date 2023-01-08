package commands;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equalsIgnoreCase("welcome")) {
            // run the '/welcome' command
            String userTag = event.getUser().getAsTag();
            event.reply("*Drumroll* Everyone say hello to " + userTag + "! Welcome to the server ^^").queue();
        } else if (command.equalsIgnoreCase("about")) {
            // run the '/about' command
            event.reply("I am a discord bot created by Tyson. I am designed to play various simple games and provide" +
                    " other fun features for your Discord server. You can see my full documentation at " +
                    "[https://github.com/ch-tyson/Geisha], and if you have any issues or suggestions, please don't " +
                    "hesitate to let me know. Enjoy!").setEphemeral(true).queue();
        } else if (command.equalsIgnoreCase("roles")) {
            // run the '/role' command
            event.deferReply().setEphemeral(true).queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                response += role.getAsMention() + "\n";
            }
            event.getHook().sendMessage(response).queue();
        } else if (command.equalsIgnoreCase("say")) {
            // run the '/say' command
            OptionMapping messageOption = event.getOption("message");
            String message = messageOption.getAsString();

            MessageChannel channel;
            OptionMapping channelOption = event.getOption("channel");
            if (channelOption != null) {
                channel = channelOption.getAsChannel().asGuildMessageChannel();
            } else {
                channel = event.getChannel();
            }

            //Send message in chat
            channel.sendMessage(message).queue();
            event.reply("Your message was sent!").setEphemeral(true).queue();
        }
     }

    // Guild commands -- instant update / 100 max
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot."));
        commandData.add(Commands.slash("about", "Bot information and repository link."));
        commandData.add(Commands.slash("roles", "Display all the roles on the server."));

        // Command is '/say <message> [channel]', where the channel is optional
        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you want the bot to say", true);
        OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "The channel you want to send this message in.", false);
        commandData.add(Commands.slash("say", "Make the bot say a message.").addOptions(option1, option2));


        event.getGuild().updateCommands().addCommands(commandData).queue();


    }

    /*
 Global commands -- up to an hour to update / unlimited
    @Override
    public void onReady(ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot."));
        commandData.add(Commands.slash("about", "Bot information and repository link."));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
*/

}