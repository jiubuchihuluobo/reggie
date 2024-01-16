DELETE
FROM dish_flavor
WHERE dish_id NOT IN (SELECT DISTINCT id FROM dish);


DELETE
FROM dish
WHERE category_id NOT IN (SELECT DISTINCT id FROM category);


UPDATE dish
SET code = REPLACE(UUID(), '-', '');



