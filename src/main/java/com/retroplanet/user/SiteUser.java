package com.retroplanet.user;

import com.retroplanet.feed.Feed;
import com.retroplanet.userprofile.UserProfile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "siteuser")
public class SiteUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  @Column(unique = true)
  private String email;


  @OneToOne(mappedBy = "siteUser", cascade = CascadeType.REMOVE)
  private UserProfile userProfile;




  @OneToMany(mappedBy = "siteUser", cascade = CascadeType.REMOVE)
  private List<Feed> feedList;


  @ManyToMany
  private Set<SiteUser> following;

  @ManyToMany
  private Set<SiteUser> followers;

  public SiteUser() {

  }


  public SiteUser(String username, String email) {
    this.username = username;
    this.email = email;
    this.password = "passw0rd";

  }


}