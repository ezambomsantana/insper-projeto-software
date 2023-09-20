package com.insper.partida.equipe;

import com.insper.partida.equipe.dto.SaveTeamDTO;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<TeamReturnDTO> listTeams() {
        return teamService.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamReturnDTO saveTeam(@RequestBody SaveTeamDTO team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable String identifier) {
        teamService.deleteTeam(identifier);
    }


}
