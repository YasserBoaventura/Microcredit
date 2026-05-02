package com.Microcredito.Endereco;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.Cliente.ClienteRepository;
import com.Microcredito.entity.Cliente;
import com.Microcredito.entity.Endereco;

import DTO.EndercoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EndereocoRepository repository;
    private final ClienteRepository clienteRepository;
    
    @Transactional
    public Endereco save(EndercoDTO dto) {

Cliente cliente = clienteRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	Endereco endereco = new Endereco();
	endereco.setCliente(cliente);
	endereco.setCep(dto.getCep());
	endereco.setLogradouro(dto.getLogradouro());
	endereco.setNumero(dto.getNumero());
	endereco.setComplemento(dto.getComplemento());
	endereco.setBairro(dto.getBairro());
	endereco.setCidade(dto.getCidade());
	endereco.setEstado(dto.getEstado());
	endereco.setTipoEndereco(dto.getTipoEndereco());
	endereco.setPrincipal(dto.getPrincipal());
	
	        return repository.save(endereco);
	    }
    @Transactional
    public Endereco atualizar(Long id, EndercoDTO dto) {
 
    Endereco endereco = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

    // para alterar o cliente
    if (dto.getClienteId() != null) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        endereco.setCliente(cliente);
    }

        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setTipoEndereco(dto.getTipoEndereco());
        endereco.setPrincipal(dto.getPrincipal());
 
        return repository.save(endereco);
    }
    public Endereco findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Endereco> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Endereco> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }

}
