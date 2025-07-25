package com.gg.administrative_system_backend.pettycash.service;

import com.gg.administrative_system_backend.pettycash.dto.pettycash.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.entity.pettycash.PettyCash;
import com.gg.administrative_system_backend.pettycash.mapper.pettycash.PettyCashMapper;
import com.gg.administrative_system_backend.pettycash.repository.PettyCashRepository;
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
}
