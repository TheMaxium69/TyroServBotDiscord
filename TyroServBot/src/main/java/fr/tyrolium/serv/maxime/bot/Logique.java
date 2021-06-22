package fr.tyrolium.serv.maxime.bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Logique extends ListenerAdapter {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        String Pref = "ts!";
        User userTab = event.getAuthor();
        MessageChannel channelTab = event.getChannel();
        Guild guildTab = event.getGuild();
        System.out.println(guildTab.getName() + " / " + channelTab.getName() + " / " + userTab.getAsTag() + " ----> " + event.getMessage().getContentRaw());

        if (event.getMessage().getContentRaw().toLowerCase().contains(Pref + "help")) {
            event.getChannel().sendMessage("``` [ts!] help = cette commande```").queue();
            event.getChannel().sendMessage("``` [ts!] status = status du serveur minecraft```").queue();
            event.getChannel().sendMessage("``` [ts!] ip = ip du serv meme si y'en a pas ahah```").queue();
            event.getChannel().sendMessage("``` [ts!] site = le site web du serv minecraft```").queue();
            System.out.println(guildTab.getName() + " / " + channelTab.getName() + " <Requette Effectuez>");
        }

        if (event.getMessage().getContentRaw().toLowerCase().contains(Pref + "status")) {
            String result = null;
            try {
                result = get("http://tyrolium.fr/Contenu/Php/api/status.php");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (result == ""){
                result = "SERVEUR OFFLINE";
            }else {
                result = "SERVEUR ONLINE";
            }
            event.getChannel().sendMessage("__**STATUS**__").queue();
            event.getChannel().sendMessage("``" + result + "``").queue();
        }

        if (event.getMessage().getContentRaw().toLowerCase().contains(Pref + "ip")) {
            event.getChannel().sendMessage("__**IP**__").queue();
            event.getChannel().sendMessage("``le serveur ne possede pas d'ip il faut installer un launcher sur``"+ "http://tyroserv.fr/").queue();
            System.out.println(guildTab.getName() + " / " + channelTab.getName() + " <Requette Effectuez>");
        }

        if (event.getMessage().getContentRaw().toLowerCase().contains(Pref + "site")) {
            event.getChannel().sendMessage("__**SITE WEB**__").queue();
            event.getChannel().sendMessage("http://tyroserv.fr/").queue();
            System.out.println(guildTab.getName() + " / " + channelTab.getName() + " <Requette Effectuez>");
        }

        if (event.getMessage().getContentRaw().toLowerCase().contains(Pref + "player")) {
            String result = null;
            String result2 = null;
            try {
                result = get("http://tyrolium.fr/Contenu/Php/api/status.php");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (result == ""){
                event.getChannel().sendMessage("__**STATUS**__").queue();
                event.getChannel().sendMessage("``" + result + "``").queue();
            }else {
                try {
                    result2 = get("http://tyrolium.fr/Contenu/Php/api/player.php");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getChannel().sendMessage("__**NOMBRE DE PLAYER CONNECTER**__").queue();
                event.getChannel().sendMessage("``" + result2 + "``").queue();
            }
        }












    }

    public static String get(String url) throws IOException {

        String source ="";
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source +=inputLine;
        in.close();
        return source;
    }
}
