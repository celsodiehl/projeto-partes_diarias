package br.com.projeto.converter;

import br.com.projeto.model.Contratado;
import br.com.projeto.service.ContratadoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "contratadoConverter", managed = true)
public class ContratadoConverter implements Converter<Contratado> {

    @Inject
    private ContratadoService service;

    @Override
    public Contratado getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return service.buscarPorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Contratado value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
