package com.footballmanager.Football.Manager.controller;

import com.footballmanager.Football.Manager.model.dtos.AddPlayerDTO;
import com.footballmanager.Football.Manager.model.entity.Player;
import com.footballmanager.Football.Manager.repository.PlayerRepository;
import com.footballmanager.Football.Manager.service.AuthService;
import com.footballmanager.Football.Manager.service.PlayerService;
import com.footballmanager.Football.Manager.service.UserService;
import com.footballmanager.Football.Manager.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final AuthService authService;

    private final LoggedUser loggedUser;
    private final UserService userService;

    public PlayerController(PlayerService playerService,
                            AuthService authService,
                            LoggedUser loggedUser,
                            UserService userService) {
        this.playerService = playerService;
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute("addPlayerDTO")
    public AddPlayerDTO initAddPlayerDTO() {
        return new AddPlayerDTO();
    }

    @GetMapping("/player/add")
    public String player() {
        if (this.authService.isLogged()) {
            return "player-add";
        }
        return "redirect:/login";
    }

    @PostMapping("/player/add")
    public String register(@Valid AddPlayerDTO addPlayerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPlayerDTO", addPlayerDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addPlayerDTO", bindingResult);

            return "redirect:/player/add";
        }

        this.playerService.created(addPlayerDTO);

        return "redirect:/home";
    }

    @GetMapping("player/add_team/{id}")
    String likePost(@PathVariable Long id) {

        userService.addPlayerToTeam(id, loggedUser.getId());
        return "redirect:/home";

    }


    @GetMapping("/remove_all")
    String clearUserTeam() {

        userService.clearUserTeam(loggedUser.getId());
        return "redirect:/home";

    }


}
