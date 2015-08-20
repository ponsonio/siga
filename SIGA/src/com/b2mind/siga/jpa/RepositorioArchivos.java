/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "REPOSITORIO_ARCHIVOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepositorioArchivos.findAll", query = "SELECT r FROM RepositorioArchivos r"),
    @NamedQuery(name = "RepositorioArchivos.findByIdRepositorio", query = "SELECT r FROM RepositorioArchivos r WHERE r.idRepositorio = :idRepositorio"),
    @NamedQuery(name = "RepositorioArchivos.findByUrl", query = "SELECT r FROM RepositorioArchivos r WHERE r.url = :url")})
public class RepositorioArchivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REPOSITORIO")
    private Integer idRepositorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "URL")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRepositorio")
    private Collection<FotoPersona> fotoPersonaCollection;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRepositorio")
    private Collection<Materiales> materialesCollection;

    public RepositorioArchivos() {
    }

    public RepositorioArchivos(Integer idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public RepositorioArchivos(Integer idRepositorio, String url) {
        this.idRepositorio = idRepositorio;
        this.url = url;
    }

    public Integer getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(Integer idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<FotoPersona> getFotoPersonaCollection() {
        return fotoPersonaCollection;
    }

    public void setFotoPersonaCollection(Collection<FotoPersona> fotoPersonaCollection) {
        this.fotoPersonaCollection = fotoPersonaCollection;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @XmlTransient
    public Collection<Materiales> getMaterialesCollection() {
        return materialesCollection;
    }

    public void setMaterialesCollection(Collection<Materiales> materialesCollection) {
        this.materialesCollection = materialesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepositorio != null ? idRepositorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepositorioArchivos)) {
            return false;
        }
        RepositorioArchivos other = (RepositorioArchivos) object;
        if ((this.idRepositorio == null && other.idRepositorio != null) || (this.idRepositorio != null && !this.idRepositorio.equals(other.idRepositorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.RepositorioArchivos[ idRepositorio=" + idRepositorio + " ]";
    }
    
}
