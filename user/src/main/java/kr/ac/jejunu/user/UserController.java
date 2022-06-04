package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userDao.findById(id).get();
    }
}
