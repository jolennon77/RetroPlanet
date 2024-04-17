package com.retroplanet.userprofile;

import com.retroplanet.user.SiteUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.retroplanet.userprofile.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  Optional<UserProfile> findBySiteUser(SiteUser siteUser);

}