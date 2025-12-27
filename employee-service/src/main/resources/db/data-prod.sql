INSERT INTO organization (name, contact_name, contact_email, contact_phone) VALUES
    ('TechCorp Production', 'Admin User', 'admin@techcorp.com', '+1111111111')
    ON CONFLICT DO NOTHING;