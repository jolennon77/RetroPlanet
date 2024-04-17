package com.retroplanet.feed;

import com.retroplanet.user.SiteUser;
import com.retroplanet.user.UserRepository;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FeedService {

  private final FeedRepository feedRepository;
  private final UserRepository userRepository;


  public void updateFeed(String username, String content, MultipartFile file) throws Exception {

    Feed feed = new Feed();
    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);

    if (siteUser.isPresent()) {
      feed.setSiteUser(siteUser.get());
      feed.setContent(content);
      feed.setCreateDate(LocalDateTime.now());

      String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/feed";
      UUID uuid = UUID.randomUUID();
      String fileName = uuid + "_" + file.getOriginalFilename();
      File saveFile = new File(projectPath, fileName);
      file.transferTo(saveFile);

      feed.setPhotoImgName(fileName);
      feed.setPhotoImgPath("/files/feed/" + fileName);

      this.feedRepository.save(feed);
    } else {
      System.out.println("피드업로드실패");
    }

  }


  public void deleteFeedById(Long id) {
    this.feedRepository.deleteById(id);
  }


  public Feed getFeedById(Long id) {

    return this.feedRepository.findById(id).orElse(null);
  }
}
