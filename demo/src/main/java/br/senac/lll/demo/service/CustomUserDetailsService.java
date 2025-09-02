package br.senac.lll.demo.service;

import br.senac.lll.demo.model.Pessoa;
import br.senac.lll.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Busca a pessoa no banco de dados pelo e-mail
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        
        // 2. Se não encontrar, lança uma exceção que o Spring Security trata
        if (pessoa == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        // 3. Se encontrar, retorna um objeto UserDetails com os dados do usuário
        // O Spring Security usará isso para comparar a senha
        return new User(pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>());
    }
}