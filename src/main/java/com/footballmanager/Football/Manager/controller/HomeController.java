package com.footballmanager.Football.Manager.controller;

import com.footballmanager.Football.Manager.model.dtos.PlayerDTO;
import com.footballmanager.Football.Manager.model.entity.PositionType;
import com.footballmanager.Football.Manager.service.AuthService;
import com.footballmanager.Football.Manager.service.PlayerService;
import com.footballmanager.Football.Manager.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;
    private final PlayerService playerService;
    private final LoggedUser loggedUser;

    @Autowired
    public HomeController(AuthService authService,
                          PlayerService playerService,
                          LoggedUser loggedUser) {
        this.authService = authService;
        this.playerService = playerService;
        this.loggedUser = loggedUser;
    }

    @GetMapping({"/home"})
    public String loggedInIndex(Model model){
        if(!this.authService.isLogged()) {
            return "redirect:/";
        }

        List<PlayerDTO> goalkeeper = this.playerService.getPositionPlayer(PositionType.Goalkeeper);
        List<PlayerDTO> defender = this.playerService.getPositionPlayer(PositionType.Defender);
        List<PlayerDTO> midfielder = this.playerService.getPositionPlayer(PositionType.Midfielder);
        List<PlayerDTO> attacker = this.playerService.getPositionPlayer(PositionType.Attacker);
        List<PlayerDTO> userTeam = this.playerService.getUserLoggedTeam(loggedUser.getId());

        double totalValue = userTeam
                .stream()
                .mapToDouble(PlayerDTO::getValue)
                .sum();

        model.addAttribute("goalkeeperPlayer", goalkeeper);
        model.addAttribute("defenderPlayer", defender);
        model.addAttribute("midfielderPlayer", midfielder);
        model.addAttribute("attackerPlayer", attacker);
        model.addAttribute("currentUserId", loggedUser.getId());
        model.addAttribute("userTeam", userTeam);
        model.addAttribute("totalValue", totalValue);


        return "home";
    }

    @GetMapping({"/"})
    public String loggedOutIndex(){
        if(this.authService.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

}