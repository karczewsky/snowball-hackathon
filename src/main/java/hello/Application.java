package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

  static class Self {
    public String href;
  }

  static class Links {
    public Self self;
  }

  static class PlayerState {
    public Integer x;
    public Integer y;
    public String direction;
    public Boolean wasHit;
    public Integer score;
  }

  static class Arena {
    public List<Integer> dims;
    public Map<String, PlayerState> state;
  }

  static class ArenaUpdate {
    public Links _links;
    public Arena arena;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.initDirectFieldAccess();
  }

  @GetMapping("/")
  public String index() {
    return "Let the battle begin!";
  }

  @PostMapping("/**")
  public String index(@RequestBody ArenaUpdate arenaUpdate) {
    // Integer width = arenaUpdate.arena.dims.get(0);
    // Integer height = arenaUpdate.arena.dims.get(1);
    String myUrl = "https://35.227.242.25.sslip.io";
    PlayerState myState = arenaUpdate.arena.state.get(myUrl);

    counter = counter < 10000 ? counter + 1 : 0;

    if (myState == null) {
        System.err.print("Got null for myState");
        return "T";
    }

    if (myState.wasHit) {
        if (counter % 3 == 0) {
            return "L";
        } else {
            return "F";
        }
    }

    if (myState.score >= prevScore) {
        prevScore = myState.score;
        return "T";
    } else {
        prevScore = myState.score;
    }

    if (counter % 2 == 0) {
        return "R";
    } else {
        return "T";
    }
  }

  private Integer prevScore = -100;
  private Integer counter = 0;
}

