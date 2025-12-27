INSERT INTO organization (name, contact_name, contact_email, contact_phone) VALUES
    ('TechCorp Dev', 'John Doe', 'john@techcorp.dev', '+1234567890'),
    ('Innovate Labs Dev', 'Jane Smith', 'jane@innovatelabs.dev', '+0987654321')
    ON CONFLICT DO NOTHING;

INSERT INTO employees (employee_id, first_name, last_name, organization_id, employee_type, commit) VALUES
   ('EMP001', 'Alex', 'Johnson', '1', 'DEVELOPER', 'Initial commit dev'),
   ('EMP002', 'Maria', 'Garcia', '1', 'MANAGER', 'Onboarding dev'),
   ('EMP003', 'David', 'Chen', '2', 'DEVELOPER', 'Project setup dev'),
   ('EMP004', 'Sarah', 'Williams', '2', 'QA_ENGINEER', 'Testing framework'),
   ('EMP005', 'Michael', 'Brown', '1', 'DEVOPS', 'CI/CD setup')
    ON CONFLICT (employee_id) DO UPDATE SET
    first_name = EXCLUDED.first_name,
     last_name = EXCLUDED.last_name,
     organization_id = EXCLUDED.organization_id,
     employee_type = EXCLUDED.employee_type,
     commit = EXCLUDED.commit;