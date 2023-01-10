package geisha;
import javax.security.auth.login.LoginException;

import org.jetbrains.annotations.NotNull;

import geisha.commands.CommandManager;
import geisha.commands.CommandRegistry;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

/**
 * This is the main class where we initialize our bot.
 */
public class Geisha {

    public final @NotNull Dotenv config;
    public final @NotNull ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * 
     * @throws LoginException when the bot token is invalid.
     */
    public Geisha() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        // Builds shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("tyson cry"));

        // Enables intents for guild members, messages, and user presence
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES);

        shardManager = builder.build();
        shardManager.addEventListener(new CommandRegistry(this));

        // Setting up flags for user cache on Geisha load-up
        // builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);
        
    }

    /**
     * Main method where the bot starts.
     * 
     * @params args ignored
     */
    public static void main(String[] args) {
        try {
            Geisha bot = new Geisha();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid.");
        }
    }
}
