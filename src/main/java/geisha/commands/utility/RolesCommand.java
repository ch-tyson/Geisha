package geisha.commands.utility;

import geisha.Geisha;
import net.dv8tion.jda.api.entities.Role;
import geisha.commands.Category;
import geisha.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import geisha.utility.embeds.EmbedColors;

public class RolesCommand extends Command {

    public RolesCommand(Geisha bot) {
        super(bot);
        this.name = "roles";
        this.description = "Display all the roles on the server.";
        this.category = Category.UTILITY;
    }

    public void execute(SlashCommandInteractionEvent event) {
        // run the '/roles' command
        StringBuilder response = new StringBuilder();
        for (Role role : event.getGuild().getRoles()) {
            if (!role.isManaged()) {
                response.append(role.getAsMention());
                response.append("\n");

                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(EmbedColors.DEFAULT.color)
                        .setTitle("All Roles")
                        .setDescription(response);
                event.replyEmbeds(embed.build()).queue();
            }
        }
    }
}