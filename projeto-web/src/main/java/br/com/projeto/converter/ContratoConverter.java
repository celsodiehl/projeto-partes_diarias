package br.com.projeto.converter;

import br.com.projeto.model.Contrato;
import br.com.projeto.service.ContratoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "contratoConverter", managed = true)
public class ContratoConverter implements Converter<Contrato> {

    @Inject
    private ContratoService service;

    @Override
    public Contrato getAsObject(
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
            Contrato value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
