package com.retroplanet.user;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
  Optional<SiteUser> findByUsername(String username);

  SiteUser findByEmail(String username);


  @Query("SELECT u.followers FROM SiteUser u WHERE u.username = :username")
  Set<SiteUser> findFollowersByUsername(String username);

  @Query("SELECT u.following FROM SiteUser u WHERE u.username = :username")
  Set<SiteUser> findFollowingsByUsername(String username);
}