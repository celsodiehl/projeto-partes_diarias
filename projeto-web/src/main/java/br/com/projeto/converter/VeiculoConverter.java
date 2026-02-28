package br.com.projeto.converter;

import br.com.projeto.model.Veiculo;
import br.com.projeto.service.VeiculoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "veiculoConverter", managed = true)
public class VeiculoConverter implements Converter<Veiculo> {

    @Inject
    private VeiculoService service;

    @Override
    public Veiculo getAsObject(
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
            Veiculo value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
