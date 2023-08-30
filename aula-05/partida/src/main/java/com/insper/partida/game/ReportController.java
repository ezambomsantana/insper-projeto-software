package com.insper.partida.game;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamRepository;
import com.insper.partida.game.dto.GameReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/numbers")
    public HashMap<Integer,Integer> getInts(@RequestParam("amount") Integer amount) {
        List<Integer> nums = new ArrayList<>();
        Random ran = new Random();

        for (int i = 0; i < amount; i++) {
            nums.add(ran.nextInt(100));
        }

        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0 ; j < nums.size(); j++) {
                if (nums.get(j) == i) {
                    count.merge(i, 1, Integer::sum);
                }
            }
        }
        return count;
    }

    @GetMapping("/tenGamesHomeByTeam")
    public List<GameReturnDTO> getTenGamesHomeByTeam(@RequestParam(name = "team") String team) {

        Team teamDB = teamRepository.findByIdentifier(team);
        if (teamDB == null) {
            throw new RuntimeException("Time não encontrado");
        }

        List<Game> games = gameRepository
                .findAll()
                .stream()
                .filter(g -> g.getHome().equals(team) || g.getAway().equals(team))
                .limit(10)
                .toList();

        return games.stream().map(g -> GameReturnDTO.covert(g)).toList();

    }

    @GetMapping("/getAllGamesByTeams")
    public List<GameReturnDTO> getAllTeams(@RequestParam(name = "team") List<String> teams) {

        for (String team : teams) {
            Team teamDB = teamRepository.findByIdentifier(team);
            if (teamDB == null) {
                throw new RuntimeException("Time não encontrado");
            }
        }

        List<Game> allGames = new ArrayList<>();

        for (String team : teams) {
            List<Game> games = gameRepository
                    .findAll()
                    .stream()
                    .filter(g -> g.getHome().equals(team) || g.getAway().equals(team))
                    .limit(10)
                    .toList();

            allGames.addAll(games);
        }

        return allGames.stream().map(g -> GameReturnDTO.covert(g)).toList();
    }


    @GetMapping("/getPointsByTeam")
    public HashMap<String, Integer>  getPointsByTeam(@RequestParam(name = "team") List<String> teams) {

        for (String team : teams) {
            Team teamDB = teamRepository.findByIdentifier(team);
            if (teamDB == null) {
                throw new RuntimeException("Time não encontrado");
            }
        }

        HashMap<String, Integer> teamPoints = new HashMap<>();

        for (String team : teams) {
            List<Game> games = gameRepository
                    .findAll()
                    .stream()
                    .filter(g -> g.getHome().equals(team) || g.getAway().equals(team))
                    .toList();

            Optional<Integer> points = games
                    .stream()
                    .map(g -> numPoints(g, team))
                    .reduce((a, p) -> a + p);
            teamPoints.put(team, points.get());

        }

        return teamPoints;
    }

    private Integer numPoints(Game g, String team) {
        if (g.getScoreAway() == g.getScoreHome()) {
            return 1;
        } else if (g.getHome().equals(team) && g.getScoreHome() > g.getScoreAway()) {
            return 3;
        } else if (g.getAway().equals(team) && g.getScoreAway() > g.getScoreHome()) {
            return 3;
        }
        return 0;
    }


}
