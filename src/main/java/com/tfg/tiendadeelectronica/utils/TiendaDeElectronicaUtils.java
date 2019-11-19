package com.tfg.tiendadeelectronica.utils;

public class TiendaDeElectronicaUtils {

	public static Double obtenerPrecioConIVA(double precio) {
		return precio != 0.0 ? precio + (precio * (Constantes.IVA / 100)) : 0.0;
	}
}
