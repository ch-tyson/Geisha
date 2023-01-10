package geisha.commands.util;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;


public class AboutCommand extends Command {

    public AboutCommand(Geisha bot) {
        super(bot);
        this.name = "about";
        this.description = "Bot information and repository link.";
        this.category = Category.UTILITY;
    }

    public void execute(SlashCommandInteractionEvent event) {
        // run the '/about' command
        event.getChannel().sendTyping().queue();
        event.reply("I am a discord bot created by Tyson. I am designed to play various simple games and provide" +
                " other fun features for your Discord server. You can see my full documentation at " +
                "[https://github.com/ch-tyson/Geisha], and if you have any issues or suggestions, please don't " +
                "hesitate to let me know. Enjoy!").setEphemeral(true).queue();
    }
}
