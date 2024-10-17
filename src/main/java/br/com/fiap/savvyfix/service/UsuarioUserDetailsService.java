package br.com.fiap.savvyfix.service;

import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRep;


    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Cliente cliente = clienteRep.findByCpf(cpf);

        return new User(cliente.getCpf(), cliente.getSenha(), cliente.getRoles().stream().map(role ->
                        new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList()));
    }
}
