/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author villtann
 */
public class StatTracker {
    private HashMap<String,GameStats> gameStats;
    private String currGame = "";
    private static StatTracker instance;
    
    public StatTracker()
    {
        gameStats = new HashMap<String,GameStats>();
        instance = this;
    }
    
    public static StatTracker getInstance()
    {
        return instance;
    }
    
    public void setCurrGame(String game) 
    {
        this.currGame = game;
    }
    
    public void add(boolean win, double score, int timesteps)
    {
        this.add(currGame, win, score, timesteps);
    }
    
    public void add(String game, boolean win, double score, int timesteps)
    {
        if(gameStats.containsKey(game))
        {
            gameStats.get(game).add(win, score, timesteps);
        }
        else
        {
            gameStats.put(game, new GameStats(game));
            gameStats.get(game).add(win, score, timesteps);
        }
    }
    
    public void save(String file)
    {
        List<String> lines = new ArrayList<String>();
        Set<String> games = gameStats.keySet();
        for(String game:games)
        {
            GameStats gameStat = gameStats.get(game);
            lines.addAll(gameStat.CSVify());
            lines.add("");
        }
        Path filePath = Paths.get(file + ".csv");
        try {
            Files.write(filePath, lines, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(StatTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
