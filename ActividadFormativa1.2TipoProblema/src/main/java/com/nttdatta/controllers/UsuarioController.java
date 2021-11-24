package com.nttdatta.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdatta.models.Usuario;
import com.nttdatta.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController { //encargado de hacer las primeras validaciones
	@Autowired //inyección de dependencia
	UsuarioService usuarioService;
	
	
	//desplegar inicalmente el jsp
	@RequestMapping("")
	public String usuarioIndex(@ModelAttribute("usuario") Usuario usuario, Model model) {
		List<Usuario> listaUsuarios = usuarioService.obtenerListaUsuario();
		//Lista de usuario
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		return "usuario.jsp";
	}
	
	
	
	
	//capturar la info del form
		@RequestMapping("/login")
		public String login(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {	
			//System.out.println(usuario.getNombre() + " " +usuario.getApellido() + " " +usuario.getLimite() +" " + usuario.getCodigoPostal());
			return validaUsuario(usuario, model);
		}

		
		private String validaUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
		// TODO Auto-generated method stub
			Boolean flagNombre = false;
			Boolean flagApellido = false;
			Boolean flagLimite = false;
			Boolean flagCodigoPostal = false;

			//Validación nombre
			if (usuario.getNombre().length() > 1 && usuario.getNombre().length() < 10) {
				System.out.println("ta bien el nombre");
				flagNombre=true;
			}else {
				System.out.println("======RECUERDA=======");
				System.out.println("El nombre no puede ser mayor a 10 caracteres");
				
			}
			
			//Validación apellido
			if (usuario.getApellido().length() > 1 && usuario.getApellido().length() < 10) {
				System.out.println("ta bien el apellido");
				flagApellido=true;
			}else {
				System.out.println("======RECUERDA=======");
				System.out.println("El apellido no puede ser mayor a 10 caracteres");
			}
				
			//Validación límite
			if (usuario.getLimite() >= 0 && Integer.toString(usuario.getLimite()).length() > 0 && Integer.toString(usuario.getLimite()).length() < 6 ) {
				System.out.println("ta bien el limite");
				flagLimite=true;

			}else {
				System.out.println("======RECUERDA=======");
				System.out.println("El límite debe ser positivo y menor a 6 dígitos");
			}
			
			//Validación codigo postal
			if (Integer.toString(usuario.getCodigoPostal()).length() == 8) {
				System.out.println("ta bien el codigo postal");
				flagCodigoPostal=true;

			}else {
				System.out.println("======RECUERDA=======");
				System.out.println("El código postal debe tener solo 8 dígitos. ");
			}
			
			
				
			if(flagNombre && flagApellido && flagLimite && flagCodigoPostal) {
				//si esta bien
				model.addAttribute("nombre", usuario.getNombre());
				model.addAttribute("apellido", usuario.getApellido());
				model.addAttribute("limite", usuario.getLimite());
				model.addAttribute("codigoPostal", usuario.getCodigoPostal());

				usuarioService.insertarUsuario(usuario);
				
				return "respuesta.jsp";
			}else {
				//Si esta mal
				return "redirect:/usuario";
			}

	}




		//Eliminar usuario
		@RequestMapping("/eliminar/usuario/{id}")
		public String eliminar(@PathVariable("id") Long id) {

			Usuario usuario = usuarioService.buscarUsuarioId(id);
			if(usuario != null) {
				usuarioService.eliminarUsuario(id);
				return "Usuario Eliminado";

			}else {
				return "Usuario No Pudo Ser Eliminado";

			}
		}
		
		@RequestMapping("/eliminar")
		public String eliminarUsuario(@RequestParam("id") Long id) {

			Usuario usuario = usuarioService.buscarUsuarioId(id);

			if(usuario !=null) {
				usuarioService.eliminarUsuarioObjeto(usuario);
			}

			return "redirect:/usuario";
		}
		
		@RequestMapping("/editar")
		public String editar(@RequestParam("id") Long id, Model model, HttpSession session) {

			Usuario usuario = usuarioService.buscarUsuarioId(id);
			System.out.println(usuario.toString());
	
			session.setAttribute("usuarioAntiguo", usuario);
	
			model.addAttribute("editarUsuario", usuario);
			return "editarUsuario.jsp";
		}

		@RequestMapping("/editarUsuario")
		public String editarUsuario(@ModelAttribute("editarUsuario") Usuario usuario, Model model, HttpSession session) {
	
			Usuario usuarioAntiguo = (Usuario)session.getAttribute("usuarioAntiguo");
	
			System.out.println("Nombre nuevo: "+ usuario.getNombre());
			String error = validaUsuario(usuario, model);
			if(error.isEmpty()) {
				usuarioAntiguo.setNombre(usuario.getNombre());
				usuarioAntiguo.setApellido(usuario.getApellido());
				usuarioAntiguo.setLimite(usuario.getLimite());
				usuarioAntiguo.setCodigoPostal(usuario.getCodigoPostal());
		
				usuarioService.editarUsuario(usuarioAntiguo);
				//model.addAttribute("usuario", new Usuario());
				return "redirect:/usuario";
			}else {
				return "error.jsp";
			}
		}
		
//		@RequestMapping("/editar")
//		public String editarUsuario(@RequestParam("id") Long id) {
//
//			Usuario usuario = usuarioService.buscarUsuarioId(id);
//
//			if(usuario !=null) {
//				
//				
//				usuarioService.editarUsuario(usuario);
//			}
//
//			return "redirect:/usuario";
//		}
		/*
		 * //respuesta
		 * 
		 * @RequestMapping("/respuesta") public String registro(Model model) {
		 * 
		 * System.out.println(nombre + " " +apellido + " " +limite +" " + codigoPostal);
		 * 
		 * return "hola2"; }
		 */
		 
}
