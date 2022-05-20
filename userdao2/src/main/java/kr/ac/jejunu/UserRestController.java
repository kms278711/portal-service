package kr.ac.jejunu;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @PostMapping
    public User create(@RequestBody User user) {
        return user;
    }

    @GetMapping
    public User get(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return User.builder().id(id).name(name).build();
    }

    @PutMapping
    public User modify(@RequestBody User user) {
        user.setName("modified");
        return user;
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id) {

    }
}
