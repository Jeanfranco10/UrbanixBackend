package com.cibertec.dto;

import com.cibertec.enums.Modelos_enum;
import com.cibertec.model.AreaMunicipal;
import com.cibertec.model.Caso;
import lombok.Getter;

import java.util.List;

@Getter
public class AreaResumenDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String[] distritos;
    private Boolean activo;
    private Object responsable;
    private List<Caso> casos;
    private long totalCasos;
    private long casosActivos;

    public AreaResumenDTO(AreaMunicipal area, List<Caso> casos) {
        this.id = area.getId();
        this.nombre = area.getNombre();
        this.descripcion = area.getDescripcion();
        this.distritos = area.getDistritos();
        this.activo = area.getActivo();
        this.responsable = area.getResponsable();
        this.casos = casos;
        this.totalCasos = casos.size();
        this.casosActivos = casos.stream()
                .filter(c -> c.getEstado() != Modelos_enum.EstadoIncidencia.resuelto
                        && c.getEstado() != Modelos_enum.EstadoIncidencia.cerrado
                        && c.getEstado() != Modelos_enum.EstadoIncidencia.rechazado)
                .count();
    }
}
