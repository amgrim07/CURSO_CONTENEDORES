package co.com.usb.anuncios.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.com.usb.anuncios.domain.Empresa;
import co.com.usb.anuncios.dto.EmpresaDTO;

@Mapper
public interface EmpresaMapper {
	
    public EmpresaDTO empresaToempresaDTO(Empresa empresa) throws Exception;

    public Empresa empresaDTOToempresa(EmpresaDTO empresaDTO)
        throws Exception;

    public List<EmpresaDTO> listempresaToListempresaDTO(List<Empresa> empresas)
        throws Exception;

    public List<Empresa> listempresaDTOToListempresa(List<EmpresaDTO> empresaDTOs)
        throws Exception;

}
