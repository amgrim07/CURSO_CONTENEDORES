package co.com.usb.anuncios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.usb.anuncios.domain.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
