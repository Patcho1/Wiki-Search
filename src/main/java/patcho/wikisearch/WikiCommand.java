package patcho.wikisearch;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class WikiCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("wiki")
                .then(CommandManager.argument("query", MessageArgumentType.message())
                        .executes(ctx -> search(ctx.getSource(), MessageArgumentType.getMessage(ctx, "query"))
                        )));
    }

    private static int search(ServerCommandSource source, Text search) {
        String url = "https://minecraft.wiki/w/";

        Text clickableUrl = Text.literal(url + search.getString()).setStyle(Style.EMPTY
                .withFormatting(Formatting.BLUE)
                .withFormatting(Formatting.UNDERLINE)
                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url + search.getString())));

        source.sendMessage(Text.of("§2Here is the wiki page corresponding to your query :"));
        source.sendMessage(clickableUrl);

        return 1;
    }
}
