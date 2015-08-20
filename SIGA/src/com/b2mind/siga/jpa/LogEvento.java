/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "LOG_EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogEvento.findAll", query = "SELECT l FROM LogEvento l"),
    @NamedQuery(name = "LogEvento.findByIdLogEvento", query = "SELECT l FROM LogEvento l WHERE l.idLogEvento = :idLogEvento"),
    @NamedQuery(name = "LogEvento.findByFecha", query = "SELECT l FROM LogEvento l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "LogEvento.findByClase", query = "SELECT l FROM LogEvento l WHERE l.clase = :clase"),
    @NamedQuery(name = "LogEvento.findByUsuario", query = "SELECT l FROM LogEvento l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "LogEvento.findByMensaje", query = "SELECT l FROM LogEvento l WHERE l.mensaje = :mensaje"),
    @NamedQuery(name = "LogEvento.findByMensajeDetallado", query = "SELECT l FROM LogEvento l WHERE l.mensajeDetallado = :mensajeDetallado"),
    @NamedQuery(name = "LogEvento.findBySeveridad", query = "SELECT l FROM LogEvento l WHERE l.severidad = :severidad"),
    @NamedQuery(name = "LogEvento.findByReferencia", query = "SELECT l FROM LogEvento l WHERE l.referencia = :referencia")})
public class LogEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOG_EVENTO")
    private Long idLogEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 250)
    @Column(name = "CLASE")
    private String clase;
    @Size(max = 40)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 1000)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Size(max = 32600)
    @Column(name = "MENSAJE_DETALLADO")
    private String mensajeDetallado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEVERIDAD")
    private String severidad;
    @Size(max = 32600)
    @Column(name = "REFERENCIA")
    private String referencia;

    public LogEvento() {
    }

    public LogEvento(Long idLogEvento) {
        this.idLogEvento = idLogEvento;
    }

    public LogEvento(Long idLogEvento, Date fecha, String severidad) {
        this.idLogEvento = idLogEvento;
        this.fecha = fecha;
        this.severidad = severidad;
    }

    public Long getIdLogEvento() {
        return idLogEvento;
    }

    public void setIdLogEvento(Long idLogEvento) {
        this.idLogEvento = idLogEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeDetallado() {
        return mensajeDetallado;
    }

    public void setMensajeDetallado(String mensajeDetallado) {
        this.mensajeDetallado = mensajeDetallado;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogEvento != null ? idLogEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogEvento)) {
            return false;
        }
        LogEvento other = (LogEvento) object;
        if ((this.idLogEvento == null && other.idLogEvento != null) || (this.idLogEvento != null && !this.idLogEvento.equals(other.idLogEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.LogEvento[ idLogEvento=" + idLogEvento + " ]";
    }
    
}