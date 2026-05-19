package com.cibertec.enums;


import com.fasterxml.jackson.annotation.JsonCreator;

public class Modelos_enum {
	
	// Mapeo de los datos tipo EMUM
	
	
	public enum RolUsuario { ciudadano, inspector, responsable_area, administrador;
		@JsonCreator
		public static RolUsuario fromValue(String value) {
			for (RolUsuario rol : values()) {
				if (rol.name().equalsIgnoreCase(value)) return rol;
			}
			throw new IllegalArgumentException("Rol desconocido: " + value);
		}
	}

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
