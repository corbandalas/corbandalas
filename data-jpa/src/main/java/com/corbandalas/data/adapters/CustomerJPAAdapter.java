package com.corbandalas.data.adapters;

import com.corbandalas.data.mapper.CustomerMapper;
import com.corbandalas.data.model.Customer;
import com.corbandalas.data.repository.CustomerRepository;
import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.ports.spi.CustomerPersistencePort;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class CustomerJPAAdapter implements CustomerPersistencePort {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {

        var customer = customerMapper.toEntity(customerDTO);

//        var user = Customer.builder()
//                .active(true)
//                .createDate(LocalDateTime.now())
//                .uuid(customerDTO.getUuid())
//                .roles(customerDTO.getRoles())
//                .email(customerDTO.getEmail())
//                .hashedPassword(customerDTO.getHashedPassword())
//                .name(customerDTO.getName())
//                .build();

        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public Optional<CustomerDTO> retrieve(UUID uuid) {
        return Optional.ofNullable(customerMapper.toDto(customerRepository.getReferenceById(uuid)));
    }

    @Override
    public CustomerDTO update(CustomerDTO userDTO) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    public List<CustomerDTO> getAll(int page, int pageSize, String... sortFieldName) {
        return customerMapper.toDto(customerRepository.findAllCustomers(PageRequest.of(page, pageSize, (sortFieldName != null && sortFieldName.length > 0) ? Sort.by(sortFieldName).descending() : Sort.unsorted())));
    }

    @Override
    public Optional<CustomerDTO> retrieveByEmail(String email) {
        return customerRepository.findByEmail(email).map(customerMapper::toDto);
    }
}
