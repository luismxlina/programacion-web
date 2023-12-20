DROP TABLE IF EXISTS User;

DROP TABLE IF EXISTS ActividadMonitor;

DROP TABLE IF EXISTS CampamentoActividad;

DROP TABLE IF EXISTS CampamentoMonitor;

DROP TABLE IF EXISTS Inscripcion;

DROP TABLE IF EXISTS Campamento;

DROP TABLE IF EXISTS Actividad;

DROP TABLE IF EXISTS Monitor;

DROP TABLE IF EXISTS Asistente;

-- Tabla para la entidad Asistente
CREATE TABLE Asistente (
    Identificador INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    FechaNacimiento DATE,
    RequiereAtencionEspecial BOOLEAN DEFAULT false
);

-- Tabla para la entidad Monitor
CREATE TABLE Monitor (
    Identificador INT PRIMARY KEY,
    Nombre VARCHAR(255),
    Apellidos VARCHAR(255),
    EducadorEspecial BOOLEAN DEFAULT false
);

-- Tabla para la entidad Actividad
CREATE TABLE Actividad (
    Nombre VARCHAR(255) PRIMARY KEY,
    NivelEducativo ENUM('Infantil', 'Juvenil', 'Adolescente'),
    Horario ENUM('Mañana', 'Tarde'),
    NumeroMaximoParticipantes INT,
    NumeroMonitoresNecesarios INT
);

-- Tabla para la entidad Campamento
CREATE TABLE Campamento (
    Identificador INT PRIMARY KEY,
    FechaInicio DATE,
    FechaFin DATE,
    NivelEducativo ENUM('Infantil', 'Juvenil', 'Adolescente'),
    NumeroMaximoAsistentes INT,
    INDEX idx_nivel_educativo (NivelEducativo)
);

-- Tabla para la entidad Inscripción
CREATE TABLE Inscripcion (
    AsistenteId INT,
    CampamentoId INT,
    FechaInscripcion DATE,
    Precio DECIMAL(10, 2),
    TipoInscripcion ENUM('Completa', 'Parcial'),
    PRIMARY KEY (AsistenteId, CampamentoId),
    FOREIGN KEY (AsistenteId) REFERENCES Asistente(Identificador) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de unión para la relación N-M entre Campamento y Monitor
CREATE TABLE CampamentoMonitor (
    CampamentoId INT,
    MonitorId INT,
    PRIMARY KEY (CampamentoId, MonitorId),
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (MonitorId) REFERENCES Monitor(Identificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Relación directa entre Campamento y Actividad
CREATE TABLE CampamentoActividad (
    CampamentoId INT,
    ActividadNombre VARCHAR(255),
    PRIMARY KEY (CampamentoId, ActividadNombre),
    FOREIGN KEY (CampamentoId) REFERENCES Campamento(Identificador) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ActividadNombre) REFERENCES Actividad(Nombre) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Relación directa entre Actividad y Monitor
CREATE TABLE ActividadMonitor (
    ActividadNombre VARCHAR(255),
    MonitorId INT,
    PRIMARY KEY (ActividadNombre, MonitorId),
    FOREIGN KEY (ActividadNombre) REFERENCES Actividad(Nombre) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (MonitorId) REFERENCES Monitor(Identificador) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE User (
    email VARCHAR(32) NOT NULL,
    id INT NOT NULL,
    password VARCHAR(32) NOT NULL,
    rol VARCHAR(32) NOT NULL,
    fechaNacimiento date NOT NULL,
    nombreCompleto VARCHAR(64) NOT NULL,
    fechaInscripcion date NOT NULL,
    PRIMARY KEY (email),
    FOREIGN KEY (id) REFERENCES Asistente(Identificador) ON DELETE CASCADE ON UPDATE CASCADE
);