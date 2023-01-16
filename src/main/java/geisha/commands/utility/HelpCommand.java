package geisha.commands.utility;

import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import geisha.commands.CommandRegistry;
import geisha.utility.embeds.EmbedColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand(Geisha bot) {
        super(bot);
        this.name = "help";
        this.description = "Display a list of all available commands and their categories.";
        this.category = Category.UTILITY;
        OptionData data = new OptionData(OptionType.STRING, "category", "See the commands under this category");

        // Adds all categories to data
        for (Category category : Category.values()) {
            String name = category.name.toLowerCase();
            data.addChoice(name, name);
        }
        this.args.add(data);
        this.args.add(new OptionData(OptionType.STRING, "command", "See the command details."));

    }

    public void execute(SlashCommandInteractionEvent event) {
        // run the '/help' command
        event.deferReply().queue();

        // Create a hashmap that groups commands by categories.
        HashMap<Category, List<Command>> categories = new HashMap<>();
        EmbedBuilder builder = new EmbedBuilder().setColor(EmbedColors.DEFAULT.color);

        for (Category category : Category.values()) {
            categories.put(category, new ArrayList<>());
        }
        for (Command Command : CommandRegistry.commands) {
            categories.get(Command.category).add(Command);
        }

        OptionMapping option = event.getOption("category");
        OptionMapping option2 = event.getOption("command");

        if (option != null) {
            // Display the category commands menu
            Category category = Category.valueOf(option.getAsString().toUpperCase());
            List<Command> commands = categories.get(category);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(category.emoji + "  **%s Commands**".formatted(category.name));
            embed.setColor(EmbedColors.DEFAULT.color);
            for (Command Command : commands) {
                embed.appendDescription("`" + getUsage(Command) + "`\n" + Command.description + "\n\n");
            }
            event.getHook().sendMessageEmbeds(embed.build()).queue();
        } else if (option2 != null) {
            // Display the command details menu
            Command Command = null;

            for (Command c : CommandRegistry.commands) {
                if (c.name.equals(option2.getAsString())) {
                    Command = c;
                }
            }

            assert Command != null;

            builder.setTitle("Command: " + Command.name);
            builder.setDescription(Command.description);
            builder.addField("Usage:", "`" + getUsage(Command) + "`", false);
            builder.addField("Permission:", getPermissions(Command), false);
            event.getHook().sendMessageEmbeds(builder.build()).queue();

        } else {
            // Display default menu
            builder.setTitle("TechnoBot Commands");
            categories.forEach((category, commands) -> {
                String categoryName = category.name().toLowerCase();
                String value = "`/help " + categoryName + "`";
                builder.addField(category.emoji + " " + category.name, value, true);
            });
            event.getHook().sendMessageEmbeds(builder.build()).queue();
        }
    }

    /**
     * Creates a string of command usage.
     *
     * @param Command Command to build usage for.
     * @return String with name and args stitched together.
     */
    public String getUsage(Command Command) {
        String usage = "/" + Command.name;
        if (Command.args.isEmpty())
            return usage;
        boolean isRequired = Command.args.get(0).isRequired();
        if (isRequired) {
            usage += " <";
        }

        else {
            usage += " [";
        }

        for (int i = 0; i < Command.args.size(); i++) {
            usage += Command.args.get(i).getName();

            if (i + 1 != Command.args.size()) {
                usage += " | ";
            }

        }

        if (isRequired) {
            usage += ">";
        }

        else {
            usage += "]";
        }

        return usage;
    }

    /**
     * Builds a string of permissions from command.
     *
     * @param Command the command to draw perms from.
     * @return A string of command perms.
     */
    private String getPermissions(Command Command) {
        if (Command.permission == null) {
            return "None";
            
        }
        return Command.permission.getName();
    }
}
