// package es.uco.pw.business.inscripcion.handler;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.concurrent.TimeUnit;

// import es.uco.pw.business.campamento.models.actividad.Actividad;
// import es.uco.pw.business.campamento.models.actividad.Horario;
// import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
// import es.uco.pw.business.campamento.models.campamento.Campamento;
// import es.uco.pw.business.campamento.models.monitor.Monitor;
// import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
// import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
// import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
// import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
// import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
// import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;

// public class main {

    // public Boolean getEsTemprana(Date fechaInscripcion, Date fechaInicioCampamento) {
    //     long diffInMillies = Math.abs(fechaInicioCampamento.getTime() - fechaInscripcion.getTime());
    //     long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    //     return diff > 15;
    // }

//     public Inscripcion getInscripcion(Integer asistenteId, Integer campamentoId) {
//         // public InscripcionDTO(int asistenteId, int campamentoId, Date
//         // fechaInscripcion, double precio,
//         // String tipoInscripcion) {
//         // this.asistenteId = asistenteId;
//         // this.campamentoId = campamentoId;
//         // this.fechaInscripcion = fechaInscripcion;
//         // this.precio = precio;
//         // this.tipoInscripcion = tipoInscripcion;
//         // }

//         InscripcionDTO inscripcionDTO = new InscripcionDTO(asistenteId, campamentoId, new Date(), 200, TipoInscripcion.COMPLETA);

//         System.out.println("aqui aqui \n \n");
//         System.out.println(inscripcionDTO.getAsistenteId() + " " + inscripcionDTO.getCampamentoId() + " " +
//                 inscripcionDTO.getFechaInscripcion() + " " + inscripcionDTO.getPrecio() + " "
//                 + inscripcionDTO.getTipoInscripcion());

//         Boolean esTemprana = getEsTemprana(inscripcionDTO.getFechaInscripcion(), new Date());
//         System.out.println("es temprana: " + esTemprana);
//         InscripcionCreator creator;

//         if (esTemprana) {
//             System.out.println("he entrado aqui");
//             creator = new InscripcionTemprana();
//         } else {
//             System.out.println("he entrado alli");

//             creator = new InscripcionTardia();
//         }

//         Inscripcion inscripcion;

//         if (inscripcionDTO.getTipoInscripcion() == TipoInscripcion.COMPLETA) {

//             System.out.println("\n\n\n3\n\n\n");
//             inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
//                     inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

//             System.out.println("holaholaaaaaa" +inscripcion.getId_Campamento() + " " + inscripcion.getId_Participante() + " "
//                     + inscripcion.getFechaInscripcion() + " " + inscripcion.getPrecio() + " "
//                     + inscripcion.getTipoInscripcion()+ "\n\n\n");

//         } else {

//             inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
//                     inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

//         }

//         return inscripcion;
//     }

//     public static void main(String[] args) {
//         main m = new main();

//         // Crea un campamento e insertale actividades
//         // Crear un nuevo campamento
//         Campamento campamento = new Campamento(1, new Date(), new Date(), NivelEducativo.INFANTIL, 100);
        
// 	// public Monitor(int id, String n, String a, Boolean e) {
// 	// 	this.identificador = id;
// 	// 	this.nombre = n;
// 	// 	this.apellidos = a;
// 	// 	this.esEducador = e;
// 	// }

//         Monitor monitor1 = new Monitor(1, "Juan", "Perez", false);
//         Monitor monitor2 = new Monitor(2, "Maria", "Garcia", false);
//         ArrayList<Monitor> monitores = new ArrayList<Monitor>();
//         monitores.add(monitor1);
//         monitores.add(monitor2);
//         campamento.setMonitoresResponsables(monitores);
//         // Crear actividades y asociarlas al campamento
//         Actividad actividad1 = new Actividad("Senderismo", NivelEducativo.INFANTIL, Horario.MAÑANA, 30, 3);
//         Actividad actividad2 = new Actividad("Taller de arte", NivelEducativo.INFANTIL, Horario.TARDE, 25, 2);

//         // Asociar las actividades al campamento
//         campamento.asociarActividad(actividad1);
//         campamento.asociarActividad(actividad2);

//         // Verificar las actividades asociadas al campamento
//         for (Actividad actividad : campamento.getActividades()) {
//             System.out.println("Actividad asociada: " + actividad.getNombreActividad());
//         }

//         Inscripcion inscripcion = m.getInscripcion(1, 1);
//         System.out.println(inscripcion.toString());
//     }
// }
