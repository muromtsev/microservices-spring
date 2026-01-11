INSERT INTO organization (uuid, name, code, description, created_at, updated_at) VALUES
('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'IT Department', 'IT_DEPT', 'Отдел информационных технологий и разработки', '2024-01-01 10:00:00', '2024-01-15 14:30:00'),
('b2c3d4e5-f6a7-8901-bcde-f23456789012', 'HR Department', 'HR_DEPT', 'Отдел кадров и управления персоналом', '2024-01-02 09:15:00', '2024-01-10 16:45:00'),
('c3d4e5f6-a7b8-9012-cdef-345678901234', 'Finance Department', 'FINANCE_DEPT', 'Финансовый отдел и бухгалтерия', '2024-01-03 14:20:00', '2024-01-12 11:20:00'),
('d4e5f6a7-b8c9-0123-def0-456789012345', 'Sales Department', 'SALES_DEPT', 'Отдел продаж и маркетинга', '2024-01-04 11:30:00', '2024-01-14 09:10:00'),
('e5f6a7b8-c9d0-1234-ef01-567890123456', 'Marketing Department', 'MARKETING_DEPT', 'Маркетинговые коммуникации и реклама', '2024-01-05 13:45:00', '2024-01-13 15:55:00'),
('f6a7b8c9-d0e1-2345-f012-678901234567', 'Operations Department', 'OPERATIONS_DEPT', 'Операционное управление и логистика', '2024-01-06 08:00:00', '2024-01-16 17:30:00');

DO $$
    DECLARE
        row_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO row_count FROM organization;
        RAISE NOTICE 'Добавлено % тестовых организаций', row_count;
    END $$;