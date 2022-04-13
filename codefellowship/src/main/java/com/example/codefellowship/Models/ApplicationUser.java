package com.example.codefellowship.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {
    @ManyToMany
    @JoinTable(
            name = "user_user",
            joinColumns = {@JoinColumn(name = "from_id")},
            inverseJoinColumns = {@JoinColumn(name = "to_id")}
    )
    public List<ApplicationUser> following;
    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    public List<ApplicationUser> followers;
    @OneToMany(mappedBy = "applicationUser")
    List<Post> posts;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(updatable = false)
    private String password;
    @Column(unique = true, updatable = false)
    private String username;
    @Column(updatable = false)
    private String firstName;
    @Column(updatable = false)
    private String lastName;
    @Column(updatable = false)
    private String dateOfBirth;
    @Column(updatable = false)
    private String bio;

    public ApplicationUser() {
    }


    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(List<ApplicationUser> following) {
        this.following = following;
    }

    public List<ApplicationUser> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ApplicationUser> followers) {
        this.followers = followers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "following=" + following +
                '}';
    }
}
