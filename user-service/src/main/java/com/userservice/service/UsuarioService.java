package com.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.entities.Usuario;
import com.userservice.models.Carro;
import com.userservice.models.Moto;
import com.userservice.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuarioRepository usuariorepo;
	
	public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }
 
    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }
	
	public List<Usuario> obtenerTodos(){
		return usuariorepo.findAll();
	}
	
	public Usuario getUsuarioById(int id){
		return usuariorepo.findById(id).orElse(null);
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		Usuario nuevoUsuario = usuariorepo.save(usuario);
		return nuevoUsuario;
	}
	
	public void eliminarUsuario(int id) {
		usuariorepo.deleteById(id);
	}
}
