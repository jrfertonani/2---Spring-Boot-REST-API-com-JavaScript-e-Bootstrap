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

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){

        if(usuario.getId() == null){
            return new ResponseEntity<String>("Id não foi informado!", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete (@RequestParam Long id){

        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>("User deletado com sucesso",HttpStatus.CREATED);

    }

    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam (name = "iduser") Long iduser){
        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }





    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome( @RequestParam (name = "name") String name){
        List<Usuario> usuario = usuarioRepository.buscarPosNome(name.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }


}
