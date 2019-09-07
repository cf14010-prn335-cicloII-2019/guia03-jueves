/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.management.Query;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.MenuConsumible;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Pelicula;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Sucursal;

/**
 *
 * @author CF14010
 */
@ExtendWith(MockitoExtension.class)
public class UtilitiesFacadeTest {
    
    public UtilitiesFacadeTest() {
    }
    
    @Inject
    UtilitiesFacade uf;
    List<Pelicula> esperadoPelicula;
    List<MenuConsumible> esperadoConsumible;
    Pelicula p;
    MenuConsumible mc;
    
    @BeforeAll
    public static void setUpClass() {
        
    }

    @AfterAll
    public static void tearDownClass() {
        
    }

    @BeforeEach
    public void setUp() {
        uf=Mockito.mock(UtilitiesFacade.class);
        esperadoPelicula= new ArrayList<>();
        esperadoConsumible= new ArrayList<>();
        p = new Pelicula(1);
        esperadoPelicula.add(p);
        mc= new MenuConsumible(1);
        esperadoConsumible.add(mc);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of tipoPelicula method, of class UtilitiesFacade.
     * @throws java.lang.Exception
     */
    @Test
    public void tipoPeliculaTest() throws Exception {
        System.out.println("tipoPelicula");
        List<Pelicula> list = new ArrayList<>();
        Pelicula pe = new Pelicula(1);
        list.add(pe);
        Mockito.when(uf.tipoPelicula(Mockito.any(), Mockito.any())).thenReturn(list);
        assertEquals(esperadoPelicula, uf.tipoPelicula(Mockito.any(), Mockito.any()));
        
    }

    /**
     * Test of productoDescuento method, of class UtilitiesFacade.
     * @throws java.lang.Exception
     */
    @Test
    public void productoDescuentoTest() throws Exception {
        System.out.println("productoDescuento");
        List<MenuConsumible> list= new ArrayList<>();
        MenuConsumible mec = new MenuConsumible(1);
        list.add(mec);
        Mockito.when(uf.productoDescuento(Mockito.any())).thenReturn(list);
        assertEquals(esperadoConsumible, uf.productoDescuento(Mockito.any()));
        
    }
    
    
    @Test
    public void sucursalSalaTest()throws Exception{
        System.out.println("Consulta Metodo Tipo Proyeccion");
        List<Sucursal> listaTipoProyeccion = new ArrayList<>();
        EntityManager MockEm = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        List<Sucursal> MocklistaTipoProyeccion;
        MocklistaTipoProyeccion = Mockito.mock(List.class);
        uf.em = MockEm;
        Mockito.when(MockEm.createQuery("SELECT e.idSucursal,e.nombre,e.direccion,e.contacto,b.caracteristicaSala.caracteristica,"
                + "b.caracteristicaSala.descripcion FROM Sucursal e JOIN e.salaList a JOIN a.atributoSalaList b "
                + "WHERE b.caracteristicaSala.caracteristica= :tipoProyeccion")).thenReturn((javax.persistence.Query) query);
        Mockito.when(Mockito.any()).thenReturn(listaTipoProyeccion);
        List<Sucursal> expResult = listaTipoProyeccion;
        List<Sucursal> result = uf.sucursalSala("4D");
        assertEquals(expResult, result);
    }


}