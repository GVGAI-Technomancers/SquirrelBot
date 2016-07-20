/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author villtann
 */
public class GameStats {
    private String game;
    private int wins;
    private float winPercentage;
    private List<Double> scores;
    private int n;
    private double minScore;
    private double maxScore;
    private double avgScore;
    private List<Integer> timesteps;
    private int minSteps;
    private int maxSteps;
    private double avgSteps;
    
    
    public GameStats(String game) {
        this.game = game;
        this.wins = 0;
        this.scores = new ArrayList();
        this.timesteps = new ArrayList();
        this.n = 0;
    }
    
    public void add(boolean win, double score, int timestep)
    {
        if(win) wins++;
        this.scores.add(score);
        this.timesteps.add(timestep);
    }
    
    private void calc()
    {
        this.n = scores.size();
        this.winPercentage = ((float)wins)/n*100;
        minScore = Collections.min(scores);
        maxScore = Collections.max(scores);
        for(Double score:scores)
        {
            avgScore+=score;
        }
        avgScore=avgScore/n;
        
        minSteps = Collections.min(timesteps);
        maxSteps = Collections.max(timesteps);
        for(Integer step:timesteps)
        {
            avgSteps+=step;
        }
        avgSteps=avgSteps/n;
    }
    
    public List<String> CSVify()
    {
        //Calculate the averages etc
        calc();
        
        ArrayList<String> result = new ArrayList();
        String line;
        
        result.add(game);
        result.add(",n," + this.n);
        result.add(",Wins,"+this.wins);
        result.add(",Win Percentage,"+this.winPercentage);
        line = ",Scores";
        for(Double score:scores)
        {
            line = line + "," + score;
        }
        result.add(line);
        
        result.add(",Max Score,"+maxScore);
        result.add(",Min Score,"+minScore);
        result.add(",Avg Score,"+avgScore);
        
        line = ",Timesteps";
        for(Integer step:timesteps)
        {
            line = line + "," + step;
        }
        result.add(line);
        
        result.add(",Max Timesteps,"+maxSteps);
        result.add(",Min Timesteps,"+minSteps);
        result.add(",Avg Timesteps,"+avgSteps);
        
        return result;
    }


}
