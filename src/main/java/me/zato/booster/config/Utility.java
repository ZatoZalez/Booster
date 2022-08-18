package me.zato.booster.config;

import me.zato.booster.Booster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static ArrayList<Data> configData = new ArrayList<>();
    private static String configPath = Booster.getPlugin().getDataFolder().getAbsolutePath();
    private static String configFile = "config.yml";

    public static void Build()  {
        try {
            Create();
        }catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage("[" + Booster.getPlugin().getDescription().getName() + "] " +
                    ChatColor.RED + "Could not create " + configFile + "\n" + e.getMessage());
        }
    }

    private static void Create() throws IOException {
        try {
            String data = "";
            File file = new File(configPath + "/" + configFile);
            file.getParentFile().mkdir();
            ArrayList<String> fileLines = new ArrayList<>();

            //default config
            fileLines.add(addData("Configuration for " + Booster.getPlugin().getDescription().getName() + " " + Booster.getPlugin().getDescription().getVersion() + " by ZatoZalez.").getInline());
            fileLines.add(addData("This plugin is custom build for Saber Network.").getInline());
            fileLines.add(addData("").getInline());

            fileLines.add(addData("Enable or disable any event.").getInline());
            fileLines.add(addData("EnableMarketBooster", true).getInline());
            fileLines.add(addData("EnableMobDropBooster", true).getInline());
            fileLines.add(addData("EnableInterestBooster", true).getInline());

            fileLines.add(addData("").getInline());
            fileLines.add(addData("The default duration of an event in minutes.").getInline());
            fileLines.add(addData("DefaultEventDuration", 10).getInline());
            fileLines.add(addData("").getInline());
            fileLines.add(addData("Default tip amount when using the /tip command.").getInline());
            fileLines.add(addData("DefaultTipAmount", 10).getInline());
            fileLines.add(addData("-------------------------").getInline());

            fileLines.add(addData("").getInline());
            fileLines.add(addData("Market Event").getInline());
            fileLines.add(addData("Market boost multiplier during the market event (example x1.5 => 1.5).").getInline());
            fileLines.add(addData("MarketMultiplier", 1.5).getInline());
            fileLines.add(addData("-------------------------").getInline());

            fileLines.add(addData("").getInline());
            fileLines.add(addData("MobDrop Event").getInline());
            fileLines.add(addData("MobDrop boost multiplier during the mobdrop event (example x2 => 2).").getInline());
            fileLines.add(addData("MobDropMultiplier", 2).getInline());
            fileLines.add(addData("-------------------------").getInline());

            fileLines.add(addData("").getInline());
            fileLines.add(addData("Interest Event").getInline());
            fileLines.add(addData("Interest boost delay during the interest event (minutes, no decimals).").getInline());
            fileLines.add(addData("InterestDelay", 10).getInline());
            fileLines.add(addData("Interest boost amount during the interest event in decimals (example 5% => 0.05).").getInline());
            fileLines.add(addData("InterestAmount", 0.05).getInline());
            fileLines.add(addData("-------------------------").getInline());
            //--

            for(String s : fileLines){
                if(data.equalsIgnoreCase(""))
                    data += s;
                else
                    data += "\n" + s;
            }
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage("[" + Booster.getPlugin().getDescription().getName() + "] " +
                    ChatColor.RED + "Could not create " + configFile + "\n" + e.getMessage());
            return;
        }
    }

    public static void Read(){
        File file = new File(configPath + "/" + configFile);
        if(!file.exists()){
            Build();
            return;
        }

        ArrayList<Data> tempData = new ArrayList<>();
        try
        {
            List<String> fileLines = new ArrayList<>();
            fileLines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
            if(fileLines.size() < 1){
                Build();
                return;
            }
            for(String s : fileLines){
                if(s.equals(null) || s.equals("") || s.length() < 3 || !s.contains(":") || s.startsWith("#"))
                    continue;

                String key = s.split(":")[0].trim();
                String value = s.split(":", 2)[1].trim();
                tempData.add(new Data(key, value));
            }
        }catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage("[" + Booster.getPlugin().getDescription().getName() + "] " +
                    ChatColor.RED + "Could not read " + configFile + "\n" + e.getMessage());
            return;
        }

        if(tempData.size() < 1)
            Bukkit.getConsoleSender().sendMessage("[" + Booster.getPlugin().getDescription().getName() + "] " +
                    ChatColor.RED + "The " + configFile + " could not be read. Creating new config");
        configData = tempData;
        Build();
    }

    public static void Save(){
        File file = new File(configPath + "/" + configFile);
        if(!file.exists()){
            Build();
            return;
        }

        try {
            String data = "";
            ArrayList<String> fileLines = new ArrayList<>();
            for(Data d : configData){
                if(data.equalsIgnoreCase(""))
                    data += d.getInline();
                else
                    data += "\n" + d.getInline();
            }
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage("[" + Booster.getPlugin().getDescription().getName() + "] " +
                    ChatColor.RED + "Could not write to " + configFile + "\n" + e.getMessage());
            return;
        }
    }

    public static Data addData(String key, Object value){
        for(Data data : configData){
            if(data.isValid() && data.getKey().equalsIgnoreCase(key))
                return data;
        }

        Data data = new Data(key, value.toString());
        configData.add(data);
        return data;
    }

    public static Data addData(boolean override, String key, Object value){
        for(Data data : configData){
            if(data.isValid() && data.getKey().equalsIgnoreCase(key))
            {
                if(!override)
                    configData.remove(data);
                else
                    return data;
            }
        }
        Data data = new Data(key, value.toString());
        configData.add(data);
        return data;
    }

    public static Data addData(String description){
        for(Data data : configData){
            if(data.isDescription() && data.getDescription().equalsIgnoreCase(description))
                return data;
        }
        Data data = new Data(description);
        configData.add(data);
        return data;
    }

    public static Data addData(boolean override, String description){
        for(Data data : configData){
            if(data.isDescription() && data.getDescription().equalsIgnoreCase(description))
            {
                if(!override)
                    configData.remove(data);
                else
                    return data;
            }
        }
        Data data = new Data(description);
        configData.add(data);
        return data;
    }

    public static Data getData(String key){
        for(Data data : configData){
            if(data.isValid() && data.getKey().equalsIgnoreCase(key))
                return data;
        }
        return null;
    }

    public static void removeData(String key){
        for(Data data : configData){
            if(data.isValid() && data.getKey().equalsIgnoreCase(key)){
                configData.remove(data);
                return;
            }
        }
        return;
    }
}

