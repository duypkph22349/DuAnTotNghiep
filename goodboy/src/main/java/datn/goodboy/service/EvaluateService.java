package datn.goodboy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Evaluate;
import datn.goodboy.repository.EvaluteRepository;

/**
 * EvaluateService
 */
@Service
public class EvaluateService {
  @Autowired
  EvaluteRepository repository;

  public Evaluate createEvaluate(Evaluate evaluate) {
    return repository.save(evaluate);
  }

  public Optional<Evaluate> getEvaluateById(Integer id) {
    return repository.findById(id);
  }

  public Evaluate updateEvaluate(Integer id, Evaluate updatedEvaluate) {
    Optional<Evaluate> existingEvaluate = repository.findById(id);
    if (existingEvaluate.isPresent()) {
      existingEvaluate.get().setDescription(updatedEvaluate.getDescription());
      existingEvaluate.get().setStart(updatedEvaluate.getStart());
      return repository.save(existingEvaluate.get());
    } else {
      return null;
    }
  }

  public void deleteEvaluate(Integer id) {
    repository.deleteById(id);
  }
}
