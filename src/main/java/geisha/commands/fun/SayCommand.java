package geisha.commands.fun;

import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class SayCommand extends Command {

    public SayCommand(Geisha bot) {
        super(bot);
        this.name = "say";
        this.description = "Make the bot say a message.";
        this.category = Category.FUN;
        this.args.add(new OptionData(OptionType.STRING, "message", "The message you want the bot to say", true));
        this.args.add(new OptionData(OptionType.CHANNEL, "channel",
                "The channel you want to send this message in.", false)
                .setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_PUBLIC_THREAD));
    }

    public void execute(SlashCommandInteractionEvent event) {
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

        // Send message in chat
        channel.sendMessage(message).queue();
        event.reply("Your message was sent!").setEphemeral(true).queue();
    }
}
