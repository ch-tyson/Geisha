package geisha.commands.util;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;


public class WelcomeCommand extends Command {

    public WelcomeCommand(Geisha bot) {
        super(bot);
        this.name = "welcome";
        this.description = "Get welcomed by the server by the bot.";
        this.category = Category.UTILITY;
    }

    public void execute(SlashCommandInteractionEvent event) {
        // Get user
        String userTag = event.getUser().getAsTag();

        event.reply("🌟 Everyone say hello to " + userTag + "✨").queue();
    }
}