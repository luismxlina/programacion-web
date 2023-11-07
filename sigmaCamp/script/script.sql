-- Tabla para la entidad Asistente
CREATE TABLE Asistente (
    Identificador INT PRIMARY KEY,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    FechaNacimiento DATE,
    RequiereAtencionEspecial BOOLEAN
);

-- Tabla para la entidad Monitor
CREATE TABLE Monitor (
    Identificador INT PRIMARY KEY,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    EducadorEspecial BOOLEAN
);

-- Tabla para la entidad Actividad
CREATE TABLE Actividad (
    Identificador INT PRIMARY KEY,
    Nombre VARCHAR(255),
    NivelEducativo ENUM('Infantil', 'Juvenil', 'Adolescente'),
    Horario VARCHAR(50),
    NumeroMaximoParticipantes INT,
    NumeroMonitoresNecesarios INT
);

-- Tabla para la entidad Campamento
CREATE TABLE Campamento (
    Identificador INT PRIMARY KEY,
    FechaInicio DATE,
    FechaFin DATE,
    NivelEducativo ENUM('Infantil', 'Juvenil', 'Adolescente'),
    NumeroMaximoAsistentes INT
);

-- Tabla para la entidad Inscripción
CREATE TABLE Inscripcion (
    AsistenteId INT,
    CampamentoId INT,
    FechaInscripcion DATE,
    Precio DECIMAL(10, 2),
    TipoInscripcion ENUM('Completa', 'Parcial'),
    PRIMARY KEY (AsistenteId, CampamentoId),
    FOREIGN KEY (AsistenteId) REFERENCES Asistente(Identificador),
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador)
);

-- Tabla de unión para la relación N-M entre Campamento y Monitor
CREATE TABLE CampamentoMonitor (
    CampamentoId INT,
    MonitorId INT,
    PRIMARY KEY (CampamentoId, MonitorId),
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador),
    FOREIGN KEY (MonitorId) REFERENCES Monitor(Identificador)
);

-- Relación directa entre Campamento y Actividad
CREATE TABLE CampamentoActividad (
    CampamentoId INT,
    ActividadId INT,
    PRIMARY KEY (CampamentoId, ActividadId),
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador),
    FOREIGN KEY (ActividadId) REFERENCES Actividad(Identificador)
);