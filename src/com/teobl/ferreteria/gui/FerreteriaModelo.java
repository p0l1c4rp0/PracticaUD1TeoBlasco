package com.teobl.ferreteria.gui;

import com.teobl.ferreteria.base.ArticuloFerreteria;
import com.teobl.ferreteria.base.HerramientaElectrica;
import com.teobl.ferreteria.base.HerramientaManual;
import com.teobl.ferreteria.base.Tornillos;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FerreteriaModelo {
    public ArrayList<ArticuloFerreteria> listaArticulos;

    public FerreteriaModelo() {
        listaArticulos = new ArrayList<>();
    }

    public ArrayList<ArticuloFerreteria> obtenerArticulos() {
        return listaArticulos;
    }

    public void altaHerramientaElectrica(String codigo, String modelo, String marca,
                                         LocalDate fechaCompra, double precio,
                                         int potencia, int numBaterias) {
        HerramientaElectrica herramienta = new HerramientaElectrica(codigo, modelo, marca, fechaCompra, precio, potencia, numBaterias);
        listaArticulos.add(herramienta);
    }

    public void altaHerramientaManual(String codigo, String modelo, String marca,
                                      double precio, LocalDate fechaCompra, double peso) {
        HerramientaManual herramienta = new HerramientaManual(codigo, modelo, marca, precio, fechaCompra, peso);
        listaArticulos.add(herramienta);
    }

    public void altaTornillos(String codigo, String modelo, String marca,
                              LocalDate fechaCompra, double precio,
                              int cantidadPorCaja, int tamanyo) {
        Tornillos tornillos = new Tornillos(codigo, modelo, marca, fechaCompra, precio, cantidadPorCaja, tamanyo);
        listaArticulos.add(tornillos);
    }

    public boolean existeCodigo(String codigo) {
        for (ArticuloFerreteria articulo : listaArticulos) {
            if (articulo.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("ArticulosFerreteria");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoArticulo = null;
        Element nodoDatos = null;

        for (ArticuloFerreteria articulo : listaArticulos) {
            if (articulo instanceof HerramientaElectrica) {
                nodoArticulo = documento.createElement("HerramientaElectrica");
            } else if (articulo instanceof HerramientaManual) {
                nodoArticulo = documento.createElement("HerramientaManual");
            } else if (articulo instanceof Tornillos) {
                nodoArticulo = documento.createElement("Tornillos");
            }
            raiz.appendChild(nodoArticulo);

            nodoDatos = documento.createElement("codigo");
            nodoArticulo.appendChild(nodoDatos);
            nodoDatos.appendChild(documento.createTextNode(articulo.getCodigo()));

            nodoDatos = documento.createElement("modelo");
            nodoArticulo.appendChild(nodoDatos);
            nodoDatos.appendChild(documento.createTextNode(articulo.getModelo()));

            nodoDatos = documento.createElement("marca");
            nodoArticulo.appendChild(nodoDatos);
            nodoDatos.appendChild(documento.createTextNode(articulo.getMarca()));

            nodoDatos = documento.createElement("fechaCompra");
            nodoArticulo.appendChild(nodoDatos);
            nodoDatos.appendChild(documento.createTextNode(articulo.getFechaCompra().toString()));

            nodoDatos = documento.createElement("precio");
            nodoArticulo.appendChild(nodoDatos);
            nodoDatos.appendChild(documento.createTextNode(String.valueOf(articulo.getPrecio())));

            if (articulo instanceof HerramientaElectrica) {
                HerramientaElectrica he = (HerramientaElectrica) articulo;
                nodoDatos = documento.createElement("potencia");
                nodoArticulo.appendChild(nodoDatos);
                nodoDatos.appendChild(documento.createTextNode(String.valueOf(he.getPotencia())));

                nodoDatos = documento.createElement("numBaterias");
                nodoArticulo.appendChild(nodoDatos);
                nodoDatos.appendChild(documento.createTextNode(String.valueOf(he.getNumBaterias())));
            } else if (articulo instanceof HerramientaManual) {
                HerramientaManual hm = (HerramientaManual) articulo;
                nodoDatos = documento.createElement("peso");
                nodoArticulo.appendChild(nodoDatos);
                nodoDatos.appendChild(documento.createTextNode(String.valueOf(hm.getPeso())));
            } else if (articulo instanceof Tornillos) {
                Tornillos t = (Tornillos) articulo;
                nodoDatos = documento.createElement("cantidadPorCaja");
                nodoArticulo.appendChild(nodoDatos);
                nodoDatos.appendChild(documento.createTextNode(String.valueOf(t.getCantidadPorCaja())));

                nodoDatos = documento.createElement("tamanyo");
                nodoArticulo.appendChild(nodoDatos);
                nodoDatos.appendChild(documento.createTextNode(String.valueOf(t.getTamanyo())));
            }
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaArticulos = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodo = (Element) listaElementos.item(i);
            String tag = nodo.getTagName();

            if (tag.equals("HerramientaElectrica")) {
                HerramientaElectrica he = new HerramientaElectrica();
                he.setCodigo(nodo.getChildNodes().item(0).getTextContent());
                he.setModelo(nodo.getChildNodes().item(1).getTextContent());
                he.setMarca(nodo.getChildNodes().item(2).getTextContent());
                he.setFechaCompra(LocalDate.parse(nodo.getChildNodes().item(3).getTextContent()));
                he.setPrecio(Double.parseDouble(nodo.getChildNodes().item(4).getTextContent()));
                he.setPotencia(Integer.parseInt(nodo.getChildNodes().item(5).getTextContent()));
                he.setNumBaterias(Integer.parseInt(nodo.getChildNodes().item(6).getTextContent()));
                listaArticulos.add(he);
            } else if (tag.equals("HerramientaManual")) {
                HerramientaManual hm = new HerramientaManual();
                hm.setCodigo(nodo.getChildNodes().item(0).getTextContent());
                hm.setModelo(nodo.getChildNodes().item(1).getTextContent());
                hm.setMarca(nodo.getChildNodes().item(2).getTextContent());
                hm.setFechaCompra(LocalDate.parse(nodo.getChildNodes().item(3).getTextContent()));
                hm.setPrecio(Double.parseDouble(nodo.getChildNodes().item(4).getTextContent()));
                hm.setPeso(Double.parseDouble(nodo.getChildNodes().item(5).getTextContent()));
                listaArticulos.add(hm);
            } else if (tag.equals("Tornillos")) {
                Tornillos t = new Tornillos();
                t.setCodigo(nodo.getChildNodes().item(0).getTextContent());
                t.setModelo(nodo.getChildNodes().item(1).getTextContent());
                t.setMarca(nodo.getChildNodes().item(2).getTextContent());
                t.setFechaCompra(LocalDate.parse(nodo.getChildNodes().item(3).getTextContent()));
                t.setPrecio(Double.parseDouble(nodo.getChildNodes().item(4).getTextContent()));
                t.setCantidadPorCaja(Integer.parseInt(nodo.getChildNodes().item(5).getTextContent()));
                t.setTamanyo(Integer.parseInt(nodo.getChildNodes().item(6).getTextContent()));
                listaArticulos.add(t);
            }
        }
    }
}
