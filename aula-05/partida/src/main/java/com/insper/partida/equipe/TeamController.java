package com.insper.partida.equipe;

import com.insper.partida.equipe.dto.SaveTeamDTO;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import com.insper.partida.game.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private Cache cache;

    @GetMapping
    public List<TeamReturnDTO> listTeams() {
        return teamService.listTeams();
    }


    @GetMapping("/{identificador}")
    public TeamReturnDTO getTeam(@PathVariable String identificador) {
        return TeamReturnDTO.covert(cache.getTeam(identificador));
    }

    @PostMapping
    public TeamReturnDTO saveTeam(@RequestBody SaveTeamDTO team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{identifier}")
    public void deleteTeam(@PathVariable String identifier) {
        teamService.deleteTeam(identifier);
    }


}
