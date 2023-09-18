package imobiliaria.Service;

import imobiliaria.Entity.BairroEntity;
import imobiliaria.Entity.EstadoEntity;
import imobiliaria.Entity.MunicipioEntity;
import imobiliaria.Model.Bairro;
import imobiliaria.Model.Estado;
import imobiliaria.Model.Municipio;
import imobiliaria.Repository.BairroRepository;
import imobiliaria.Repository.EstadoRepository;
import imobiliaria.Repository.MunicipioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocalidadeService {


    @Autowired
    private EstadoRepository estRep;

    @Autowired
    private MunicipioRepository munRep;

    @Autowired
    private BairroRepository baiRep;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Estado> findAllEstados() {
        List<EstadoEntity> estados = estRep.findAll();
        return estados.stream().map(entity -> modelMapper.map(entity, Estado.class)).collect(Collectors.toList());
    }

    public Estado findEstadoById(Long id) {
        EstadoEntity estado = estRep.findById(id).get();
        return modelMapper.map(estado, Estado.class);
    }

    public List<Estado> findEstadoByNome(String nome) {
        List<EstadoEntity> estados = estRep.findByNomeContainingIgnoreCase(nome);
        return estados.stream().map(entity -> modelMapper.map(entity, Estado.class)).collect(Collectors.toList());
    }

    public Estado findEstadoByMunicipio(Municipio municipio) {
        Estado est = null;
        List<Estado> estados = findAllEstados();
        for (Estado estado : estados) {
            if(estado.getId() == municipio.getEstado().getId()) {
                est = estado;
            }
        }
        return est;
    }

    public Estado saveEstado(Estado estado) {
        EstadoEntity estadoEntity = modelMapper.map(estado, EstadoEntity.class);
        EstadoEntity saveCategoria = estRep.save(estadoEntity);
        return modelMapper.map(saveCategoria, Estado.class);
    }

    public void excluirEstadoById(Long id) {
        estRep.deleteById(id);
    }

    public List<Municipio> findAllMunicipios() {
        List<MunicipioEntity> municipios = munRep.findAll();
        return municipios.stream().map(entity -> modelMapper.map(entity, Municipio.class)).collect(Collectors.toList());
    }

    public Municipio findMunicipioById(Long id) {
        MunicipioEntity municipio = munRep.findById(id).get();
        return modelMapper.map(municipio, Municipio.class);
    }

    public List<Municipio> findMunicipioByNome(String nome) {
        List<MunicipioEntity> municipios = munRep.findByNomeContainingIgnoreCase(nome);
        return municipios.stream().map(entity -> modelMapper.map(entity, Municipio.class)).collect(Collectors.toList());
    }

    public Municipio findMunicipioByBairro(Bairro bairro) {
        MunicipioEntity municipioEntity = munRep.findByBairros(bairro).get(0);
        return modelMapper.map(municipioEntity, Municipio.class);
    }

    public Municipio saveMunicipio(Municipio municipio) {
        MunicipioEntity municipioEntity = modelMapper.map(municipio, MunicipioEntity.class);
        MunicipioEntity saveCategoria = munRep.save(municipioEntity);
        return modelMapper.map(saveCategoria, Municipio.class);
    }

    public void excluirMunicipioById(Long id) {
        munRep.deleteById(id);
    }

    public List<Bairro> findAllBairros() {
        List<BairroEntity> bairros = baiRep.findAll();
        return bairros.stream().map(entity -> modelMapper.map(entity, Bairro.class)).collect(Collectors.toList());
    }

    public Bairro findBairroById(Long id) {
        BairroEntity bairro = baiRep.findById(id).get();
        return modelMapper.map(bairro, Bairro.class);
    }

    public List<Bairro> findBairroByNome(String nome) {
        List<BairroEntity> bairros = baiRep.findByNomeContainingIgnoreCase(nome);
        return bairros.stream().map(entity -> modelMapper.map(entity, Bairro.class)).collect(Collectors.toList());
    }

    public Bairro saveBairro(Bairro bairro) {
        BairroEntity bairroEntity = modelMapper.map(bairro, BairroEntity.class);
        BairroEntity saveCategoria = baiRep.save(bairroEntity);
        return modelMapper.map(saveCategoria, Bairro.class);
    }

    public void excluirBairroById(Long id) {
        baiRep.deleteById(id);
    }

    public List<Municipio> findMunicipioPerEstado(Long idEstado) {
        Estado estado = findEstadoById(idEstado);
        List<MunicipioEntity> municipios = munRep.findByEstado(estado);
        return municipios.stream().map(entity -> modelMapper.map(entity, Municipio.class)).collect(Collectors.toList());
    }

    public Map<Long, String> findMunicipioAsMapPerEstado(Long idEstado) {
        Map<Long, String> response = new HashMap<Long, String>();
        List<Municipio> municipios = findMunicipioPerEstado(idEstado);
        for (Municipio municipio : municipios) {
            response.put(municipio.getId(), municipio.getNome());
        }
        return response;
    }

    public Map<Long, String> findBairroAsMapPerMunicipio(Long idMunicipio) {
        Map<Long, String> response = new HashMap<Long, String>();
        List<Bairro> bairros = findMunicipioById(idMunicipio).getBairros();
        for (Bairro bairro : bairros) {
            response.put(bairro.getId(), bairro.getNome());
        }
        return response;
    }

}
