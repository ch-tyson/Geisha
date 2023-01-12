package geisha.commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import geisha.Geisha;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Represents a general slash command with properties.
 *
 * @author ch_tys
 */
public abstract class Command extends ListenerAdapter {

    public Geisha bot;
    public String name;
    public String description;
    public Category category;
    public List<OptionData> args;
    public Permission permission;

    public Command(Geisha bot) {
        this.bot = bot;
        this.args = new ArrayList<>();
    }

    public abstract void execute(SlashCommandInteractionEvent event);

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals(name)) {
            execute(event);
        }
    }
}