package datn.goodboy.repository;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Styles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
}
