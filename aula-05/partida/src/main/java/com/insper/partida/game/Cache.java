package com.insper.partida.game;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class Cache {

    @Autowired
    private TeamRepository teamRepository;

    private Map<String, Team> teams = new HashMap<>();

    public Team getTeam(String team) {
        if (teams.containsKey(team)) {
            return teams.get(team);
        }
        Team teamDB = teamRepository.findByIdentifier(team);
        if (teamDB != null) {
            teams.put(team, teamDB);
        }
        return teamDB;
    }


}
