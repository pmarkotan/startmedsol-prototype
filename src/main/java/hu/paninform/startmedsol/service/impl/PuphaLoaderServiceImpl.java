package hu.paninform.startmedsol.service.impl;

import hu.paninform.startmedsol.service.PuphaLoaderService;
import hu.paninform.startmedsol.domain.PuphaLoader;
import hu.paninform.startmedsol.repository.PuphaLoaderRepository;
import hu.paninform.startmedsol.service.dto.PuphaLoaderDTO;
import hu.paninform.startmedsol.service.mapper.PuphaLoaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PuphaLoader}.
 */
@Service
@Transactional
public class PuphaLoaderServiceImpl implements PuphaLoaderService {

    private final Logger log = LoggerFactory.getLogger(PuphaLoaderServiceImpl.class);

    private final PuphaLoaderRepository puphaLoaderRepository;

    private final PuphaLoaderMapper puphaLoaderMapper;

    public PuphaLoaderServiceImpl(PuphaLoaderRepository puphaLoaderRepository, PuphaLoaderMapper puphaLoaderMapper) {
        this.puphaLoaderRepository = puphaLoaderRepository;
        this.puphaLoaderMapper = puphaLoaderMapper;
    }

    @Override
    public PuphaLoaderDTO save(PuphaLoaderDTO puphaLoaderDTO) {
        log.debug("Request to save PuphaLoader : {}", puphaLoaderDTO);
        PuphaLoader puphaLoader = puphaLoaderMapper.toEntity(puphaLoaderDTO);
        puphaLoader = puphaLoaderRepository.save(puphaLoader);
        return puphaLoaderMapper.toDto(puphaLoader);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PuphaLoaderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PuphaLoaders");
        return puphaLoaderRepository.findAll(pageable)
            .map(puphaLoaderMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PuphaLoaderDTO> findOne(Long id) {
        log.debug("Request to get PuphaLoader : {}", id);
        return puphaLoaderRepository.findById(id)
            .map(puphaLoaderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PuphaLoader : {}", id);
        puphaLoaderRepository.deleteById(id);
    }
}
