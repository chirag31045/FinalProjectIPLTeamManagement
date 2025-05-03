package in.jd.main.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.jd.main.entity.Player;
import in.jd.main.entity.Team;
import in.jd.main.service.PlayerService;
import in.jd.main.service.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private PlayerService playerService;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	 @GetMapping
	    public String viewTeams(Model model) {
	        List<Team> teams = teamService.getAllTeams();
	        model.addAttribute("teams", teams);
	        return "teams";
	    }

	 @PostMapping(value="/upload", consumes = "multipart/form-data")
	 public String uploadTeam(
	         @RequestParam("name") String name,
	         @RequestParam("coach") String coach,
	         @RequestParam("owner") String owner,
	         @RequestParam("totalMatchesWon") int totalMatchesWon,
	         @RequestParam("logo") MultipartFile logo) throws IOException {

	     String filename = StringUtils.cleanPath(logo.getOriginalFilename());
	     Path filePath = Paths.get(uploadDir, filename);
	     Files.createDirectories(filePath.getParent());
	     Files.copy(logo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	     Team team = new Team();
	     team.setName(name);
	     team.setCoach(coach);
	     team.setOwner(owner);
	     team.setTotalMatchesWon(totalMatchesWon);
	     team.setLogo("/uploads/" + filename); // Save just the filename or path

	     teamService.save(team);

	     return "redirect:/teams";
	 }
	    
	    @PostMapping("/{id}")
	    public ResponseEntity<Team> update(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("owner") String owner, @RequestParam("totalMatchesWon") String totalMatchesWon , @RequestParam("logo") MultipartFile logo) throws IOException {
	        Team team = teamService.getAllTeams().stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow();
	        String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
	        Files.copy(logo.getInputStream(), Paths.get("src/main/resources/static/uploads/" + fileName), StandardCopyOption.REPLACE_EXISTING);
	        team.setName(name);
	        team.setLogo(fileName);
	        return ResponseEntity.ok(teamService.save(team));
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable("id") Long id) {
	        teamService.deleteTeam(id);
	    }
	    
	    @GetMapping("/search")
	    public String searchTeams(@RequestParam("keyword") String keyword, Model model) {
	        List<Team> teams = teamService.searchTeamsByName(keyword);
	        model.addAttribute("teams", teams);
	        return "teams";
	    }
	    
	    @GetMapping("/new")
	    public String showCreateForm(Model model) {
	        model.addAttribute("team", new Team());
	        return "team_form";
	    }
	    
	    @GetMapping("/{id}/players")
	    public String viewTeamPlayers(@PathVariable("id") Long id, Model model) {
	        Team team = teamService.getTeamById(id);
	        List<Player> players = playerService.getPlayersByTeamId(id);

	        model.addAttribute("team", team);
	        model.addAttribute("players", players);
	        return "team_player";
	    }
	    
	  
}
