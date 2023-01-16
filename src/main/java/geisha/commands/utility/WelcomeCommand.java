package geisha.commands.utility;

import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


public class WelcomeCommand extends Command {

    public WelcomeCommand(Geisha bot) {
        super(bot);
        this.name = "welcome";
        this.description = "Get welcomed by the server by the bot.";
        this.category = Category.UTILITY;
    }

    public void execute(SlashCommandInteractionEvent event) {
        // run the '/welcome' command
        String userTag = event.getUser().getAsTag();

        event.reply("🌟 Everyone say hello to " + userTag + "✨").queue();
    }
}