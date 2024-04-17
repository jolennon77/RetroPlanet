package com.retroplanet.feed;

import com.retroplanet.user.SiteUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String photoImgName;

  private String photoImgPath;

  private String content;

  private LocalDateTime createDate;

  @ManyToOne
  private SiteUser siteUser;

}
