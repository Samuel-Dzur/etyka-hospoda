INSERT INTO drinks (id, product_name, is_for_adult)
VALUES
    (1, 'Čepovaný ležák', TRUE),
    (2, 'Piña Colada', TRUE),
    (3, 'Martini Koktejl', TRUE),
    (4, 'Kola', FALSE),
    (5, 'Káva', FALSE);

INSERT INTO price_records (drink_id, price, effective_from)
VALUES
    (1, 69.00, CURRENT_TIMESTAMP),
    (2, 179.00, CURRENT_TIMESTAMP),
    (3, 149.00, CURRENT_TIMESTAMP),
    (4, 55.00, CURRENT_TIMESTAMP),
    (5, 65.00, CURRENT_TIMESTAMP);

SELECT setval(
    pg_get_serial_sequence('drinks', 'id'),
    (SELECT MAX(id) FROM drinks),
    TRUE
);
