// package es.uco.pw.data.dao;
// import es.uco.pw.data.dao.ActividadDAO;
// import es.uco.pw.data.dao.CampamentoDAO;
// import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
// import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
// import es.uco.pw.business.campamento.models.actividad.NivelEducativo;

// import java.util.Date;
// import java.time.LocalTime;
// import java.util.ArrayList;

// import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
// import es.uco.pw.data.dao.AsistenteDAO;

// import java.util.ArrayList;
// import java.util.Date;

// public class Main2 {

//     public static void main(String[] args) {
//         // Crear un objeto AsistenteDTO
//         AsistenteDTO asistenteDTO = new AsistenteDTO(1, "Juan", "Perez", new Date(), true);

//         // Crear un objeto AsistenteDAO
//         AsistenteDAO asistenteDAO = new AsistenteDAO();

//         // Insertar el asistente en la base de datos
//         if (asistenteDAO.insert(asistenteDTO)) {
//             System.out.println("Asistente insertado correctamente en la base de datos.");
//         } else {
//             System.out.println("Error al insertar el asistente en la base de datos.");
//         }

//         // Obtener todos los asistentes de la base de datos
//         ArrayList<AsistenteDTO> listaAsistentes = asistenteDAO.getAll();
//         if (listaAsistentes != null) {
//             System.out.println("Lista de asistentes en la base de datos:");
//             for (AsistenteDTO asistente : listaAsistentes) {
//                 System.out.println(asistente);
//             }
//         } else {
//             System.out.println("Error al obtener la lista de asistentes de la base de datos.");
//         }

//         // Actualizar los datos del asistente
//         asistenteDTO.setNombre("Carlos");
//         if (asistenteDAO.update(asistenteDTO)) {
//             System.out.println("Datos del asistente actualizados correctamente en la base de datos.");
//         } else {
//             System.out.println("Error al actualizar los datos del asistente en la base de datos.");
//         }

//         // Obtener un asistente por su identificador
//         AsistenteDTO asistenteObtenido = asistenteDAO.get(asistenteDTO.getIdentificador());
//         if (asistenteObtenido != null) {
//             System.out.println("Asistente obtenido de la base de datos: " + asistenteObtenido);
//         } else {
//             System.out.println("Error al obtener el asistente de la base de datos.");
//         }

//         // Eliminar el asistente de la base de datos
//         if (asistenteDAO.delete(asistenteDTO.getIdentificador())) {
//             System.out.println("Asistente eliminado correctamente de la base de datos.");
//         } else {
//             System.out.println("Error al eliminar el asistente de la base de datos.");
//         }
//     }
// }
