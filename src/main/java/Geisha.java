import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;

import listeners.EventListener;

public class Geisha {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException when the bot token is invalid.
     */
    public Geisha() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("tyson cry"));

        //Enables intents for guild members, messages, and user presence
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS);

        //Setting up flags for user cache on Geisha load-up
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);

        shardManager = builder.build();

        //Register listeners
        shardManager.addEventListener(new EventListener());
    }
    public Dotenv getConfig() {
        return config;
    }

    /** Basic getter that retrieves bot shard manager.
     * @return the ShardManager instance for Geisha bot.
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

        public static void main(String[] args) {
        try {
            Geisha bot = new Geisha();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid.");
        }
    }
}