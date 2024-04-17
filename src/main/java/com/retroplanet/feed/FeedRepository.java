package com.retroplanet.feed;

import com.retroplanet.user.SiteUser;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {

  List<Feed> findBySiteUserOrderByCreateDateDesc(SiteUser siteUser);
}
