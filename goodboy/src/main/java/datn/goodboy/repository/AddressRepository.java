package datn.goodboy.repository;

import datn.goodboy.model.entity.Address;
import datn.goodboy.model.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    @Query("""
            select a from Address a where a.id_customer.id = :id_customer""")
    List<Address> findById_customer(@Param("id_customer") UUID id);

    @Query(value = """
            select * from addresss a where a.id_customer = :id_customer
            """,nativeQuery = true )
    List<Address> findAddressByIDCustomer(@Param("id_customer") UUID id);

}
