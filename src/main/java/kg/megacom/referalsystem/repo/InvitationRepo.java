package kg.megacom.referalsystem.repo;

import kg.megacom.referalsystem.model.entity.Invitation;
import kg.megacom.referalsystem.model.entity.Subscriber;
import kg.megacom.referalsystem.model.enums.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepo extends JpaRepository<Invitation, Long> {

    @Query(value = "SELECT * FROM tb_invitation ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Invitation> findLast();

    @Query(value = "" +
            "SELECT inv.* FROM tb_invitation AS inv " +
            "JOIN tb_subscriber AS subs " +
            "ON inv.id = subs.id " +
            "WHERE subs.phone IN (?1) AND inv.invitation_status IN (?2) " +
            "ORDER BY inv.id DESC LIMIT 1", nativeQuery = true)
    Optional<Invitation> findLastByReceiverIdAndStatus(String phoneNumber, String invitationStatus);
    List<Invitation> findByStartDateBetween(LocalDateTime then, LocalDateTime now);

    int countBySenderAndReceiverAndStartDateBetween(Subscriber sender, Subscriber receiver, LocalDateTime then ,LocalDateTime now);
    Optional<Invitation> findBySenderAndReceiverAndInvitationStatus(Subscriber sender, Subscriber receiver, String status);
    Optional<Invitation> findBySenderAndInvitationStatus(Subscriber sender, String status);
    Optional<List<Invitation>> findAllBySenderAndInvitationStatus(Subscriber subscriber, String status);
    Optional<List<Invitation>> findAllByReceiverAndInvitationStatus(Subscriber subscriber, String status);

}
