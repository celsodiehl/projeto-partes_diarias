package br.com.projeto.converter;

import br.com.projeto.model.Contratante;
import br.com.projeto.service.ContratanteService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "contratanteConverter", managed = true)
public class ContratanteConverter implements Converter<Contratante> {

    @Inject
    private ContratanteService service;

    @Override
    public Contratante getAsObject(
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
            Contratante value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
