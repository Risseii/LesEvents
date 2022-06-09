package com.codingdojo.vany.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.vany.modelos.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {
	

}
