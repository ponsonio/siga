/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "FOTO_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FotoPersona.findAll", query = "SELECT f FROM FotoPersona f"),
    @NamedQuery(name = "FotoPersona.findByIdFoto", query = "SELECT f FROM FotoPersona f WHERE f.idFoto = :idFoto"),
    @NamedQuery(name = "FotoPersona.findByVigente", query = "SELECT f FROM FotoPersona f WHERE f.vigente = :vigente"),
    @NamedQuery(name = "FotoPersona.findByNombreArchivo", query = "SELECT f FROM FotoPersona f WHERE f.nombreArchivo = :nombreArchivo")})
public class FotoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FOTO")
    private Long idFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private Character vigente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @JoinColumn(name = "ID_REPOSITORIO", referencedColumnName = "ID_REPOSITORIO")
    @ManyToOne(optional = false)
    private RepositorioArchivos idRepositorio;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public FotoPersona() {
    }

    public FotoPersona(Long idFoto) {
        this.idFoto = idFoto;
    }

    public FotoPersona(Long idFoto, Character vigente, String nombreArchivo) {
        this.idFoto = idFoto;
        this.vigente = vigente;
        this.nombreArchivo = nombreArchivo;
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public Character getVigente() {
        return vigente;
    }

    public void setVigente(Character vigente) {
        this.vigente = vigente;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public RepositorioArchivos getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(RepositorioArchivos idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFoto != null ? idFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FotoPersona)) {
            return false;
        }
        FotoPersona other = (FotoPersona) object;
        if ((this.idFoto == null && other.idFoto != null) || (this.idFoto != null && !this.idFoto.equals(other.idFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.FotoPersona[ idFoto=" + idFoto + " ]";
    }
    
}
