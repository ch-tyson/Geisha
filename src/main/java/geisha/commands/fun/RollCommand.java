package geisha.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import geisha.utility.embeds.EmbedUtility;

import java.util.Random;

/**
 * Command to roll a die for random number generation with a number specific
 * die.
 *
 * @author ch_tys
 */
public class RollCommand extends Command {

    private final Random random;

    public RollCommand(Geisha bot) {
        super(bot);
        this.name = "roll";
        this.description = "Roll a dice.";
        this.category = Category.FUN;
        this.args.add(new OptionData(OptionType.INTEGER, "dice", "The number of sides on the dice").setMinValue(1)
                .setMaxValue(1000000));
        this.random = new Random();
    }

    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping option = event.getOption("dice");
        int bound = option != null ? option.getAsInt() : 6;
        if (bound == 0)
            bound = 1;
        int result = random.nextInt(bound) + 1;
        event.replyEmbeds(EmbedUtility
                .createDefault(":game_die: You rolled a " + bound + "-sided dice and got: **" + result + "**")).queue();
    }
}
