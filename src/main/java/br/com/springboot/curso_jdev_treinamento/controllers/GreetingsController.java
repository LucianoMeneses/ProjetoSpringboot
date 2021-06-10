package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    
	@Autowired
	private UsuarioRepository usuarioReposiory;

    //Listar todos os usuários
	
    @GetMapping(value = "listarUsuarios")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuarios(){
    	
    	List<Usuario> usuarios = usuarioReposiory.findAll();    	
    	
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
    
    //Adicionar Usuario
    
    @PostMapping(value = "salvarUsuario")
    @ResponseBody
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
    	
    	Usuario user = usuarioReposiory.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
   
    }
    
    //Buscar Usuario
    @GetMapping(value = "BuscarUsuario")
    @ResponseBody
    public ResponseEntity<Usuario> BuscarUsuario(@RequestParam Long id){
    	
    	Usuario user = usuarioReposiory.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    //Deletar Usuario
    
    @DeleteMapping(value = "deletarUsuario")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario(@RequestParam Long id){
    	
    	usuarioReposiory.deleteById(id);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    //Alterar usuario
    
    @PutMapping(value = "alterarUsuario")
    @ResponseBody
    public ResponseEntity<?> alterarUsuario(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == 0) {
    		
        	return new ResponseEntity<String>("Id não informado", HttpStatus.BAD_REQUEST);
    	} else {
    	
    	Usuario user = usuarioReposiory.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user ,HttpStatus.OK);
    	}
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){
    	
    	List<Usuario> usuarios = usuarioReposiory.buscarPorNome(nome.trim().toUpperCase());
    	    	
    	return new ResponseEntity<List<Usuario>>(usuarios ,HttpStatus.OK);
    }
    
}
