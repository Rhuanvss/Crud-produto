package com.example.projeto_springboot.service;

import com.example.projeto_springboot.model.Usuario;
import com.example.projeto_springboot.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario (String username, String password) {
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean verificarSenha(String senhaRaw, String senhaEncriptada) {
        return passwordEncoder.matches(senhaRaw, senhaEncriptada);
    }



}
