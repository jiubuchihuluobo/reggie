DELETE
FROM dish
WHERE is_deleted = 1;

DELETE
FROM dish_flavor
WHERE is_deleted = 1;