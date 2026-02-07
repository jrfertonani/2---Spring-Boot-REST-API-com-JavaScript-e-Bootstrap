package Java.controller;

import Java.model.Usuario;
import Java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/{nome}")
    public String ola(@PathVariable String nome){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "Ola" + nome;
    };

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuario() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }


    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

    }



}
