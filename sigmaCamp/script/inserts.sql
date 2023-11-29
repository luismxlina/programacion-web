-- Insert data into Asistente
INSERT INTO
    Asistente (
        Identificador,
        Nombre,
        Apellidos,
        FechaNacimiento,
        RequiereAtencionEspecial
    )
VALUES
    (1, 'John', 'Doe', '2000-01-01', false),
    (2, 'Jane', 'Doe', '2002-02-02', true),
    (3, 'Alice', 'Smith', '1998-05-15', true),
    (4, 'Bob', 'Johnson', '2003-09-20', false),
    (5, 'Emily', 'Brown', '1999-11-10', false),
    (6, 'Michael', 'Davis', '2001-03-25', true),
    (7, 'Sophia', 'Martinez', '2004-07-12', true),
    (8, 'Daniel', 'Garcia', '1997-12-28', false),
    (9, 'Olivia', 'Lopez', '2000-10-05', true),
    (10, 'Liam', 'Hernandez', '2005-02-18', false);

-- Insert data into Monitor
INSERT INTO
    Monitor (
        Identificador,
        Nombre,
        Apellidos,
        EducadorEspecial
    )
VALUES
    (1, 'Alice', 'Smith', true),
    (2, 'Bob', 'Johnson', false),
    (3, 'Emily', 'Brown', true),
    (4, 'Michael', 'Davis', false),
    (5, 'Sophia', 'Martinez', true),
    (6, 'Daniel', 'Garcia', false),
    (7, 'Olivia', 'Lopez', true),
    (8, 'Liam', 'Hernandez', false),
    (9, 'Ella', 'Wilson', true),
    (10, 'Noah', 'Taylor', false);

-- Insert data into Actividad
INSERT INTO
    Actividad (
        Nombre,
        NivelEducativo,
        Horario,
        NumeroMaximoParticipantes,
        NumeroMonitoresNecesarios
    )
VALUES
    ('Football', 'Juvenil', 'Mañana', 20, 2),
    ('Art', 'Infantil', 'Tarde', 15, 1),
    ('Swimming', 'Infantil', 'Mañana', 25, 3),
    ('Chess', 'Juvenil', 'Tarde', 12, 1),
    ('Dance', 'Adolescente', 'Tarde', 18, 2),
    ('Coding', 'Juvenil', 'Tarde', 10, 1),
    ('Cooking', 'Adolescente', 'Mañana', 20, 2),
    ('Basketball', 'Infantil', 'Tarde', 15, 1),
    ('Music', 'Adolescente', 'Mañana', 22, 2),
    ('Theater', 'Juvenil', 'Tarde', 16, 2),
    ('Yoga', 'Infantil', 'Mañana', 30, 3),
    ('Science', 'Adolescente', 'Tarde', 20, 2);

-- Insert data into Campamento
INSERT INTO
    Campamento (
        Identificador,
        FechaInicio,
        FechaFin,
        NivelEducativo,
        NumeroMaximoAsistentes
    )
VALUES
    (1, '2022-07-01', '2022-07-31', 'Juvenil', 100),
    (2, '2022-08-01', '2022-08-31', 'Infantil', 80),
    (
        3,
        '2022-06-15',
        '2022-07-15',
        'Adolescente',
        120
    ),
    (4, '2022-08-15', '2022-09-15', 'Juvenil', 90),
    (5, '2022-07-10', '2022-08-10', 'Infantil', 75),
    (
        6,
        '2022-06-20',
        '2022-07-20',
        'Adolescente',
        110
    ),
    (7, '2022-08-20', '2022-09-20', 'Juvenil', 85),
    (8, '2022-07-05', '2022-08-05', 'Infantil', 70),
    (
        9,
        '2022-06-25',
        '2022-07-25',
        'Adolescente',
        105
    ),
    (10, '2022-08-25', '2022-09-25', 'Juvenil', 95);

-- Insert data into Inscripcion
-- INSERT INTO
--     Inscripcion (
--         AsistenteId,
--         CampamentoId,
--         FechaInscripcion,
--         Precio,
--         TipoInscripcion
--     )
-- VALUES
--     (1, 1, '2022-06-01', 200.00, 'Completa'),
--     (2, 2, '2022-07-01', 150.00, 'Parcial');
-- -- Insert data into CampamentoMonitor
-- INSERT INTO
--     CampamentoMonitor (CampamentoId, MonitorId)
-- VALUES
--     (1, 1),
--     (2, 2);
-- Insert data into CampamentoActividad
-- INSERT INTO
-- CampamentoActividad (CampamentoId, ActividadNombre)
-- VALUES
-- (1, 'Football'),
-- (2, 'Art');