package co.com.usb.anuncios.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.usb.anuncios.domain.Empresa;
import co.com.usb.anuncios.repository.EmpresaRepository;

@Scope("singleton")
@Service
public class EmpresaServiceImpl implements EmpresaService{

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	EmpresaRepository empresaRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(Empresa entity) throws Exception{		
		try {
			Set<ConstraintViolation<Empresa>> constraintViolations =validator.validate(entity);
			if(constraintViolations.size()>0){
				StringBuilder strMessage=new StringBuilder();
				for (ConstraintViolation<Empresa> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}
				throw new Exception(strMessage.toString());
			}
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Long count(){
		return empresaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Empresa> findAll(){
		log.debug("finding all Empresa instances");
		return empresaRepository.findAll();
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
	public Empresa save(Empresa entity) throws Exception {
		log.debug("saving Empresa instance");
		try {

			if(entity==null){
				throw new Exception("Empresa no existe");
			}

			validate(entity);	

			if(empresaRepository.findById(entity.getEmpId()).isPresent()){
			}    

			return empresaRepository.save(entity);

		} catch (Exception e) {
			log.error("save Empresa failed", e);
			throw e;
		}
	}


	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void delete(Empresa entity) throws Exception {
		log.debug("deleting Empresa instance");

		if(entity==null){
			throw new Exception("Empresa no existe");
		}

		if(entity.getEmpId()==null){
			throw new Exception("Id ingresado no puede estar vacio");
		}
		
		try {

			empresaRepository.deleteById(entity.getEmpId());
			log.debug("delete Empresa successful");

		} catch (Exception e) {
			log.error("delete Empresa failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void deleteById(Long id) throws Exception {            
		log.debug("deleting Empresa instance");
		if(id==null){
			throw new Exception("Id ingresado no puede estar vacio");
		}
		if(empresaRepository.findById(id).isPresent()){
			delete(empresaRepository.findById(id).get());
		}    
	}	

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public Empresa update(Empresa entity) throws Exception {

		log.debug("updating Empresa instance");

		try {

			if(entity==null){
				throw new Exception("Empresa no existe");
			}

			validate(entity);

			return empresaRepository.save(entity);

		} catch (Exception e) {
			log.error("update Empresa failed", e);
			throw e;		
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Empresa> findById(Long clieId) throws Exception {            
		log.debug("getting Empresa instance");
		return empresaRepository.findById(clieId);
	}

}
