package com.reservaonline.controle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.reservaonline.modelos.Quarto;
import com.reservaonline.repositorios.QuartoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QuartoControle {
    private static String caminhoImagens = "/Users/rafae/Pictures/Imagens";

	@Autowired
	private QuartoRepositorio quartoRepositorio;

	@GetMapping("/administrativo/quarto/cadastrar")
	public ModelAndView cadastrar(Quarto quarto) {
		ModelAndView mv = new ModelAndView("administrativo/quarto/cadastro");
		mv.addObject("quarto", quarto);
		return mv;
	}

	@GetMapping("/administrativo/quarto/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/quarto/lista"); 
		mv.addObject("listaQuarto", quartoRepositorio.findAll());
		return mv;
	}

	@GetMapping("/administrativo/quarto/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Quarto> quarto = quartoRepositorio.findById(id);
		return cadastrar(quarto.get());
	}

	@GetMapping("/administrativo/quarto/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Quarto> quarto = quartoRepositorio.findById(id);
		quartoRepositorio.delete(quarto.get());
		return listar();
	}
    @GetMapping("/administrativo/quarto/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("No IF");
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

	@PostMapping("/administrativo/quarto/salvar")
	public ModelAndView salvar(@Validated Quarto quarto, BindingResult result,
			@RequestParam("file") MultipartFile arquivo) {

		if (result.hasErrors()) {
			return cadastrar(quarto);
		}

		quartoRepositorio.saveAndFlush(quarto);

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(quarto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				quarto.setNomeImagem(String.valueOf(quarto.getId()) + arquivo.getOriginalFilename());
				quartoRepositorio.saveAndFlush(quarto);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cadastrar(new Quarto());
	}

}