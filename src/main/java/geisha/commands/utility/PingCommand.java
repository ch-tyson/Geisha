package geisha.commands.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import geisha.utility.embeds.EmbedColors;

/**
 * Ping command to check latency with Discord API.
 *
 * @author ch_tys
 */
public class PingCommand extends Command {

    public PingCommand(Geisha bot) {
        super(bot);
        this.name = "ping";
        this.description = "Display the bot latency.";
        this.category = Category.UTILITY;
    }

    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        long time = System.currentTimeMillis();
        event.getHook().sendMessage(":signal_strength: Ping").queue(m -> {
            long latency = System.currentTimeMillis() - time;
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(":ping_pong: Pong!");
            embed.addField("Latency", latency + "ms", false);
            embed.addField("Discord API", event.getJDA().getGatewayPing() + "ms", false);
            embed.setColor(EmbedColors.DEFAULT.color);
            m.editMessageEmbeds(embed.build()).queue();
        });
    }
}
