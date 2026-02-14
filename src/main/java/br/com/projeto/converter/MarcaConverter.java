package br.com.projeto.converter;

import br.com.projeto.model.Marca;
import br.com.projeto.service.MarcaService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "marcaConverter", managed = true)
public class MarcaConverter implements Converter<Marca> {

    @Inject
    private MarcaService service;

    @Override
    public Marca getAsObject(
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
            Marca value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
