package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.DynamicForm;
import hu.paninform.startmedsol.repository.DynamicFormRepository;
import hu.paninform.startmedsol.service.dto.DynamicFormDTO;
import hu.paninform.startmedsol.service.mapper.DynamicFormMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DynamicForm}.
 */
@Service
@Transactional
public class DynamicFormService {

    private final Logger log = LoggerFactory.getLogger(DynamicFormService.class);

    private final DynamicFormRepository dynamicFormRepository;

    private final DynamicFormMapper dynamicFormMapper;

    public DynamicFormService(DynamicFormRepository dynamicFormRepository, DynamicFormMapper dynamicFormMapper) {
        this.dynamicFormRepository = dynamicFormRepository;
        this.dynamicFormMapper = dynamicFormMapper;
    }

    /**
     * Save a dynamicForm.
     *
     * @param dynamicFormDTO the entity to save.
     * @return the persisted entity.
     */
    public DynamicFormDTO save(DynamicFormDTO dynamicFormDTO) {
        log.debug("Request to save DynamicForm : {}", dynamicFormDTO);
        DynamicForm dynamicForm = dynamicFormMapper.toEntity(dynamicFormDTO);
        dynamicForm = dynamicFormRepository.save(dynamicForm);
        return dynamicFormMapper.toDto(dynamicForm);
    }

    /**
     * Get all the dynamicForms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DynamicFormDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DynamicForms");
        return dynamicFormRepository.findAll(pageable)
            .map(dynamicFormMapper::toDto);
    }


    /**
     * Get one dynamicForm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DynamicFormDTO> findOne(Long id) {
        log.debug("Request to get DynamicForm : {}", id);
        return dynamicFormRepository.findById(id)
            .map(dynamicFormMapper::toDto);
    }

    /**
     * Delete the dynamicForm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DynamicForm : {}", id);
        dynamicFormRepository.deleteById(id);
    }
}
