/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.MenuConsumible;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Pelicula;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Sucursal;

/**
 *
 * @author CF14010
 */
@Stateless
public class UtilitiesFacade implements Serializable {


@PersistenceContext(unitName = "cinePU")
public javax.persistence.EntityManager em;
  /**
   * Metodo que recibe dos parametro para
   * filtra una pelicula por clasificacion y genero
   * @param clasificacion
   * @param genero
   * @return Retorna una lista filtrada de 
   * peliculas por clasificacion y genero
   * @throws Exception 
   */
    public List<Pelicula> tipoPelicula(String clasificacion, String genero) throws Exception {
        try {
         if (em != null && clasificacion != null && genero != null) {
          Query query = em.createQuery("SELECT p.idPelicula,p.titulo,p.idClasificacion.clasificacion,gl.nombre FROM Pelicula p "
                  + "JOIN p.generoList gl WHERE p.idClasificacion.clasificacion= :clasificacion AND gl.nombre= :genero");
                query.setParameter("genero", genero);
                query.setParameter("clasificacion", clasificacion);
                return query.getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(UtilitiesFacade.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    
    /**
     * Metodo que filtra el Menu de comestibles
     * con algun tipo de descuento
     * @param tipoDescuento
     * @return Retorna una lista
     * @throws Exception 
     */
    public List<MenuConsumible> productoDescuento(String tipoDescuento) throws Exception{
       try{
           if(em != null && tipoDescuento != null){
        List<MenuConsumible> menu = em.createQuery("SELECT mc.idConsumible,mc.nombre,mc.precio,"
                + "ocl.orden.idDescuento.idTipoDescuento.tipo,ocl.orden.idDescuento.idTipoDescuento.porcentajeSugerido "
                + "FROM MenuConsumible mc JOIN mc.ordenConsumibleList ocl "
                + "ON ocl.orden.idDescuento.idTipoDescuento.tipo="+tipoDescuento).getResultList();
                return menu;
           } else {
            throw new NullPointerException("La lista esta vacia");
        }
       }catch(Exception e){
           Logger.getLogger(UtilitiesFacade.class.getName()).log(Level.SEVERE, e.getMessage(), e);
       }
    return Collections.emptyList();
    }
 
    /**
     * 
     * @param tipoProyeccion
     * @return 
     */
    public List<Sucursal> sucursalSala(String tipoProyeccion){
     try {
         if (em != null && tipoProyeccion != null ) {
          Query q = em.createQuery("SELECT su.idSucursal,su.nombre,su.direccion,su.contacto,"
                  + "c.caracteristicaSala.caracteristica,c.caracteristicaSala.descripcion "
                  + "FROM Sucursal su "
                  + "JOIN su.salaList sl JOIN sl.atributoSalaList c "
                  + "ON c.caracteristicaSala.caracteristica= :tipoProyeccion");
                q.setParameter("tipoPreyeccion", tipoProyeccion);
                return q.getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(UtilitiesFacade.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Collections.emptyList();
    }
}