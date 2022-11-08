package pl.gov.orlikiapi.sportsfieldtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;

@Repository
public interface SportsFieldTypeRepository extends JpaRepository<SportsFieldType, Long> {
}
