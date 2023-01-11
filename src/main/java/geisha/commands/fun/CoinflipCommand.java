package geisha.commands.fun;

import java.util.Random;

import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import geisha.utility.embeds.EmbedColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CoinflipCommand extends Command {

    public CoinflipCommand(Geisha bot) {
        super(bot);
        this.name = "coinflip";
        this.description = "Flip a coin!";
        this.category = Category.FUN;
    }

    public void execute(SlashCommandInteractionEvent event) {
        boolean isHeads = new Random().nextBoolean();

        String HEADS = "https://i.imgur.com/HavOS7J.png";
        String TAILS = "https://i.imgur.com/u1pmQMV.png";

        String result = isHeads ? HEADS : TAILS;

        EmbedBuilder embed = new EmbedBuilder().setColor(EmbedColors.DEFAULT.color)
                .setTitle("You flipped a coin and got...")
                .setImage(result);

        event.replyEmbeds(embed.build()).queue();

    }
}
