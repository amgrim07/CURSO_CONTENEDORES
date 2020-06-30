package co.com.usb.anuncios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.usb.anuncios.domain.Empresa;
import co.com.usb.anuncios.dto.EmpresaDTO;
import co.com.usb.anuncios.mapper.EmpresaMapper;
import co.com.usb.anuncios.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaRestController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaRestController.class);
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private EmpresaMapper empresaMapper;

	@GetMapping(value = "/findById/{empId}")
	public ResponseEntity<?> findById(@PathVariable("empId") Long empId) {
		log.debug("Request to findById() Empresa");

		try {
			Empresa empresa = empresaService.findById(empId).get();

			return ResponseEntity.ok().body(empresaMapper.empresaToempresaDTO(empresa));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		log.debug("Request to findAll() Empresa");

		try {
			return ResponseEntity.ok()
					.body(empresaMapper.listempresaToListempresaDTO(
							empresaService.findAll()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody
			EmpresaDTO empresaDTO) {
		log.debug("Request to save empresa: {}", empresaDTO);

		try {
			Empresa empresa = empresaMapper.empresaDTOToempresa(empresaDTO);
			empresa = empresaService.save(empresa);

			return ResponseEntity.ok()
					.body(empresaMapper.empresaToempresaDTO(empresa));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody
			EmpresaDTO empresaDTO) {
		log.debug("Request to update empresa: {}", empresaDTO);

		try {
			Empresa empresa = empresaMapper.empresaDTOToempresa(empresaDTO);
			empresa = empresaService.update(empresa);

			return ResponseEntity.ok()
					.body(empresaMapper.empresaToempresaDTO(empresa));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{clieId}")
	public ResponseEntity<?> delete(@PathVariable("clieId")
	Long clieId) throws Exception {
		log.debug("Request to delete empresa");

		try {
			Empresa empresa = empresaService.findById(clieId).get();

			empresaService.delete(empresa);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(empresaService.count());
	}
}
