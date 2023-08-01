package com.corbandalas.data.adapters;

import com.corbandalas.data.mapper.PostMapper;
import com.corbandalas.data.mapper.RoleMapper;
import com.corbandalas.data.model.Role;
import com.corbandalas.data.repository.RoleRepository;
import com.corbandalas.domain.model.RoleDTO;
import com.corbandalas.domain.ports.spi.RolePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoleJPAAdapter implements RolePersistencePort {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO create(RoleDTO roleDTO) {

        var post = roleMapper.toEntity(roleDTO);

        return roleMapper.toDto(roleRepository.save(post));
    }

    @Override
    public Optional<RoleDTO> retrieve(UUID uuid) {
        return Optional.ofNullable(roleMapper.toDto(roleRepository.getReferenceById(uuid)));
    }

    @Override
    public RoleDTO update(RoleDTO dto) {
        return null;
    }


    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }

    @Override
    public List<RoleDTO> getAll(int page, int pageSize, String... sortFieldName) {
        return getAll();
    }

    @Override
    public Optional<RoleDTO> getRoleByName(String name) {


        return roleMapper.toDto(roleRepository.findByName(name));
    }
}
