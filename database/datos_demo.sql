USE lims360;

INSERT INTO profiles (
    id, user_id, full_name, email, department, position, is_active
) VALUES (
    '11111111-1111-1111-1111-111111111111',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'Persona de demostracion',
    'demo@lims360.local',
    'Metrologia',
    'Tecnico',
    TRUE
)
ON DUPLICATE KEY UPDATE
    full_name = VALUES(full_name),
    email = VALUES(email),
    department = VALUES(department),
    position = VALUES(position),
    is_active = VALUES(is_active);
