package com.gg.administrative_system_backend.pettycash.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.pettycash.dto.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.dto.UpdatePettyCash;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.mapper.PettyCashMapper;
import com.gg.administrative_system_backend.pettycash.repository.PettyCashRepository;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.UpdateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PettyCashService {
    private final PettyCashRepository pettyCashRepository;
    private final PettyCashMapper pettyCashMapper;
    public List<PettyCash> findAll(){
        return pettyCashRepository.findAll();
    }

    public PettyCash savePettyCash(CreatePettyCashDTO createPettyCashDTO){
        return pettyCashRepository.save(pettyCashMapper.toPettyCash(createPettyCashDTO));
    }

    @Transactional
    public PettyCash updatePettyCash(UpdatePettyCash updatePettyCash, Long id){
        PettyCash pettyCash = findPettyCash(id);
        UpdateUtils.updateIfChanged(pettyCash::getType, updatePettyCash::getType, pettyCash::setType);
        UpdateUtils.updateIfChanged(pettyCash::getDate, updatePettyCash::getDate, pettyCash::setDate);
        return pettyCash;
    }

    public PettyCash findPettyCash(Long id){
        return pettyCashRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(Report.PETTY_CASH.getName(), id)));
    }
}
