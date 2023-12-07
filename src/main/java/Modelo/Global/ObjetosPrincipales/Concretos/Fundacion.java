package Modelo.Global.ObjetosPrincipales.Concretos;

import Modelo.Global.Constantes.Klondlike;
import Modelo.Global.Interfaces.AgregarCartas.ReceptorDeCartas;
import Modelo.Global.Interfaces.Validaciones.ValidacionAFundacion;
import Modelo.Global.ObjetosPrincipales.Universales.Carta;

import java.io.Serializable;
import java.util.ArrayList;


public class Fundacion implements Serializable, ReceptorDeCartas {
    private final ArrayList<ArrayList<Carta>> fundaciones;
    private final ValidacionAFundacion validacion;

    public Fundacion(ValidacionAFundacion validacion) {
        this.fundaciones = new ArrayList<>();
        for (int i = 0; i < Klondlike.FUNDACIONES; i++) {
            fundaciones.add(new ArrayList<>());
        }
        this.validacion = validacion;
    }

    public Carta sacarCartas(int posFund) {
        ArrayList<Carta> fund = this.fundaciones.get(posFund);
        if (fund.isEmpty()) {
            return null;
        }
        return fund.remove(fund.size() - 1);
    }

    public boolean agregarCarta(Carta cartaNueva, int posFund) {
        ArrayList<Carta> fundDest = this.fundaciones.get(posFund);
        Carta cartaFund = this.verPrimerCartaFundacion(posFund);
        if (validacion.validarCartaParaFundacion(cartaNueva, cartaFund)) {
            fundDest.add(cartaNueva);
            cartaNueva.ConvertirAFundacion();
            cartaNueva.establecerColumna(posFund);
            cartaNueva.establecerPos(this.fundaciones.get(posFund).size());
            return true;
        }
        return false;
    }

    public Carta verPrimerCartaFundacion(int posFund) {
        ArrayList<Carta> fund = this.fundaciones.get(posFund);
        if (fund.isEmpty()) {
            return null;
        }
        return fund.get(fund.size() - 1);
    }

    public boolean moverEntreFundaciones(int posFundOrig, int posFundDest) {
        Carta cartaOrig = this.verPrimerCartaFundacion(posFundOrig);
        Carta cartaDest = this.verPrimerCartaFundacion(posFundDest);
        if (validacion.validarCartaParaFundacion(cartaOrig, cartaDest)) {
            this.fundaciones.get(posFundDest).add(cartaOrig);
            this.sacarCartas(posFundOrig);
            cartaOrig.establecerColumna(posFundDest);
            return true;
        }
        return false;
    }

    public int cartasTotales() {
        int numero = 0;
        for (ArrayList<Carta> pila : this.fundaciones) {
            numero += pila.size();
        }
        return numero;
    }

    public int cantCartas(int posFund) {
        return this.fundaciones.get(posFund).size();
    }

    public ArrayList<ArrayList<Carta>> obtenerFundaciones() {
        return this.fundaciones;
    }

    public void prepararFundacionesEspecificas(ArrayList<ArrayList<Carta>> fundacion) {
        this.fundaciones.clear();
        this.fundaciones.addAll(fundacion);
    }

}

