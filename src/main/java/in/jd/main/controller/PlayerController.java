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
	@RequestMapping("/players")
	public class PlayerController {
		
		@Autowired
		private PlayerService playerService;
		
		@Autowired
		private TeamService teamService;
		
		
		@Value("${file.upload-dir}")
	    private String uploadDir;
		
		 @GetMapping
		    public String viewPlayers(Model model) {
		        List<Player> players = playerService.getAllPlayers();
		        model.addAttribute("players", players);
		        return "players";
		    }
	
		    @PostMapping("/upload")
		    public String uploadPlayer(@RequestParam("name") String name,
		                                @RequestParam("role") String role,
		                                @RequestParam("netWorth") double netWorth,
		                                @RequestParam("totalMatches") int totalMatches,
		                                @RequestParam("totalRuns") int totalRuns,
		                                @RequestParam("bio") String bio,
		                                @RequestParam("teamId") Long teamId,
		                                @RequestParam("photo") MultipartFile photo) throws IOException {
		        String filename = StringUtils.cleanPath(photo.getOriginalFilename());
	
		        
		        Path filePath = Paths.get(uploadDir, filename);
		        Files.createDirectories(filePath.getParent());
		        Files.copy(photo.getInputStream(), filePath);
	
		        Team team = teamService.getAllTeams().stream().filter(t -> t.getId().equals(teamId)).findFirst().orElse(null);
	
		        Player player = new Player();
		        player.setName(name);
		        player.setRole(role);
		        player.setNetWorth(netWorth);
		        player.setTotalMatches(totalMatches);
		        player.setTotalRuns(totalRuns);
		        player.setBio(bio);
		        player.setPhoto("/uploads/" + filename);
		        player.setTeam(team);
	
		         playerService.save(player);
		        return "redirect:/players";
		    }
		    
		  
	
		    @DeleteMapping("/delete/{id}")
		    public String delete(@PathVariable("id") Long id) {
		    	 Player player = playerService.getPlayerById(id);
		    	    Long teamId = player.getTeam().getId();
		    	    playerService.deletePlayer(id);
		    	    return "redirect:/teams/" + teamId;
		    }
		    
		   @GetMapping("/players")
		    public String listPlayers(Model model) {
		        model.addAttribute("players", playerService.getAllPlayers());
		        model.addAttribute("teams", teamService.getAllTeams());
		        return "players";
		    }
	
		    @GetMapping("/search")
		    public String search(@RequestParam(name="keyword",required = false) String keyword,
		                         @RequestParam(name="teamId",required = false) Long teamId,
		                         Model model) {
		        model.addAttribute("players", playerService.searchPlayers(keyword, teamId));
		        model.addAttribute("teams", teamService.getAllTeams());
		        return "players";
		    }
		    
		    @GetMapping("/new")
		    public String showAddPlayerForm(Model model) {
		        model.addAttribute("player", new Player());
		        model.addAttribute("teams", teamService.getAllTeams());
		        return "new-player";
		    }
		    
		    
		 // Show update form
		    @GetMapping("/update/{id}")
		    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		        Player player = playerService.getPlayerById(id);
		         if (player == null) {
                    return "redirect:/players?error=PlayerNotFound";
                 }
		        model.addAttribute("player", player);
		        return "player_update";
		    }
		    
		    @PostMapping("/update/{id}")
		    public String updatePlayer(@PathVariable("id") Long id,
		                               @RequestParam("name") String name,
		                               @RequestParam("role") String role,
		                               @RequestParam("netWorth") double netWorth,
		                               @RequestParam("totalMatches") int totalMatches,
		                               @RequestParam("totalRuns") int totalRuns,
		                               @RequestParam("bio") String bio,
		                               @RequestParam("photo") MultipartFile photo) throws IOException {

		        Player player = playerService.getPlayerById(id);
		        player.setName(name);
		        player.setRole(role);
		        player.setNetWorth(netWorth);
		        player.setTotalMatches(totalMatches);
		        player.setTotalRuns(totalRuns);
		        player.setBio(bio);

		        if (!photo.isEmpty()) {
		            String filename = UUID.randomUUID() + "_" + photo.getOriginalFilename();
		            Path filePath = Paths.get("src/main/resources/static/uploads", filename);
		            Files.createDirectories(filePath.getParent());
		            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		            player.setPhoto("/uploads/" + filename);
		        }

		        playerService.save(player);
		        return "redirect:/teams" + player.getTeam().getId();
		    }
		   
	
	
	}