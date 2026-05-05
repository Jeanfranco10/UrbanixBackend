package com.cibertec.enums;


public class Modelos_enum {
	
	// Mapeo de los datos tipo EMUM
	
	
	public enum RolUsuario { ciudadano, inspector, responsable_area, administrador }

	public enum EstadoIncidencia { pendiente, en_revision, en_proceso, resuelto, rechazado, cerrado }

	public enum NivelPrioridad { baja, media, alta, critica }
	
	public enum TipoArchivo { foto, video, audio }

	public enum PlataformaMovil { ios }
	
	public enum TipoNotificacion { 
		cambio_estado, caso_acumulativo, asignacion, resolucion, alerta, push 
	}

	public enum EstadoChat { 
		activo, completado, abandonado 
	}

	public enum RemitenteChat { 
		usuario, asistente 
	}

	public enum TipoMensaje { 
		texto, opcion, confirmacion, ubicacion, multimedia 
	}
}
