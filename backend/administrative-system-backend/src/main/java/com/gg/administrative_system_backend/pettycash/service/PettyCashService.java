package com.gg.administrative_system_backend.pettycash.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.pettycash.dto.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.mapper.PettyCashMapper;
import com.gg.administrative_system_backend.pettycash.repository.PettyCashRepository;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PettyCashService {
    private final PettyCashRepository pettyCashRepository;
    private final PettyCashMapper pettyCashMapper;
    public List<PettyCash> findAll(){
        return pettyCashRepository.findAll();
    }
    public PettyCash savePettyCash(CreatePettyCashDTO createPettyCashDTO){
        return pettyCashRepository.save(pettyCashMapper.toPettyCash(createPettyCashDTO));
    }
    public PettyCash findPettyCash(Long id){
        return pettyCashRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(id)));
    }
}
