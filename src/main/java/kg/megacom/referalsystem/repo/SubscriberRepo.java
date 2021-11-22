package kg.megacom.referalsystem.repo;

import kg.megacom.referalsystem.model.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByPhone(String phone);
    boolean existsByPhone(String phone);

    @Query(value = "SELECT subs.is_active FROM tb_subscriber AS subs WHERE subs.phone = ?1", nativeQuery = true)
    boolean isActive(String phone);
}
