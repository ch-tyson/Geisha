package geisha.commands.fun;

import geisha.Geisha;
import geisha.commands.Category;
import geisha.commands.Command;
import geisha.utility.embeds.EmbedColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.Random;

public class MiyazakiCommand extends Command {

    public MiyazakiCommand(Geisha bot) {
        super(bot);
        this.name = "miyazaki";
        this.description = "Miyazaki.";
        this.category = Category.FUN;
    }

    public void execute(SlashCommandInteractionEvent event) {
        // run the '/miyazaki' command

        ArrayList<String> miyazakiImages = new ArrayList<String>();
        miyazakiImages
                .add("https://preview.redd.it/6udydsml5s481.jpg?auto=webp&s=d9b0730a4aa3251b949b2be532487eb7a893210b");
        miyazakiImages.add("https://i.pinimg.com/originals/bc/66/52/bc66522b0b1e3e7770007c128ace8d42.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/ErdC8csUUAI4JnD.jpg:large");
        miyazakiImages
                .add("https://animemotivation.com/wp-content/uploads/2020/12/anime-fans-worthless-miyazaki-meme.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/E6iPBF-XsAYe4SC.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/EQDUvT6U8AE2Lq9.jpg");
        miyazakiImages.add("https://images.summitmedia-digital.com/esquiremagph/images/2021/01/19/139566584_2930100380643403_9238180081597448_n.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/ErdC_vvVgAI39R-.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/ErdC9-WVkAcYLde.jpg");
        miyazakiImages.add("https://pbs.twimg.com/media/EZejS0JUwAAqMio.jpg");

        EmbedBuilder embed = new EmbedBuilder().setColor(EmbedColors.DEFAULT.color)
                .setImage(getRandomImage(miyazakiImages));

        event.replyEmbeds(embed.build()).queue();
        ;
    }

    public static String getRandomImage(ArrayList<String> array) {
        int random = new Random().nextInt(array.size());
        return array.get(random);
    }

}