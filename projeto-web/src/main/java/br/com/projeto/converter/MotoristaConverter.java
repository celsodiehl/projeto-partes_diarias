package br.com.projeto.converter;

import br.com.projeto.model.Motorista;
import br.com.projeto.service.MotoristaService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "motoristaConverter", managed = true)
public class MotoristaConverter implements Converter<Motorista> {

    @Inject
    private MotoristaService service;

    @Override
    public Motorista getAsObject(
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
            Motorista value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
