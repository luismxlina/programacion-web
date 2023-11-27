-- Insert data into Asistente
INSERT INTO Asistente (Identificador, Nombre, Apellidos, FechaNacimiento, RequiereAtencionEspecial) VALUES
(1, 'John', 'Doe', '2000-01-01', false),
(2, 'Jane', 'Doe', '2002-02-02', true);

-- Insert data into Monitor
INSERT INTO Monitor (Identificador, Nombre, Apellidos, EducadorEspecial) VALUES
(1, 'Alice', 'Smith', true),
(2, 'Bob', 'Johnson', false);

-- Insert data into Actividad
INSERT INTO Actividad (Nombre, NivelEducativo, Horario, NumeroMaximoParticipantes, NumeroMonitoresNecesarios) VALUES
('Football', 'Juvenil', 'Mañana', 20, 2),
('Art', 'Infantil', 'Tarde', 15, 1);

-- Insert data into Campamento
INSERT INTO Campamento (Identificador, FechaInicio, FechaFin, NivelEducativo, NumeroMaximoAsistentes) VALUES
(1, '2022-07-01', '2022-07-31', 'Juvenil', 100),
(2, '2022-08-01', '2022-08-31', 'Infantil', 80);

-- Insert data into Inscripcion
INSERT INTO Inscripcion (AsistenteId, CampamentoId, FechaInscripcion, Precio, TipoInscripcion) VALUES
(1, 1, '2022-06-01', 200.00, 'Completa'),
(2, 2, '2022-07-01', 150.00, 'Parcial');

-- Insert data into CampamentoMonitor
INSERT INTO CampamentoMonitor (CampamentoId, MonitorId) VALUES
(1, 1),
(2, 2);

-- Insert data into CampamentoActividad
INSERT INTO CampamentoActividad (CampamentoId, ActividadNombre) VALUES
(1, 'Football'),
(2, 'Art');