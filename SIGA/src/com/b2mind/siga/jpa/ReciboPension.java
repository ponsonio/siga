/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "RECIBO_PENSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboPension.findAll", query = "SELECT r FROM ReciboPension r"),
    @NamedQuery(name = "ReciboPension.findByIdReciboPension", query = "SELECT r FROM ReciboPension r WHERE r.idReciboPension = :idReciboPension"),
    @NamedQuery(name = "ReciboPension.findByFechaEmision", query = "SELECT r FROM ReciboPension r WHERE r.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "ReciboPension.findByFechaMaximaPago", query = "SELECT r FROM ReciboPension r WHERE r.fechaMaximaPago = :fechaMaximaPago"),
    @NamedQuery(name = "ReciboPension.findByFechaPago", query = "SELECT r FROM ReciboPension r WHERE r.fechaPago = :fechaPago"),
    @NamedQuery(name = "ReciboPension.findByIdColegio", query = "SELECT r FROM ReciboPension r WHERE r.idColegio = :idColegio"),
    @NamedQuery(name = "ReciboPension.findByMora", query = "SELECT r FROM ReciboPension r WHERE r.mora = :mora"),
    @NamedQuery(name = "ReciboPension.findByMonto", query = "SELECT r FROM ReciboPension r WHERE r.monto = :monto"),
    @NamedQuery(name = "ReciboPension.findByIdBanco", query = "SELECT r FROM ReciboPension r WHERE r.idBanco = :idBanco")})
public class ReciboPension implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RECIBO_PENSION")
    private Long idReciboPension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MAXIMA_PAGO")
    @Temporal(TemporalType.DATE)
    private Date fechaMaximaPago;
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLEGIO")
    private long idColegio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA")
    private BigDecimal mora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BANCO")
    private short idBanco;
    @JoinColumn(name = "ID_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID_MONEDA")
    @ManyToOne(optional = false)
    private Moneda idMoneda;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Alumno idAlumno;

    public ReciboPension() {
    }

    public ReciboPension(Long idReciboPension) {
        this.idReciboPension = idReciboPension;
    }

    public ReciboPension(Long idReciboPension, Date fechaEmision, Date fechaMaximaPago, long idColegio, BigDecimal mora, BigDecimal monto, short idBanco) {
        this.idReciboPension = idReciboPension;
        this.fechaEmision = fechaEmision;
        this.fechaMaximaPago = fechaMaximaPago;
        this.idColegio = idColegio;
        this.mora = mora;
        this.monto = monto;
        this.idBanco = idBanco;
    }

    public Long getIdReciboPension() {
        return idReciboPension;
    }

    public void setIdReciboPension(Long idReciboPension) {
        this.idReciboPension = idReciboPension;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    public void setFechaMaximaPago(Date fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(long idColegio) {
        this.idColegio = idColegio;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public short getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(short idBanco) {
        this.idBanco = idBanco;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Moneda getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReciboPension != null ? idReciboPension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReciboPension)) {
            return false;
        }
        ReciboPension other = (ReciboPension) object;
        if ((this.idReciboPension == null && other.idReciboPension != null) || (this.idReciboPension != null && !this.idReciboPension.equals(other.idReciboPension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.ReciboPension[ idReciboPension=" + idReciboPension + " ]";
    }
    
}
