package geisha.utility.embeds;

/**
 * Utility enum storing color codes and helpful methods.
 *
 * @author ch_tys
 */
public enum EmbedColors {
    DEFAULT(0x57a2ff),
    ERROR(0xdd5f53),
    SUCCESS(0x77b255);

    public final int color;

    EmbedColors(int hexCode) {
        this.color = hexCode;
    }
}