insert into roles (
    id,
    name,
    description,
    active,
    created_at,
    updated_at
) values
(
    '11111111-1111-1111-1111-111111111111',
    'USER',
    'Default role for standard user access.',
    true,
    current_timestamp,
    current_timestamp
),
(
    '22222222-2222-2222-2222-222222222222',
    'PROFILE_ADMIN',
    'Role for managing user profile records.',
    true,
    current_timestamp,
    current_timestamp
);

insert into profiles (
    id,
    username,
    email,
    first_name,
    last_name,
    phone_number,
    display_name,
    job_title,
    department,
    location,
    date_of_birth,
    bio,
    avatar_url,
    preferred_language,
    time_zone,
    status,
    created_at,
    updated_at
) values (
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'ananya.rao',
    'ananya.rao@example.com',
    'Ananya',
    'Rao',
    '+91-9876543210',
    'Ananya R.',
    'Product Manager',
    'Product',
    'Bengaluru',
    '1992-04-18',
    'Builds user-facing products for enterprise teams.',
    'https://example.com/avatars/ananya.png',
    'en-IN',
    'Asia/Kolkata',
    'ACTIVE',
    current_timestamp,
    current_timestamp
);

insert into profile_roles (
    profile_id,
    role_id
) values (
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '11111111-1111-1111-1111-111111111111'
);
