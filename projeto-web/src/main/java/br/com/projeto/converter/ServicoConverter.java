package br.com.projeto.converter;

import br.com.projeto.model.Servico;
import br.com.projeto.service.ServicoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "servicoConverter", managed = true)
public class ServicoConverter implements Converter<Servico> {

    @Inject
    private ServicoService service;

    @Override
    public Servico getAsObject(
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
            Servico value) {

        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
