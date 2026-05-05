package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.model.MensajeChat;
import java.util.List;


@Repository
public interface MensajeChatRepository extends JpaRepository<MensajeChat, Integer> {
	List<MensajeChat> findBySesionIdOrderByCreadoEnAsc(Integer sesionId);
}
