package com.example.codefellowship.Controllers;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Models.Post;
import com.example.codefellowship.Repositries.AppUserRepository;
import com.example.codefellowship.Repositries.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;
    PostRepository postRepository;

    @GetMapping("/")
    public String getHomePage(Principal principl , Model model){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            model.addAttribute("username" , username);
        }
        else username = principal.toString();

        return principl != null ? "home" : "login";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    ////String password, String username, String firstName, String lastName, String dateOfBirth, String bio
    public String signupUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
        ApplicationUser appuser = new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        appUserRepository.save(appuser);
        return "login";
    }

    @GetMapping("/users")
    public String getUserProfilePage(Model model) {
        UserDetails uDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username" , uDetails.getUsername());
        model.addAttribute("users", appUserRepository.findAll());
        return "users";
    }
    @PostMapping("/addPost")
    public String addNewUserPost(Model model , @RequestParam String body){

    // using the time of computer to know which time the post created
    //https://stackoverflow.com/questions/39527752/how-to-test-date-created-with-localdatetime-now

        LocalDateTime time=  LocalDateTime.now();
//        System.out.println(" 1111");
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("222");
        ApplicationUser applicationUser = appUserRepository.findByUsername(user);
//        System.out.println("333");
        List<Post> allPosts = applicationUser.getPosts();
//        System.out.println("444");
        model.addAttribute("allPosts",applicationUser.getPosts());
//        System.out.println("555");
        Post post = new Post(body,time);
        post.setApplicationUser(applicationUser);
        allPosts.add(post);
        applicationUser.setPosts(allPosts);
        postRepository.save(post);
        System.out.println("post :"+ post);

        System.out.println(allPosts);
        return "/myprofile";
    }


    @GetMapping("/myprofile")
    String getProfilePage(Model model){
        UserDetails uDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username" , uDetails.getUsername());
        model.addAttribute("bio" , appUserRepository.findByUsername(uDetails.getUsername()).getBio());
        model.addAttribute("posts" , appUserRepository.findByUsername(uDetails.getUsername()).getPosts());
        return "myprofile";
    }
    @GetMapping("/users/{id}")
    String getuseidUser(@PathVariable int id  , Model model){
        ApplicationUser applicationUser =  appUserRepository.findById(id).orElseThrow();
        model.addAttribute("username" , applicationUser.getUsername());
        model.addAttribute("bio" , applicationUser.getBio());
        model.addAttribute("postsList" , applicationUser.getPosts());
        return "user";
    }

    // from read 17
    //https://spring.io/guides/tutorials/spring-boot-oauth2/
    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }

}
