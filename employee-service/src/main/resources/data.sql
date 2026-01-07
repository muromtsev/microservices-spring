INSERT INTO employees (first_name, last_name, email, position, organization_uuid, create_at, update_at)
VALUES
-- Сотрудники для организации IT_DEPT (uuid: a1b2c3d4-e5f6-7890-abcd-ef1234567890)
('Иван', 'Петров', 'ivan.petrov@company.com', 'Senior Java Developer', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP - INTERVAL '1 year', CURRENT_TIMESTAMP),
('Мария', 'Сидорова', 'maria.sidorova@company.com', 'DevOps Engineer', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP - INTERVAL '8 months', CURRENT_TIMESTAMP),
('Алексей', 'Кузнецов', 'alexey.kuznetsov@company.com', 'Frontend Developer', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP - INTERVAL '6 months', CURRENT_TIMESTAMP),
('Елена', 'Васильева', 'elena.vasilyeva@company.com', 'QA Engineer', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP - INTERVAL '4 months', CURRENT_TIMESTAMP),

-- Сотрудники для организации HR_DEPT (uuid: b2c3d4e5-f6a7-8901-bcde-f23456789012)
('Анна', 'Смирнова', 'anna.smirnova@company.com', 'HR Manager', 'b2c3d4e5-f6a7-8901-bcde-f23456789012', CURRENT_TIMESTAMP - INTERVAL '2 years', CURRENT_TIMESTAMP),
('Дмитрий', 'Попов', 'dmitry.popov@company.com', 'Recruiter', 'b2c3d4e5-f6a7-8901-bcde-f23456789012', CURRENT_TIMESTAMP - INTERVAL '1 year', CURRENT_TIMESTAMP),
('Ольга', 'Лебедева', 'olga.lebedeva@company.com', 'HR Business Partner', 'b2c3d4e5-f6a7-8901-bcde-f23456789012', CURRENT_TIMESTAMP - INTERVAL '9 months', CURRENT_TIMESTAMP),

-- Сотрудники для организации FINANCE_DEPT (uuid: c3d4e5f6-a7b8-9012-cdef-345678901234)
('Сергей', 'Новиков', 'sergey.novikov@company.com', 'Financial Analyst', 'c3d4e5f6-a7b8-9012-cdef-345678901234', CURRENT_TIMESTAMP - INTERVAL '3 years', CURRENT_TIMESTAMP),
('Наталья', 'Морозова', 'natalya.morozova@company.com', 'Accountant', 'c3d4e5f6-a7b8-9012-cdef-345678901234', CURRENT_TIMESTAMP - INTERVAL '2 years', CURRENT_TIMESTAMP),
('Андрей', 'Волков', 'andrey.volkov@company.com', 'CFO', 'c3d4e5f6-a7b8-9012-cdef-345678901234', CURRENT_TIMESTAMP - INTERVAL '4 years', CURRENT_TIMESTAMP),

-- Сотрудники для организации SALES_DEPT (uuid: d4e5f6a7-b8c9-0123-def0-456789012345)
('Артем', 'Соловьев', 'artem.soloviev@company.com', 'Sales Manager', 'd4e5f6a7-b8c9-0123-def0-456789012345', CURRENT_TIMESTAMP - INTERVAL '1 year', CURRENT_TIMESTAMP),
('Виктория', 'Козлова', 'victoria.kozlova@company.com', 'Account Executive', 'd4e5f6a7-b8c9-0123-def0-456789012345', CURRENT_TIMESTAMP - INTERVAL '7 months', CURRENT_TIMESTAMP),

-- Сотрудник без организации (для тестирования)
('Тестовый', 'Сотрудник', 'test.employee@company.com', 'Tester', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Руководители
('Александр', 'Иванов', 'alexander.ivanov@company.com', 'CTO', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP - INTERVAL '5 years', CURRENT_TIMESTAMP),
('Екатерина', 'Федорова', 'ekaterina.fedorova@company.com', 'Head of HR', 'b2c3d4e5-f6a7-8901-bcde-f23456789012', CURRENT_TIMESTAMP - INTERVAL '6 years', CURRENT_TIMESTAMP);

-- Логируем количество добавленных записей
DO $$
    DECLARE
        row_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO row_count FROM employees;
        RAISE NOTICE 'Добавлено % тестовых сотрудников в таблицу employees', row_count;
    END $$;