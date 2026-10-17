package co.edu.sena.lims360.model;

import java.time.LocalDateTime;

/**
 * Representa un perfil de personal registrado en LIMS 360 Personal.
 */
public class PerfilPersonal {

    private String id;
    private String userId;
    private String nombreCompleto;
    private String correo;
    private String area;
    private String cargo;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    public PerfilPersonal() {
    }

    public PerfilPersonal(String id, String userId, String nombreCompleto, String correo,
                          String area, String cargo, boolean activo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.userId = userId;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.area = area;
        this.cargo = cargo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "PerfilPersonal{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", area='" + area + '\'' +
                ", cargo='" + cargo + '\'' +
                ", activo=" + activo +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
