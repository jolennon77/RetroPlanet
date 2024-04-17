package com.retroplanet.user;

import static org.junit.jupiter.api.Assertions.*;

import com.retroplanet.feed.Feed;
import com.retroplanet.feed.FeedRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeedTest {

  @Autowired
  private FeedRepository feedRepository;
  @Autowired
  private UserRepository userRepository;


  @Test
  public void 피드임의생성() {

    Optional<SiteUser> siteUser = this.userRepository.findById(1L);

    if (siteUser.isPresent()) {
      SiteUser user1 = siteUser.get();

      Feed feed1 = new Feed();
      feed1.setContent("피드내용 테스트2");
      feed1.setSiteUser(user1);
      feed1.setPhotoImgPath("피드이미지 테스트경로2");
      feed1.setPhotoImgName("피드이미지 테스트이름2");
      feed1.setCreateDate(LocalDateTime.now());
      this.feedRepository.save(feed1);
    }
  }

  @Test
  public void 피드10개임의생성() {
    Optional<SiteUser> siteUser = this.userRepository.findById(1L);

    if (siteUser.isPresent()) {
      SiteUser user1 = siteUser.get();
      LocalDateTime now = LocalDateTime.now();

      for (int i = 1; i < 10; i++) {
        Feed feed = new Feed();
        feed.setContent("피드내용 테스트 " + (i + 1));
        feed.setSiteUser(user1);
        feed.setPhotoImgPath("피드이미지 테스트경로 " + (i + 1));
        feed.setPhotoImgName("피드이미지 테스트이름 " + (i + 1));
        feed.setCreateDate(now);
        this.feedRepository.save(feed);
      }
    }
  }

  @Test
  public void 피드찾기테스트() {
    List<Feed> all = this.feedRepository.findAll();
    assertEquals(2, all.size());

    Feed f = all.get(0);
    assertEquals("피드내용 테스트1", f.getContent());
  }

  @Test
  public void 피드찾기테스트byID() {
    Optional<Feed> f = this.feedRepository.findById(1L);
    if (f.isPresent()) {
      Feed feed = f.get();
      assertEquals("피드내용 테스트1", feed.getContent());
    }
  }


@Test
  public void 피드삭제테스트(){
    assertEquals(2, this.feedRepository.count());
    Optional<Feed> f = this.feedRepository.findById(2L);
    if (f.isPresent()) {

      Feed feed = f.get();
      this.feedRepository.delete(feed);
      assertEquals(1, this.feedRepository.count());
    }
}

}

