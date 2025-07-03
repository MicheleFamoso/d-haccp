package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.CalendarioDto;
import it.epicode.d.haccp.model.Fornitura;
import it.epicode.d.haccp.model.Infestanti;

import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarioService {

    @Autowired
    private TemperaturaGiornalieraService temperaturaService;

    @Autowired
    private InfestantiService infestantiService;

    @Autowired
    private FornituraService fornituraService;

    public List<CalendarioDto> getControlliPerCalendario(LocalDate start, LocalDate end, int aziendaId) {
        List<CalendarioDto> calendario = new ArrayList<>();

        List<TemperaturaGiornaliera> temperature = temperaturaService.findByDate(start, end, aziendaId);
        for (TemperaturaGiornaliera t : temperature) {
            calendario.add(new CalendarioDto(
                    t.getId(),
                    t.getData(),
                    "Temperatura",
                    "Frigo " + t.getFrigo(),
                    String.valueOf(t.getTemperatura()),
                    t.getConformita().name()
            ));
        }

        List<Infestanti> infestanti = infestantiService.findInfestantiByDate(start, end, aziendaId);
        for (Infestanti i : infestanti) {
            calendario.add(new CalendarioDto(
                    i.getId(),
                    i.getData(),
                    "Infestanti",
                    "Controllo roditori/insetti",
                    null,
                    (i.getRoditori() != null) ? i.getRoditori().name() : null
            ));
        }

        List<Fornitura> forniture = fornituraService.findByDataBetween(start, end, aziendaId);
        for (Fornitura f : forniture) {
            calendario.add(new CalendarioDto(
                    f.getId(),
                    f.getData(),
                    "Fornitura",
                    f.getProdotto(),
                    f.getLotto(),
                    f.getConformita().name()
            ));
        }

        return calendario;
    }
}
