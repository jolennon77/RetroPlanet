package com.retroplanet.userprofile;

import com.retroplanet.user.SiteUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String profileImgName;

  private String profileImgPath;

  private String mainPageImgName;

  private String mainPageImgPath;

  private String todayMood;

  private String introduction;

  private String youTubeURL;


  @OneToOne
  private SiteUser siteUser;
}